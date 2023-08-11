package com.shegs.idme.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, ViewModelModule::class])
interface ApplicationComponent {
}