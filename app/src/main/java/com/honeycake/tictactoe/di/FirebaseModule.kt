package com.honeycake.tictactoe.di

import com.honeycake.tictactoe.data.Firebase
import com.honeycake.tictactoe.data.FirebaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideFirebase(): Firebase {
        return FirebaseImpl()
    }
}