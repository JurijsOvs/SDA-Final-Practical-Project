package lv.sda.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    @Length(min = 3, message = "Product Name must be at least 3 characters long")
    private String productName;

    private Long categoryID;

    @Positive
    private double price;

    @PositiveOrZero
    private int quantity;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    @Lob
    @Column(nullable = true, length = 64)
    private byte[] photos;

    private String description;

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "../static/product-photos/" + id + "/" + photos;
    }



}
