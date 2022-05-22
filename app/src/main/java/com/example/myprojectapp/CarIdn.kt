package com.example.myprojectapp


data class CarIdn(
    val id : String?,
    val plate : String,
    val name : String
){
    constructor(): this("","",""){

    }
}