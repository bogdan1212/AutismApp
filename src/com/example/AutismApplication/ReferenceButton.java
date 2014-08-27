package com.example.AutismApplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Bogdan on 18.08.14.
 */
public class ReferenceButton extends Button {


        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        int position;

        public ReferenceButton(Context context) {
            super(context);
        }

        public ReferenceButton(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public ReferenceButton(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }
}

