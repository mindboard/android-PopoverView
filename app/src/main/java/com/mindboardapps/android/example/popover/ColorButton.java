package com.mindboardapps.android.example.popover;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

/**
 * Copyright © 2017 tomoaki oshima All Rights Reserved.
 */

public class ColorButton extends AppCompatButton {
    public static int PADDING_TOP = 6;

    private static String SVG_OVAL = "M 12.0,3.0 C 16.970562,3.0 21.0,7.029437 21.0,12.0 C 21.0,16.970562 16.970562,21.0 12.0,21.0 C 7.029437,21.0 3.0,16.970562 3.0,12.0 C 3.0,7.029437 7.029437,3.0 12.0,3.0 z";

    private final Rect mRectF;
    private final Path path;
    private final Paint paint;

    public ColorButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mRectF = new Rect();

        final SVGParser p = new SVGParser();
        this.path = p.parse(SVG_OVAL);

        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStyle( Paint.Style.FILL );
    }


    @Override
    public final void setPressed(final boolean pressed) {
        if (pressed && getParent() instanceof View && ((View) getParent()).isPressed()) {
            return;
        }
        super.setPressed(pressed);
    }


    private int color;

    public final void setColor(final int color) {
        this.color = color;
    }

    public final int getColor() {
        return color;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        final int w = getWidth();
        final int h = getHeight();

        if (w > 0 && h > 0) {
            final int edgeSize = Math.min(w, h);

            final int margin = (int) (edgeSize * 0.1f);

            final int marginTop = margin + PADDING_TOP;
            final int marginBottom = margin;
            final int marginLeft = margin;
            final int marginRight = margin;


            final int myW = (w - marginRight) - marginLeft;
            final int myH = (h - marginBottom) - marginTop;

            final int myEdge = Math.min(myW, myH);

            final int additionalMarginLeftAndRight = (myW - myEdge) / 2;
            final int additionalMarginTopAndBottom = (myH - myEdge) / 2;


            mRectF.set(
                    marginLeft + additionalMarginLeftAndRight,
                    marginTop + additionalMarginTopAndBottom,
                    w - (marginRight + additionalMarginLeftAndRight),
                    h - (marginBottom + additionalMarginTopAndBottom));

            paint.setColor(getColor());
            canvas.drawPath(
                    IconPathBuilder.createPath(path, mRectF),
                    paint);
        }
    }
}

