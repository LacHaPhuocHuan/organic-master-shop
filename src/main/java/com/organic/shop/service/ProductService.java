package com.organic.shop.service;

import com.organic.shop.Dtos.ProductDto;
import com.organic.shop.Dtos.ProductSaleDto;
import com.organic.shop.Dtos.SimpleProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface ProductService {
     List<ProductDto> getFeaturedProduct();

    List<ProductDto> getLatestProducts();

    List<ProductDto> getReviewProducts(List<ProductDto> productDtos);

    List<ProductDto> getTopRatedProducts();

    List<ProductDto> getAll();

    List<ProductSaleDto> getSaleProduct(List<ProductDto> productDtoList);

    ProductSaleDto findProductById(Long id);

    List<ProductDto> findProductsByCategory(Long idCategory);

    List<ProductDto> getProductByKeyAndCategory(String keyWord, Long idProduct);

    List<ProductDto> getProductByKey(String keyWord);

    List<SimpleProductDto> getSimpleProduct();

    void ReceivedProduct(ProductDto productDto, String img);

    void deleteProduct(Long id);

    void deleteVariousProduct(List<Long> products);


    void updateProduct(ProductDto productDto, Long id, String fileName);

    void deleteCategory(Long id);

    ProductDto saveImg(MultipartFile img, Long id, HttpServletRequest request);
}
