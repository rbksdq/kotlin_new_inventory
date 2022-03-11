package com.learning.springbootkotlinh2.service

import com.learning.springbootkotlinh2.entity.Product
import com.learning.springbootkotlinh2.repository.ProductRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class ProductService(private val productRepo: ProductRepo) {

    fun getAllProducts(): ResponseEntity<List<Product>> {
        val products = productRepo.findAll()
        if (products.isEmpty()) {
            return ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<List<Product>>(products, HttpStatus.OK)
    }

    fun createProduct(product: Product): ResponseEntity<Product> {
        val product= productRepo.save(product)
        if(product!=null)
        {
            return ResponseEntity<Product>(HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity<Product>(HttpStatus.CREATED)
    }

    fun updateProductById(productId: Long, product: Product): ResponseEntity<Product> {
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

    fun deleteProductById(productId: Long): ResponseEntity<Void> {
        val product = productRepo.findById(productId)
        if (product.isPresent) {
            productRepo.deleteById(productId)
            return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR)
    }


//    fun getProductById(productId: Long ) : ResponseEntity<Product>{
//        val product= productRepo.findById(productId)
//        if (product.isPresent){
//            return ResponseEntity<Product>(product.get(),HttpStatus.OK)
//        }
//        return ResponseEntity<Product>(HttpStatus.NOT_FOUND)
//    }

}