package com.example.chatapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {

  @Provides
    fun provideSharedPrefObj(@ApplicationContext context: Context):SharedPreferences{
      return context.getSharedPreferences("ChatPref",Context.MODE_PRIVATE)
    }
}