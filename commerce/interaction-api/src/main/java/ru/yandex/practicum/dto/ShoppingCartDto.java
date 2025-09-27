package ru.yandex.practicum.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShoppingCartDto {
    @NotNull
    UUID shoppingCartId;

    @NotNull
    @JsonDeserialize(using = CustomMapDeserializer.class)
    Map<UUID, Integer> products;
}