// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

// Referenced classes of package android.widget:
//            FrameLayout

public class ViewAnimator extends FrameLayout
{

    boolean mAnimateFirstTime;
    boolean mFirstTime;
    Animation mInAnimation;
    Animation mOutAnimation;
    int mWhichChild;

    public ViewAnimator(Context context)
    {
        FrameLayout(context);
        mWhichChild = 0;
        mFirstTime = true;
        mAnimateFirstTime = true;
        initViewAnimator(context, null);
    }

    public ViewAnimator(Context context, AttributeSet attributeset)
    {
        FrameLayout(context, attributeset);
        mWhichChild = 0;
        mFirstTime = true;
        mAnimateFirstTime = true;
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ViewAnimator);
        int i = typedarray.getResourceId(0, 0);
        if (i > 0)
            setInAnimation(context, i);
        int j = typedarray.getResourceId(1, 0);
        if (j > 0)
            setOutAnimation(context, j);
        setAnimateFirstView(typedarray.getBoolean(2, true));
        typedarray.recycle();
        initViewAnimator(context, attributeset);
    }

    private void initViewAnimator(Context context, AttributeSet attributeset)
    {
        if (attributeset == null)
        {
            super.mMeasureAllChildren = true;
            return;
        } else
        {
            TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.FrameLayout);
            setMeasureAllChildren(typedarray.getBoolean(1, true));
            typedarray.recycle();
            return;
        }
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        addView(view, i, layoutparams);
        if (getChildCount() == 1)
            view.setVisibility(0);
        else
            view.setVisibility(8);
        if (i >= 0 && mWhichChild >= i)
            setDisplayedChild(1 + mWhichChild);
    }

    public boolean getAnimateFirstView()
    {
        return mAnimateFirstTime;
    }

    public int getBaseline()
    {
        if (getCurrentView() != null)
            return getCurrentView().getBaseline();
        else
            return getBaseline();
    }

    public View getCurrentView()
    {
        return getChildAt(mWhichChild);
    }

    public int getDisplayedChild()
    {
        return mWhichChild;
    }

    public Animation getInAnimation()
    {
        return mInAnimation;
    }

    public Animation getOutAnimation()
    {
        return mOutAnimation;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        onInitializeAccessibilityEvent(accessibilityevent);
        accessibilityevent.setClassName(android/widget/ViewAnimator.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        accessibilitynodeinfo.setClassName(android/widget/ViewAnimator.getName());
    }

    public void removeAllViews()
    {
        removeAllViews();
        mWhichChild = 0;
        mFirstTime = true;
    }

    public void removeView(View view)
    {
        int i = indexOfChild(view);
        if (i >= 0)
            removeViewAt(i);
    }

    public void removeViewAt(int i)
    {
        removeViewAt(i);
        int j = getChildCount();
        if (j == 0)
        {
            mWhichChild = 0;
            mFirstTime = true;
        } else
        {
            if (mWhichChild >= j)
            {
                setDisplayedChild(j - 1);
                return;
            }
            if (mWhichChild == i)
            {
                setDisplayedChild(mWhichChild);
                return;
            }
        }
    }

    public void removeViewInLayout(View view)
    {
        removeView(view);
    }

    public void removeViews(int i, int j)
    {
        removeViews(i, j);
        if (getChildCount() == 0)
        {
            mWhichChild = 0;
            mFirstTime = true;
        } else
        if (mWhichChild >= i && mWhichChild < i + j)
        {
            setDisplayedChild(mWhichChild);
            return;
        }
    }

    public void removeViewsInLayout(int i, int j)
    {
        removeViews(i, j);
    }

    public void setAnimateFirstView(boolean flag)
    {
        mAnimateFirstTime = flag;
    }

    public void setDisplayedChild(int i)
    {
        mWhichChild = i;
        if (i >= getChildCount())
            mWhichChild = 0;
        else
        if (i < 0)
            mWhichChild = -1 + getChildCount();
        View view = getFocusedChild();
        boolean flag = false;
        if (view != null)
            flag = true;
        showOnly(mWhichChild);
        if (flag)
            requestFocus(2);
    }

    public void setInAnimation(Context context, int i)
    {
        setInAnimation(AnimationUtils.loadAnimation(context, i));
    }

    public void setInAnimation(Animation animation)
    {
        mInAnimation = animation;
    }

    public void setOutAnimation(Context context, int i)
    {
        setOutAnimation(AnimationUtils.loadAnimation(context, i));
    }

    public void setOutAnimation(Animation animation)
    {
        mOutAnimation = animation;
    }

    public void showNext()
    {
        setDisplayedChild(1 + mWhichChild);
    }

    void showOnly(int i)
    {
        boolean flag;
        if (mFirstTime && !mAnimateFirstTime)
            flag = false;
        else
            flag = true;
        showOnly(i, flag);
    }

    void showOnly(int i, boolean flag)
    {
        int j = getChildCount();
        for (int k = 0; k < j; k++)
        {
            View view = getChildAt(k);
            if (k == i)
            {
                if (flag && mInAnimation != null)
                    view.startAnimation(mInAnimation);
                view.setVisibility(0);
                mFirstTime = false;
                continue;
            }
            if (flag && mOutAnimation != null && view.getVisibility() == 0)
                view.startAnimation(mOutAnimation);
            else
            if (view.getAnimation() == mInAnimation)
                view.clearAnimation();
            view.setVisibility(8);
        }

    }

    public void showPrevious()
    {
        setDisplayedChild(-1 + mWhichChild);
    }
}
