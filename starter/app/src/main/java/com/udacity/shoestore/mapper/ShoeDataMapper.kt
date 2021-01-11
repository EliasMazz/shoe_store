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
}
