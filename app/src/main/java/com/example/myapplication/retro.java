package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class retro {

    public interface GetValidUser{
        @GET
        Call<String> getvalidation(@Url String url);
    }
}
