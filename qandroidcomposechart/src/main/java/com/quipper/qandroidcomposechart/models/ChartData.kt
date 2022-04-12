package com.quipper.qandroidcomposechart.models

import kotlin.math.roundToInt

class ChartData(
    val data: List<ChartValue>
) {

    companion object {
        private const val MAX_Y_VALUE_LEGEND = 5
    }

    val convertedYValueLegend: Array<Float> = run {
        val highestValue = getHighestValue()
        val list = mutableListOf<Float>()

        if (highestValue < 1f) {
            for (yValue in MAX_Y_VALUE_LEGEND downTo 1) {
                list.add(yValue.toFloat())
            }
        } else {
            // round five
            var rounded = (highestValue / 5f).roundToInt() * 5f
            if (highestValue > rounded) rounded += 5f

            val betweenValue = rounded / 5
            repeat(MAX_Y_VALUE_LEGEND) {index ->
                val yValue = (rounded - (betweenValue * index))
                list.add(yValue)
            }
        }

        list.toTypedArray()
    }

    val highestYValueChart: Float = convertedYValueLegend.first()

    private fun getHighestValue(): Float {
        var currentHighest = 0f
        data.forEach {
            it.valueAsFloat?.let {value ->
                if (value > currentHighest) currentHighest = value
            }
        }
        return currentHighest
    }
}
