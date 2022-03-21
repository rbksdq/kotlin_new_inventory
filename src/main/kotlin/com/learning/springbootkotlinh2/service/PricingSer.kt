package com.learning.springbootkotlinh2.service

import com.learning.springbootkotlinh2.dto.PricingDto

interface PricingSer {
    fun getAllPricing() : List<PricingDto>
    fun createPricing(pricingDto: PricingDto): PricingDto
    fun getPricingById(pricingId: Long): PricingDto
    fun updatePricingById(pricingDto: PricingDto): PricingDto
    fun deletePricingById(pricingId: Long)
}