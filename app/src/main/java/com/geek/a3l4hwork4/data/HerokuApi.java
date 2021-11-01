package com.geek.a3l4hwork4.data;

import com.geek.a3l4hwork4.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HerokuApi {
    @GET("posts")
    Call<List<Post>> getPosts();

    @POST("posts")
    Call<Post> createMocker(@Body Post model);

    @PUT("posts/{id}")
    Call<Post> update(@Path("id") String id, @Body Post model);

    @DELETE("posts/{id}")
    Call<Post> deleteMockerModel(
            @Path("id") Integer id
    );}