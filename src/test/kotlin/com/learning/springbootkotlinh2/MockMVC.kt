package com.learning.springbootkotlinh2

import com.fasterxml.jackson.databind.ObjectMapper
import com.learning.springbootkotlinh2.dto.PricingDto
import com.learning.springbootkotlinh2.dto.ProductDto
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*
import java.time.LocalDate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = [
        "spring.datasource.urljdbc:h2:mem:testdb"
    ]
)

@AutoConfigureMockMvc
class MockMVC @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class ProductMvc {
        @Test
        fun `productCrud`() {
            val productList: MutableList<ProductDto> = mutableListOf()
            val createProduct = ProductDto(1, "dalda", LocalDate.now(), LocalDate.now(), true)
            val updatedProduct = ProductDto(1, "meezan", LocalDate.now(), LocalDate.now(), false)
            productList.add(createProduct)

            mockMvc.post("/products") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(createProduct)
            }
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(createProduct))
                    }
                }

            mockMvc.get("/products")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(productList))
                    }
                }

            mockMvc.get("/products/${createProduct.productId}")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(createProduct))
                    }
                }

            mockMvc.put("/products/${createProduct.productId}") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedProduct)
            }
                .andDo { print() }
                .andExpect {
                    status { isOk() }

                }

            mockMvc.delete("/products/${updatedProduct.productId}")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }
        }

        //=================== pricing MockMVC


        @Test
        fun `pricingCrud`(){
            val pricingList: MutableList<PricingDto> = mutableListOf()
            val createPricing= PricingDto(1,"oil sale",1000, LocalDate.now(), LocalDate.now(),true,1)
            val updatedPricing= PricingDto(1,"oil",1500, LocalDate.now(), LocalDate.now(),false,1)
            pricingList.add(createPricing)
            mockMvc.post("/pricing"){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(createPricing)
            }
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(createPricing))
                    }
                }
            mockMvc.get("/pricing")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(pricingList))
                    }
                }
            mockMvc.get("/pricing/${createPricing.pricingId}")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(createPricing))
                    }
                }
            mockMvc.put("/pricing/${createPricing.pricingId}"){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedPricing)
            }
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                }
            mockMvc.delete("/pricing/${updatedPricing.pricingId}")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }
        }
    }
}