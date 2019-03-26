# PhotoEditorSDK

![alt tag](https://s18.postimg.org/xza5yw53d/photoeditorsdk.png)

It is an android image editing SDK with simple, easy support for image manipulation.

-----------------------------------------------------------------------------------------------------

## Features
- Adding Images.
- Adding Stickers.
- Adding Text with option to change its Color.
- Drawing on image with option to change its Color, its Size and Erasing.
- Scaling and Rotating views.
- Deleting views.
- Saving photo after editing.
- Undo after adding views.
- Transforms
  - Flip
  - Rotate
  - Resize
-----------------------------------------------------------------------------------------------------

## Benefits
- Hassle free coding
- Increase efficiency
- Easy image editing

-----------------------------------------------------------------------------------------------------

## Installation

### For Gradle : ###
Step 1 : Add it in your root build.gradle at the end of repositories
```java
allprojects {
  repositories {
   ...
   maven { url 'https://jitpack.io' }
  }
}
```
Step 2 : Add the dependency
```java
dependencies {
         compile 'com.github.eventtus:photo-editor-android:v1.0'
 }
 ```

### For Maven : ###

Step 1 : Add it in your root build.gradle at the end of repositories
```java
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```
Step 2 : Add the dependency
```java
<dependency>
     <groupId>com.github.eventtus</groupId>
     <artifactId>photo-editor-android</artifactId>
     <version>v1.0</version>
</dependency>
```

-----------------------------------------------------------------------------------------------------

# User Documentation :

1. First of all you have to get instance of PhotoEditorSDK to initialize it and start calling the desired functions.
```java
photoEditorSDK = new PhotoEditorSDK.PhotoEditorSDKBuilder(PhotoEditorActivity.this)
.parentView(parentImageRelativeLayout)
//add parent image view
.childView(photoEditImageView)
//add the desired image view
.deleteView(deleteRelativeLayout)
//add the deleted view that will appear during the movement of the views
.brushDrawingView(brushDrawingView)
// add the brush drawing view that is responsible for drawing on the image view
.buildPhotoEditorSDK();
// build photo editor sdk
```

2. To add **Text** on the image:
```java
photoEditorSDK.addText(text, colorCodeTextView);
```

3. To add **Image** or **Stickers**:
```java
photoEditorSDK.addImage(image);
```

4. To add **Emoji**:
```java
photoEditorSDK.addEmoji(emojiName, emojiFont);
```

5. To **Draw** on the image:
```java
photoEditorSDK.setBrushDrawingMode(brushDrawingMode);
// brushDrawingMode is false by default, true if you want to draw on the image view
```

6. To change the **Color** and **Size** of the drawing view and the **Size** and the **Color** of the **Eraser** view:
```java
photoEditorSDK.setBrushSize(size);
photoEditorSDK.setBrushColor(colorCode);
photoEditorSDK.brushEraser();
photoEditorSDK.setBrushEraserSize(brushEraserSize);
photoEditorSDK.setBrushEraserColor(color);
```

7. To **Save** the image after editing:
```java
photoEditorSDK.saveImage(folderName, imageName);
```

8. To **Undo** the added **Views (Image or Text)**:
```java
photoEditorSDK.viewUndo();
```

9. To **Clear All** the added **Views (Image or Text)**:
```java
photoEditorSDK.clearAllViews();
```

10. To **Clear All** the added **Drawing Views**:
```java
photoEditorSDK.clearBrushAllViews();
```

11. To listen on **Added Views**, **Edit the added Text Views**, **Added and Removed Views** and **Start and Stop Moving Views**. You can implement:
```java
photoEditorSDK.setOnPhotoEditorSDKListener(new OnPhotoEditorSDKListener() {
    @Override
	public void onEditTextChangeListener(String text, int colorCode) {

	}
	@Override
    public void onAddViewListener(ViewType viewType, int numberOfAddedViews) {

    }
    @Override
	public void onRemoveViewListener(int numberOfAddedViews) {

	}
    @Override
	public void onStartViewChangeListener(ViewType viewType) {

	}
    @Override
	public void onStopViewChangeListener(ViewType viewType) {
  
	}
});
```

-----------------------------------------------------------------------------------------------------

## Contributing

We welcome contributions from other developers to help us make the SDK even better.
Before you contribute there are a number of things that you should know please see [CONTRIBUTING.md](https://github.com/eventtus/photo-editor-android/blob/master/CONTRIBUTING.md) for details.

## Credits

PhotoEditorSDK was originally written by [Ahmed Adel](https://github.com/ahmed-adel-said).
Project is maintained and funded by

[![Eventtus](http://assets.eventtus.com/logos/eventtus/standard.png)](http://eventtus.com)

## License

Copyright (c) 2017 Eventtus, PhotoEditorSDK is released under the MIT license.
