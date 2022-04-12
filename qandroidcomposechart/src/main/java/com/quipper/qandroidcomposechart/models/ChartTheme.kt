package com.quipper.qandroidcomposechart.models

import androidx.compose.ui.graphics.Color
import com.quipper.qandroidcomposechart.R

sealed class ChartTheme {
    abstract val baseColor: Color
    abstract val pointColor: Color
    abstract val iconId: Int

    class Yellow(
        override val baseColor: Color = Color.Black,
        override val pointColor: Color = Color.Black,
        override val iconId: Int = R.drawable.ic_chart_bottom_label
    ) : ChartTheme()

    class Green(
        override val baseColor: Color = Color.Black,
        override val pointColor: Color = Color.Black,
        override val iconId: Int = R.drawable.ic_chart_bottom_label
    ) : ChartTheme()

    class Blue(
        override val baseColor: Color = Color.Black,
        override val pointColor: Color = Color.Black,
        override val iconId: Int = R.drawable.ic_chart_bottom_label
    ) : ChartTheme()
}
