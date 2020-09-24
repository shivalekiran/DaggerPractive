package com.fideltech.daggerpractive.ui.auth

data class User(
    val address: Address,
    val email: String,
    val id: Int,
    val name: String,
    val username: String
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