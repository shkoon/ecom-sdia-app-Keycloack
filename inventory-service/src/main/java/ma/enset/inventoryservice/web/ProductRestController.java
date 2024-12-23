package ma.enset.inventoryservice.web;

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.repository.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@EnableMethodSecurity(prePostEnabled = true)
public class ProductRestController {

    private ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping("/products")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Product> productList(){
        return productRepository.findAll();
    }
    @GetMapping("/products/{id}")
    public Product productById(@PathVariable Long id){
        return productRepository.findById(id).get();
    }
}
