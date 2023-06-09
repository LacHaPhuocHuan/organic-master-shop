package com.organic.shop.controllerImpl;

import com.organic.shop.Dtos.PaginatesDto;
import com.organic.shop.Dtos.ProductDto;
import com.organic.shop.Dtos.ProductSaleDto;
import com.organic.shop.controller.ShopController;
import com.organic.shop.service.ProductService;
import com.organic.shop.serviceImpl.PaginatesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class ShopControllerImpl extends BaseUserController implements ShopController {
    @Autowired
    ProductService  productService;
    @Autowired
    PaginatesServiceImpl paginatesService;
    List<ProductDto> productDtoList;
    @Override
    public ModelAndView goShop() {
        _ModelAndView.addObject("userCurrent", myUserService.getUser());
        _ModelAndView.setViewName("/user/shop-grid");
        if( Objects.isNull(productDtoList))
             productDtoList= productService.getAll();
        List<ProductSaleDto> pSale= productService.getSaleProduct(productDtoList);

        _ModelAndView.addObject("productSaleOff", productService.getSaleProduct(productDtoList).stream().limit(3).collect(Collectors.toList()));
        _ModelAndView.addObject("productAll",productDtoList.stream()
                .limit(6).collect(Collectors.toList()));
        PaginatesDto paginatesDtoSale=paginatesService.getInfoPaginatesDto(pSale.size(),
                3,
                1);
        _ModelAndView.addObject("pp",paginatesDtoSale);
        PaginatesDto paginatesDto=paginatesService.getInfoPaginatesDto(productDtoList.size(),
                6,
                1);
        _ModelAndView.addObject("pa",paginatesDto);
        _ModelAndView.addObject("totalProduct",productDtoList.size());
        return _ModelAndView;
    }

    @Override
    public ModelAndView goDetailsProduct(Long id) {
        System.out.println("goDetailsProduct : "+id );
        _ModelAndView.setViewName("/user/shop-details");
        ProductSaleDto productSaleDto= productService.findProductById(id);
        _ModelAndView.addObject("productDetails",productSaleDto);
        _ModelAndView.addObject("productsRelate", productService.findProductsByCategory(productSaleDto.getIdCategory())
                .stream().limit(4)
                .collect(Collectors.toList()));

        return _ModelAndView;
    }

    @Override
    public ModelAndView getProductCategory(Long id) {
        System.out.println("goDetailsProduct : "+id );
        _ModelAndView.setViewName("/user/shop-grid");
        _ModelAndView.addObject("productsRelate", productService.findProductsByCategory(id));
        return _ModelAndView;
    }

    @Override
    public ModelAndView getPageAll(Integer currentPage) {
        _ModelAndView.setViewName("/user/shop-grid");
//        List<ProductDto> products= productService.getAll();
        List<ProductDto> productDto=new ArrayList<>();
        PaginatesDto paginatesDto=paginatesService.getInfoPaginatesDto(productDtoList.size(),
                6,
                currentPage);
        _ModelAndView.addObject("pa",paginatesDto);
        for (int i=paginatesDto.getStart()-1; i<paginatesDto.getEnd(); i++){
            productDto.add(productDtoList.get(i));
        }
        _ModelAndView.addObject("productAll",productDto.stream().limit(6).collect(Collectors.toList()));
        return _ModelAndView;
    }

    @Override
    public ModelAndView getPageSale(Integer currentPage) {
        _ModelAndView.setViewName("/user/shop-grid");
        List<ProductSaleDto> products= productService.getSaleProduct(productDtoList);
        List<ProductSaleDto> productDto=new ArrayList<>();
        PaginatesDto paginatesDto=paginatesService.getInfoPaginatesDto(products.size(),
                3,
                currentPage);
        _ModelAndView.addObject("pp",paginatesDto);
        for (int i=paginatesDto.getStart()-1; i<paginatesDto.getEnd(); i++){
            productDto.add(products.get(i));
        }
        _ModelAndView.addObject("productSaleOff",productDto);
        return _ModelAndView;
    }

    @Override
    public ModelAndView sortBy(Integer sortBy) {
        _ModelAndView.setViewName("/user/shop-grid");
        if (sortBy==1)
            productDtoList=productDtoList.stream().sorted(new Comparator<ProductDto>() {
                @Override
                public int compare(ProductDto o1, ProductDto o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            }).collect(Collectors.toList());
        if (sortBy==2)
            productDtoList=productDtoList.stream().sorted(new Comparator<ProductDto>() {
                @Override
                public int compare(ProductDto o1, ProductDto o2) {
                    return o1.getPrice().compareTo(o2.getPrice());
                }
            }).collect(Collectors.toList());
        _ModelAndView.setView(new RedirectView("/api/u/shop/"));
        return _ModelAndView;
    }
}
