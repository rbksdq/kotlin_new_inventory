package com.learning.springbootkotlinh2.controller

import com.learning.springbootkotlinh2.dto.ProductDto
import com.learning.springbootkotlinh2.entity.Product
import com.learning.springbootkotlinh2.repository.ProductRepo
import com.learning.springbootkotlinh2.service.ProductSer
import com.learning.springbootkotlinh2.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.ObjectUtils
import org.springframework.web.bind.annotation.*
import javax.persistence.Id

@RestController
class ProductController(private val productSer: ProductSer /*val productService: ProductService, val productRepo: ProductRepo*/) {

    @PostMapping("/products")
    fun createProduct(@RequestBody productDto: ProductDto): ResponseEntity<ProductDto>  {
        try {
            return ResponseEntity(productSer.createProduct(productDto), HttpStatus.CREATED)
        } catch (exception: IllegalAccessException){
        return ResponseEntity(null, HttpStatus.BAD_GATEWAY)
    }
    }


    @GetMapping("/products")
    fun getAllProducts() : ResponseEntity<List<ProductDto>>{
        return ResponseEntity(productSer.getAllProducts(),HttpStatus.OK)
    }

    @GetMapping("/products/{productId}")
    fun getProductById(@PathVariable productId: Long)=
        ResponseEntity(productSer.getProductById(productId),HttpStatus.OK)


    @PutMapping("/products/{productId}")
    fun updateProductById(@PathVariable("productId") productId: Long, @RequestBody productDto: ProductDto)/*: ResponseEntity<ProductDto>*/{
        ResponseEntity(productSer.updateProductById(productDto), HttpStatus.OK)
    }

    @DeleteMapping("/products/{productId}")
    fun deleteProductById(@PathVariable("productId") productId: Long): ResponseEntity<Unit> =
        ResponseEntity(productSer.deleteProductById(productId),HttpStatus.NO_CONTENT)




    //===============without dto

////    fun getAllProducts(): ResponseEntity<List<Product>> {
////        val products = productRepo.findAll()
////        if (products.isEmpty()) {
////            return ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT)
////        }
////        return ResponseEntity<List<Product>>(products, HttpStatus.OK)
////    }
////
//    @GetMapping("/products/{productId}")
//    fun getProductById(@PathVariable("productId") productId : Long): ResponseEntity<Product>{
//
//        val product= productRepo.findById(productId)
//        if (product.isPresent){
//            return  ResponseEntity<Product>(product.get(), HttpStatus.OK)
//        }
//        return ResponseEntity<Product>(HttpStatus.NOT_FOUND)
//    }
//
//    @PostMapping("/products")
//
//    fun createProduct(@RequestBody product: Product) : ResponseEntity<Product>{
//       try {
//           val product = productService.createProduct(product)//productRepo.save(product)
//           if(ObjectUtils.isEmpty(product)){
//               return ResponseEntity<Product>(HttpStatus.BAD_REQUEST)
//           }
//           return ResponseEntity(HttpStatus.CREATED)
//       }
//       catch (e:Exception){
//           return ResponseEntity(HttpStatus.BAD_GATEWAY)
//       }
//    }
////    fun createProduct(@RequestBody product: Product, uri: UriComponentsBuilder) : ResponseEntity<Product>{
////        val product = productRepo.save(product)
////        if(ObjectUtils.isEmpty(product)){
////            return ResponseEntity<Product>(HttpStatus.BAD_REQUEST)
////        }
////        val headers = HttpHeaders()
////        headers.setLocation(uri.path("/products/{productId}").buildAndExpand(product.productId).toUri());
////        return ResponseEntity(headers, HttpStatus.CREATED)
////    }
//
//    @PutMapping("/products/{productId}")
//    fun updateProductById(@PathVariable("productId") productId: Long, @RequestBody product: Product): ResponseEntity<Product> {
//        try {
//            return  productService.updateProductById(productId, product)
//        }
//        catch (e: Exception){
//            return ResponseEntity<Product>(HttpStatus.BAD_GATEWAY)
//        }
//    }
////        return productRepo.findById(productId).map {
////                productDetails ->
////            val updatedProduct: Product = productDetails.copy(
////                productName = product.productName,
////                modifiedDate = LocalDate.now(),
////                isActive = product.isActive
////            )
////            ResponseEntity(productRepo.save(updatedProduct), HttpStatus.OK)
////        }.orElse(ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR))
////    }
//
//    @DeleteMapping("/products/{productId}")
//    fun deleteProductById(@PathVariable("productId") productId: Long): ResponseEntity<Void> {
//        return try {
//            productService.deleteProductById(productId)
//        } catch (e: Exception){
//            ResponseEntity(HttpStatus.BAD_GATEWAY)
//        }
//    }
//        val product = productRepo.findById(productId)
//        if (product.isPresent) {
//            productRepo.deleteById(productId)
//            return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
//        }
//        return ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR)
//    }
}