package com.example.todoapp2

import android.app.Application
import androidx.room.Room
import com.example.todoapp2.Data_Layer.database.dbDatabase
import com.example.todoapp2.Data_Layer.repository.repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DI_module {
    @Provides
    @Singleton
    fun get_DbInstance(application: Application): dbDatabase{
        return Room.databaseBuilder(application,dbDatabase::class.java,db_file).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun get_repo(DBInstance:dbDatabase): repo {
        return repo(DBInstance)
    }
}