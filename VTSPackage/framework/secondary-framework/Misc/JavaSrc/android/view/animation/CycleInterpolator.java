// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package android.view.animation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

// Referenced classes of package android.view.animation:
//            Interpolator

public class CycleInterpolator
    implements Interpolator
{

    private float mCycles;

    public CycleInterpolator(float f)
    {
        mCycles = f;
    }

    public CycleInterpolator(Context context, AttributeSet attributeset)
    {
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.CycleInterpolator);
        mCycles = typedarray.getFloat(0, 1.0F);
        typedarray.recycle();
    }

    public float getInterpolation(float f)
    {
        return (float)Math.sin(3.1415926535897931D * (double)(2.0F * mCycles) * (double)f);
    }
}
