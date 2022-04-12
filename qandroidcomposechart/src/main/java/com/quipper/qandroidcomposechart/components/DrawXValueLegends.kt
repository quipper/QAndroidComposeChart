package com.quipper.qandroidcomposechart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.quipper.qandroidcomposechart.R
import com.quipper.qandroidcomposechart.models.ChartTheme
import com.quipper.qandroidcomposechart.models.ChartValue
import com.quipper.qandroidcomposechart.utils.DateFormat
import com.quipper.qandroidcomposechart.utils.convertFormat

@Composable
internal fun DrawXValueLegend(
    height: Dp,
    data: List<ChartValue>,
    onClickIndex: Int,
    chartTheme: ChartTheme,
    onValueClicked: (index: Int, value: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 28.dp, end = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .height(height = height),
            verticalAlignment = Alignment.CenterVertically
        ) {
            data.forEachIndexed { index, value ->
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .clickable(enabled = value.isValueAvailable) {
                            onValueClicked(index, value.date)
                        }
                )
            }
        }
        Row(
            modifier = Modifier.padding(top = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            data.forEachIndexed { index, value ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(enabled = value.isValueAvailable) {
                            onValueClicked(index, value.date)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    val textColor = if (onClickIndex == index) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_chart_bottom_label),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(chartTheme.legendsClickShapeColor)
                        )
                        chartTheme.legendsClickedTextColor
                    } else {
                        chartTheme.legendsTextColor
                    }
                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = value.date.convertFormat(
                            DateFormat.DEFAULT_FORMAT,
                            DateFormat.MONTH_DATE_FORMAT
                        ),
                        style = chartTheme.legendsTextStyle,
                        color = textColor,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
