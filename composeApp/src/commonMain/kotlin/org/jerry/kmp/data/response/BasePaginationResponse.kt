package org.jerry.kmp.data.response

import kotlinx.serialization.Serializable

@Serializable
data class BasePaginationResponse<T>(
    val results: List<T> = emptyList(),
    val limit: Int = 0,
    val offset: Int = 0,
    val total: Int = 0,
)