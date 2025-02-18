package com.ase.pokedex.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ase.pokedex.ui.theme.PokeBackground
import com.ase.pokedex.ui.theme.PokeColor
import com.ase.pokedex.ui.theme.PokeGray

const val ERROR_INDICATOR_TAG = "ErrorIndicator"

@Composable
@Preview
fun ErrorIndicator(
    modifier: Modifier = Modifier.testTag(ERROR_INDICATOR_TAG),
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(PokeBackground)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.error),
                contentDescription = null,
                modifier = Modifier.size(160.dp)
            )
            Text(text = "PokeError!", style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = PokeGray
            ))
        }
    }
}
