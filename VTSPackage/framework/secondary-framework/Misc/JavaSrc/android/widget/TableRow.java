// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.widget:
//            LinearLayout

public class TableRow extends LinearLayout
{
    private class ChildrenTracker
        implements android.view.ViewGroup.OnHierarchyChangeListener
    {

        private android.view.ViewGroup.OnHierarchyChangeListener listener;
        final TableRow this$0;

        private void setOnHierarchyChangeListener(android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
        {
            listener = onhierarchychangelistener;
        }

        public void onChildViewAdded(View view, View view1)
        {
            mColumnToChildIndex = null;
            if (listener != null)
                listener.onChildViewAdded(view, view1);
        }

        public void onChildViewRemoved(View view, View view1)
        {
            mColumnToChildIndex = null;
            if (listener != null)
                listener.onChildViewRemoved(view, view1);
        }


        private ChildrenTracker()
        {
            this$0 = TableRow.this;
            super();
        }

    }

    public static class LayoutParams extends LinearLayout.LayoutParams
    {

        private static final int LOCATION = 0;
        private static final int LOCATION_NEXT = 1;
        public int column;
        private int mOffset[];
        public int span;

        protected void setBaseAttributes(TypedArray typedarray, int i, int j)
        {
            if (typedarray.hasValue(i))
                super.width = typedarray.getLayoutDimension(i, "layout_width");
            else
                super.width = -1;
            if (typedarray.hasValue(j))
            {
                super.height = typedarray.getLayoutDimension(j, "layout_height");
                return;
            } else
            {
                super.height = -2;
                return;
            }
        }


        public LayoutParams()
        {
            super(-1, -2);
            mOffset = new int[2];
            column = -1;
            span = 1;
        }

        public LayoutParams(int i)
        {
            this();
            column = i;
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
            mOffset = new int[2];
            column = -1;
            span = 1;
        }

        public LayoutParams(int i, int j, float f)
        {
            super(i, j, f);
            mOffset = new int[2];
            column = -1;
            span = 1;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mOffset = new int[2];
            TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TableRow_Cell);
            column = typedarray.getInt(0, -1);
            span = typedarray.getInt(1, 1);
            if (span <= 1)
                span = 1;
            typedarray.recycle();
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            mOffset = new int[2];
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            mOffset = new int[2];
        }
    }


    private ChildrenTracker mChildrenTracker;
    private SparseIntArray mColumnToChildIndex;
    private int mColumnWidths[];
    private int mConstrainedColumnWidths[];
    private int mNumColumns;

    public TableRow(Context context)
    {
        super(context);
        mNumColumns = 0;
        initTableRow();
    }

    public TableRow(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mNumColumns = 0;
        initTableRow();
    }

    private void initTableRow()
    {
        android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener = super.mOnHierarchyChangeListener;
        mChildrenTracker = new ChildrenTracker();
        if (onhierarchychangelistener != null)
            mChildrenTracker.setOnHierarchyChangeListener(onhierarchychangelistener);
        super.setOnHierarchyChangeListener(mChildrenTracker);
    }

    private void mapIndexAndColumns()
    {
        if (mColumnToChildIndex == null)
        {
            int i = 0;
            int j = getChildCount();
            mColumnToChildIndex = new SparseIntArray();
            SparseIntArray sparseintarray = mColumnToChildIndex;
            for (int k = 0; k < j; k++)
            {
                LayoutParams layoutparams = (LayoutParams)getChildAt(k).getLayoutParams();
                if (layoutparams.column >= i)
                    i = layoutparams.column;
                for (int l = 0; l < layoutparams.span;)
                {
                    int i1 = i + 1;
                    sparseintarray.put(i, k);
                    l++;
                    i = i1;
                }

            }

            mNumColumns = i;
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected LinearLayout.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams();
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public volatile LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected LinearLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new LayoutParams(layoutparams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    int getChildrenSkipCount(View view, int i)
    {
        return -1 + ((LayoutParams)view.getLayoutParams()).span;
    }

    int[] getColumnsWidths(int i)
    {
        int j = getVirtualChildCount();
        if (mColumnWidths == null || j != mColumnWidths.length)
            mColumnWidths = new int[j];
        int ai[] = mColumnWidths;
        for (int k = 0; k < j; k++)
        {
            View view = getVirtualChildAt(k);
            if (view != null && view.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                if (layoutparams.span == 1)
                {
                    int l;
                    switch (((android.view.ViewGroup.LayoutParams) (layoutparams)).width)
                    {
                    case -1: 
                        l = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
                        break;

                    case -2: 
                        l = getChildMeasureSpec(i, 0, -2);
                        break;

                    default:
                        l = android.view.View.MeasureSpec.makeMeasureSpec(((android.view.ViewGroup.LayoutParams) (layoutparams)).width, 0x40000000);
                        break;
                    }
                    view.measure(l, l);
                    ai[k] = view.getMeasuredWidth() + ((android.view.ViewGroup.MarginLayoutParams) (layoutparams)).leftMargin + ((android.view.ViewGroup.MarginLayoutParams) (layoutparams)).rightMargin;
                } else
                {
                    ai[k] = 0;
                }
            } else
            {
                ai[k] = 0;
            }
        }

        return ai;
    }

    int getLocationOffset(View view)
    {
        return ((LayoutParams)view.getLayoutParams()).mOffset[0];
    }

    int getNextLocationOffset(View view)
    {
        return ((LayoutParams)view.getLayoutParams()).mOffset[1];
    }

    public View getVirtualChildAt(int i)
    {
        if (mColumnToChildIndex == null)
            mapIndexAndColumns();
        int j = mColumnToChildIndex.get(i, -1);
        if (j != -1)
            return getChildAt(j);
        else
            return null;
    }

    public int getVirtualChildCount()
    {
        if (mColumnToChildIndex == null)
            mapIndexAndColumns();
        return mNumColumns;
    }

    void measureChildBeforeLayout(View view, int i, int j, int k, int l, int i1)
    {
        if (mConstrainedColumnWidths != null)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            int j1 = 0x40000000;
            int k1 = 0;
            int l1 = layoutparams.span;
            int ai[] = mConstrainedColumnWidths;
            for (int i2 = 0; i2 < l1; i2++)
                k1 += ai[i + i2];

            int j2 = ((LinearLayout.LayoutParams) (layoutparams)).gravity;
            boolean flag = Gravity.isHorizontal(j2);
            if (flag)
                j1 = 0x80000000;
            view.measure(android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, k1 - ((android.view.ViewGroup.MarginLayoutParams) (layoutparams)).leftMargin - ((android.view.ViewGroup.MarginLayoutParams) (layoutparams)).rightMargin), j1), getChildMeasureSpec(l, i1 + (super.mPaddingTop + super.mPaddingBottom + ((android.view.ViewGroup.MarginLayoutParams) (layoutparams)).topMargin + ((android.view.ViewGroup.MarginLayoutParams) (layoutparams)).bottomMargin), ((android.view.ViewGroup.LayoutParams) (layoutparams)).height));
            if (flag)
            {
                int k2 = view.getMeasuredWidth();
                layoutparams.mOffset[1] = k1 - k2;
                switch (7 & Gravity.getAbsoluteGravity(j2, getLayoutDirection()))
                {
                case 1: // '\001'
                    layoutparams.mOffset[0] = layoutparams.mOffset[1] / 2;
                    return;

                case 5: // '\005'
                    layoutparams.mOffset[0] = layoutparams.mOffset[1];
                    return;

                case 2: // '\002'
                case 3: // '\003'
                case 4: // '\004'
                default:
                    return;
                }
            } else
            {
                int ai1[] = layoutparams.mOffset;
                layoutparams.mOffset[1] = 0;
                ai1[0] = 0;
                return;
            }
        } else
        {
            super.measureChildBeforeLayout(view, i, j, k, l, i1);
            return;
        }
    }

    int measureNullChild(int i)
    {
        return mConstrainedColumnWidths[i];
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(accessibilityevent);
        accessibilityevent.setClassName(android/widget/TableRow.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        accessibilitynodeinfo.setClassName(android/widget/TableRow.getName());
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        layoutHorizontal();
    }

    protected void onMeasure(int i, int j)
    {
        measureHorizontal(i, j);
    }

    void setColumnCollapsed(int i, boolean flag)
    {
        View view = getVirtualChildAt(i);
        if (view != null)
        {
            byte byte0;
            if (flag)
                byte0 = 8;
            else
                byte0 = 0;
            view.setVisibility(byte0);
        }
    }

    void setColumnsWidthConstraints(int ai[])
    {
        if (ai != null && ai.length >= getVirtualChildCount())
        {
            mConstrainedColumnWidths = ai;
            return;
        } else
        {
            throw new IllegalArgumentException("columnWidths should be >= getVirtualChildCount()");
        }
    }

    public void setOnHierarchyChangeListener(android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
    {
        mChildrenTracker.setOnHierarchyChangeListener(onhierarchychangelistener);
    }


/*
    static SparseIntArray access$302(TableRow tablerow, SparseIntArray sparseintarray)
    {
        tablerow.mColumnToChildIndex = sparseintarray;
        return sparseintarray;
    }

*/
}
