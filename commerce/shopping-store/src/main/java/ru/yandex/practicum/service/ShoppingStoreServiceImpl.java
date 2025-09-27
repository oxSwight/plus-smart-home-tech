package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.dto.ProductDto;
import ru.yandex.practicum.enums.ProductCategory;
import ru.yandex.practicum.exception.ProductNotFoundException;
import ru.yandex.practicum.mapper.ProductMapper;
import ru.yandex.practicum.repository.ShoppingStoreRepository;
import ru.yandex.practicum.shoppingstore.model.Product;
import ru.yandex.practicum.request.SetProductQuantityStateRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ShoppingStoreServiceImpl implements ShoppingStoreService {

    private final ShoppingStoreRepository storeRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public Page<ProductDto> getProducts(String productCategory, Pageable pageableDto) {
        Sort sort = pageableDto.getSort();
        if (sort.isEmpty()) {
            sort = Sort.by(Sort.Direction.ASC, "productName");
        }

        PageRequest pageRequest = PageRequest.of(
                pageableDto.getPageNumber(),
                pageableDto.getPageSize(),
                sort
        );

        Page<Product> products = storeRepository.findAllByProductCategory(ProductCategory.valueOf(productCategory), pageRequest);
        return products.map(productMapper::productToProductDto);
    }

    @Override
    public ProductDto createNewProduct(ProductDto productDto) {
        Product newProduct = productMapper.productDtoToProduct(productDto);
        return productMapper.productToProductDto(storeRepository.save(newProduct));
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product oldProduct = storeRepository.findById(productDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Ошибка, товар по id %s в БД не найден", productDto.getProductId()))
                );
        Product newProduct = productMapper.productDtoToProduct(productDto);
        newProduct.setProductId(oldProduct.getProductId());
        return productMapper.productToProductDto(storeRepository.save(newProduct));
    }

    @Override
    public boolean removeProductFromStore(UUID productId) {
        Product product = storeRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException(String.format("Ошибка, товар по id %s в БД не найден", productId))
        );
        product.setProductState("DEACTIVATE");
        storeRepository.save(product);
        return true;
    }

    @Override
    public boolean setProductQuantityState(SetProductQuantityStateRequest request) {
        Product product = storeRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Ошибка, товар по id %s в БД не найден", request.getProductId()))
                );
        if (!product.getQuantityState().equals(request.getQuantityState())) {
            product.setQuantityState(String.valueOf(request.getQuantityState()));
            storeRepository.save(product);
        }
        return true;
    }

    @Override
    public ProductDto getProduct(UUID productId) {
        Product product = storeRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException(String.format("Ошибка, товар по id %s в БД не найден", productId))
        );
        return productMapper.productToProductDto(product);
    }
}
