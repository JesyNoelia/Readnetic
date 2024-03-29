package com.acoders.readnetic.di

import com.acoders.readnetic.data.network.BookApiClientNYT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("NYT")
    fun provideRetrofitNYT(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/books/v3/lists/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideBookApiClientNYT(@Named("NYT") retrofit: Retrofit): BookApiClientNYT {
        return retrofit.create(BookApiClientNYT::class.java)
    }
}
