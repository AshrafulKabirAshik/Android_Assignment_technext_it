package com.ashrafulkabirashik.ashrafulkabirashik.response;

import com.ashrafulkabirashik.ashrafulkabirashik.models.Blog;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlogResponse {
    @SerializedName("blogs")
    @Expose()
    private List<Blog> blogList;

    public List<Blog> getBlogList() {
        return blogList;
    }
}
