package com.learning.springbootkotlinh2.entity

import java.time.LocalDate
import javax.persistence.*


@Entity
@Table(name = "Pricing")
data class Pricing(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val pricingId : Long,
    val pricingName : String,
    val pricingAmount : Long,
    val createdDate : LocalDate,
    val modifiedDate : LocalDate,
    val isActive : Boolean,
    val productId : Long

)