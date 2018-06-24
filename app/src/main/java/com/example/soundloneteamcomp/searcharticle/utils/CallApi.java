package com.example.soundloneteamcomp.searcharticle.utils;

import com.example.soundloneteamcomp.searcharticle.ApiInterface.InterfaceApi;
import com.example.soundloneteamcomp.searcharticle.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallApi {
    private static InterfaceApi inter;

    private static String url = "8ed8422c95fc47a7bce1af60885368b9";
    private static String BASE_url = "https://api.nytimes.com/svc/search/v2/";

    public static Retrofit builder(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_url)
                .client(client())
                .build();
    }

    private static OkHttpClient client() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url()
                                .newBuilder()
                                .addQueryParameter("api_key", BuildConfig.API_KEY)
                                .build();
                        request = request.newBuilder()
                                .url(url)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    public static InterfaceApi createService(){
        return builder().create(InterfaceApi.class);
    }


}
