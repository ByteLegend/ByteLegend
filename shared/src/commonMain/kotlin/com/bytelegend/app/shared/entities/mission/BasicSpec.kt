package com.bytelegend.app.shared.entities.mission

data class Pagination<T>(
    val items: List<T>,
    val totalPages: Int,
    val pageNumber: Int,
    val pageSize: Int
)


