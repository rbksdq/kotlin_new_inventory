package com.learning.springbootkotlinh2

import com.learning.springbootkotlinh2.entity.Product
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
import org.springframework.web.client.RestTemplate
import java.time.LocalDate
import javax.lang.model.element.Element


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = [
        "spring.datasource.urljdbc:h2:mem:testdb"
    ]
)
class MockitoTests(@Autowired val client: TestRestTemplate) {

    /*@Test
    fun `getAllProducts`(){
        val entity = client.getForEntity<String>("/products")
        AssertionsForClassTypes.assertThat(entity.statusCode).isEqualTo(HttpStatus.NO_CONTENT)
    }*/


//
//    @Mock
//    lateinit var productRepo: ProductRepo

   // @InjectMocks

    /*@Test
    fun `createPRoduct`(){
        val product  =  Product(1,"dalda", LocalDate.now(), LocalDate.now(),true)

        Mockito.`when`(client.postForObject<Product>("/products",product))
        val entity= client.getForEntity<String>("/products")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains(product.productName)
                  //  .thenReturn( ResponseEntity(emp, HttpStatus.OK));

        //Employee employee = empService.getEmployee(id);
       // assertEquals();
    }*/

    fun `display non-empty list mockito-kotlin`() {
        val elements = listOf(
            Element(1, "first"),
            Element(2, "second")
        )

        val dataProvider: DataProvider = mock {
            on { getAll() } doReturn elements
        }

        val view: View = mock()

        val presenter = Presenter(view, dataProvider)

        presenter.start()

        Mockito.verify(view).displayItems(elements)
    }

}