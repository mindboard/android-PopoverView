package com.mindboardapps.android.example.popover;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Rect;

/**
 * Copyright © 2017 tomoaki oshima All Rights Reserved.
 */

class IconPathBuilder {

    public static Path createPath( final Path path, final Rect tmpBounds ){
        final int w = tmpBounds.width();
        final int h = tmpBounds.height();

        final Matrix matrix = new Matrix();
        //matrix.reset();
        matrix.postScale(w/24f,h/24f);
        matrix.postTranslate(tmpBounds.left, tmpBounds.top);

        final Path newPath = new Path(path);
        newPath.transform(matrix);
        return newPath;
    }
}
