package com.learning.springbootkotlinh2.service

import com.learning.springbootkotlinh2.dto.ProductDto

interface ProductSer {
    fun getAllProducts() : List<ProductDto>
    fun createProduct(productDto: ProductDto): ProductDto
    fun getProductById(productId: Long): ProductDto
    fun updateProductById(productDto: ProductDto): ProductDto
    fun deleteProductById(productId: Long)
}