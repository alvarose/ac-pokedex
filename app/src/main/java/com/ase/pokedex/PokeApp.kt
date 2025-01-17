package com.ase.pokedex

import android.app.Application
import com.ase.pokedex.domain.pokemon.pokemonDomainModule
import com.ase.pokedex.feature.detail.detailFeatureModule
import com.ase.pokedex.feature.home.homeFeatureModule
import com.ase.pokedex.framework.pokemon.dataSourceFrameworkModule
import com.ase.pokedex.framework.pokemon.pokemonFrameworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class PokeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@PokeApp)
            modules(
                appModule,
                homeFeatureModule,
                detailFeatureModule,
                dataSourceFrameworkModule,
                pokemonFrameworkModule,
                pokemonDomainModule,
                pokemonFrameworkModule,
            )
        }
    }
}

val appModule = module {
//    single {
//        named("apiUrl") { BuildConfig.API_URL }
//    }
}
