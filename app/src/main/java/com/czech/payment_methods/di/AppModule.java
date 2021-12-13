package com.czech.payment_methods.di;

import com.czech.payment_methods.network.ApiService;
import com.czech.payment_methods.network.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    private static final String base_url = "https://raw.githubusercontent.com/optile/checkout-android/develop/shared-test/lists/";

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public Repository provideRepository(ApiService apiService)  {
        return new Repository(apiService);
    }
}
