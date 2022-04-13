# QAndroidComposeChart
Simple chart library build using compose and kotlin

[![Release](https://img.shields.io/github/release/PhilJay/MPAndroidChart.svg?style=flat)](https://jitpack.io/#quipper/QAndroidComposeChart)

## Gradle setup

**Step 1.** Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

**Step 2.** Add the dependency:
```
dependencies {
	implementation 'com.github.denisyordanp:QAndroidComposeChart:1.1.0'
}
```

## How to use

**Step 1.** Create _ChartTheme_ for chart styling:
```
val chartTheme = ChartTheme.Builder().build()
```

**Step 2.** Create _ChartData_
```
val chartData = ChartData(listOf())
```

**Step 3.** Implement the Chart
```
QChart(
    chartModalTitle = "Chart title",
    chartTheme = chartTheme,
    chartData = chartData,
    onValueClicked = {}
)
```

## Creator
- [denisyordanp](https://github.com/denisyordanp) - Denis Y P

**_Want your name listed here as creator? Please just create the PR!_** :zap:
