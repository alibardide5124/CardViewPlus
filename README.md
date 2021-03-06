CardViewPlus
=================

<img src="/readme.files/sample.gif" alt="sample" title="sample" width="300" height="450" align="right" vspace="52" />

[![Version](https://jitpack.io/v/alibardide5124/CardViewPlus.svg)](https://jitpack.io/#alibardide5124/CardViewPlus)
[![API](https://img.shields.io/badge/API-14%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9eca05909e6640d1b56d704c5601d68d)](https://www.codacy.com/manual/alibardide5124/CardViewPlus?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=alibardide5124/CardViewPlus&amp;utm_campaign=Badge_Grade)

A library to make animated CardView elevation
<br/>
Download sample app from [**here**](https://github.com/alibardide5124/CardViewPlus/blob/master/readme.files/app-debug.apk)
<br/>
<br/>

Usage
-----
Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency
```groovy
dependencies {
		implementation 'androidx.cardview:cardview:1.0.0'
        implementation 'com.github.alibardide5124:CardViewPlus:1.0.6'
}
```

Now you're ready to use **CardViewPlus**

You can use CardViewPlus just like a normal CardView
```xml
<com.alibardide5124.cardviewplus.CardViewPlus
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
	... >	
	
</com.alibardide5124.cardviewplus.CardViewPlus>
```

<br/>

But it gives you some more attributes 

| Properties                      | Type                                           | Default                 |
| ------------------------------- | ---------------------------------------------- | ----------------------- |
| `normalCardElevation`           | Dimension - Float (in code)                    | 2dp                     |
| `pressedCardElevation`          | Dimension - Float (in code)                    | 5dp                     |
| `cardPressedColor`              | Color                                          | `cardBackgroundColor`   |
| `animationEnabled`              | Boolean                                        | true                    |
| `clickDelay`                    | Int (milli second)                             | 150                     |

`normalCardElevation` : set CardViewPlus elevation in noraml state. Default: 2dp.

`pressedCardElevation`: set CardViewPlus elevation in pressed state. Default: 5dp.

   If you want to disable elevation animation, set the same value for normalElevation and maxElevation.

`cardPressedColor`: set color for onTouch color animation. Default: #e0e0e0.

   If you want to disable color animation, set the same value for pressedColor and cardBackgroundColor.

`animationEnabled`: enable or Disable CardViewPlus shadow and color animations. Default: true.

`clickDelay`: set a delay for click after release the CardViewPlus. Default: 150ms.

   Suggest setting click delay more than 150ms.
  
<br/>

   Please use `normalCardElevation` and `pressedCardElevation` instead of `cardElevation` and `maxCardElevation`.

<br/>

XML
-----
```xml
<com.alibardide5124.cardviewplus.CardViewPlus
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	app:normalCardElevation="2dp"
	app:pressedCardElevation="5dp"
	app:cardPressedColor="#ffe0e0e0"
	app:animationEnabled="true"
	app:clickDelay="150">


</com.alibardide5124.cardviewplus.CardViewPlus>
```

Kotlin
----
```kotlin
cardViewPlus.apply {
	normalCardElevation = 4
	pessedCardElevation = 10
	cardPressedColor = Color.parseColor("#e0e0e0")
	isAnimationEnabled = true
	clickDelay = 150
}
```

Java
-----
```java
CardViewPlus cardViewPlus = findViewById(R.id.cardViewPlus);
cardViewPlus.setNormalCardElevation(4);
cardViewPlus.setPressedCardElevation(10);
cardViewPlus.setCardPressedColor(Color.parseColor("#e0e0e0"));
cardViewPlus.setAnimationEnabled(true);
cardViewPlus.setClickDelay(150);
```

<br/>
<br/>

Support
-----

Find this library useful? Support it by joining [**stargazers**](https://https://github.com/alibardide5124/CardViewPlus/stargazers) for this repository
<br/>
And [**follow me**](https://https://https://github.com/alibardide5124?tab=followers) for my next creations

License
-----

CardViewPlus by [Ali Bardide](https://github.com/alibardide5124) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

