package com.honeycake.tictactoe.di

import com.honeycake.tictactoe.domain.repository.XORepository
import com.honeycake.tictactoe.domain.repository.XORepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsRepository(repository: XORepositoryImpl): XORepository
}