package com.duongdk.hust.it4785.phonebook

import com.github.javafaker.Faker

data class phoneModel(
    var id: Int,
    var hoTen: String,
    var soDienThoai: String,
    var email: String
)

fun createFakePhoneData(): List<phoneModel> {
    val faker = Faker()

    val phoneList = mutableListOf<phoneModel>()

    for (i in 1..15) {
        val id = i
        val hoTen = faker.name().fullName()
        val soDienThoai = faker.phoneNumber().cellPhone()
        val email = faker.internet().emailAddress()

        val phone = phoneModel(id, hoTen, soDienThoai, email)
        phoneList.add(phone)
    }

    return phoneList
}