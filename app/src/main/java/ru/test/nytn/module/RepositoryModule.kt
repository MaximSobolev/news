package ru.test.nytn.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.test.nytn.repository.NewsRepository
import ru.test.nytn.repository.NewsRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindsGameRepository(impl : NewsRepositoryImpl) : NewsRepository
}