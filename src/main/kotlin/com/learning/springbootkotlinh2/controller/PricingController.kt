package com.learning.springbootkotlinh2.controller

import com.learning.springbootkotlinh2.entity.Pricing
import com.learning.springbootkotlinh2.repository.PricingRepo
import com.learning.springbootkotlinh2.service.PricingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.ObjectUtils
import org.springframework.web.bind.annotation.*


@RestController
class PricingController(private val pricingService: PricingService,private val pricingRepo: PricingRepo) {

    @GetMapping("/pricing")
    fun getAllPricing() : ResponseEntity<List<Pricing>> {
        return pricingService.getAllPricing()
    }

    @GetMapping("pricing/{pricingId}")
    fun getPricingById(@PathVariable("pricingId") pricingId : Long): ResponseEntity<Pricing>{
        val pricing= pricingRepo.findById(pricingId)
        if (pricing.isPresent){
            return  ResponseEntity<Pricing>(pricing.get(), HttpStatus.OK)
        }
        return ResponseEntity<Pricing>(HttpStatus.NOT_FOUND)
    }

    @PostMapping("/pricing")

    fun createPricing(@RequestBody pricing: Pricing) : ResponseEntity<Pricing>{
        val pricing = pricingService.createPricing(pricing)//productRepo.save(product)
        if(ObjectUtils.isEmpty(pricing)){
            return ResponseEntity<Pricing>(HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity(HttpStatus.CREATED)
    }
//    fun createPricing(@RequestBody pricing: Pricing, uri: UriComponentsBuilder) : ResponseEntity<Pricing>{
//        val pricing = pricingRepo.save(pricing)
//        if(ObjectUtils.isEmpty(pricing)){
//            return ResponseEntity<Pricing>(HttpStatus.BAD_REQUEST)
//        }
//        val headers = HttpHeaders()
//        headers.setLocation(uri.path("/pricing/{pricingId}").buildAndExpand(pricing.productId).toUri());
//        return ResponseEntity(headers, HttpStatus.CREATED)
//    }
    @PutMapping("/pricing/{pricingId}")
    fun updatePricingById(@PathVariable("pricingId") pricingId: Long, @RequestBody pricing: Pricing): ResponseEntity<Pricing> {
        return pricingService.updatePricingById(pricingId, pricing)
//        return pricingRepo.findById(pricingId).map {
//                pricingDetails ->
//            val updatedPricing: Pricing = pricingDetails.copy(
//                pricingName = pricing.pricingName,
//                pricingAmount = pricing.pricingAmount,
//                modifiedDate = LocalDate.now(),
//                isActive = pricing.isActive,
//                productId = pricing.productId
//            )
//            ResponseEntity(pricingRepo.save(updatedPricing), HttpStatus.OK)
//        }.orElse(ResponseEntity<Pricing>(HttpStatus.INTERNAL_SERVER_ERROR))
    }

    @DeleteMapping("/pricing/{pricingId}")
    fun deletePricingById(@PathVariable("pricingId") pricingId: Long): ResponseEntity<Void> {
        return pricingService.deletePricingById(pricingId)
    }
//        val pricing = pricingRepo.findById(pricingId)
//        if (pricing.isPresent) {
//            pricingRepo.deleteById(pricingId)
//            return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
//        }
//        return ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR)
//    }
}