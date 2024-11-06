package org.jerry.kmp.data.repository.impl

import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend

import dev.mokkery.mock
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.jerry.kmp.MockStubs
import org.jerry.kmp.data.database.dao.FavouriteDao
import org.jerry.kmp.utilities.Resource
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FavouriteRepositoryImplTest {
    private lateinit var dao: FavouriteDao
    private lateinit var repository: FavouriteRepositoryImpl

    @BeforeTest
    fun setup() {
        dao = mock<FavouriteDao> {}
        repository = FavouriteRepositoryImpl(dao)
    }

    @Test
    fun `test FavouriteRepositoryImpl getFavourites successfully`() = runTest {
        //Given
        everySuspend { dao.getAllFavourites() } returns MockStubs.mockFavourites

        //when
        val actual = repository.getFavourites()

        assertEquals(MockStubs.mockFavourites.size, actual.size)
    }

    @Test
    fun `test FavouriteRepositoryImpl getFavourites error`() = runTest {
        //Given
        everySuspend { dao.getAllFavourites() } throws MockStubs.mockNormalException

        //when
        var isError = false
        try {
            repository.getFavourites()
        } catch (e: Exception){
            isError = true
        }

        assertEquals(true, isError)

    }
}