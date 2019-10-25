package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
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

    interface GetValidProcess{
       @GET
        Call<String> getvalidprocess(@Url String url);
    }

    interface GetValidMachineId{
       @GET
        Call<String> getvalidmachineid(@Url String url);
    }

    interface PutCheckin{
       @PUT
        Call<String>putcheckin(@Url String url);
    }

//    public interface  GetInfo{
//        @GET
//        Call<tool_info.JsonResponse> getJson(@Url String url);
//    }

    interface GetInsertdata{
       @GET
       Call<String>getinsertdata(@Url String url);
    }
}
