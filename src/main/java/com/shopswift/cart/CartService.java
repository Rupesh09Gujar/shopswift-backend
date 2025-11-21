package com.shopswift.cart;

import com.shopswift.product.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepo;
    private final ProductService productService;

    public CartService(CartRepository cartRepo, ProductService productService) {
        this.cartRepo = cartRepo;
        this.productService = productService;
    }

    public Cart getOrCreateCart(Long userId) {
        Optional<Cart> c = cartRepo.findByUserId(userId);
        if (c.isPresent()) return c.get();
        Cart cart = new Cart(userId);
        return cartRepo.save(cart);
    }

    @Transactional
    public Cart addItem(Long userId, Long productId, int qty) {
        // validate stock (read-only check)
        productService.get(productId); // throws if not exists
        Cart cart = getOrCreateCart(userId);
        // find existing
        cart.getItems().removeIf(item -> {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + qty);
                return false;
            }
            return false;
        });
        // if not present, add
        boolean exists = cart.getItems().stream().anyMatch(i -> i.getProductId().equals(productId));
        if (!exists) {
            cart.getItems().add(new CartItem(productId, qty));
        }
        return cartRepo.save(cart);
    }

    @Transactional
    public Cart removeItem(Long userId, Long productId) {
        Cart cart = getOrCreateCart(userId);
        cart.getItems().removeIf(i -> i.getProductId().equals(productId));
        return cartRepo.save(cart);
    }
}
