package com.ase.pokedex.common.ex

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun getIcon(@DrawableRes iconId: Int) = ImageVector.vectorResource(iconId)
