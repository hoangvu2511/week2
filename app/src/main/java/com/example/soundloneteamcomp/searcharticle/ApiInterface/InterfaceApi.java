package com.example.soundloneteamcomp.searcharticle.ApiInterface;

import android.support.annotation.Nullable;

import com.example.soundloneteamcomp.searcharticle.SearchArticle.ListSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceApi {

    @GET("articlesearch.json")
    Call<ListSearch> getListArticle();

    @GET("articlesearch.json")
    Call<ListSearch> searchFull(@Nullable @Query("begin_date") String begin,
                                @Nullable @Query("sort") String sort,
                                @Nullable @Query("fq") String newVal,
                                @Nullable @Query("q") String search,
                                @Query("page") int page);

    @GET("articlesearch.json")
    Call<ListSearch> searchByWord(@Query("q") String search);

    @GET("articlesearch.json")
    Call<ListSearch> searchByFilter(@Nullable @Query("begin_date") String begin,
                                    @Nullable @Query("sort") String sort,
                                    @Nullable @Query("fq") String newVal,
                                    @Query("page") int page);
}
