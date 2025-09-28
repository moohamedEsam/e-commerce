package com.example.product.application.repository;

import com.example.product.application.mapper.ProductMapper;
import com.example.proudct.domain.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Primary
public class CachedProductRepositoryImpl extends ProductRepositoryImpl {
    public static final String PRODUCT_HASH_KEY = "product";
    private final ReactiveRedisTemplate<String, ProductEntity> redisTemplate;
    private final ProductMapper productMapper;

    public CachedProductRepositoryImpl(
            PostgresProductRepository postgresProductRepository,
            ReactiveRedisTemplate<String, ProductEntity> redisProductRepository,
            ProductMapper productMapper) {
        super(postgresProductRepository, productMapper);
        this.redisTemplate = redisProductRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Mono<Product> findById(String id) {
        return redisTemplate.opsForHash()
                .get(PRODUCT_HASH_KEY, id)
                .map(x -> productMapper.toProduct((ProductEntity) x))
                .switchIfEmpty(Mono.defer(() -> super.findById(id)));
    }

    @Override
    public Mono<Product> save(Product product) {
        return super.save(product)
                .doOnSuccess(x -> redisTemplate.opsForHash()
                        .put(PRODUCT_HASH_KEY, x.getId(), productMapper.fromProduct(x)));

    }

    @Override
    public Mono<Void> deleteById(String id) {
        return super.deleteById(id)
                .doOnSuccess(x -> redisTemplate.opsForHash().delete(PRODUCT_HASH_KEY));
    }
}
