package com.quipper.qandroidcomposechart.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.quipper.qandroidcomposechart.R

class ChartTheme(
    val baseColor: Color,
    val pointColor: Color,
    val horizontalLineColor: Color,
    val clickedLineColor: Color,
    val legendsTextColor: Color,
    val legendsClickedTextColor: Color,
    val legendsClickShapeColor: Color,
    val modalBackgroundColor: Color,
    @DrawableRes val iconId: Int,
    val valueTextStyle: TextStyle,
    val labelTextStyle: TextStyle,
    val modalTextStyle: TextStyle,
    val legendsTextStyle: TextStyle
) {

    private constructor(builder: Builder): this(
        builder.baseColor,
        builder.pointColor,
        builder.horizontalLineColor,
        builder.clickedLineColor,
        builder.legendsTextColor,
        builder.legendsClickedTextColor,
        builder.legendsClickShapeColor,
        builder.modalBackgroundColor,
        builder.iconId,
        builder.valueTextStyle,
        builder.labelTextStyle,
        builder.modalTextStyle,
        builder.legendsTextStyle
    )

    class Builder(
        internal var baseColor: Color = Color.Black,
        internal var pointColor: Color = Color.Black,
        internal var horizontalLineColor: Color = Color.Black,
        internal var clickedLineColor: Color = Color.Black,
        internal var legendsTextColor: Color = Color.Black,
        internal var legendsClickedTextColor: Color = Color.Black,
        internal var legendsClickShapeColor: Color = Color.Black,
        internal var modalBackgroundColor: Color = Color.Black,
        @DrawableRes internal var iconId: Int = R.drawable.ic_chart_bottom_label,
        internal var valueTextStyle: TextStyle = TextStyle.default(),
        internal var labelTextStyle: TextStyle = TextStyle.default(),
        internal var modalTextStyle: TextStyle = TextStyle.default(),
        internal var legendsTextStyle: TextStyle = TextStyle.default()
    ) {

        fun baseColor(
            lineValueColor: Color,
            onClickDotOuterColor: Color
        ) = apply {
            this.baseColor = lineValueColor
            this.pointColor = onClickDotOuterColor
        }

        fun horizontalLineColor(horizontalLineColor: Color) = apply {
            this.horizontalLineColor = horizontalLineColor
        }

        fun clickedVerticalDashLineColor(clickedLineColor: Color) = apply {
            this.clickedLineColor = clickedLineColor
        }

        fun horizontalBottomLegendsStyle(
            textColor: Color,
            onClickedTextColor: Color,
            onClickShapeColor: Color,
            textStyle: TextStyle
        ) = apply {
            this.legendsTextColor = textColor
            this.legendsTextStyle = textStyle
            this.legendsClickedTextColor = onClickedTextColor
            this.legendsClickShapeColor = onClickShapeColor
        }

        fun verticalLeftLegendsStyle(
            textStyle: TextStyle
        ) = apply {
            this.valueTextStyle = textStyle
        }

        fun onCLickLabelStyle(
            @DrawableRes valueIconResId: Int,
            textStyle: TextStyle
        ) = apply {
            this.iconId = valueIconResId
            this.labelTextStyle = textStyle
        }

        fun modalStyle(
            backgroundColor: Color,
            textStyle: TextStyle,
        ) = apply {
            this.modalBackgroundColor = backgroundColor
            this.modalTextStyle = textStyle
        }

        fun build() = ChartTheme(this)
    }
}

private fun TextStyle.Companion.default() = TextStyle(fontSize = 10.sp)
