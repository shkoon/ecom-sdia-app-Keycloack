package ma.enset.orderservice.web;

import ma.enset.orderservice.entities.Order;
import ma.enset.orderservice.repository.OrderRepository;
import ma.enset.orderservice.restClients.InventoryRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRestController {
    private OrderRepository orderRepository;
    private InventoryRestClient inventoryRestClient;

    public OrderRestController(OrderRepository orderRepository, InventoryRestClient inventoryRestClient) {
        this.orderRepository = orderRepository;
        this.inventoryRestClient = inventoryRestClient;
    }

    @GetMapping("/orders")
    public List<Order> orderList(){
        return orderRepository.findAll();
    }
    @GetMapping("/orders/{id}")
    public Order orderById(@PathVariable Long id){
        Order order= orderRepository.findById(id).get();
        order.getProductItems().forEach(productItem -> {
            productItem.setProduct(inventoryRestClient.getProductById(productItem.getProductID()));
        });
        return order;
    }
}
