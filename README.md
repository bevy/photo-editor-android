# PhotoEditorSDK

![alt tag](https://s18.postimg.org/xza5yw53d/photoeditorsdk.png)

Photo Editor SDK that contains a lot of features to edit, scale, rotate and draw on images like Instagram stories.

-----------------------------------------------------------------------------------------------------

# Application Features
1. Adding **Images**.
2. Adding **Stickers**.
3. Adding **Text** with option to change its **Color**.
4. **Drawing** on image with option to change its **Color**, its **Size** and **Erasing**.
5. **Scaling** and **Rotating** views.
6. **Deleting** views.
7. **Saving** photo after editing.
8. **Undo** after adding views.

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
# License

**Open Source, waiting your contributions !**