package org.iga.web;

import org.iga.dao.ProductRepository;
import org.iga.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/products/")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Product save(@RequestBody Product p){
        return productRepository.save(p);
    }
    @GetMapping
    public List<Product> allProduct() {
        return productRepository.findAll();
    }
    @GetMapping("{id}")
    public Optional<Product> getProduct(@PathVariable Long id) {
        return productRepository.findById(id);
    }
    @GetMapping("search")
    public Page<Product> chercher(
            @RequestParam(name="mc", defaultValue="") String mc,
            @RequestParam(name="page", defaultValue="0") int page,
            @RequestParam(name="size", defaultValue="5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return productRepository.chercher("%" + mc +"%",paging);
    }

}

