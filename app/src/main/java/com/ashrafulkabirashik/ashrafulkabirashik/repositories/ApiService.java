package com.ashrafulkabirashik.ashrafulkabirashik.repositories;

import com.ashrafulkabirashik.ashrafulkabirashik.response.BlogResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/sohel-cse/simple-blog-api/db")
    Call<BlogResponse> getBlogList();
}
