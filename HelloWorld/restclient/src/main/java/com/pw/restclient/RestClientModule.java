package com.pw.restclient;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class RestClientModule {

    private final Context context;

    public RestClientModule(Context context) {
        this.context = context;
    }

    @Provides
    @Named("baseUrl")
    public String provideBaseUrl() {
        return context.getString(R.string.base_url);
    }

    @Provides
    public OkHttpClient provideOkHttpClient() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }

    @Provides
    public Retrofit provideRetrofit(OkHttpClient httpClient, @Named("baseUrl") String baseUrl) {
        final RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
    }

    @Provides
    public RestApi provideRestApi(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }

    @Provides
    public RestApiClient provideRestApiClient(RestApi restApi) {
        return new RestApiClientImpl(restApi);
    }

    @Provides
    @Singleton
    public RestApiMediator provideRestApiMediator(RestApiClient restApiClient) {
        return new RestApiMediatorImpl(restApiClient);
    }
}
