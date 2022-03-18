package com.learning.springbootkotlinh2.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table( name = "Product")
data class Product (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productId : Long,
    // validation
    @Column(name = "PRODUCT_NAME", length = 50, nullable = false, unique = true)
    val productName : String,
    val createdDate : LocalDate,
    val modifiedDate : LocalDate,
    val isActive : Boolean

)