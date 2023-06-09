package com.organic.shop.restImpl;

import com.organic.shop.Dtos.CategoryDto;
import com.organic.shop.Dtos.ProductDto;
import com.organic.shop.Dtos.ProductDtoMultipart;
import com.organic.shop.Dtos.SimpleProductDto;
import com.organic.shop.rest.AdminProductRest;
import com.organic.shop.service.CategoryService;
import com.organic.shop.service.ProductService;
import com.organic.shop.utils.Shipping;
import com.organic.shop.utils.UploadFile;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class AdminProductRestImpl extends BaseAdminHomeRest implements AdminProductRest {
    private static final SimpleDateFormat  DATE_FORMAT=new SimpleDateFormat("dd/MM/yyyy");
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Override
    public ModelAndView goProduct() {
        _ModelAndView.setViewName("/admin/products");
        try {
            List<SimpleProductDto> productDtos = productService.getSimpleProduct();
            _ModelAndView.addObject("productList", productDtos);
            _ModelAndView.addObject("categoryList", categoryService.getCategories());
        }catch (Exception e){
            e.printStackTrace();
        }
        return _ModelAndView;
    }

    @Override
    public ModelAndView goAddProduct(HttpServletRequest request) {
        String message=request.getParameter("message");
        _ModelAndView.setViewName("/admin/add-product");
        _ModelAndView.addObject("newProduct", new ProductDto());
        _ModelAndView.addObject("categoryList", categoryService.getCategories());

        return _ModelAndView;
    }

    @Override
    public ModelAndView addProduct(HttpServletRequest request, String name,
                                   String date,
                                   Long id,
                                   Integer quantity,
                                   String price,
                                   MultipartFile file) throws ParseException {

        _ModelAndView.setView(new RedirectView("/admin/product/page-add-product"));
        ProductDto productDto=new ProductDto();
        productDto.setPrice(100.0);
        productDto.setName(name);
        productDto.setPrice(Double.parseDouble(price));
        productDto.setExpireDate(DATE_FORMAT.parse(date));
        productDto.setAddAt(new Date());
        productDto.setUntilInStock(quantity);
        productDto.setIdCategory(id);
        productDto.setShipping(Shipping.FIFTYPERCENT);
//        productDto.setIdCategory(id);
        try {
            String nameImg = UploadFile.uploadProductImg(file, request);
            productService.ReceivedProduct(productDto,"/img/product/"+ nameImg);
        }catch (Exception e){
            e.printStackTrace();
        }
        return _ModelAndView;
    }

//    @Override
//    public ModelAndView addProduct(HttpServletRequest request, ProductDto productDto, MultipartFile file) {
//        _ModelAndView.setView(new RedirectView("/admin/product/page-add-product"));
//        try {
//            String nameImg = UploadFile.uploadProductImg(file, request);
//            productService.ReceivedProduct(productDto, nameImg);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return _ModelAndView;
//    }

    @Override
    public ModelAndView addP(HttpServletRequest request, MultipartFile file) {
        _ModelAndView.setView(new RedirectView("/admin/product/page-add-product"));
        try {
            String nameImg = UploadFile.uploadProductImg(file, request);
            System.out.println("IMG :"+nameImg);
        }catch (Exception e){
            e.printStackTrace();
        }
        return _ModelAndView;
    }

//    @Override
//    public ModelAndView addProduct(HttpServletRequest request,ProductDtoMultipart productDto) {
//        _ModelAndView.setView(new RedirectView("/admin/product/page-add-product"));
//        String nameImg= UploadFile.uploadProductImg(productDto.getImg(),request);
//        productService.ReceivedProduct(productDto, "");
//        return _ModelAndView;
//    }

    @Override
    public ModelAndView uploadImg(MultipartFile img, Long id, HttpServletRequest request) {
        _ModelAndView.setView(new RedirectView("/admin/product/page-add-product?message=success"));
//        String nameImg= UploadFile.uploadProductImg(img,request);
        ProductDto productDto=productService.saveImg(img,id,request);
        _ModelAndView.addObject("product",productDto);
        return _ModelAndView;
    }

    @Override
    public ModelAndView deleteProduct(Long id) {
         productService.deleteProduct(id);
        _ModelAndView.setView(new RedirectView("/admin/product/"));
        return _ModelAndView;
    }

    @Override
    public ModelAndView deleteProduct(List<Long> products) {
        _ModelAndView.setView(new RedirectView("/admin/product/"));
        productService.deleteVariousProduct(products);
        return _ModelAndView;
    }

    @Override
    public ModelAndView addCategory() {
        _ModelAndView.setViewName("/admin/add-category");
        _ModelAndView.addObject("categoryDto", new CategoryDto());
        return _ModelAndView;
    }

    @Override
    public ModelAndView addCategory(HttpServletRequest request, CategoryDto categoryDto, MultipartFile img) {
        _ModelAndView.setView(new RedirectView("/admin/product/page-add-category"));
        categoryDto.setImg(UploadFile.uploadCategoryImg(img,request));
        categoryService.addCategory(categoryDto);
        return _ModelAndView;
    }

    @Override
    public ModelAndView editProducta() {
        _ModelAndView.setViewName("/admin/edit-product");
        _ModelAndView.addObject("product", new ProductDto());
        return _ModelAndView;
    }

    @Override
    public ModelAndView deleteCategory(Long id) {
        productService.deleteCategory(id);
        _ModelAndView.setView(new RedirectView("/admin/product/"));
        return _ModelAndView;
    }

    @Override
    public ModelAndView editProduct(ProductDto productDto, Long id, MultipartFile file, HttpServletRequest request) {
        _ModelAndView.setView(new RedirectView("/admin/product/page-edit-product"));
        String fileName=null;
        if(file !=null)
             fileName= UploadFile.uploadProductImg(file,request);
        productService.updateProduct(productDto, id, fileName);
        return _ModelAndView;
    }


}
