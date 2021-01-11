package com.udacity.shoestore.mapper

import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeDataView

object ShoeDataMapper {

    fun mapToViewData(shoe: Shoe) =
        ShoeDataView(
            name = shoe.name,
            size = shoe.size.toString(),
            company = shoe.company,
            description = shoe.description
        )

    fun mapFromViewData(
        name: String,
        company: String,
        size: String,
        description: String
    ) = Shoe(
        name = name.ifEmpty { "Empty Name" },
        company = company.ifEmpty { "Company Name" },
        size = size.ifEmpty { "0" }.toInt(),
        description = description
    )
}
