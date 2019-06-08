package com.example.administrator.penapp.DIY_View;

import android.widget.RelativeLayout;

import com.mingle.sweetpick.Delegate;
import com.mingle.sweetpick.SweetSheet;

public class diy_SweetSheet extends SweetSheet {
    Delegate mDelegate;
    public diy_SweetSheet(RelativeLayout parentVG) {
        super(parentVG);
    }

    public Delegate getDelegate() {

        return mDelegate;
    }
}
