package ma.enset.orderservice;

import ma.enset.orderservice.entities.Order;
import ma.enset.orderservice.entities.OrderState;
import ma.enset.orderservice.entities.ProductItem;
import ma.enset.orderservice.models.Product;
import ma.enset.orderservice.repository.OrderRepository;
import ma.enset.orderservice.repository.ProductItemRepository;
import ma.enset.orderservice.restClients.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(OrderRepository orderRepository, ProductItemRepository productItemRepository,
                                        InventoryRestClient inventoryRestClient) {
        return (args) -> {
          //List<Product> products= inventoryRestClient.getProducts();
            List<Long> productIds=List.of(1L,2L,3L);
            for (int i = 0; i < 5; i++) {
                Order order = Order.builder().date( LocalDate.now()).state(
                        Math.random() > 0.5 ? OrderState.PENDING : OrderState.SHIPPED).build();
                orderRepository.save(order);
                productIds.forEach(id -> {
                    ProductItem productItem = ProductItem.builder()
                            .price((Math.random() * 10) + 10000)
                            .productID(id)
                            .quantity((int) (Math.random() * 10) + 1).order(order).build();
                    productItemRepository.save(productItem);

                });
            }
        };
    }

}
