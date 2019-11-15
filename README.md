# CardViewPlus [![](https://jitpack.io/v/AliBardide5124/CardViewPlus.svg)](https://jitpack.io/#AliBardide5124/CardViewPlus)
 An small library to animate Card View shadow
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
	        implementation 'com.github.AliBardide5124:CardViewPlus:1.0.0'
	}
<br/>

## How to use
You can use CardViewPlus just like a noraml CardView
But it give you some more attributes 

	app:minElevation="2dp"
	app:maxElevation="5dp"
	app:animationEnabled="true"
	app:clickMode="collapse"
<br/>

  Lets know whats this attributes do
<br/>

  #### minElevation - Dimension:
   You can set minimum shadow elevation by this attribute. Its like cardElevation on a regular CardView. By default its set to "2dp"
<br/>
  #### maxElevation - Dimension:
   This attribute determines how large the shadow of your card can be. When user touch the CardViewPlus, The shadow expands to this value and returns to the original value after the CardViewPlus is released. By default its set to "5dp"
 <br/>
 #### animationEnabled - Boolean: 
   You can enable or disable CardViewPlus shadow animation by this attribute. By default its set to "true".
<br/>
#### clickMode - collapse.release: 
   You can specify when the CardViewPlus is clicked . When it value is equals "collase", CardViewPlus click after its released and it shadow was collapsed. When it value is equals "release", CardViewPlus click just after its released. 
<br/>
<br/>

  Thank you for using this library.
  <br/>
  You can send your suggestions to my email: 
   
	alibardide5124@gmail.com 
  
