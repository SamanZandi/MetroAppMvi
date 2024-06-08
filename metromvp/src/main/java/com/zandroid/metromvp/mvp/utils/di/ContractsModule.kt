package com.zandroid.metromvp.mvp.utils.di


import androidx.fragment.app.Fragment
import com.zandroid.metromvp.mvp.ui.line.LineContracts
import com.zandroid.metromvp.mvp.ui.station.StationContracts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ContractsModule {
    @Provides
    fun lineView(fragment: Fragment):LineContracts.View{
        return fragment as LineContracts.View
    }

    @Provides
    fun stationView(fragment: Fragment):StationContracts.View{
        return fragment as StationContracts.View
    }

/*    @Provides
    fun detailView(fragment: Fragment):DetailContracts.View{
        return fragment as DetailContracts.View
    }*/
}