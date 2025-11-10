package com.example.userapplication.di

import com.example.userapplication.data.remote.ApiService
import com.example.userapplication.data.repository.UserRepositoryImpl
import com.example.userapplication.domain.repository.UserRepository
import com.example.userapplication.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesApiService(): ApiService {
        return Retrofit
            .Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(apiService: ApiService): UserRepository {
        return UserRepositoryImpl(apiService)
    }
}