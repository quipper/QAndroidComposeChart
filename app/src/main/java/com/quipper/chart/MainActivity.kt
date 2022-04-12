package com.quipper.chart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import com.quipper.qandroidcomposechart.QChart
import com.quipper.qandroidcomposechart.models.ChartData
import com.quipper.qandroidcomposechart.models.ChartTheme
import com.quipper.qandroidcomposechart.models.ChartValue

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QChart(
                chartModalTitle = "",
                chartTheme = ChartTheme.Builder()
                    .baseColor(
                        Color.Black,
                        Color.Yellow
                    )
                    .build(),
                chartData = createDummyChartData(),
                onValueClicked = {}
            )
        }
    }

    private fun createDummyChartData(): ChartData {
        return ChartData(
            listOf(
                ChartValue(10, "2022-10-20"),
                ChartValue(13, "2022-10-20"),
                ChartValue(25, "2022-10-20"),
                ChartValue(50, "2022-10-20"),
                ChartValue(60, "2022-10-20"),
                ChartValue(30, "2022-10-20"),
                ChartValue(10, "2022-10-20"),
            )
        )
    }
}
