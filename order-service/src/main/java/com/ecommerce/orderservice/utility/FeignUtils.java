package com.ecommerce.orderservice.utility;

import com.ecommerce.orderservice.dto.CartDto;
import com.ecommerce.orderservice.model.ShoppingCart;

import java.util.*;

public class FeignUtils {
    public static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>)obj);
        }
        return list;
    }
    public static ShoppingCart buildCart(List<Map<String, Object>> productData, CartDto cartDto, String userId){
        ShoppingCart cart = new ShoppingCart();
        cart.setProductId(((Number) productData.get(0).get("id")).longValue());
        cart.setQuantity(cartDto.getQuantity());
        cart.setPrice((Double)productData.get(0).get("price"));
        cart.setUserId(userId);
        return cart;
    }
}
