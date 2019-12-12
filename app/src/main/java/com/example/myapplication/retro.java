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

    interface GetValidCategory{
        @GET
        Call<String> getvalidcategory(@Url String url);
    }

    interface GetValidMachineId{
       @GET
        Call<String> getvalidmachineid(@Url String url);
    }

    interface PutCheckin{
       @PUT
        Call<String>putcheckin(@Url String url);
    }

    interface Getcheckin{
        @GET
        Call<String>getvalidcheckin(@Url String url);
    }

    interface GetValidSpec{
        @GET
        Call<String>getvalidspec(@Url String url);
    }


//    public interface  GetInfo{
//        @GET
//        Call<tool_info.JsonResponse> getJson(@Url String url);
//    }

    interface GetInsertdata{
       @GET
       Call<String>getinsertdata(@Url String url);
    }

    interface GetRegisterProcess{
        @GET
        Call<String>getregisterprocess(@Url String url);
    }

    interface GetVendor{
        @GET
        Call<String>getvendor(@Url String url);
    }

    interface GetPm{
        @GET
        Call<String>getpm(@Url String url);
    }

    interface GetStatus{
        @GET
        Call<String>getstatus(@Url String url);
    }

    interface GetRack{
        @GET
        Call<String>getrack(@Url String url);
    }

    interface GetRow{
        @GET
        Call<String>getrow(@Url String url);
    }

    interface GetSection{
        @GET
        Call<String>getsection(@Url String url);
    }

    interface GetNewRegister{
        @GET
        Call<String>getregister(@Url String url);
    }

    interface GetNewProcess{
        @GET
        Call<String>getnewprocess(@Url String url);
    }

    interface GetNewCategory{
        @GET
        Call<String>getcategory(@Url String url);
    }

    interface GetNewSpec{
        @GET
        Call<String>getspec(@Url String url);
    }

    interface GetPIC{
        @GET
        Call<String>getpic(@Url String url);
    }

    interface GetResp{
        @GET
        Call<String>getresp(@Url String url);
    }

}
