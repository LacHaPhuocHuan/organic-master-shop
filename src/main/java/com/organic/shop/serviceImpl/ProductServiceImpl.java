package com.organic.shop.serviceImpl;

import com.organic.shop.Daos.*;
import com.organic.shop.Dtos.*;
import com.organic.shop.entities.Category;
import com.organic.shop.entities.InvoiceForGoodsReceived;
import com.organic.shop.entities.Product;
import com.organic.shop.entities.Review;
import com.organic.shop.security.MyUserService;
import com.organic.shop.service.InvoiceForGoodsReceivedService;
import com.organic.shop.service.ProductService;
import com.organic.shop.utils.InternalServerErrorException;
import com.organic.shop.utils.NotFoundException;
import com.organic.shop.utils.UploadFile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ModelMapper mapper=new ModelMapper();

    private final static SimpleDateFormat DATE_FORMAT=new SimpleDateFormat("dd/MM/yyyy");
    @Autowired
    InvoiceForGoodsReceivedService receivedService;
    @Autowired
    InvoiceDetailsRepository invoiceDetailsRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
//    BillRepository billRepository;
    BillDetailsRepository billDetailsRepository;



    @Autowired
    MyUserService userService;
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<ProductDto> getFeaturedProduct() {
        HashMap<CategoryDto, List<ProductDto>> listHashMap=new HashMap<>();
        List<Product>products=productRepository.findAll();
        return mapper(products);
    }

    @Override
    public List<ProductDto> getLatestProducts() {
        List<Product>products=productRepository.findAll();
//        Comparator<Product> comparator=
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o2.getAddAt().compareTo(o1.getAddAt());
            }
        });
        return mapper(products);
    }

    @Override
    public List<ProductDto> getReviewProducts(List<ProductDto> productDtos) {
        List<Review> reviews=reviewRepository.findAll();
        HashMap<Double, ProductDto> productHashMap=new HashMap<>();
        for (ProductDto product: productDtos
             ) {
            productHashMap.put(reviewRepository.findAverageStar(product.getId()), product);
        }
        TreeMap<Double, ProductDto> dtoTreeMap=new TreeMap<>(productHashMap);
        return new ArrayList<>(dtoTreeMap.values());
    }

    @Override
    public List<ProductDto> getTopRatedProducts() {
        List<Product>products=billDetailsRepository.getAndSortAccodingQuantity();
        return mapper(products);
//        return null;
    }

    @Override
    public List<ProductDto> getAll() {
        return mapper(productRepository.findAll());
    }

    @Override
    public List<ProductSaleDto> getSaleProduct(List<ProductDto> productDtoList) {
        Predicate<ProductSaleDto> predicate = e -> e.getSalePercent() > 0.0;
        List<ProductSaleDto> p=productDtoList.stream()
                .map(t -> mapper.map(t, ProductSaleDto.class))
                .peek(e -> e.setSalePercent(setSale(e.getExpireDate())))
                .filter(e->e.getSalePercent()>0.0)
                .collect(Collectors.toList());
        p.stream().forEach(t->System.out.println("||"+ t.getSalePercent()));
        System.out.println("Size "+p.size());
        return p;
    }


    private Double setSale(Date expireDate) {
        Date date2=new Date();
        Instant  instant1 = expireDate.toInstant();
        Instant instant2 = (date2).toInstant();

        // Tính khoảng cách giữa hai Instant
        Duration duration = Duration.between(instant2,instant1);
        System.out.println(duration.toDays() +"|| "+ ((duration.toDays()>100)?0.0:((duration.toDays()<50)?50.0:30.0)));

        return (duration.toDays()>100)?0.0:((duration.toDays()<50)?50.0:30.0);

    }

    @Override
    public ProductSaleDto findProductById(Long id) {
        System.out.println("AALELE1");
        Product product=productRepository.findById(id).orElseThrow(()-> new NotFoundException("Not found product have id: "+ id));
        System.out.println("AALELE2");
        return mapper.map(product, ProductSaleDto.class);
    }

    @Override
    public List<ProductDto> findProductsByCategory(Long idCategory) {
        List<Product> products=productRepository.getProductsAccordingCategory(idCategory);
        List<ProductDto> dtos=
                products.stream()
                        .map(t-> mapper.map(t, ProductDto.class)).
                        peek(e->e.setIdCategory(products.stream().filter(t->t.getId().equals(e.getId())).findFirst().get().getCategory().getId()))
                        .limit(3).
                        collect(Collectors.toList());

        return dtos;
    }

    @Override
    public List<ProductDto> getProductByKeyAndCategory(String keyWord, Long idProduct) {
       try {
           List<Product> products = productRepository.findByKeyWordAndCategory(keyWord, idProduct);
           List<ProductDto> productDtos = products
                   .stream()
                   .map(t -> mapper.map(t, ProductDto.class))
                   .collect(Collectors.toList());
           return productDtos;
       }
       catch (Exception e){
           e.printStackTrace();
           throw new InternalServerErrorException("Sorry, our server has an error");
       }

    }

    @Override
    public List<ProductDto> getProductByKey(String keyWord) {
        try {
            List<Product> products = productRepository.findByKey(keyWord);
            List<ProductDto> productDtos = products
                    .stream()
                    .map(t -> mapper.map(t, ProductDto.class))
                    .collect(Collectors.toList());
            return productDtos;
        }
        catch (Exception e){
            e.printStackTrace();
            throw new InternalServerErrorException("Sorry, our server has an error");
        }
    }

    @Override
    public List<SimpleProductDto> getSimpleProduct() {
        List<Product> products=productRepository.findAll();
        return products.stream()
                .map(t->new SimpleProductDto(
                        t.getId(),
                        t.getName(),
                        t.getUntilInStock(),
                        invoiceDetailsRepository.findUnitSold(t.getId())
                        ,
                        DATE_FORMAT.format( t.getExpireDate())
                )).collect(Collectors.toList());
    }

    @Override
    public void ReceivedProduct(ProductDto productDto, String img) {

        Product product=mapper.map(productDto, Product.class);
        product.setAddAt(new Date());
        product.setImg(img);
        try {
            Category category = categoryRepository.findById(productDto.getIdCategory())
                    .orElseThrow();
            product.setCategory(category);
        }catch (Exception e){
            e.printStackTrace();
        }
        productRepository.save(product);
        InvoiceForGoodsReceived invoice=new InvoiceForGoodsReceived();
        invoice.setLocalDateTime(LocalDateTime.now());
        invoice.setUser(userService.getUser());
        receivedService.SaveInvoiceReceived(invoice, product);
    }

    @Override
    @Modifying
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteVariousProduct(List<Long> products) {
        int n=0;
        int a=deleteProduct(products, n);
    }



    @Override
    public void updateProduct(ProductDto productDto, Long id, String fileName) {
        Product product
                =productRepository.findById(id).orElseThrow(()->new NotFoundException("Not found product which needed update"));
        Double price=productDto.getPrice();
        String name =productDto.getName();
        Integer inStock=productDto.getUntilInStock();
        Date date=productDto.getExpireDate();
        String img=fileName;
        Double weigh= productDto.getWeigh();
        Long category=productDto.getIdCategory();
        if(!Objects.isNull(price))   product.setPrice(price);
        if (!Objects.isNull(name)) product.setName(name);
        if (!Objects.isNull(inStock) && inStock != 0) product.setUntilInStock(inStock);
        if (!Objects.isNull(date)) product.setExpireDate(date);
        if (!Objects.isNull(img)) product.setImg(img);
        if (!Objects.isNull(weigh) && weigh != 0) product.setWeigh(weigh);
        if (!Objects.isNull(category) && category != 0) product.setCategory(categoryRepository.findById(category)
                .orElseThrow());
        productRepository.save(product);

    }

    @Override
    @Transactional
    @Modifying
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public ProductDto saveImg(MultipartFile img, Long id, HttpServletRequest request) {
        String imgName= UploadFile.uploadProductImg(img, request);
        Product product=productRepository.findById(id).orElseThrow();
        product.setImg("/img/product/"+imgName);
        return mapper.map(product,ProductDto.class);
    }


    private int deleteProduct(List<Long> products, int n){
        if(n==products.size())
            return 0;
        deleteProduct(products.get(n));
        return deleteProduct(products,n+1);

    }


    private List<ProductDto> mapper(List<Product> products){
        List<ProductDto> productDtos=new ArrayList<>();
// create a mapping configuration for SubMenu to SubMenuDto
//        TypeMap<Product, ProductDto> typeMap = mapper.createTypeMap(Product.class, ProductDto.class);
//
//// configure the mapping
//        typeMap.addMappings(mapper -> {
//            mapper.map(src -> src.getCategory().getId(), ProductDto::setIdCategory);
//            mapper.map(src -> src.getShipping().name(), ProductDto::setShipping);
//        });

        return products.stream()
                .map(t->mapper.map(t, ProductDto.class) )
                .peek(t->t.setIdCategory(products.
                        stream().
                        filter(i->i.getName().equalsIgnoreCase(t.getName()))
                        .findFirst().get().getCategory().getId())
                ).collect(Collectors.toList());
    }


}
