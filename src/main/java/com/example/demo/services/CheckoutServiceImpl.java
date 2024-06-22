package com.example.demo.services;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository,
                               CartItemRepository cartItemRepository){
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;

    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {


        //retrieve the cart
        Cart cart = purchase.getCart();

        //set the order tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        //set cartItem
        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> cart.add(item));


        //retrieve the customer. add cart to the customer.
        Customer customer = purchase.getCustomer();
        customer.add(cart);
        //customerRepository.save(customer);

        //set the cart status to ordered
        cart.setStatus(StatusType.ordered);
        cartRepository.save(cart);

        //return message
        if (purchase.getCart() == null || purchase.getCartItems().isEmpty()){

            String errorMessage = "Cart is empty or null";
            return new PurchaseResponse(errorMessage);
        }
        else {
            return new PurchaseResponse(orderTrackingNumber);
        }

    }

    private String generateOrderTrackingNumber() {

        //generates random UUID
        return UUID.randomUUID().toString();

    }
}
