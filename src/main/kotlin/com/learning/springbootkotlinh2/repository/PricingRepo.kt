package com.learning.springbootkotlinh2.repository

import com.learning.springbootkotlinh2.entity.Pricing
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PricingRepo : JpaRepository<Pricing, Long> {
}