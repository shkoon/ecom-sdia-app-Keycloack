package ma.enset.inventoryservice;

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
          productRepository.save(Product.builder().name("Ordinateur").price(Math.random()*20000).quantity((int) Math.random()*11).build()) ;
            productRepository.save(Product.builder().name("Iphone").price(Math.random()*20000).quantity((int) Math.random()*11).build()) ;
            productRepository.save(Product.builder().name("Ecran").price(Math.random()*20000).quantity((int) Math.random()*11).build()) ;
        };
    }
}
