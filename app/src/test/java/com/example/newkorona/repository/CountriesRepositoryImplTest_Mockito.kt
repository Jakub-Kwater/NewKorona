package com.example.newkorona.repository

import com.example.newkorona.ApiService
import com.example.newkorona.Country
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

import org.junit.Assert.*
import rx.Single

class CountriesRepositoryImplTestMockito {

    private val apiService: ApiService = mock()
    private val repositoryImpl = CountriesRepositoryImpl(api = apiService)

    @Test
    fun `fetchAllCountriesSingle test`() {

            //given
            val countryA = Country("Poland",23,321,2,3,4,5,6,8,8,2,3)
            val countryB = Country("USA",23,321,2,3,4,5,6,8,8,2,3)
            val countryC = Country("UK",23,321,2,3,4,5,6,8,8,2,3)
            val testList = listOf<Country>(countryA,countryB,countryC)

//            whenever(apiService.fetchAllCountriesSingle()) doReturn Single.just(testList) //well it did not work

            doReturn(Single.just(testList)).`when`(repositoryImpl.fetchAllCountriesSingle())


            //when
            val testObserver = repositoryImpl.fetchAllCountriesSingle().test()

            //then
            testObserver.assertNoErrors().assertValue(testList)
    }
}