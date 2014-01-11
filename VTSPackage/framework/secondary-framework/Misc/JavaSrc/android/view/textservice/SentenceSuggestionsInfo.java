// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package android.view.textservice;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

// Referenced classes of package android.view.textservice:
//            SuggestionsInfo

public final class SentenceSuggestionsInfo
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SentenceSuggestionsInfo createFromParcel(Parcel parcel)
        {
            return new SentenceSuggestionsInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SentenceSuggestionsInfo[] newArray(int i)
        {
            return new SentenceSuggestionsInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mLengths[];
    private final int mOffsets[];
    private final SuggestionsInfo mSuggestionsInfos[];

    public SentenceSuggestionsInfo(Parcel parcel)
    {
        mSuggestionsInfos = new SuggestionsInfo[parcel.readInt()];
        parcel.readTypedArray(mSuggestionsInfos, SuggestionsInfo.CREATOR);
        mOffsets = new int[mSuggestionsInfos.length];
        parcel.readIntArray(mOffsets);
        mLengths = new int[mSuggestionsInfos.length];
        parcel.readIntArray(mLengths);
    }

    public SentenceSuggestionsInfo(SuggestionsInfo asuggestionsinfo[], int ai[], int ai1[])
    {
        if (asuggestionsinfo != null && ai != null && ai1 != null)
        {
            if (asuggestionsinfo.length == ai.length && ai.length == ai1.length)
            {
                int i = asuggestionsinfo.length;
                mSuggestionsInfos = (SuggestionsInfo[])Arrays.copyOf(asuggestionsinfo, i);
                mOffsets = Arrays.copyOf(ai, i);
                mLengths = Arrays.copyOf(ai1, i);
                return;
            } else
            {
                throw new IllegalArgumentException();
            }
        } else
        {
            throw new NullPointerException();
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public int getLengthAt(int i)
    {
        if (i >= 0 && i < mLengths.length)
            return mLengths[i];
        else
            return -1;
    }

    public int getOffsetAt(int i)
    {
        if (i >= 0 && i < mOffsets.length)
            return mOffsets[i];
        else
            return -1;
    }

    public int getSuggestionsCount()
    {
        return mSuggestionsInfos.length;
    }

    public SuggestionsInfo getSuggestionsInfoAt(int i)
    {
        if (i >= 0 && i < mSuggestionsInfos.length)
            return mSuggestionsInfos[i];
        else
            return null;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mSuggestionsInfos.length);
        parcel.writeTypedArray(mSuggestionsInfos, 0);
        parcel.writeIntArray(mOffsets);
        parcel.writeIntArray(mLengths);
    }

}
