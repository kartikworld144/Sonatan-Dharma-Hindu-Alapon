package com.kartikworld.bangali;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class TypeWriter extends androidx.appcompat.widget.AppCompatTextView {

    private CharSequence myText;
    private int anInt;
    private long aLong=150;

    public TypeWriter(Context context) {
        super(context);
    }

    public TypeWriter(Context context, @Nullable AttributeSet attributeSet){
        super(context,attributeSet);

    }
    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            setText(myText.subSequence(0,anInt++));
            if (anInt<myText.length()){
                handler.postDelayed(runnable,aLong);
            }
        }
    };
    public void AnimateText(CharSequence myTxt){
        myText= myTxt;
        anInt=0;
        setText("");
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable,aLong);

    }
    public void SetCharacterDelay(long lon){
        aLong=lon;

    }
}
