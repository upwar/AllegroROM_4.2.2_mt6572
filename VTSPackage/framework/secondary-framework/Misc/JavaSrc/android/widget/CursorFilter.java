// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package android.widget;

import android.database.Cursor;

// Referenced classes of package android.widget:
//            Filter

class CursorFilter extends Filter
{
    static interface CursorFilterClient
    {

        public abstract void changeCursor(Cursor cursor);

        public abstract CharSequence convertToString(Cursor cursor);

        public abstract Cursor getCursor();

        public abstract Cursor runQueryOnBackgroundThread(CharSequence charsequence);
    }


    CursorFilterClient mClient;

    CursorFilter(CursorFilterClient cursorfilterclient)
    {
        mClient = cursorfilterclient;
    }

    public CharSequence convertResultToString(Object obj)
    {
        return mClient.convertToString((Cursor)obj);
    }

    protected Filter.FilterResults performFiltering(CharSequence charsequence)
    {
        Cursor cursor = mClient.runQueryOnBackgroundThread(charsequence);
        Filter.FilterResults filterresults = new Filter.FilterResults();
        if (cursor != null)
        {
            filterresults.count = cursor.getCount();
            filterresults.values = cursor;
            return filterresults;
        } else
        {
            filterresults.count = 0;
            filterresults.values = null;
            return filterresults;
        }
    }

    protected void publishResults(CharSequence charsequence, Filter.FilterResults filterresults)
    {
        Cursor cursor = mClient.getCursor();
        if (filterresults.values != null && filterresults.values != cursor)
            mClient.changeCursor((Cursor)filterresults.values);
    }
}
