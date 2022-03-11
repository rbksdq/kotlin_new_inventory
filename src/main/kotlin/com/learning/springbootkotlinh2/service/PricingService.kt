package com.learning.springbootkotlinh2.service

import com.learning.springbootkotlinh2.entity.Pricing
import com.learning.springbootkotlinh2.repository.PricingRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PricingService(private val pricingRepo: PricingRepo) {

    fun getAllPricing(): ResponseEntity<List<Pricing>> {
        val pricing = pricingRepo.findAll()
        if (pricing.isEmpty()) {
            return ResponseEntity<List<Pricing>>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<List<Pricing>>(pricing, HttpStatus.OK)
    }

    fun createPricing(pricing: Pricing): ResponseEntity<Pricing> {
        val pricing= pricingRepo.save(pricing)
        if(pricing!=null)
        {
            return ResponseEntity<Pricing>(HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity<Pricing>(HttpStatus.CREATED)
    }

    fun updatePricingById(pricingId: Long, pricing: Pricing): ResponseEntity<Pricing> {
        return pricingRepo.findById(pricingId).map {
                pricingDetails ->
            val updatedPricing: Pricing = pricingDetails.copy(
                pricingName = pricing.pricingName,
                pricingAmount = pricing.pricingAmount,
                modifiedDate = LocalDate.now(),
                isActive = pricing.isActive,
                productId = pricing.productId
            )
            ResponseEntity(pricingRepo.save(updatedPricing), HttpStatus.OK)
        }.orElse(ResponseEntity<Pricing>(HttpStatus.INTERNAL_SERVER_ERROR))
    }

    fun deletePricingById(pricingId: Long): ResponseEntity<Void> {
        val pricing = pricingRepo.findById(pricingId)
        if (pricing.isPresent) {
            pricingRepo.deleteById(pricingId)
            return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR)
    }
}