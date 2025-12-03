package com.example.callapi.data.remote

import com.example.callapi.data.model.Product
import retrofit2.http.GET

interface ProductApiService {
    @GET("m1/890655-872447-default/v2/product")
    suspend fun getProduct(): Product
}
