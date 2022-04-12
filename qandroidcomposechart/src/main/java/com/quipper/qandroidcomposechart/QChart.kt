package com.quipper.qandroidcomposechart

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.quipper.qandroidcomposechart.components.*
import com.quipper.qandroidcomposechart.models.ChartClickValue
import com.quipper.qandroidcomposechart.models.ChartData
import com.quipper.qandroidcomposechart.models.ChartTheme

@Composable
fun QChart(
    modifier: Modifier = Modifier,
    chartHeight: Dp = 300.dp,
    chartModalTitle: String,
    chartLineWidth: Dp = 4.dp,
    chartTheme: ChartTheme = ChartTheme.Builder().build(),
    isChartLineCurved: Boolean = false,
    bgLineWidth: Dp = 1.dp,
    chartData: ChartData,
    onValueClicked: (chartClickValue: ChartClickValue) -> Unit
) {

    Box(
        modifier = modifier
    ) {

        val marginY = chartHeight.div(6)
        DrawTopLine(
            bottomPadding = marginY,
            color = chartTheme.horizontalLineColor,
            strokeWidth = bgLineWidth
        )

        var offsetsIndexed: List<Offset> by remember {
            mutableStateOf(listOf())
        }
        var isFirstClickDefine by remember {
            mutableStateOf(true)
        }
        var onClickIndex by remember {
            mutableStateOf(0)
        }
        var isFirstOnClick by remember {
            mutableStateOf(false)
        }
        var labelWidth by remember {
            mutableStateOf(0f)
        }

        LaunchedEffect(key1 = chartData) {
            isFirstClickDefine = true
            isFirstOnClick = false
            offsetsIndexed = emptyList()
            onClickIndex = 0
            labelWidth = 0f
        }

        DrawChart(
            height = chartHeight,
            lineWidth = bgLineWidth,
            chartLineWidth = chartLineWidth,
            isChartLineCurved = isChartLineCurved,
            chartTheme = chartTheme,
            offsetsIndexed = offsetsIndexed,
            onClickIndex = onClickIndex,
            chartData = chartData,
            isFirstClickDefine = isFirstClickDefine,
            valueTextStyle = chartTheme.valueTextStyle,
            onSetClickIndex = { onClickIndex = it },
            onFalseFirstClickDefine = { isFirstClickDefine = false },
            onSetOffsetIndexed = { offsetsIndexed = it }
        )
        DrawOnClickLabel(
            offsetsIndexed = offsetsIndexed,
            onClickIndex = onClickIndex,
            data = chartData.data,
            onWidthChange = { labelWidth = it },
            chartTheme = chartTheme
        )
        DrawOnClickModal(
            title = chartModalTitle,
            offsetsIndexed = offsetsIndexed,
            isFirstOnClick = isFirstOnClick,
            chartData = chartData,
            onClickIndex = onClickIndex,
            labelWidth = labelWidth,
            chartTheme = chartTheme
        )
        DrawXValueLegend(
            height = chartHeight,
            data = chartData.data,
            chartTheme = chartTheme,
            onClickIndex = onClickIndex,
            onValueClicked = { index, value ->
                if (!isFirstOnClick) isFirstOnClick = true
                onClickIndex = index
                onValueClicked(ChartClickValue(index, value))
            }
        )
    }
}
