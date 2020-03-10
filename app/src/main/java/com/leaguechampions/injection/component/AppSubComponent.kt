package com.leaguechampions.injection.component

import com.leaguechampions.features.champions.injection.ChampionComponent
import dagger.Module

@Module(subcomponents = [ChampionComponent::class])
class AppSubcomponents
