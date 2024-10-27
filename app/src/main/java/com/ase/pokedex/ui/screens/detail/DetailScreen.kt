package com.ase.pokedex.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.ase.pokedex.R
import com.ase.pokedex.Screen
import com.ase.pokedex.common.ex.getIcon
import com.ase.pokedex.data.model.PokeType
import com.ase.pokedex.ui.common.LoadingIndicator
import com.ase.pokedex.ui.common.PokeTopAppBar
import com.ase.pokedex.ui.theme.PokeBackgroundLight
import com.ase.pokedex.ui.theme.PokeGray
import com.ase.pokedex.ui.theme.PokeGrayLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    vm: DetailViewModel = viewModel(),
    onBack: () -> Unit = {},
) {
    val detailState = rememberDetailState()
    val state by vm.state.collectAsState()

//    detailState.ShowMessageEffect(message = state.message) { vm.onMessageShown() }

    Screen(scrollBehavior = detailState.scrollBehavior, topAppBar = { scrollBehavior ->
        PokeTopAppBar(
            title = state.pokemon?.name ?: "", navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back"
                    )
                }
            }, scrollBehavior = scrollBehavior
        )
    }) {
        if (state.loading) {
            LoadingIndicator()
        }

        state.pokemon?.let { pokemon ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 24.dp,
                            vertical = 32.dp
                        )
                ) {
                    AsyncImage(
                        model = pokemon.image,
                        contentDescription = pokemon.name,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .height(165.dp)
                            .aspectRatio(1f)
                            .clip(MaterialTheme.shapes.large)
                            .background(PokeBackgroundLight)
                            .padding(12.dp)
                    )
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = getIcon(R.drawable.ic_pokeball),
                                contentDescription = null,
                                tint = PokeGrayLight,
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = pokemon.id.toString().padStart(3, '0'),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 14.sp,
                                color = PokeGrayLight,
                            )
                        }

                        Text(
                            text = pokemon.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 24.sp,
                            color = PokeGray,
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            pokemon.types.forEach { type ->
                                PokemonTypeItem(type)
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .clip(MaterialTheme.shapes.large)
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                }
            }
        }
    }
}

@Composable
fun PokemonTypeItem(type: PokeType) {
    Text(
        text = type.value.uppercase(),
        fontSize = 9.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 9.sp,
        color = Color.White,
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraSmall)
            .background(type.color)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}
