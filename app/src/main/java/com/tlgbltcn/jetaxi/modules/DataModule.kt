package com.tlgbltcn.jetaxi.modules

import com.tlgbltcn.jetaxi.data.repository.JeTaxiRepositoryImp
import com.tlgbltcn.jetaxi.domain.repository.JeTaxiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindJeTaxiRepository(repository: JeTaxiRepositoryImp): JeTaxiRepository
}
