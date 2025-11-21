package com.shopswift.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo) { this.repo = repo; }

    public List<Product> listAll() { return repo.findAll(); }

    public Product get(Long id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("product not found")); }

    @Transactional
    public Product reduceStock(Long productId, int qty) {
        Product p = get(productId);
        if (p.getStock() < qty) throw new RuntimeException("insufficient stock");
        p.setStock(p.getStock() - qty);
        return repo.save(p);
    }
}
