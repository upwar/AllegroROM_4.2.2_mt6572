// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package android.widget;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

public interface Adapter
{

    public static final int IGNORE_ITEM_VIEW_TYPE = -1;
    public static final int NO_SELECTION = 0x80000000;

    public abstract int getCount();

    public abstract Object getItem(int i);

    public abstract long getItemId(int i);

    public abstract int getItemViewType(int i);

    public abstract View getView(int i, View view, ViewGroup viewgroup);

    public abstract int getViewTypeCount();

    public abstract boolean hasStableIds();

    public abstract boolean isEmpty();

    public abstract void registerDataSetObserver(DataSetObserver datasetobserver);

    public abstract void unregisterDataSetObserver(DataSetObserver datasetobserver);
}
