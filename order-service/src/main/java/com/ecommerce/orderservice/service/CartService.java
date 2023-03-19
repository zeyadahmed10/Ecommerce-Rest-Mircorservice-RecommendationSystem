package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.CartDto;
import com.ecommerce.orderservice.dto.OrderDetailsDto;
import com.ecommerce.orderservice.exception.EmptyResourceException;
import com.ecommerce.orderservice.exception.ResourceNotFoundException;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.model.OrderDetails;
import com.ecommerce.orderservice.model.ShoppingCart;
import com.ecommerce.orderservice.proxy.ProductProxy;
import com.ecommerce.orderservice.repository.CartRepository;
import com.ecommerce.orderservice.repository.OrderDetailsRepository;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.utility.FeignUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductProxy productProxy;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<ShoppingCart> getCartItems(String userId){
        return cartRepository.findAllByUserId(userId);
    }
    public ShoppingCart addCartItem(String userId, CartDto cartDto){
        var response = productProxy.getProduct(cartDto.getProductName());
        if(response.getStatusCode().value()==404)
            throw new ResourceNotFoundException("product with name: "+cartDto.getProductName()+" not found");
        List<Map<String,Object>> productData = (List<Map<String, Object>>) FeignUtils.convertObjectToList(response.getBody().getData());
        ShoppingCart cart = FeignUtils.buildCart(productData,cartDto ,userId );
        return cartRepository.save(cart);
    }
    public ShoppingCart updateCartItem(String userId,Long cartItemId, CartDto cartDto){
        var cart = cartRepository.findById(cartItemId);
        if(cart.isEmpty())
            throw new ResourceNotFoundException("cart item with id " + cartItemId + "not found");
        cart.get().setQuantity(cartDto.getQuantity());
        cart.get().setPrice(cart.get().getPrice());
        return cartRepository.save(cart.get());
    }
    public void deleteCartItem(Long cartItemId){
        var cart = cartRepository.findById(cartItemId);
        if(cart.isEmpty())
            throw new ResourceNotFoundException("cart item with id " + cartItemId + "not found");
        cartRepository.delete(cart.get());
    }
    public List<ShoppingCart> getCartItems(String userId, String productName){
        var response = productProxy.getProduct(productName);
        if(response.getStatusCode().value()==404)
            throw new ResourceNotFoundException("product with name: "+productName+" not found");
        List<Map<String,Object>> productData = (List<Map<String, Object>>) FeignUtils.convertObjectToList(response.getBody().getData());
        Long productId = ((Number)productData.get(0).get("id")).longValue();
        return cartRepository.findAllByUserIdAndProductId(userId, productId);
    }
    public OrderDetails makeOrder(String userId, OrderDetailsDto orderDetailsDto ){
        List<ShoppingCart> cartItems = cartRepository.findAllByUserId(userId);
        if(cartItems.isEmpty())
            throw new EmptyResourceException("Not cart items found for user " + userId);
        OrderDetails orderDetails = new OrderDetails();
        ArrayList<Order> orderItems = new ArrayList<>();
        Double totalPrice = 0.0;
        for(ShoppingCart cartItem : cartItems){
            orderItems.add(new Order(userId, cartItem.getProductId(), cartItem.getQuantity(), cartItem.getPrice()));
            totalPrice += cartItem.getPrice();
        }
        orderDetails.setPrice(totalPrice);
        orderDetails.setAddress(orderDetailsDto.getAddress());
        orderDetails.setPhoneNumber(orderDetailsDto.getPhoneNumber());
        orderDetails.setTime();
        orderDetails.setUserId(userId);
        orderDetails.setOrders(orderItems);
        orderDetailsRepository.save(orderDetails);
        cartRepository.deleteAll(cartItems);
        return orderDetails;

    }
}
