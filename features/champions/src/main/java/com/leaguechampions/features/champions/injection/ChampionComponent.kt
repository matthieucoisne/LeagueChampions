package com.leaguechampions.features.champions.injection

import com.leaguechampions.features.champions.presentation.championdetails.ChampionDetailsFragment
import com.leaguechampions.features.champions.presentation.ChampionsActivity
import com.leaguechampions.features.champions.presentation.champions.ChampionsFragment
import com.leaguechampions.libraries.core.injection.scope.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ChampionComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): ChampionComponent
    }

    fun inject(activity: ChampionsActivity)
    fun inject(fragment: ChampionsFragment)
    fun inject(fragment: ChampionDetailsFragment)
}

interface ChampionComponentProvider {
    fun provideChampionComponent(): ChampionComponent
}
