## ActionSheet [![](https://jitpack.io/v/WindSekirun/ActionSheet.svg)](https://jitpack.io/#WindSekirun/ActionSheet) [![codebeat badge](https://codebeat.co/badges/01d0775b-2e7b-4240-83f7-3abfff86591f)](https://codebeat.co/projects/github-com-windsekirun-actionsheet-master)

[![Kotlin](https://img.shields.io/badge/kotlin-1.2.0-blue.svg)](http://kotlinlang.org)

bring [UIAlertController-UIActionSheet](https://developer.apple.com/documentation/uikit/uialertcontroller) in Android Application, written in [Kotlin](http://kotlinlang.org).

<img src="https://github.com/WindSekirun/ActionSheet/blob/master/sample.png" width="202" height="360">

This was fork of [android-ActionSheet](https://github.com/baoyongzhang/android-ActionSheet), but has been completely rewritten in Kotlin. See licensing info.

### Usages
*rootProject/build.gradle*
```	
allprojects {
    repositories {
	    maven { url 'https://jitpack.io' }
    }
}
```

*app/build.gradle*
```
dependencies {
    implementation 'com.github.WindSekirun:ActionSheet:1.0.0'
}
```

### Usages

#### Kotlin
```Kotlin
val config = ActionSheetConfig.Builder()
                .addItem("Select from my Gallery", "Capture from Camera")
                .addItem("Delete Picture", Color.RED)
                .setCancelableOnTouchOutside(true)
                .setCancelButton("Cancel")
                .setOnDismissListener(OnDismissListener { actionSheet, isCancel ->

                })
                .setOnActionButtonClickListener(OnActionButtonClickListener { actionSheet, actionButton, index ->
                    Toast.makeText(this@MainActivity, "clicked %s".format(actionButton.title), Toast.LENGTH_SHORT).show()
                })
                .build()


actionsheet = ActionSheet.show(this, supportFragmentManager, config)
```

#### Java
```Java
ActionSheetConfig config = new ActionSheetConfig.Builder()
                .addItem("Select from my Gallery", "Capture from Camera")
                .addItem("Delete Picture", Color.RED)
                .setCancelableOnTouchOutside(true)
                .setCancelButton("Cancel")
                .setOnDismissListener((OnDismissListener) (actionSheet, isCancel) -> {

                })
                .setOnActionButtonClickListener((OnActionButtonClickListener) (actionSheet, actionButton, index) -> {
                    Toast.makeText(this, String.format("clicked %s", actionButton.getTitle()), Toast.LENGTH_SHORT).show();
                })
                .build();

actionSheet = ActionSheet.show(this, getSupportFragmentManager(), config);
````

#### Common
You should call ```actionSheet.dismiss();``` in your onPause of Activity. cause user can press home button.

### License 
```
The MIT License (MIT)

Copyright (c) 2018 WindSekirun (DongGil, Seo)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```
