package com.ase.pokedex.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ase.pokedex.R
import com.ase.pokedex.Screen
import com.ase.pokedex.common.ex.getIcon
import com.ase.pokedex.data.model.Pokemon
import com.ase.pokedex.ui.common.LoadingIndicator
import com.ase.pokedex.ui.common.PokeTopAppBar
import com.ase.pokedex.ui.theme.PokeBackgroundLight
import com.ase.pokedex.ui.theme.PokeGray
import com.ase.pokedex.ui.theme.PokeGrayLight

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
    vm: HomeViewModel = viewModel(),
    onPokemonClick: (Pokemon) -> Unit,
) {
    val homeState = rememberHomeState()
    val state by vm.state.collectAsState()

    Screen(
        scrollBehavior = homeState.scrollBehavior,
        topAppBar = { scrollBehavior ->
            PokeTopAppBar(
                title = "Pokedex",
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        if (state.loading) {
            LoadingIndicator()
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize(),
            state = homeState.lazyGridState
        ) {
            items(state.pokemon, key = { it.id }) { pokemon ->
                PokemonItem(pokemon) { onPokemonClick(pokemon) }
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon, onPokemonClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.fillMaxWidth(),
        onClick = onPokemonClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.avatar)
                    .crossfade(true)
                    .build(),
                contentDescription = pokemon.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.extraSmall)
                    .background(PokeBackgroundLight)
                    .padding(8.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = getIcon(R.drawable.ic_pokeball),
                        contentDescription = null,
                        tint = PokeGrayLight,
                        modifier = Modifier.size(10.dp)
                    )
                    Text(
                        text = pokemon.id.toString().padStart(3, '0'),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 1.sp,
                        color = PokeGrayLight,
                    )
                }
                Text(
                    text = pokemon.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 14.sp,
                    color = PokeGray,
                )
            }
        }
    }
}
