package com.learning.springbootkotlinh2.controller

import com.learning.springbootkotlinh2.entity.Product
import com.learning.springbootkotlinh2.repository.ProductRepo
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.ObjectUtils
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.time.LocalDate

@RestController
class ProductController(val productRepo: ProductRepo) {


    @GetMapping("/products")
    fun getAllProducts(): ResponseEntity<List<Product>> {
        val products = productRepo.findAll()
        if (products.isEmpty()) {
            return ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<List<Product>>(products, HttpStatus.OK)
    }

    @GetMapping("/products/{productId}")
    fun getProductById(@PathVariable("productId") productId : Long): ResponseEntity<Product>{
        val product= productRepo.findById(productId)
        if (product.isPresent){
            return  ResponseEntity<Product>(product.get(), HttpStatus.OK)
        }
        return ResponseEntity<Product>(HttpStatus.NOT_FOUND)
    }

    @PostMapping("/products")
    fun createProduct(@RequestBody product: Product, uri: UriComponentsBuilder) : ResponseEntity<Product>{
        val product = productRepo.save(product)
        if(ObjectUtils.isEmpty(product)){
            return ResponseEntity<Product>(HttpStatus.BAD_REQUEST)
        }
        val headers = HttpHeaders()
        headers.setLocation(uri.path("/products/{productId}").buildAndExpand(product.productId).toUri());
        return ResponseEntity(headers, HttpStatus.CREATED)
    }

    @PutMapping("/products/{productId}")
    fun updateProductById(@PathVariable("productId") productId: Long, @RequestBody product: Product): ResponseEntity<Product> {
        return productRepo.findById(productId).map {
                productDetails ->
            val updatedProduct: Product = productDetails.copy(
                productName = product.productName,
                modifiedDate = LocalDate.now(),
                isActive = product.isActive
            )
            ResponseEntity(productRepo.save(updatedProduct), HttpStatus.OK)
        }.orElse(ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR))
    }

    @DeleteMapping("/products/{productId}")
    fun deleteProductById(@PathVariable("productId") productId: Long): ResponseEntity<Void> {
        val product = productRepo.findById(productId)
        if (product.isPresent) {
            productRepo.deleteById(productId)
            return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR)
    }
}