package com.example.consumirwsretrofit.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface InterfazL {
    @Headers({"x-rapidapi-key: c1ff95d264msh5b7960c362d7596p1212f5jsn1f346837947e","x-rapidapi-host:judge0.p.rapidapi.com"})
    @GET("languages")
    Call<List<LenguajesP>> getLanguages();

}
