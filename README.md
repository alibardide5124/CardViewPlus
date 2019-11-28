# CardViewPlus [![](https://jitpack.io/v/AliBardide5124/CardViewPlus.svg)](https://jitpack.io/#AliBardide5124/CardViewPlus)
 An small library to animate Card View shadow
<br/>
[![](https://doc-00-28-docs.googleusercontent.com/docs/securesc/uo01kfkr3e6q8pkr5oa589ftvba16jck/mltfdloggt4qssg3dgo1aql2qk59j5fj/1574906400000/14018589600902707905/14018589600902707905/1M0buUC6WQiKrr7oQEtO1y0rH53j5MXl9?e=download&authuser=0)](https://drive.google.com/file/d/1M0buUC6WQiKrr7oQEtO1y0rH53j5MXl9/view?usp=drivesdk)
<br/>
Download app
[![](https://doc-0k-28-docs.googleusercontent.com/docs/securesc/uo01kfkr3e6q8pkr5oa589ftvba16jck/p5mqqlrhosareafpe44khn0gj5g1dgc3/1574906400000/14018589600902707905/14018589600902707905/1aApAC_d7a034z3r0bo2j19x4rsKggjuL?e=download&authuser=0)](https://doc-04-28-docs.googleusercontent.com/docs/securesc/uo01kfkr3e6q8pkr5oa589ftvba16jck/gpe5bkenjlguep579krqnlvo3o06mn0c/1574906400000/14018589600902707905/14018589600902707905/1PGaxhKUXieBOdJNAn2VwCsLl_6knV7DK?e=download&authuser=0&nonce=9upmr7bedbl1u&user=14018589600902707905&hash=m1q0ls03b7sofrr1c134qos2rt3vgf32)
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
   You can set minimum CardViewPlus elevation by this attribute. Its like cardElevation on a regular CardView. This is the CardViewPlus elevation before touching.  By default its set to "2dp"
<br/>
  #### maxElevation - Dimension:
   You can set maximum CardViewPlus elevation. This is the CardViewPlus elevation after touching. By default its set to "5dp"
 <br/>
 #### animationEnabled - Boolean: 
   You can enable or disable CardViewPlus shadow animation by this attribute. if you want to use CardViewPlus just like a regular CardView, set this to "false". By default its set to "true".
<br/>
#### clickMode - collapse.release:
   You can specify when CardViewPlus is clicked. When it value is equal to "collapse", CardViewPlus is clicked after its released and its shadow is collapsed. When it value is equal to "release", CardViewPlus is clicked right after release.
<br/>
<br/>

  Thank you for using my library.
  <br/>
  You can send your suggestions to my email: 
   
	alibardide5124@gmail.com 
  
