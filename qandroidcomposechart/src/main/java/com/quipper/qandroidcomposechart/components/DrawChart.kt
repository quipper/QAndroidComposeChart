package com.quipper.qandroidcomposechart.components

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.quipper.qandroidcomposechart.models.ChartData
import com.quipper.qandroidcomposechart.models.ChartTheme

@Composable
internal fun DrawChart(
    height: Dp,
    lineWidth: Dp,
    chartLineWidth: Dp,
    isChartLineCurved: Boolean,
    chartTheme: ChartTheme,
    offsetsIndexed: List<Offset>,
    onClickIndex: Int,
    chartData: ChartData,
    isFirstClickDefine: Boolean,
    valueTextStyle: TextStyle,
    onSetClickIndex: (Int) -> Unit,
    onFalseFirstClickDefine: () -> Unit,
    onSetOffsetIndexed: (List<Offset>) -> Unit
) {
    val marginY = height.div(6)

    var currentHighestValue = 0f
    val yValueLegendsHeight = 27.dp

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = height + yValueLegendsHeight)
            .padding(
                start = 8.dp,
                end = 8.dp
            )
    ) {

        var currentLinePos: Float

        val startChartLinePos = 20.dp.toPx()
        val endChartLinePos = size.width
        var topChartLinePos = 0f
        var bottomChartLinePos = 0f

        val yValueTextStyle = Paint().apply {
            textSize = valueTextStyle.fontSize.toPx()
            color = chartTheme.legendsTextColor.toArgb()
            textAlign = Paint.Align.RIGHT
            typeface = Typeface.SANS_SERIF
        }

        chartData.convertedYValueLegend.forEachIndexed { index, value ->

            currentLinePos = marginY.toPx() * (index + 1)
            if (index == 0) {
                currentHighestValue = value
                topChartLinePos = marginY.toPx()
            }
            if (index == 4) {
                bottomChartLinePos = currentLinePos + marginY.toPx()
            }

            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    value.toInt().toString(),
                    10.dp.toPx(),
                    currentLinePos + 3.dp.toPx(),
                    yValueTextStyle
                )
            }

            drawLine(
                color = chartTheme.horizontalLineColor.copy(alpha = 0.5f),
                start = Offset(x = startChartLinePos, y = currentLinePos),
                end = Offset(x = endChartLinePos, y = currentLinePos),
                strokeWidth = lineWidth.toPx()
            )
        }

        drawLine(
            color = chartTheme.horizontalLineColor,
            start = Offset(x = 0f, y = bottomChartLinePos),
            end = Offset(x = endChartLinePos, y = bottomChartLinePos),
            strokeWidth = 1.dp.toPx()
        )

        val chartSize = Size(
            width = endChartLinePos - startChartLinePos,
            height = bottomChartLinePos - topChartLinePos
        )
        val rangeBetweenYOffset = chartSize.width.div(7)
        val middleLineOffset = rangeBetweenYOffset.div(2)
        val currentOffsetIndexed: MutableList<Offset> = mutableListOf()

        val path = chartData.data.run {
            return@run Path().apply {

                val firstXPosition = startChartLinePos + middleLineOffset

                forEachIndexed {index, value ->

                    value.valueAsFloat?.let {floatValue ->

                        if (isFirstClickDefine) {
                            onSetClickIndex(index)
                        }

                        if (index != chartData.data.lastIndex) {

                            val nextPoint = chartData.data[index + 1].valueAsFloat

                            if (index == 0) {

                                val yOffset =
                                    chartSize.height - (chartSize.height * (floatValue / currentHighestValue)) + topChartLinePos

                                moveTo(x = firstXPosition, y = yOffset)
                                currentOffsetIndexed.add(Offset(x = firstXPosition, y = yOffset))

                                if (nextPoint == null) {
                                    lineTo(x = firstXPosition, y = yOffset)
                                }
                            }

                            if (nextPoint != null) {
                                val xOffset =
                                    firstXPosition + (rangeBetweenYOffset * (index + 1))
                                val yOffset =
                                    chartSize.height - (chartSize.height * (nextPoint / currentHighestValue)) + topChartLinePos

                                lineTo(x = xOffset, y = yOffset)
                                currentOffsetIndexed.add(Offset(x = xOffset, y = yOffset))
                            }
                        }
                    }
                }
                onFalseFirstClickDefine()
                onSetOffsetIndexed(currentOffsetIndexed)
            }
        }

        //            draw value chart
        drawPath(
            path = path,
            color = chartTheme.baseColor,
            style = Stroke(
                width = chartLineWidth.toPx(),
                cap = StrokeCap.Round,
                pathEffect = if (isChartLineCurved) PathEffect.cornerPathEffect(
                    chartLineWidth.toPx().times(20)
                ) else null
            )
        )

        if (offsetsIndexed.isNotEmpty()) {
            //            draw clicked line
            drawLine(
                color = chartTheme.clickedLineColor,
                strokeWidth = 1.dp.toPx(),
                start = Offset(
                    x = offsetsIndexed[onClickIndex].x,
                    y = bottomChartLinePos
                ),
                end = Offset(
                    x = offsetsIndexed[onClickIndex].x,
                    y = topChartLinePos
                ),
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(15f, 15f),
                    phase = 0f
                )
            )

            //            draw clicked circle
            drawCircle(
                color = chartTheme.pointColor,
                radius = 6.dp.toPx(),
                style = Stroke(
                    width = 2.dp.toPx()
                ),
                center = offsetsIndexed[onClickIndex]
            )
            drawCircle(
                color = chartTheme.baseColor,
                radius = 6.dp.toPx(),
                center = offsetsIndexed[onClickIndex]
            )
        }

    }
}
