# CardViewPlus [![](https://jitpack.io/v/alibardide5124/CardViewPlus.svg)](https://jitpack.io/#alibardide5124/CardViewPlus)
A library to make animated CardView elevation
<br/>
<br/>
    ![Demo](https://github.com/alibardide5124/CardViewPlus/blob/master/readme.files/sample.gif)
<br/>
<br/>
<br/>
Download demo app from [here](https://github.com/alibardide5124/CardViewPlus/blob/master/readme.files/app-debug.apk)
<br/>
<br/>

## How to add
Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
		implementation 'androidx.cardview:cardview:1.0.0'
	        implementation 'com.github.alibardide5124:CardViewPlus:1.0.5'
	}
<br/>

## How to use
You can use CardViewPlus just like a normal CardView
	
	<com.alibardide5124.cardviewplus.CardViewPlus
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
		... >
		
		....
	
	</com.alibardide5124.cardviewplus.CardViewPlus>
	
<br/>
<br/>

But it gives you some more attributes 

	app:normalElevation="2dp"
	app:maxElevation="5dp"
	app:pressedColor="#ffe0e0e0"
	app:animationEnabled="true"
	app:clickDelay="150"

<br/>

You can also use these attributes in code

	CardViewPlus cardViewPlus = ...
	....
	cardViewPlus.setNormalElevation(4);
	cardViewPlus.setPressedElevation(10);
	cardViewPlus.setPressedColor(Color.parseColor("#e0e0e0"));
	cardViewPlus.setAnimationEnabled(true)
	cardViewPlus.setClickDelay(150)
<br/>
<br/>

#### normalElevation - Dimension
  Set CardViewPlus elevation in noraml state. Default: 2dp

#### maxElevation - Dimension
  Set CardViewPlus elevation in pressed state. Default: 5dp
  
  If you want to disable elevation animation, set 'app:animationEnabled="false"' or set the same value for normalElevation and maxElevation

#### pressedColor - Color
  Set color for onTouch color animation
  If you want to disable color animation, set 'app:animationEnabled="false"' or set the same value for pressedColor and cardBackgroundColor

#### animationEnabled - Boolean
  Enable or Disable CardViewPlus shadow and color animations. Default: true.

#### clickDelay - Int (ms)
  Set a delay for click after release the CardViewPlus. Default: 150ms
  Suggest setting click delay more than 150ms.
  
<br/>
<br/>

  Thank you for using my library.
  <br/>
  You can send your suggestions to my email: 
   
	alibardide5124@gmail.com 
	
<br/>
<br/>

# License
	
	Copyright © 2019-2020 Ali Bardide

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
  
