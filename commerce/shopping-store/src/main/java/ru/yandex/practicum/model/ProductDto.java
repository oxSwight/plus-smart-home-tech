package ru.yandex.practicum.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {

    UUID productId;
    String productName;
    String description;
    String imageSrc;
    String quantityState;
    String productState;
    String productCategory;
    double price;
    int rating;
}
