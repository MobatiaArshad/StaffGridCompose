package com.mobileapp.staffgridcompose.api.repository

import com.mobileapp.staffgridcompose.api.StaffXApi
import javax.inject.Inject

class StaffXRepository @Inject constructor(
    private val api: StaffXApi
) {

//    suspend fun getCurrencies(): CurrenciesResponse = api.getCurrencies()

//    suspend fun getCurrentRate(base: String,countries: String): CurrentRateResponse = api.getCurrentRates(base = base, symbol = countries)
}