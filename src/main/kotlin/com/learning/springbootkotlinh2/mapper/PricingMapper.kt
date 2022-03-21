package com.learning.springbootkotlinh2.mapper

import com.learning.springbootkotlinh2.dto.PricingDto
import com.learning.springbootkotlinh2.entity.Pricing
import org.springframework.stereotype.Component


@Component
class PricingMapper: Mapper<PricingDto, Pricing> {

    override fun fromEntity(entity: Pricing): PricingDto {
        return PricingDto(
            entity.pricingId,
            entity.pricingName,
            entity.pricingAmount,
            entity.createdDate,
            entity.modifiedDate,
            entity.isActive,
            entity.productId
        )

    }

    override fun toEntity(domain: PricingDto): Pricing = Pricing (
        domain.pricingId,
        domain.pricingName,
        domain.pricingAmount,
        domain.createdDate,
        domain.modifiedDate,
        domain.isActive,
        domain.productId
    )
}