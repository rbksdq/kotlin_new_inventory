package com.learning.springbootkotlinh2

import com.learning.springbootkotlinh2.controller.PricingController
import com.learning.springbootkotlinh2.controller.ProductController
import com.learning.springbootkotlinh2.entity.Pricing
import com.learning.springbootkotlinh2.entity.Product
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForObject
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.Rollback
import java.time.LocalDate

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
properties = [
		"spring.datasource.urljdbc:h2:mem:testdb"
	]
)
class SpringbootKotlinH2ApplicationTests(@Autowired val client: TestRestTemplate/*, private val productController: ProductController, pricingController: PricingController productService: ProductService, private val productRepo: ProductRepo,
																				private val pricingRepo: PricingRepo, private val pricingService: PricingService*/) {

	@Test
	fun contextLoads() {
	}


//	//create
//	@Test
//	@Rollback(false)
//	fun testCreateProduct(){
//		val saveProduct= productRepo.save( Product(1,"Dalda", LocalDate.now(), LocalDate.now(),true))
//		assertThat(saveProduct.productId).isGreaterThan(0)
//	}

	@Test
	fun `createProduct`(){
		val product= Product(1,"dalda", LocalDate.now(), LocalDate.now(),true)
		client.postForObject<Product>("/products", product)

		val entity= client.getForEntity<String>("/products")
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(entity.body).contains(product.productName)

		val updateProduct= Product(1,"meezan", LocalDate.now(), LocalDate.now(),false)
		client.put("/products/{productId}",updateProduct,product.productId)
		val updatedEntity= client.getForEntity<String>("/products")

		client.delete("/products/{productId}",product.productId)
		val deleteEntity= client.getForEntity<String>("/products")

	}

//	//findAll
//	@Test
//	fun `getAllProducts`(){
//		val entity = client.getForEntity<String>("/products")
//		assertThat(entity.statusCode).isEqualTo(HttpStatus.NO_CONTENT)
//	}
//
//	@Test
//	fun `deleteProductById`(){
//		val entity= client.getForEntity<String>("/products/101")
//		assertThat(entity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
//	}





	//==================== pricing tests================

	@Test
	fun `createPricing`() {
		val product = Product(1, "dalda", LocalDate.now(), LocalDate.now(), true)
		client.postForObject<Product>("/products", product)

		val pricing = Pricing(1, "oil", 1000, LocalDate.now(), LocalDate.now(), true, product.productId)
		client.postForObject<Pricing>("/pricing", pricing)

		val entity = client.getForEntity<String>("/pricing")
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(entity.body).contains(pricing.pricingName)

		val updatePricing= Pricing(1,"meezan Oil",1500, LocalDate.now(), LocalDate.now(),false,product.productId)
		client.put("/pricing/{pricingId}", updatePricing, pricing.pricingId)
		val updatedEntity= client.getForEntity<String>("/pricing")

		//client.delete("/products/{productId}")

		client.delete("/pricing/{pricingId}",pricing.pricingId)
		val deleteEntity= client.getForEntity<String>("/pricing")
	}


//
//	@Test
//	fun `getAllPricing`(){
//		val entity= client.getForEntity<String>("/pricing")
//		assertThat(entity.statusCode).isEqualTo(HttpStatus.NO_CONTENT)
//	}
//
//	@Test
//	fun `createPricing`(){
//		val pricing= Pricing(101,"oil",1000, LocalDate.now(), LocalDate.now(),true,101)
//		val entity= client.getForEntity<String>("/pricing")
//		assertThat(entity.statusCode).isEqualTo(HttpStatus.NO_CONTENT)
	//}




//	//update
//	@Test
//	@Rollback(false)
//	fun testUpdateProductById(){
//		val updateProduct= productRepo.findAll()
//		updateProduct.
//	}

	//delete
//	@Test
//	@Rollback(false)
//	fun testDeleteProductById(){
//		val product= productRepo.findById(1)
//		productRepo.delete(product.get())
//		assertThat(product).isNull()
//	}
//
//
//	//create pricing
//	@Test
//	@Rollback(false)
//	fun testCreatePricing(){
//		val savePricing= pricingRepo.save( Pricing(1,"Dalda oil",1000, LocalDate.now(), LocalDate.now(),true,1))
//		assertThat(savePricing.pricingId).isGreaterThan(0)
//	}
//
//	//findAllPricing
//	@Test
//	fun testFindAllPricing(){
//		val pricing = pricingRepo.findAll()
//		assertThat(pricing).size().isGreaterThan(0)
//	}
//
//	//update pricing
////	@Test
////	@Rollback(false)
////	fun testUpdatePricingById(){
////		val updatePricing= pricingRepo.findAll()
////		updatePricing.
////	}
//
//	//deletePricing
//	@Test
//	@Rollback(false)
//	fun testDeletePricingById(){
//		val pricing= pricingRepo.findById(1)
//		pricingRepo.delete(pricing.get())
//		assertThat(pricing).isNull()
//	}


}
