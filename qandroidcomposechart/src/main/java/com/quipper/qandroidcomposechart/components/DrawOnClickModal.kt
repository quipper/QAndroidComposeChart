package com.quipper.qandroidcomposechart.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quipper.qandroidcomposechart.models.ChartData
import com.quipper.qandroidcomposechart.models.ChartTheme
import com.quipper.qandroidcomposechart.utils.DateFormat
import com.quipper.qandroidcomposechart.utils.convertFormat

@Composable
internal fun DrawOnClickModal(
    title: String,
    onClickIndex: Int,
    labelWidth: Float,
    isFirstOnClick: Boolean,
    offsetsIndexed: List<Offset>,
    chartTheme: ChartTheme,
    chartData: ChartData
) {
    if (offsetsIndexed.isNotEmpty() && isFirstOnClick) {

        var cardSize by remember {
            mutableStateOf(Size.Zero)
        }

        LaunchedEffect(key1 = chartData) {
            cardSize = Size.Zero
        }

        val clickOffset: DpOffset = with(LocalDensity.current) {
            val yValue =
                if (
                    chartData.data[onClickIndex].isValueAvailable &&
                    chartData.data[onClickIndex].valueAsFloat!! <= chartData.highestYValueChart.div(
                        2
                    )
                ) {
                    if (chartData.data[onClickIndex].valueAsFloat == 0f) {
                        offsetsIndexed[onClickIndex].y - (cardSize.height + 5f)
                    } else {
                        offsetsIndexed[onClickIndex].y - (cardSize.height - 20f)
                    }
                } else {
                    val addPos = 35f
                    offsetsIndexed[onClickIndex].y - addPos
                }
            val xValue = if (onClickIndex > chartData.data.size.div(2)) {
                val addPos = cardSize.width + labelWidth + 25f
                offsetsIndexed[onClickIndex].x - addPos
            } else {
                val addPos = labelWidth + 50f
                offsetsIndexed[onClickIndex].x + addPos
            }
            DpOffset(
                x = xValue.toDp(),
                y = yValue.toDp()
            )
        }

        Card(
            modifier = Modifier
                .offset(
                    x = clickOffset.x,
                    y = clickOffset.y
                )
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                .onSizeChanged {
                    cardSize = Size(
                        width = it.width.toFloat(),
                        height = it.height.toFloat()
                    )
                },
            shape = RoundedCornerShape(10.dp),
            backgroundColor = chartTheme.modalBackgroundColor
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {

                Text(
                    modifier = Modifier.padding(bottom = 2.dp),
                    text = chartData.data[onClickIndex].date.convertFormat(
                        DateFormat.DEFAULT_FORMAT,
                        DateFormat.MONTH_DATE_YEAR_FORMAT
                    ),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = chartTheme.legendsTextColor
                )
                title.split("\\s+".toRegex()).forEach {
                    Text(
                        modifier = Modifier.widthIn(
                            max = 100.dp
                        ),
                        text = it,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = chartTheme.legendsTextColor
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    textAlign = TextAlign.Start,
                    text = chartData.data[onClickIndex].valueAsInt.toString(),
                    style = chartTheme.modalTextStyle,
                    color = chartTheme.baseColor
                )
            }
        }
    }
}
