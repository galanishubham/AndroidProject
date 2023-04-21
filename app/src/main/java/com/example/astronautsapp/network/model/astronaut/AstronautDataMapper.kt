package com.example.astronautsapp.network.model.astronaut

import com.example.astronautsapp.domain.model.Astronaut
import com.example.astronautsapp.domain.DomainMapper

class AstronautDataMapper : DomainMapper<AstronautApiModel, Astronaut> {
    override fun mapToDomainModel(data: AstronautApiModel): Astronaut {
        return Astronaut(
            id = data.id,
            age = data.age,
            name = data.name,
            profileImg = data.profile_image_thumbnail
        )
    }

    fun fromDataList(initial: List<AstronautApiModel>): List<Astronaut>{
        return initial.map { mapToDomainModel(it) }
    }

}

