# CardViewPlus [![](https://jitpack.io/v/AliBardide5124/CardViewPlus.svg)](https://jitpack.io/#AliBardide5124/CardViewPlus)
An small library to animate Card View shadow
<br/>
<br/>
    ![Demo](https://drive.google.com/uc?export=download&id=1Fyztx2EGxhkuNGGaaifAC9mu71m1n30A)
<br/>
<br/>
<br/>
Download demo app from [here](https://drive.google.com/uc?export=download&id=1pXNp_Fk3x3UJFR7eqCwPra74ri2WBLxO)
<br/>
<br/>

## How to add
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.AliBardide5124:CardViewPlus:1.0.3'
	}
<br/>

## How to use
You can use CardViewPlus just like a noraml CardView
	
	<com.alibardide5124.cardviewplus.CardViewPlus
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
	... >
		....
	
	</com.alibardide5124.cardviewplus.CardViewPlus>
	
But it give you some more attributes 

	app:normalElevation="2dp"   // Dimension - Set CardViewPlus elevation in noraml state. Default: 2dp
	app:maxElevation="5dp"      // Dimension - Set CardViewPlus elevation in pressed state. Default: 5dp
	app:animationEnabled="true" // boolean - Enable or Disable CardViewPlus shadow animation. Default: true.
	app:clickDelay="150"        // Set a delay for click after release the CardViewPlus. Default: 150ms

note: Its better to set click delay more than 150ms.
<br/>
<br/>
You can also use this attributes in code

	CardViewPlus cardViewPlus = ...
	....
	cardViewPlus.setNormalElevation(4);
	cardViewPlus.setPressedElevation(10);
	cardViewPlus.setAnimationEnabled(true)
	cardViewPlus.setClickDelay(150)
<br/>
<br/>

  Thank you for using my library.
  <br/>
  You can send your suggestions to my email: 
   
	alibardide5124@gmail.com 
  
