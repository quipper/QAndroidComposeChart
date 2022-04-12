package com.quipper.qandroidcomposechart.models

data class ChartValue(
    val value: Number?,
    val date: String
) {
    val isValueAvailable: Boolean = value != null
    val valueAsFloat: Float? = value?.toFloat()
    val valueAsInt: Int? = value?.toInt()
}
