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

        //set cartitems
        Set<CartItem> cartitems = purchase.getCartitems();
        cartitems.forEach(item -> cart.add(item));

        //set the cartitems to the cart and set the customer to the cart
        cart.setCartItem(purchase.getCartitems());
        cart.setCustomer(purchase.getCustomer());

        //retrieve the customer. add cart to the customer
        Customer customer = purchase.getCustomer();
        customer.add(cart);

        //set the cart status to ordered
        cart.setStatus(StatusType.ordered);

        //save customer to the customer repository
        customerRepository.save(customer);

        //return message
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        //generates random UUID
        return UUID.randomUUID().toString();

    }
}
