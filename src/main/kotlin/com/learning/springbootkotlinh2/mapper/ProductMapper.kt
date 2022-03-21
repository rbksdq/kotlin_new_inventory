package com.learning.springbootkotlinh2.mapper

import com.learning.springbootkotlinh2.entity.Product
import org.springframework.stereotype.Component
import com.learning.springbootkotlinh2.dto.ProductDto as ProductDto


@Component
class ProductMapper: Mapper<ProductDto, Product> {
    override fun fromEntity(entity: Product): ProductDto {
        return  ProductDto(
            entity.productId,
            entity.productName,
            entity.createdDate,
            entity.modifiedDate,
            entity.isActive
        )
    }

    override fun toEntity(domain: ProductDto): Product = Product (
        domain.productId,
        domain.productName,
        domain.createdDate,
        domain.modifiedDate,
        domain.isActive
    )


}