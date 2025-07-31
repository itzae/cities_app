package com.itgonca.citiesapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.itgonca.citiesapp.R
import com.itgonca.citiesapp.ui.theme.CitiesAppTheme

/**
 * This composable allows you to display a loader
 */
@Composable
fun LoadingScreen(modifier: Modifier= Modifier, message:String) {
    val composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition = composition.value,
        iterations = LottieConstants.IterateForever
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(composition = composition.value, progress = { progress })
        Text(
            modifier = Modifier.padding(CitiesAppTheme.dimens.paddingMedium),
            text = message,
            style = MaterialTheme.typography.titleSmall
        )
    }
}