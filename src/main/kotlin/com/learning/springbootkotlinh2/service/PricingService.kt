package com.learning.springbootkotlinh2.service

import com.learning.springbootkotlinh2.dto.PricingDto
import com.learning.springbootkotlinh2.entity.Pricing
import com.learning.springbootkotlinh2.mapper.PricingMapper
import com.learning.springbootkotlinh2.repository.PricingRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PricingService(private val pricingRepo: PricingRepo, private val pricingMapper: PricingMapper/*private val pricingRepo: PricingRepo*/): PricingSer {



    override fun getAllPricing(): List<PricingDto> {
        val pricing= pricingRepo.findAll()
        return pricing.map {
            pricingMapper.fromEntity(it)
        }
    }

    override fun createPricing(pricingDto: PricingDto): PricingDto {
        val pricing= pricingMapper.toEntity(pricingDto)
        pricingRepo.save(pricing)
        return pricingMapper.fromEntity(pricing)
    }

    override fun getPricingById(pricingId: Long): PricingDto {
        return  pricingMapper.fromEntity(pricingRepo.findById(pricingId).get())
    }

    override fun updatePricingById(pricingDto: PricingDto): PricingDto {
        TODO("Not yet implemented")
    }

    fun updatePricingById(pricingDto1: Long, pricingDto: PricingDto): PricingDto {
        getPricingById(pricingDto.pricingId)
        pricingRepo.save(pricingMapper.toEntity(pricingDto))
        return pricingDto
    }

    override fun deletePricingById(pricingId: Long) {
        getPricingById(pricingId)
        pricingRepo.deleteById(pricingId)
    }


}
//====================================== without dto


//    fun getAllPricing(): ResponseEntity<List<Pricing>> {
//        val pricing = pricingRepo.findAll()
//        if (pricing.isEmpty()) {
//            return ResponseEntity<List<Pricing>>(HttpStatus.NO_CONTENT)
//        }
//        return ResponseEntity<List<Pricing>>(pricing, HttpStatus.OK)
//    }
//
//    fun createPricing(pricing: Pricing): ResponseEntity<Pricing> {
//        val pricing= pricingRepo.save(pricing)
//        if(pricing!=null)
//        {
//            return ResponseEntity<Pricing>(HttpStatus.BAD_REQUEST)
//        }
//        return ResponseEntity<Pricing>(HttpStatus.CREATED)
//    }
//
//    fun updatePricingById(pricingId: Long, pricing: Pricing): ResponseEntity<Pricing> {
//        try {
//            return pricingRepo.findById(pricingId).map {
//                    pricingDetails ->
//                val updatedPricing: Pricing = pricingDetails.copy(
//                    pricingName = pricing.pricingName,
//                    pricingAmount = pricing.pricingAmount,
//                    modifiedDate = LocalDate.now(),
//                    isActive = pricing.isActive,
//                    productId = pricing.productId
//                )
//                ResponseEntity(pricingRepo.save(updatedPricing), HttpStatus.OK)
//            }.orElse(ResponseEntity<Pricing>(HttpStatus.INTERNAL_SERVER_ERROR))
//        }
//        catch (e: Exception){
//            throw Exception(e.message)
//        }
//
//    }
//
//    fun deletePricingById(pricingId: Long): ResponseEntity<Void> {
//       try {
//           val pricing = pricingRepo.findById(pricingId)
//           if (pricing.isPresent) {
//               pricingRepo.deleteById(pricingId)
//               return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
//           }
//
//           return ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR)
//       }catch (e:Exception){
//           throw Exception(e.message)
//
//       }
//    }
//
//}