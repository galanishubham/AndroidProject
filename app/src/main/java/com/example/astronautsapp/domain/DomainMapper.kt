package com.example.astronautsapp.domain

interface DomainMapper <NetworkModel, DomainModel>{

    // map Network data to domain model
    fun mapToDomainModel(data: NetworkModel): DomainModel

    // if need to send data to server
    // fun mapFromDomainModel(domainModel: DomainModel): T
}