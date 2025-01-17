package com.ase.pokedex

import android.app.Application
import com.ase.pokedex.domain.pokemon.PokemonDomainModule
import com.ase.pokedex.feature.detail.DetailFeatureModule
import com.ase.pokedex.feature.home.HomeFeatureModule
import com.ase.pokedex.framework.pokemon.PokemonFrameworkModule
import com.ase.pokedex.framework.pokemon.dataSourceFrameworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.ksp.generated.module

class PokeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@PokeApp)
            modules(
                HomeFeatureModule().module,
                DetailFeatureModule().module,
                dataSourceFrameworkModule,
                PokemonFrameworkModule().module,
                PokemonDomainModule().module,
            )
        }
    }
}
