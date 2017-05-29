package com.mindboardapps.android.example.popover;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;

/**
 * Copyright © 2017 tomoaki oshima All Rights Reserved.
 */

final class SettingsShape extends Shape {

    private static String SVG_SETTINGS = "M 10.437166400997627,3.1367302228901277 L 13.562833599002374,3.1367302228901277 L 14.29610059419054,6.45672280493228 L 17.162187927159415,4.627631601399074 L 19.372368398600926,6.837812072840586 L 17.54327719506772,9.703899405809462 L 20.86326977710987,10.437166400997627 L 20.86326977710987,13.562833599002373 L 17.54327719506772,14.296100594190538 L 19.372368398600926,17.162187927159415 L 17.162187927159415,19.372368398600926 L 14.29610059419054,17.54327719506772 L 13.562833599002374,20.86326977710987 L 10.437166400997627,20.86326977710987 L 9.703899405809462,17.54327719506772 L 6.837812072840587,19.372368398600926 L 4.6276316013990755,17.162187927159415 L 6.45672280493228,14.29610059419054 L 3.1367302228901277,13.562833599002374 L 3.1367302228901277,10.437166400997627 L 6.456722804932278,9.703899405809464 L 4.627631601399072,6.837812072840587 L 6.837812072840583,4.6276316013990755 L 9.703899405809462,6.456722804932279 z";

        private final Path path0;
        SettingsShape(){
            final SVGParser p = new SVGParser();
            this.path0 = p.parse(SVG_SETTINGS);
        }


        private float iconWidth;
        private float iconHeight;
        private float padding;


        @Override
        protected void onResize(float width, float height) {
            super.onResize(width, height);

            this.iconWidth = width;
            this.iconHeight = height;
            this.padding = iconWidth*0.2f;
        }

        @Override
        public void draw(Canvas canvas, Paint paint) {
            // 1)
            final Path path2 = IconPathBuilder.createPath(
                    path0,
                    new Rect(
                            0,0,
                            (int)(iconWidth-padding*2),
                            (int)(iconHeight-padding*2))
            );

            // 2)
            // menuItemにセットされるアイコン＝drawable は
            // 原点(0,0)が中央に配置される点に注意
            // なのでその分平行移動しておく
            final float halfW = (iconWidth-padding*2f)/2f;
            final float halfH = (iconHeight-padding*2)/2f;
            final Matrix matrix = new Matrix();
            matrix.setTranslate(-halfW,-halfH);
            path2.transform(matrix);

            // 3)
            canvas.drawPath( path2, paint);
        }
}
