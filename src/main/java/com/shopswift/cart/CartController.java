package com.shopswift.cart;

import com.shopswift.dto.CartItemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService svc;
    public CartController(CartService svc) { this.svc = svc; }

    // For simplicity, userId is taken from authenticated principal's username parsed as Long if numeric.
    private Long currentUserId(Authentication auth) {
        if (auth == null) return 0L;
        try { return Long.parseLong(auth.getName()); } catch (Exception e) { return 0L; }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CartItemRequest req, Authentication auth) {
        Long uid = currentUserId(auth);
        Cart c = svc.addItem(uid, req.getProductId(), req.getQuantity());
        return ResponseEntity.ok(c);
    }

    @PostMapping("/remove")
    public ResponseEntity<?> remove(@RequestBody CartItemRequest req, Authentication auth) {
        Long uid = currentUserId(auth);
        Cart c = svc.removeItem(uid, req.getProductId());
        return ResponseEntity.ok(c);
    }

    @GetMapping
    public ResponseEntity<?> getCart(Authentication auth) {
        Long uid = currentUserId(auth);
        Cart c = svc.getOrCreateCart(uid);
        return ResponseEntity.ok(c);
    }
}
