package com.quipper.qandroidcomposechart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.quipper.qandroidcomposechart.models.ChartTheme
import com.quipper.qandroidcomposechart.models.ChartValue

@Composable
internal fun DrawOnClickLabel(
    chartTheme: ChartTheme,
    onClickIndex: Int,
    offsetsIndexed: List<Offset>,
    data: List<ChartValue>,
    labelTextStyle: TextStyle,
    onWidthChange: (Float) -> Unit
) {

    if (offsetsIndexed.isNotEmpty()) {

        val currentData = data[onClickIndex]
        val isRightPosition = onClickIndex > data.size.div(2)
        val currentValueOffset = offsetsIndexed[onClickIndex]

        val lastOffset: DpOffset = with(LocalDensity.current) {
            val yValue = if (currentData.value == 0f) {
                currentValueOffset.y - 65f
            } else {
                currentValueOffset.y - 35f
            }
            val xValue = if (isRightPosition) {
                currentValueOffset.x - 130f
            } else {
                currentValueOffset.x + 50f
            }
            DpOffset(
                x = xValue.toDp(),
                y = yValue.toDp()
            )
        }

        ConstraintLayout(
            modifier = Modifier
                .offset(
                    x = lastOffset.x,
                    y = lastOffset.y
                )
                .onSizeChanged {
                    onWidthChange(it.width.toFloat())
                }
        ) {
            val (img, txt) = createRefs()
            Image(
                modifier = Modifier
                    .constrainAs(ref = img) {
                        linkTo(
                            start = parent.start,
                            end = parent.end,
                        )
                        top.linkTo(parent.top)
                    }
                    .rotate(if (isRightPosition) 180f else 0f),
                painter = painterResource(id = chartTheme.iconId),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .constrainAs(ref = txt) {
                        linkTo(
                            start = parent.start,
                            startMargin = if (isRightPosition) 0.dp else 4.dp,
                            end = parent.end,
                            endMargin = if (isRightPosition) 4.dp else 0.dp
                        )
                        linkTo(
                            top = parent.top,
                            topMargin = if (isRightPosition) 1.dp else 0.dp,
                            bottom = parent.bottom,
                            bottomMargin = if (isRightPosition) 0.dp else 2.dp
                        )
                    },
                textAlign = TextAlign.Start,
                text = data[onClickIndex].valueAsInt.toString(),
                style = labelTextStyle,
                color = Color.White
            )
        }
    }
}
