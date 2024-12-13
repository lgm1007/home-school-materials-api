package org.freewheelin.homeschoolmaterials.api.error

data class ErrorBody(
    val message: String,
    val status: Int
) {
}