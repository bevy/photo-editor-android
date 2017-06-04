package com.ahmedadeltito.photoeditorsdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Ahmed Adel on 5/8/17.
 */

public class BrushDrawingView extends View {

    private ArrayList<Path> paths = new ArrayList<>();

    private Path brushDrawPath;
    private Paint brushDrawPaint;
    private Paint canvasPaint;

    private Canvas brushDrawCanvas;
    private Bitmap canvasBitmap;
    private boolean brushDrawMode;

    private float brushSize = 10;
    private float brushEraserSize = 10;

    private OnPhotoEditorSDKListener onPhotoEditorSDKListener;

    public BrushDrawingView(Context context) {
        this(context, null);
    }

    public BrushDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupBrushDrawing();
    }

    public BrushDrawingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupBrushDrawing();
    }

    void setupBrushDrawing() {
        brushDrawPath = new Path();
        canvasPaint = new Paint(Paint.DITHER_FLAG);
        brushDrawPaint = new Paint();
        brushDrawPaint.setAntiAlias(true);
        brushDrawPaint.setDither(true);
        brushDrawPaint.setColor(Color.BLACK);
        brushDrawPaint.setStyle(Paint.Style.STROKE);
        brushDrawPaint.setStrokeJoin(Paint.Join.ROUND);
        brushDrawPaint.setStrokeCap(Paint.Cap.ROUND);
        brushDrawPaint.setStrokeWidth(brushSize);
        brushDrawMode = true;
        brushDrawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
        this.setVisibility(View.GONE);
    }

    private void refreshBrushDrawing() {
        brushDrawMode = true;
        brushDrawPaint.setAntiAlias(true);
        brushDrawPaint.setDither(true);
        brushDrawPaint.setStyle(Paint.Style.STROKE);
        brushDrawPaint.setStrokeJoin(Paint.Join.ROUND);
        brushDrawPaint.setStrokeCap(Paint.Cap.ROUND);
        brushDrawPaint.setStrokeWidth(brushSize);
        brushDrawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
    }

    void brushEraser() {
        brushDrawMode = false;
        brushDrawPaint.setColor(Color.parseColor("#f4f4f4"));
        brushDrawPaint.setStyle(Paint.Style.STROKE);
        brushDrawPaint.setStrokeWidth(brushEraserSize);
        brushDrawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    void setBrushDrawingMode(boolean brushDrawMode) {
        this.brushDrawMode = brushDrawMode;
        if (brushDrawMode)
            this.setVisibility(View.VISIBLE);
        else
            this.setVisibility(View.GONE);

    }

    void setBrushSize(float size) {
        brushSize = size;
        refreshBrushDrawing();
    }

    void setBrushColor(@ColorInt int color) {
        brushDrawPaint.setColor(color);
    }

    float getEraserSize() {
        return brushEraserSize;
    }

    float getPenSize() {
        return brushSize;
    }

    int getBrushColor() {
        return brushDrawPaint.getColor();
    }

    void brushUndo() {
        if (paths.size() > 0) {
            paths.remove(paths.size() - 1);
            invalidate();
        }
    }

    void clearAll() {
        paths.clear();
        brushDrawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    public void setOnPhotoEditorSDKListener(OnPhotoEditorSDKListener onPhotoEditorSDKListener) {
        this.onPhotoEditorSDKListener = onPhotoEditorSDKListener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        brushDrawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(brushDrawPath, brushDrawPaint);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (brushDrawMode) {
            float touchX = event.getX();
            float touchY = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    brushDrawPath.moveTo(touchX, touchY);
                    if (onPhotoEditorSDKListener != null)
                        onPhotoEditorSDKListener.onStartViewChangeListener(ViewType.BRUSH_DRAWING);
                    break;
                case MotionEvent.ACTION_MOVE:
                    brushDrawPath.lineTo(touchX, touchY);
                    break;
                case MotionEvent.ACTION_UP:
                    brushDrawCanvas.drawPath(brushDrawPath, brushDrawPaint);
                    paths.add(brushDrawPath);
                    brushDrawPath.reset();
                    if (onPhotoEditorSDKListener != null)
                        onPhotoEditorSDKListener.onStopViewChangeListener(ViewType.BRUSH_DRAWING);
                    break;
                default:
                    return false;
            }
            invalidate();
            return true;
        } else {
            return false;
        }
    }
}
