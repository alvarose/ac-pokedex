package com.ase.pokedex.feature.detail

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModelOf

val detailFeatureModule = module {
    viewModelOf(::DetailViewModel)
}
