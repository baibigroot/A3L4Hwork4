package com.geek.a3l4hwork4.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static HerokuApi api;

    public static HerokuApi getApi() {
        if (api == null) {
            api = provideRetrofit();
        }
        return api;
    }

    private static HerokuApi provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://android-3-mocker.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HerokuApi.class);

    }

/*

    private final OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(provideLoggingInterceptor()).build();

    private Interceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

    }

    private final Retrofit gRetrofit = new Retrofit.Builder()
            .baseUrl("https://ghibliapi.herokuapp.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final Retrofit hRetrofit = new Retrofit.Builder()
            .baseUrl("https://android-3-mocker.herokuapp.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Ghibli provideGhibli() {
        return gRetrofit.create(Ghibli.class);
    }

    public HerokuApi provideHeroku() {
        return gRetrofit.create(HerokuApi.class);
    }
*/

}