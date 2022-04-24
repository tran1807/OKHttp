package com.example.okhttp.data.remote;

import com.example.okhttp.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SongService {
    @GET("shop")
    Call<List<Product>> listProducts();

    @DELETE("shop/{id}")
    Call<Void> deleteProduct(@Path("id") int id);
}
