package ma.enset.orderservice.restClients;

import jakarta.ws.rs.Path;
import ma.enset.orderservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8087",name = "inventory-service")
public interface InventoryRestClient {

    @GetMapping("/api/products")
    List<Product> getProducts();
    @GetMapping("/api/products/{id}")
    Product getProductById(@PathVariable Long id);
}
