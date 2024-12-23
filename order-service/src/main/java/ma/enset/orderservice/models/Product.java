package ma.enset.orderservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Product {

    private Long id;
    private String name;
    private double price;
    private int quantity;
}
