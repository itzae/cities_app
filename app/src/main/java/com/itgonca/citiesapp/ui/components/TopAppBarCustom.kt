package com.itgonca.citiesapp.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.itgonca.citiesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCustom(
    title: String = "",
    navigationIcon: ImageVector? = null,
    onNavigate: () -> Unit = {}
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            navigationIcon?.let {
                IconButton(onClick = onNavigate) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = stringResource(R.string.top_app_bar_navigation_icon_cd)
                    )
                }
            }
        }
    )
}