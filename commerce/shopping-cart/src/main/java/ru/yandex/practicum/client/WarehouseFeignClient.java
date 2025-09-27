package ru.yandex.practicum.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.yandex.practicum.feign.WarehouseClient;

@FeignClient(name = "warehouse")
public interface WarehouseFeignClient extends WarehouseClient {
}