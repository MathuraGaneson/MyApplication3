package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface retro {

   interface GetValidUser{
        @GET
        Call<String> getvalidation(@Url String url);
    }

    interface GetValidbarcode{
        @GET
        Call<String> getvalidationbarcode(@Url String url);
    }

    interface  GetValidtool{
        @GET
        Call<String> getvalidinfo(@Url String url);
    }
}
