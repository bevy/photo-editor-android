package com.ahmedadeltito.photoeditorsdk;

/**
 * Created by Ahmed Adel on 02/06/2017.
 */

public interface OnPhotoEditorSDKListener {

    void onEditTextChangeListener(String text, int colorCode);

    void onAddViewListener(ViewType viewType);

    void onRemoveViewListener(ViewType viewType);

    void onStartViewChangeListener(ViewType viewType);

    void onStopViewChangeListener(ViewType viewType);
}
