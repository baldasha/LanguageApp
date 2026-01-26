package com.example.languageapp.comp.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.languageapp.comp.data.repository.ThemeRepositoryImpl
import com.example.languageapp.comp.domain.repository.ThemeRepository
import com.example.languageapp.comp.domain.validation.ValidateEmail
import com.example.languageapp.comp.domain.validation.ValidateFirstName
import com.example.languageapp.comp.domain.validation.ValidateLastName
import com.example.languageapp.comp.domain.validation.ValidatePassword
import com.example.languageapp.comp.domain.validation.ValidateRepeatedPassword
import com.example.languageapp.comp.domain.validation.ValidateTermsAccepted
import com.example.languageapp.core.auth.AuthStorage
import com.example.languageapp.core.auth.AuthStorageImpl
import com.example.languageapp.core.data.OnBoardingPref
import com.example.languageapp.core.data.OnBoardingSharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideThemeRepository(sharedPreferences: SharedPreferences) : ThemeRepository{
        return ThemeRepositoryImpl(sharedPreferences = sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context : Context) : SharedPreferences{
        return context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideValidateFirstName(): ValidateFirstName {
        return ValidateFirstName()
    }

    @Provides
    @Singleton
    fun provideValidateLastName(): ValidateLastName {
        return ValidateLastName()
    }

    @Provides
    @Singleton
    fun provideValidateEmail(): ValidateEmail {
        return ValidateEmail()
    }

    @Provides
    @Singleton
    fun provideValidatePassword(): ValidatePassword {
        return ValidatePassword()
    }

    @Provides
    @Singleton
    fun provideValidateRepeatedPassword(): ValidateRepeatedPassword {
        return ValidateRepeatedPassword()
    }

    @Provides
    @Singleton
    fun provideValidateTermsAccepted(): ValidateTermsAccepted {
        return ValidateTermsAccepted()
    }

    @Provides
    @Singleton
    fun provideAuthStorage(sharedPreferences: SharedPreferences) : AuthStorage {
        return AuthStorageImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideOnBoardingPref(sharedPreferences: SharedPreferences) : OnBoardingPref{
        return OnBoardingSharedPref(sharedPreferences)
    }

}