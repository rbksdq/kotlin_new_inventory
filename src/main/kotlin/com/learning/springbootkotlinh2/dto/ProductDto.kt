package com.learning.springbootkotlinh2.dto

import java.time.LocalDate

class ProductDto (

    val productId : Long,
    val productName : String,
    val createdDate : LocalDate,
    val modifiedDate : LocalDate,
    val isActive : Boolean

    )

