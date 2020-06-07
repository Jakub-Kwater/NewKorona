package com.example.newkorona.filter

import com.example.newkorona.Country
import org.junit.Assert.*
import org.junit.Test

class CountriesListFilterImplTest{

    val countriesListFilter = CountriesListFilterImpl()
    val countryA = Country("Poland",23,321,2,3,4,5,6,8,8,2,3)
    val countryB = Country("USA",23,321,2,3,4,5,6,8,8,2,3)
    val countryC = Country("UK",23,321,2,3,4,5,6,8,8,2,3)
    val testList = listOf<Country>(countryA,countryB,countryC)


    @Test
    fun `Full string filter test`(){
        //given
        val testQuery = "Poland"
        //when
        val resultOfTestFilter = countriesListFilter.filter(testList,testQuery)
        //then
        assertEquals(listOf<Country>(countryA),resultOfTestFilter)
    }

    @Test
    fun `Partial sting filter test`(){
        //given
        val testQuery = "U"
        //when
        val resultOfTestFilter = countriesListFilter.filter(testList,testQuery)
        //then
        assertEquals(listOf(countryB,countryC), resultOfTestFilter)
    }

    @Test
    fun `Query "ignore case" test`(){
        //given
        val testQuery = "polAND"
        //when
        val resultOfTestFilter = countriesListFilter.filter(testList, testQuery)
        //then
        assertEquals(listOf(countryA), resultOfTestFilter)
    }

}