package com.learning.springbootkotlinh2.dto

import java.time.LocalDate

class PricingDto (

    val pricingId : Long,
    val pricingName : String,
    val pricingAmount : Long,
    val createdDate : LocalDate,
    val modifiedDate : LocalDate,
    val isActive : Boolean,
    val productId : Long

    )