package com.example.soundloneteamcomp.searcharticle.listSearchArticle.model;

import android.support.annotation.Nullable;
import android.util.Log;

import com.example.soundloneteamcomp.searcharticle.ApiInterface.InterfaceApi;
import com.example.soundloneteamcomp.searcharticle.SearchArticle.ListSearch;
import com.example.soundloneteamcomp.searcharticle.utils.CallApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchArticleModelImp implements SearchArticleModel {

    private InterfaceApi interfaceApi;
    public SearchArticleModelImp(){interfaceApi = CallApi.createService();}


    @Override
    public void getArticle(final DataListener listener)
    {
        interfaceApi.getListArticle().enqueue(new Callback<ListSearch>() {
            @Override
            public void onResponse(Call<ListSearch> call, Response<ListSearch> response) {
                if (response.body() == null)
                    Log.e("Error","loi kia");
                else{
                    Log.e("Success","pass");
                    ListSearch search = response.body();
                    listener.onResponse(search.getResponse().getDocs());
                }
            }

            @Override
            public void onFailure(Call<ListSearch> call, Throwable t) {

            }
        });
    }

    @Override
    public void searchByBody(final DataListener listener, String search) {
        interfaceApi.searchByWord(search).enqueue(new Callback<ListSearch>() {
            @Override
            public void onResponse(Call<ListSearch> call, Response<ListSearch> response) {
                if (response.body()!=null){
                    ListSearch list = response.body();
                    listener.onResponse(list.getResponse().getDocs());
                }
            }

            @Override
            public void onFailure(Call<ListSearch> call, Throwable t) {

            }
        });
    }

    @Override
    public void searchFull(final DataListener listener, String beginDate, String sort, String fp, String q,int page) {
        interfaceApi.searchFull(beginDate,sort,fp,q,page).enqueue(new Callback<ListSearch>() {
            @Override
            public void onResponse(Call<ListSearch> call, Response<ListSearch> response) {
                if (response.body()!=null){
                    ListSearch list = response.body();
                    listener.onResponse(list.getResponse().getDocs());
                }
                else
                    listener.onError();
            }

            @Override
            public void onFailure(Call<ListSearch> call, Throwable t) {
                listener.onError();
            }
        });
    }

    @Override
    public void searchByFilter(final DataListener listener, @Nullable String begin, @Nullable String sort, @Nullable String newVal,int page) {
        interfaceApi.searchByFilter(begin,sort,newVal,page).enqueue(new Callback<ListSearch>() {
            @Override
            public void onResponse(Call<ListSearch> call, Response<ListSearch> response) {
                if (response.body()!=null){
                    ListSearch list = response.body();
                    listener.onResponse(list.getResponse().getDocs());
                }
            }

            @Override
            public void onFailure(Call<ListSearch> call, Throwable t) {

            }
        });
    }


}
