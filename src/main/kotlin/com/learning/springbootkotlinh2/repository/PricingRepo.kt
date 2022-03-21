package com.learning.springbootkotlinh2.repository

import com.learning.springbootkotlinh2.entity.Pricing
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PricingRepo : JpaRepository<Pricing, Long> {

    @Query("SELECT m FROM Pricing as m")
    fun getAllPricing(): List<Pricing>
}