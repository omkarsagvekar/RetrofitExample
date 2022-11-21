package com.example.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderApi {
    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/1/comments")
    Call<List<Comment>> getComment();

}
