// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package android.view.animation;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.view.animation:
//            Animation, Transformation, Interpolator

public class AnimationSet extends Animation
{

    private static final int PROPERTY_CHANGE_BOUNDS_MASK = 128;
    private static final int PROPERTY_DURATION_MASK = 32;
    private static final int PROPERTY_FILL_AFTER_MASK = 1;
    private static final int PROPERTY_FILL_BEFORE_MASK = 2;
    private static final int PROPERTY_MORPH_MATRIX_MASK = 64;
    private static final int PROPERTY_REPEAT_MODE_MASK = 4;
    private static final int PROPERTY_SHARE_INTERPOLATOR_MASK = 16;
    private static final int PROPERTY_START_OFFSET_MASK = 8;
    private ArrayList mAnimations;
    private boolean mDirty;
    private int mFlags;
    private boolean mHasAlpha;
    private long mLastEnd;
    private long mStoredOffsets[];
    private Transformation mTempTransformation;

    public AnimationSet(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mFlags = 0;
        mAnimations = new ArrayList();
        mTempTransformation = new Transformation();
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.AnimationSet);
        setFlag(16, typedarray.getBoolean(1, true));
        init();
        if (context.getApplicationInfo().targetSdkVersion >= 14)
        {
            if (typedarray.hasValue(0))
                mFlags = 0x20 | mFlags;
            if (typedarray.hasValue(2))
                mFlags = 2 | mFlags;
            if (typedarray.hasValue(3))
                mFlags = 1 | mFlags;
            if (typedarray.hasValue(5))
                mFlags = 4 | mFlags;
            if (typedarray.hasValue(4))
                mFlags = 8 | mFlags;
        }
        typedarray.recycle();
    }

    public AnimationSet(boolean flag)
    {
        mFlags = 0;
        mAnimations = new ArrayList();
        mTempTransformation = new Transformation();
        setFlag(16, flag);
        init();
    }

    private void init()
    {
        super.mStartTime = 0L;
    }

    private void setFlag(int i, boolean flag)
    {
        if (flag)
        {
            mFlags = i | mFlags;
            return;
        } else
        {
            mFlags = mFlags & ~i;
            return;
        }
    }

    public void addAnimation(Animation animation)
    {
        mAnimations.add(animation);
        boolean flag;
        if ((0x40 & mFlags) == 0)
            flag = true;
        else
            flag = false;
        if (flag && animation.willChangeTransformationMatrix())
            mFlags = 0x40 | mFlags;
        int i = 0x80 & mFlags;
        boolean flag1 = false;
        if (i == 0)
            flag1 = true;
        if (flag1 && animation.willChangeBounds())
            mFlags = 0x80 | mFlags;
        if ((0x20 & mFlags) == 32)
            mLastEnd = super.mStartOffset + super.mDuration;
        else
        if (mAnimations.size() == 1)
        {
            super.mDuration = animation.getStartOffset() + animation.getDuration();
            mLastEnd = super.mStartOffset + super.mDuration;
        } else
        {
            mLastEnd = Math.max(mLastEnd, animation.getStartOffset() + animation.getDuration());
            super.mDuration = mLastEnd - super.mStartOffset;
        }
        mDirty = true;
    }

    protected volatile Animation clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    protected AnimationSet clone()
        throws CloneNotSupportedException
    {
        AnimationSet animationset = (AnimationSet)super.clone();
        animationset.mTempTransformation = new Transformation();
        animationset.mAnimations = new ArrayList();
        int i = mAnimations.size();
        ArrayList arraylist = mAnimations;
        for (int j = 0; j < i; j++)
            animationset.mAnimations.add(((Animation)arraylist.get(j)).clone());

        return animationset;
    }

    protected volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public long computeDurationHint()
    {
        long l = 0L;
        int i = mAnimations.size();
        ArrayList arraylist = mAnimations;
        for (int j = i - 1; j >= 0; j--)
        {
            long l1 = ((Animation)arraylist.get(j)).computeDurationHint();
            if (l1 > l)
                l = l1;
        }

        return l;
    }

    public List getAnimations()
    {
        return mAnimations;
    }

    public long getDuration()
    {
        ArrayList arraylist = mAnimations;
        int i = arraylist.size();
        long l = 0L;
        boolean flag;
        if ((0x20 & mFlags) == 32)
            flag = true;
        else
            flag = false;
        if (flag)
        {
            l = super.mDuration;
        } else
        {
            for (int j = 0; j < i; j++)
                l = Math.max(l, ((Animation)arraylist.get(j)).getDuration());

        }
        return l;
    }

    public long getStartTime()
    {
        long l = 0x7fffffffffffffffL;
        int i = mAnimations.size();
        ArrayList arraylist = mAnimations;
        for (int j = 0; j < i; j++)
            l = Math.min(l, ((Animation)arraylist.get(j)).getStartTime());

        return l;
    }

    public boolean getTransformation(long l, Transformation transformation)
    {
        int i = mAnimations.size();
        ArrayList arraylist = mAnimations;
        Transformation transformation1 = mTempTransformation;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = true;
        transformation.clear();
        for (int j = i - 1; j >= 0; j--)
        {
            Animation animation = (Animation)arraylist.get(j);
            transformation1.clear();
            if (!animation.getTransformation(l, transformation1, getScaleFactor()) && !flag)
                flag = false;
            else
                flag = true;
            transformation.compose(transformation1);
            if (!flag1 && !animation.hasStarted())
                flag1 = false;
            else
                flag1 = true;
            if (animation.hasEnded() && flag2)
                flag2 = true;
            else
                flag2 = false;
        }

        if (flag1 && !super.mStarted)
        {
            if (super.mListener != null)
                super.mListener.onAnimationStart(this);
            super.mStarted = true;
        }
        if (flag2 != super.mEnded)
        {
            if (super.mListener != null)
                super.mListener.onAnimationEnd(this);
            super.mEnded = flag2;
        }
        return flag;
    }

    public boolean hasAlpha()
    {
        if (!mDirty) goto _L2; else goto _L1
_L1:
        int i;
        ArrayList arraylist;
        int j;
        mHasAlpha = false;
        mDirty = false;
        i = mAnimations.size();
        arraylist = mAnimations;
        j = 0;
_L7:
        if (j >= i) goto _L2; else goto _L3
_L3:
        if (!((Animation)arraylist.get(j)).hasAlpha()) goto _L5; else goto _L4
_L4:
        mHasAlpha = true;
_L2:
        return mHasAlpha;
_L5:
        j++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public void initialize(int i, int j, int k, int l)
    {
        super.initialize(i, j, k, l);
        boolean flag;
        if ((0x20 & mFlags) == 32)
            flag = true;
        else
            flag = false;
        boolean flag1;
        if ((1 & mFlags) == 1)
            flag1 = true;
        else
            flag1 = false;
        boolean flag2;
        if ((2 & mFlags) == 2)
            flag2 = true;
        else
            flag2 = false;
        boolean flag3;
        if ((4 & mFlags) == 4)
            flag3 = true;
        else
            flag3 = false;
        boolean flag4;
        if ((0x10 & mFlags) == 16)
            flag4 = true;
        else
            flag4 = false;
        boolean flag5;
        if ((8 & mFlags) == 8)
            flag5 = true;
        else
            flag5 = false;
        if (flag4)
            ensureInterpolator();
        ArrayList arraylist = mAnimations;
        int i1 = arraylist.size();
        long l1 = super.mDuration;
        boolean flag6 = super.mFillAfter;
        boolean flag7 = super.mFillBefore;
        int j1 = super.mRepeatMode;
        Interpolator interpolator = super.mInterpolator;
        long l2 = super.mStartOffset;
        long al[] = mStoredOffsets;
        if (flag5)
        {
            if (al == null || al.length != i1)
            {
                al = new long[i1];
                mStoredOffsets = al;
            }
        } else
        if (al != null)
        {
            mStoredOffsets = null;
            al = null;
        }
        for (int k1 = 0; k1 < i1; k1++)
        {
            Animation animation = (Animation)arraylist.get(k1);
            if (flag)
                animation.setDuration(l1);
            if (flag1)
                animation.setFillAfter(flag6);
            if (flag2)
                animation.setFillBefore(flag7);
            if (flag3)
                animation.setRepeatMode(j1);
            if (flag4)
                animation.setInterpolator(interpolator);
            if (flag5)
            {
                long l3 = animation.getStartOffset();
                animation.setStartOffset(l3 + l2);
                al[k1] = l3;
            }
            animation.initialize(i, j, k, l);
        }

    }

    public void initializeInvalidateRegion(int i, int j, int k, int l)
    {
        RectF rectf = super.mPreviousRegion;
        rectf.set(i, j, k, l);
        rectf.inset(-1F, -1F);
        if (super.mFillBefore)
        {
            int i1 = mAnimations.size();
            ArrayList arraylist = mAnimations;
            Transformation transformation = mTempTransformation;
            Transformation transformation1 = super.mPreviousTransformation;
            for (int j1 = i1 - 1; j1 >= 0; j1--)
            {
                Animation animation = (Animation)arraylist.get(j1);
                if (animation.isFillEnabled() && !animation.getFillBefore() && animation.getStartOffset() != 0L)
                    continue;
                transformation.clear();
                Interpolator interpolator = animation.mInterpolator;
                float f;
                if (interpolator != null)
                    f = interpolator.getInterpolation(0.0F);
                else
                    f = 0.0F;
                animation.applyTransformation(f, transformation);
                transformation1.compose(transformation);
            }

        }
    }

    public void reset()
    {
        super.reset();
        restoreChildrenStartOffset();
    }

    void restoreChildrenStartOffset()
    {
        long al[] = mStoredOffsets;
        if (al != null)
        {
            ArrayList arraylist = mAnimations;
            int i = arraylist.size();
            for (int j = 0; j < i; j++)
                ((Animation)arraylist.get(j)).setStartOffset(al[j]);

        }
    }

    public void restrictDuration(long l)
    {
        super.restrictDuration(l);
        ArrayList arraylist = mAnimations;
        int i = arraylist.size();
        for (int j = 0; j < i; j++)
            ((Animation)arraylist.get(j)).restrictDuration(l);

    }

    public void scaleCurrentDuration(float f)
    {
        ArrayList arraylist = mAnimations;
        int i = arraylist.size();
        for (int j = 0; j < i; j++)
            ((Animation)arraylist.get(j)).scaleCurrentDuration(f);

    }

    public void setDuration(long l)
    {
        mFlags = 0x20 | mFlags;
        super.setDuration(l);
        mLastEnd = super.mStartOffset + super.mDuration;
    }

    public void setFillAfter(boolean flag)
    {
        mFlags = 1 | mFlags;
        super.setFillAfter(flag);
    }

    public void setFillBefore(boolean flag)
    {
        mFlags = 2 | mFlags;
        super.setFillBefore(flag);
    }

    public void setRepeatMode(int i)
    {
        mFlags = 4 | mFlags;
        super.setRepeatMode(i);
    }

    public void setStartOffset(long l)
    {
        mFlags = 8 | mFlags;
        super.setStartOffset(l);
    }

    public void setStartTime(long l)
    {
        super.setStartTime(l);
        int i = mAnimations.size();
        ArrayList arraylist = mAnimations;
        for (int j = 0; j < i; j++)
            ((Animation)arraylist.get(j)).setStartTime(l);

    }

    public boolean willChangeBounds()
    {
        return (0x80 & mFlags) == 128;
    }

    public boolean willChangeTransformationMatrix()
    {
        return (0x40 & mFlags) == 64;
    }
}
