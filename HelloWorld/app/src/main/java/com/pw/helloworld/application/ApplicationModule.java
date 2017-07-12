package com.pw.helloworld.application;

import com.pw.helloworld.destination1.Destination1Fragment;
import com.pw.helloworld.users.UserApi;
import com.pw.helloworld.users.UserApiClient;
import com.pw.helloworld.users.UserApiClientImpl;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
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
    static OkHttpClient provideOkHttpClient() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }

    @Provides
    static Retrofit provideRetrofit(OkHttpClient httpClient, @Named("baseUrl") String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    static UserApi provideUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

    @Provides
    static UserApiClient provideUserApiClient(UserApi userApi) {
        return new UserApiClientImpl(userApi);
    }

    @ContributesAndroidInjector
    abstract Destination1Fragment contributeDestination1FragmentInjector();
}
