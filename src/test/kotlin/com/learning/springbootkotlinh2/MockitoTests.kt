package com.learning.springbootkotlinh2

import com.learning.springbootkotlinh2.entity.Pricing
import com.learning.springbootkotlinh2.entity.Product
import com.learning.springbootkotlinh2.repository.PricingRepo
import com.learning.springbootkotlinh2.repository.ProductRepo
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForObject
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import java.time.LocalDate
import javax.lang.model.element.Element


class MockitoTests {

    @Mock
    lateinit var repository: ProductRepo

    @Mock
    lateinit var pricingRepo: PricingRepo

    @Test
    fun `getAllProduct`(){
        val productList : MutableList<Product> = mutableListOf()
        val singleProduct = Product(1,"dalda", LocalDate.now(), LocalDate.now(),true)
        productList.add(singleProduct)
        repository = Mockito.mock(ProductRepo::class.java)
        Mockito.`when`(repository.findAll()).thenReturn(productList)
        assertThat(repository.findAll().contains(singleProduct))

    }

    @Test
    fun `createProduct`(){
        val createProduct= Product(1,"dalda", LocalDate.now(), LocalDate.now(),true)
        repository= Mockito.mock(ProductRepo::class.java)
        Mockito.`when`(repository.save(createProduct)).thenReturn(createProduct)
    }

    @Test
    fun `deleteProduct`(){
        val product= Product(1,"dalda", LocalDate.now(), LocalDate.now(),true)
        repository= Mockito.mock(ProductRepo::class.java)
        Mockito.`when`(repository.delete(product))
    }

    //================== product mocking ends here =============

    @Test
    fun `getAllPricing`(){
        val pricingList : MutableList<Pricing> = mutableListOf()
        val singlePricing = Pricing(1,"dalda Oil",1000, LocalDate.now(), LocalDate.now(),true,1)
        pricingList.add(singlePricing)
        pricingRepo = Mockito.mock(PricingRepo::class.java)
        Mockito.`when`(pricingRepo.findAll()).thenReturn(pricingList)
        assertThat(pricingRepo.findAll().contains(singlePricing))

    }

    @Test
    fun `createPricing`(){
        val createPricing= Pricing(1,"dalda Oil",1000, LocalDate.now(), LocalDate.now(),true,1)
        pricingRepo= Mockito.mock(PricingRepo::class.java)
        Mockito.`when`(pricingRepo.save(createPricing)).thenReturn(createPricing)
    }

    @Test
    fun `deletePricing`(){
        val pricing= Pricing(1,"dalda Oil",1000, LocalDate.now(), LocalDate.now(),true,1)
        pricingRepo= Mockito.mock(PricingRepo::class.java)
        Mockito.`when`(pricingRepo.delete(pricing))
    }



}