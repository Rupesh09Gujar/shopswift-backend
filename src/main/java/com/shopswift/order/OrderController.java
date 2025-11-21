package com.shopswift.order;

import com.shopswift.dto.CheckoutResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService svc;
    public OrderController(OrderService svc) { this.svc = svc; }

    private Long currentUserId(Authentication auth) {
        if (auth == null) return 0L;
        try { return Long.parseLong(auth.getName()); } catch (Exception e) { return 0L; }
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(Authentication auth) {
        Long uid = currentUserId(auth);
        com.shopswift.order.Order o = svc.placeOrder(uid);
        return ResponseEntity.ok(new CheckoutResponse(o.getId(), o.getStatus()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(svc.getOrder(id));
    }
}
