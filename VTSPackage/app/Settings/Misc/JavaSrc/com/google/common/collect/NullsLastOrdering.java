// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package com.google.common.collect;

import java.io.Serializable;

// Referenced classes of package com.google.common.collect:
//            Ordering

final class NullsLastOrdering extends Ordering
    implements Serializable
{

    private static final long serialVersionUID;
    final Ordering ordering;

    NullsLastOrdering(Ordering ordering1)
    {
        ordering = ordering1;
    }

    public int compare(Object obj, Object obj1)
    {
        if (obj == obj1)
            return 0;
        if (obj == null)
            return 1;
        if (obj1 == null)
            return -1;
        else
            return ordering.compare(obj, obj1);
    }

    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        if (obj instanceof NullsLastOrdering)
        {
            NullsLastOrdering nullslastordering = (NullsLastOrdering)obj;
            return ordering.equals(nullslastordering.ordering);
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return 0xc9177248 ^ ordering.hashCode();
    }

    public Ordering nullsFirst()
    {
        return ordering.nullsFirst();
    }

    public Ordering nullsLast()
    {
        return this;
    }

    public Ordering reverse()
    {
        return ordering.reverse().nullsFirst();
    }

    public String toString()
    {
        return (new StringBuilder()).append(ordering).append(".nullsLast()").toString();
    }
}
