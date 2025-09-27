package ru.yandex.practicum.shoppingstore.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id", updatable = false, nullable = false)
    UUID productId;

    @Column(name = "product_name", nullable = false)
    String productName;

    @Column(nullable = false)
    String description;

    @Column(name = "image_src")
    String imageSrc;

    @Column(name = "quantity_state", nullable = false)
    String quantityState;

    @Column(name = "product_state", nullable = false)
    String productState;

    @Column(name = "product_category", nullable = false)
    String productCategory;

    double price;
    int rating;
}
