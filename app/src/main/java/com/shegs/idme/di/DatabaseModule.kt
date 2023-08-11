package com.shegs.idme.di

import android.content.Context
import androidx.room.Room
import com.shegs.idme.database.AppDatabase
import com.shegs.idme.model.card.CardDAO
import com.shegs.idme.model.info.InfoDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .build()
    }

    @Provides
    fun provideCardDAO(database: AppDatabase): CardDAO {
        return database.cardDAO()
    }

    @Provides
    fun provideInfoDAO(database: AppDatabase): InfoDAO {
        return database.InfoDAO()
    }

}