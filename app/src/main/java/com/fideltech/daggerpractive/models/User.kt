package com.fideltech.daggerpractive.models

data class User(
    val id: Int,
    val address: Address? = null,
    val email: String = "",
    val name: String = "",
    val username: String = ""
)


data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)

data class Geo(
    val lat: String,
    val lng: String
)