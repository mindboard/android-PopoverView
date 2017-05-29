package com.mindboardapps.android.example.popover;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

/**
 * Copyright © 2017 tomoaki oshima All Rights Reserved.
 */

public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        if( menu!=null ){
            final MenuItem branchColorMenuItem = menu.findItem(R.id.action_settings);

            if( branchColorMenuItem!=null ){
                final ShapeDrawable icon = new ShapeDrawable(new SettingsShape());
                int abHeight = getSupportActionBar().getHeight();
                icon.getShape().resize(abHeight,abHeight);
                branchColorMenuItem.setIcon(icon);
            }
        }

        return true;
    }

    @Override
    public final boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        if( id == R.id.action_settings ){
            showColorChooserPopupWindow();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void showColorChooserPopupWindow() {
        final PopupWindow popupWindow = new PopupWindow(this);

        final View parentView = findViewById(R.id.action_settings);

        popupWindow.setBackgroundDrawable(new BorderDrawable(parentView, popupWindow, Color.argb(0,0,0,0)));

        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        View popupLayout = getPopupLayout();
        popupWindow.setContentView(popupLayout);

        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAsDropDown(parentView);
    }



    private View popupLayout;
    private View getPopupLayout() {
        if (popupLayout == null) {
            popupLayout = getLayoutInflater().inflate(R.layout.popover_vew, null);

            final int[] ids = new int[]{
                    R.id.action_color_0,
                    R.id.action_color_1,
                    R.id.action_color_2,
                    R.id.action_color_3,
                    R.id.action_color_4
            };

            final int[] colors = new int[]{
                    Color.argb(255,100,100,100),
                    Color.argb(255,100,120,100),
                    Color.argb(255,100,120,120),
                    Color.argb(255,120,100,100),
                    Color.argb(255,120,120,100)
            };

            for(int i=0; i<ids.length; i++){
                final int id = ids[i];
                final int color = colors[i];
                final ColorButton button = (ColorButton)popupLayout.findViewById(id);
                button.setColor(color);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(final View view) {
                        // do something
                    }
                });
            }

        }
        return popupLayout;
    }
}
