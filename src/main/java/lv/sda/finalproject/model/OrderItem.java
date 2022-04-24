package lv.sda.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @Positive
    private double price;

    @Positive
    private int quantity;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

}
