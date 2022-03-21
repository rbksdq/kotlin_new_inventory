package com.learning.springbootkotlinh2.service

import com.learning.springbootkotlinh2.dto.ProductDto
import com.learning.springbootkotlinh2.entity.Product
import com.learning.springbootkotlinh2.exceptions.ProductExistException
import com.learning.springbootkotlinh2.mapper.ProductMapper
import com.learning.springbootkotlinh2.repository.ProductRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.lang.Exception
import java.time.LocalDate
import kotlin.math.E


@Service
class ProductService(private val productRepo: ProductRepo, private val productMapper: ProductMapper
): ProductSer/*(private val productRepo: ProductRepo)*/ {


    override fun getAllProducts(): List<ProductDto> {
        val product= productRepo.getAllProducts()
        return product.map {
            productMapper.fromEntity(it)
        }
    }

    override fun createProduct(productDto: ProductDto): ProductDto {
//        if(productDto.productId != null)
//            throw IllegalAccessException("id must be null or -1")
        val product= productMapper.toEntity(productDto)
        //productRepo.save(productMapper.toEntity(productDto))
        productRepo.save(product)
        //ResponseEntity<ProductDto>(HttpStatus.OK)
        return productMapper.fromEntity(product)
    }

    override fun getProductById(productId: Long): ProductDto {
        return productMapper.fromEntity(productRepo.findById(productId).get())
    }

    override fun updateProductById(productDto: ProductDto): ProductDto {
        getProductById(productDto.productId)
       productRepo.save(productMapper.toEntity(productDto))
       return productDto
    }

    override fun deleteProductById(productId: Long) {
        getProductById(productId)
        productRepo.deleteById(productId)
//        val exists= productRepo.existsById(productId)
//        if (!exists){
//            throw Exception("product doesn't exist")
//
//            productRepo.deleteById(productId)
//        }

    }


    //=============================without dto

//    fun getAllProducts(): ResponseEntity<List<Product>> {
//        val products = productRepo.findAll()
//        if (products.isEmpty()) {
//            return ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT)
//        }
//        return ResponseEntity<List<Product>>(products, HttpStatus.OK)
//    }
//
//    fun createProduct(product: Product) {
//        try {
//            val product = productRepo.save(product)
//            if (product != null) {
//                ResponseEntity<Product>(HttpStatus.BAD_REQUEST)
//            }
//            ResponseEntity<Product>(HttpStatus.CREATED)
//        }catch (e: Exception){
//            throw Exception(e.message)
//        }
//        //return productRepo.save(product)
//    }
//
//    fun updateProductById(productId: Long, product: Product): ResponseEntity<Product> {
//        try {
//            return productRepo.findById(productId).map {
//                    productDetails ->
//                val updatedProduct: Product = productDetails.copy(
//                    productName = product.productName,
//                    modifiedDate = LocalDate.now(),
//                    isActive = product.isActive
//                )
//                ResponseEntity(productRepo.save(updatedProduct), HttpStatus.OK)
//            }.orElse(ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR))
//        }
//        catch (e: Exception){
//            throw Exception(e.message)
//        }
//
//    }
//
//    fun deleteProductById(productId: Long): ResponseEntity<Void> {
//        try {
//            val product = productRepo.findById(productId)
//            if (product.isPresent) {
//                productRepo.deleteById(productId)
//                return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
//            }
//            return ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR)
//        }
//        catch (e: Exception){
//            throw Exception(e.message)
//        }
//
//    }


//    fun getProductById(productId: Long ) : ResponseEntity<Product>{
//        val product= productRepo.findById(productId)
//        if (product.isPresent){
//            return ResponseEntity<Product>(product.get(),HttpStatus.OK)
//        }
//        return ResponseEntity<Product>(HttpStatus.NOT_FOUND)
//    }

}