package com.bytelegend.app.shared.entities.mission


interface TutorialSpec {
    val id: String
    val title: String
    val locales: List<String>
    val labels: List<String>
}

interface Pagination<T> {
    val items: List<T>
    val totalPages: Int
    val currentPage: Int
    val pageSize: Int
}


