# QAndroidComposeChart 
Simple chart library build using compose and kotlin.

### Example View

- Basic style\
![Apr-14-2022 08-28-31](https://user-images.githubusercontent.com/48426396/163296195-4c0c7a2f-440c-4f48-9e1e-6b68bcf7487d.gif)
- Curved style\
![Apr-14-2022 08-29-54](https://user-images.githubusercontent.com/48426396/163296300-feae3bef-4ea5-4457-89ed-c29133d1aa1a.gif)

### **Release version** : 
**1.1.0**

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
    chartModalTitle = "Android Compose Chart",
    chartTheme = chartTheme,
    chartData = chartData,
    onValueClicked = {}
)
```

## Creator
- [denisyordanp](https://github.com/denisyordanp) - Denis Y P

**_Want your name listed here as creator? Please just create the PR!_** :zap:
