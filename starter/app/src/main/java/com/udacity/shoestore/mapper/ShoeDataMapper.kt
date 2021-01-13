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
        shoe: ShoeDataView
    ) = Shoe(
        name = shoe.name.ifEmpty { "Empty Name" },
        company = shoe.company.ifEmpty { "Company Name" },
        size = shoe.size.ifEmpty { "0" }.toInt(),
        description = shoe.description
    )
}
