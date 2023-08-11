package com.shegs.idme.di

import com.shegs.idme.repositories.InfoRepository
import com.shegs.idme.viewModels.InfoViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideInfoViewModel(infoRepository: InfoRepository): InfoViewModel {
        return InfoViewModel(infoRepository)
    }
}
