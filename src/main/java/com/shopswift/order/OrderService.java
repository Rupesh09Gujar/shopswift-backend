package com.shopswift.order;

import com.shopswift.cart.Cart;
import com.shopswift.cart.CartItem;
import com.shopswift.cart.CartRepository;
import com.shopswift.product.Product;
import com.shopswift.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private final CartRepository cartRepo;
    private final ProductRepository productRepo;
    private final OrderRepository orderRepo;

    public OrderService(CartRepository cartRepo, ProductRepository productRepo, OrderRepository orderRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    @Transactional
    public Order placeOrder(Long userId) {
        Cart cart = cartRepo.findByUserId(userId).orElseThrow(() -> new RuntimeException("cart empty"));
        if (cart.getItems().isEmpty()) throw new RuntimeException("cart is empty");

        // validate stock and reduce
        for (CartItem ci : cart.getItems()) {
            Product p = productRepo.findById(ci.getProductId()).orElseThrow(() -> new RuntimeException("product not found"));
            if (p.getStock() < ci.getQuantity()) throw new RuntimeException("insufficient stock for product " + p.getId());
            p.setStock(p.getStock() - ci.getQuantity());
            productRepo.save(p);
        }

        Order o = new Order(userId);
        for (CartItem ci : cart.getItems()) {
            Product p = productRepo.findById(ci.getProductId()).get();
            o.getItems().add(new com.shopswift.order.OrderItem(p.getId(), ci.getQuantity(), p.getPrice()));
        }
        Order saved = orderRepo.save(o);

        // clear cart
        cart.getItems().clear();
        cartRepo.save(cart);

        return saved;
    }

    public Order getOrder(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new RuntimeException("order not found"));
    }
}
