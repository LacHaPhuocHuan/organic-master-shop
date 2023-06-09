package com.organic.shop.service;

import com.organic.shop.Dtos.ElementOnCart;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface CartService {
    HashMap<Integer, ElementOnCart>   addOneProduct(Long id, Integer quatity, HashMap<Integer, ElementOnCart> cart);

    HashMap<Integer, ElementOnCart>  delete(Integer id, HashMap<Integer, ElementOnCart> generateCart);

    Object deleteVarious(List<Long> ids, HashMap<Integer, ElementOnCart> generateCart);

    Double getTotalPrice(HashMap<Integer, ElementOnCart> cart);

    HashMap<Integer, ElementOnCart> updateById(Integer id, Integer quantity, HashMap<Integer, ElementOnCart> cart);

    HashMap<Integer, ElementOnCart> discount(String code, HashMap<Integer, ElementOnCart> cart);

    Double getSubTotalPrice(HashMap<Integer, ElementOnCart> cart);
}
