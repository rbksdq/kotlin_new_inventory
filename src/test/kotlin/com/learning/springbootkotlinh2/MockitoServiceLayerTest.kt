package com.learning.springbootkotlinh2

import com.learning.springbootkotlinh2.dto.PricingDto
import com.learning.springbootkotlinh2.dto.ProductDto
import com.learning.springbootkotlinh2.entity.Pricing
import com.learning.springbootkotlinh2.exceptions.ProductExistException
import com.learning.springbootkotlinh2.service.PricingSer
import com.learning.springbootkotlinh2.service.PricingService
import com.learning.springbootkotlinh2.service.ProductService
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDate

class MockitoServiceLayerTest {

    @Mock
    lateinit var service: ProductService

    @Mock
    lateinit var pricingService: PricingService

    @Mock
    lateinit var pSer: PricingSer

    @Test
    fun `createProductsAndFindAll`(){
        //val responseEntity : ResponseEntity<List<ProductDto>> = ResponseEntity<List<ProductDto>>(listOf<ProductDto>(),HttpStatus.OK)
        val productList: MutableList<ProductDto> = mutableListOf()
        val createProduct= ProductDto(1,"dalda", LocalDate.now(), LocalDate.now(),true)
        productList.add(createProduct)
        service= Mockito.mock(ProductService::class.java)
        Mockito.`when`(service.createProduct(createProduct)).thenReturn(createProduct)//(ProductExistException("product exists in repo"))//(ResponseEntity<Product>(HttpStatus.OK))
        Mockito.`when`(service.getAllProducts()).thenReturn(productList)
//        assertThat(service.getAllProducts().statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `deleteProduct`(){
        val createProduct= ProductDto(1,"dalda", LocalDate.now(), LocalDate.now(),true)
        service= Mockito.mock(ProductService::class.java)
        Mockito.`when`(service.deleteProductById(1))
    }

    //=====================product service ends here================

//    @Test
//    fun `createPricingAndFindAll`(){
//        val responseEntity : ResponseEntity<List<Pricing>> = ResponseEntity<List<Pricing>>(listOf<Pricing>(),HttpStatus.OK)
//        val createPricing= PricingDto(1,"dalda Oil",1000, LocalDate.now(), LocalDate.now(),true,1)
//        pricingService= Mockito.mock(PricingService::class.java)
//        Mockito.`when`(pricingService.createPricing(createPricing)).thenReturn(ResponseEntity<Pricing>(HttpStatus.OK))
//        Mockito.`when`(pricingService.getAllPricing()).thenReturn(responseEntity)
//        assertThat(pricingService.getAllPricing().statusCode).isEqualTo(HttpStatus.OK)
//    }

    @Test
    fun `deletePricing`(){
        val createPricing= Pricing(1,"dalda oil",1000, LocalDate.now(), LocalDate.now(),true,1)
        pricingService= Mockito.mock(PricingService::class.java)
        Mockito.`when`(pricingService.deletePricingById(1))
    }
//
//    @Test
//    fun `updatePricing`(){
//        val responseEntity : ResponseEntity<List<Pricing>> = ResponseEntity<List<Pricing>>(listOf<Pricing>(),HttpStatus.OK)
//        val createPricing= PricingDto(1,"dalda Oil",1000, LocalDate.now(), LocalDate.now(),true,1)
//        val updatePricing= PricingDto(1,"Meezan Oil",1000, LocalDate.now(), LocalDate.now(),true,1)
//        pricingService= Mockito.mock(PricingService::class.java)
//        Mockito.`when`(pricingService.createPricing(createPricing)).thenReturn(ResponseEntity<Pricing>(HttpStatus.OK))
//        Mockito.`when`(pricingService.updatePricingById(createPricing.pricingId, updatePricing)).thenReturn(ResponseEntity<Pricing>(HttpStatus.OK))
//        //assertThat(pricingService.getAllPricing().statusCode).isEqualTo(HttpStatus.OK)
//    }
}