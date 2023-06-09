package com.organic.shop.serviceImpl;

import com.organic.shop.Daos.DiscountCodeRepository;
import com.organic.shop.Daos.ProductRepository;
import com.organic.shop.Dtos.ElementOnCart;
import com.organic.shop.Dtos.ProductDto;
import com.organic.shop.entities.DiscountCode;
import com.organic.shop.entities.Product;
import com.organic.shop.service.CartService;
import com.organic.shop.utils.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {
    private ModelMapper mapper=new ModelMapper();
    @Autowired
    ProductRepository productRepository;

    @Autowired
    DiscountCodeRepository discountCodeRepository;
    @Override
    public HashMap<Integer, ElementOnCart> addOneProduct(Long id, Integer quatity, HashMap<Integer, ElementOnCart> cart) {
        if(cart.containsKey(Integer.parseInt(id.toString()))){
             Integer quantityOld= cart.get(id).getQuantity();
             cart.get(id).setQuantity(quantityOld+quatity);
        }
        else{
            Product product=productRepository.findById(id).orElseThrow(()->new NotFoundException("Product expired! pls choice another product"));
            ProductDto productDto=mapper.map(product,ProductDto.class);
            System.out.println(product.getPrice()+"======----======"+productDto.getPrice());
            ElementOnCart elementOnCart=new ElementOnCart();
            elementOnCart.setQuantity(quatity);
            elementOnCart.setProductDto(productDto);
            elementOnCart.setTotalPrice(productDto.getPrice()*quatity);
            cart.put( productDto.getId().intValue() , elementOnCart);
        }
        return cart;
    }
    @Override
    public HashMap<Integer, ElementOnCart> delete(Integer id, HashMap<Integer, ElementOnCart> generateCart) {
        generateCart.remove(id);
        return generateCart;
    }
    @Override
    public HashMap<Integer, ElementOnCart> deleteVarious(List<Long> ids, HashMap<Integer, ElementOnCart> generateCart) {
        return delete(ids,generateCart, 0);
    }

    @Override
    public Double getTotalPrice(HashMap<Integer, ElementOnCart> cart) {
        Double total=0.0;
        for (Map.Entry<Integer, ElementOnCart> cartEntry:cart.entrySet()
             ) {
            total=total+ cartEntry.getValue().getTotalPrice();
        }
        return total;
    }

    @Override
    public HashMap<Integer, ElementOnCart> updateById(Integer id, Integer quantity, HashMap<Integer, ElementOnCart> cart) {
        if (cart.containsKey(id)){
            ElementOnCart onCart=cart.get(id);
            onCart.setQuantity(quantity);
            cart.remove(id);
            cart.put(id,onCart);
        }
        return cart;
    }

    @Override
    public HashMap<Integer, ElementOnCart> discount(String code, HashMap<Integer, ElementOnCart> cart) {
        DiscountCode discountCode=discountCodeRepository.findByCode(code)
                .orElseThrow();
        if (!Objects.isNull(discountCode)){
            for (Map.Entry<Integer, ElementOnCart> cartEntry:cart.entrySet()
            ) {
                ElementOnCart onCart=cartEntry.getValue();
                onCart.setTotalPrice(onCart.getTotalPrice()-onCart.getTotalPrice()*discountCode.getPercentDiscount()*0.01);
                cart.remove(onCart.getProductDto().getId());
                cart.put(Integer.parseInt(onCart.getProductDto().getId().toString()),onCart);
            }
        }
        return cart;
    }

    @Override
    public Double getSubTotalPrice(HashMap<Integer, ElementOnCart> cart) {
        Double total=0.0;
        for (Map.Entry<Integer, ElementOnCart> cartEntry:cart.entrySet()
        ) {
            total=total+ cartEntry.getValue().getSubTotalPrice();
        }
        return total;
    }

    private HashMap<Integer, ElementOnCart> delete(List<Long> ids, HashMap<Integer, ElementOnCart> generateCart, int i){
        if(i>=ids.size()) {
            return generateCart;
        }
        else{
            generateCart.remove(ids.get(i).intValue());
            return delete(ids,generateCart,i+1);
        }
    }
}
