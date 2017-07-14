package com.pw.helloworld.application;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.pw.helloworld.destination1.Destination1Contract;
import com.pw.helloworld.destination1.Destination1Fragment;
import com.pw.helloworld.destination1.Destination1Model;
import com.pw.helloworld.rest.RestApi;
import com.pw.helloworld.rest.RestApiClient;
import com.pw.helloworld.rest.RestApiClientImpl;
import com.pw.helloworld.rest.RestApiMediator;
import com.pw.helloworld.rest.RestApiMediatorImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class ApplicationModule {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Provides
    @Named("baseUrl")
    static String provideBaseUrl() {
        return BASE_URL;
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient httpClient, @Named("baseUrl") String baseUrl) {
        final RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
    }

    @Provides
    @Singleton
    static RestApi provideRestApi(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }

    @Provides
    @Singleton
    static RestApiClient provideRestApiClient(RestApi restApi) {
        return new RestApiClientImpl(restApi);
    }

    @Provides
    @Singleton
    static RestApiMediator provideRestApiMediator(RestApiClient restApiClient) {
        return new RestApiMediatorImpl(restApiClient);
    }

    @Provides
    static Destination1Contract.Model provideDestination1Model(RestApiMediator restApiMediator) {
        return new Destination1Model(restApiMediator);
    }

    @ContributesAndroidInjector
    abstract Destination1Fragment contributeDestination1FragmentInjector();
}
