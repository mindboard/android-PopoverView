package com.mindboardapps.android.example.popover;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Copyright © 2017 tomoaki oshima All Rights Reserved.
 */

final class BorderDrawable extends ColorDrawable {

    private View parentView;
    private PopupWindow popupWindow;

    private final Paint borderPaint;
    private final Path path;

    BorderDrawable(final View parentView, final PopupWindow popupWindow, final int bgColor) {
        super(bgColor);

        this.parentView = parentView;
        this.popupWindow = popupWindow;

        this.borderPaint = new Paint();
        this.borderPaint.setColor(Color.BLACK);
        this.borderPaint.setStyle(Paint.Style.STROKE);
        this.borderPaint.setStrokeWidth(1f);

        this.path = new Path();
    }

    private static int createAnchorPointX(final View parentView, final PopupWindow popupWindow){
        final int[] outLocation0 = new int[2];
        parentView.getLocationOnScreen(outLocation0);
        final int[] outLocation1 = new int[2];
        popupWindow.getContentView().getLocationOnScreen(outLocation1);
        return (outLocation0[0] - outLocation1[0]) + (int) (parentView.getWidth() / 2f);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        final int tipSize = ColorButton.PADDING_TOP;

        final Rect bounds = getBounds();

        final int left = bounds.left;
        final int top = bounds.top + tipSize;
        final int right = bounds.right - 1;
        final int bottom = bounds.bottom - 1;


        path.reset();
        path.moveTo(left, top);

        final int diffX = createAnchorPointX(parentView, popupWindow);
        path.lineTo(left + diffX, top);
        path.lineTo(left + diffX + tipSize / 2, top - tipSize);
        path.lineTo(left + diffX + tipSize, top);

        path.lineTo(right, top);
        path.lineTo(right, bottom);
        path.lineTo(left, bottom);
        path.close();

        canvas.drawPath(path, borderPaint);
    }
}

