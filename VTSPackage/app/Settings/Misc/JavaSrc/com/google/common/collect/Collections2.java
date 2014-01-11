// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package com.google.common.collect;

import com.google.common.base.*;
import java.util.*;

// Referenced classes of package com.google.common.collect:
//            Iterables, Iterators, Lists

public final class Collections2
{
    static class FilteredCollection
        implements Collection
    {

        final Predicate predicate;
        final Collection unfiltered;

        public boolean add(Object obj)
        {
            Preconditions.checkArgument(predicate.apply(obj));
            return unfiltered.add(obj);
        }

        public boolean addAll(Collection collection)
        {
            Object obj;
            for (Iterator iterator1 = collection.iterator(); iterator1.hasNext(); Preconditions.checkArgument(predicate.apply(obj)))
                obj = iterator1.next();

            return unfiltered.addAll(collection);
        }

        public void clear()
        {
            Iterables.removeIf(unfiltered, predicate);
        }

        public boolean contains(Object obj)
        {
            boolean flag;
            boolean flag1;
            boolean flag2;
            try
            {
                flag = predicate.apply(obj);
            }
            catch (NullPointerException nullpointerexception)
            {
                return false;
            }
            catch (ClassCastException classcastexception)
            {
                return false;
            }
            flag1 = false;
            if (!flag)
                break MISSING_BLOCK_LABEL_43;
            flag2 = unfiltered.contains(obj);
            flag1 = false;
            if (flag2)
                flag1 = true;
            return flag1;
        }

        public boolean containsAll(Collection collection)
        {
            for (Iterator iterator1 = collection.iterator(); iterator1.hasNext();)
                if (!contains(iterator1.next()))
                    return false;

            return true;
        }

        FilteredCollection createCombined(Predicate predicate1)
        {
            return new FilteredCollection(unfiltered, Predicates.and(predicate, predicate1));
        }

        public boolean isEmpty()
        {
            return !Iterators.any(unfiltered.iterator(), predicate);
        }

        public Iterator iterator()
        {
            return Iterators.filter(unfiltered.iterator(), predicate);
        }

        public boolean remove(Object obj)
        {
            boolean flag;
            boolean flag1;
            boolean flag2;
            try
            {
                flag = predicate.apply(obj);
            }
            catch (NullPointerException nullpointerexception)
            {
                return false;
            }
            catch (ClassCastException classcastexception)
            {
                return false;
            }
            flag1 = false;
            if (!flag)
                break MISSING_BLOCK_LABEL_43;
            flag2 = unfiltered.remove(obj);
            flag1 = false;
            if (flag2)
                flag1 = true;
            return flag1;
        }

        public boolean removeAll(Collection collection)
        {
            Preconditions.checkNotNull(collection);
            Predicate predicate1 = collection. new Predicate() {

                final FilteredCollection this$0;
                final Collection val$collection;

                public boolean apply(Object obj)
                {
                    return predicate.apply(obj) && collection.contains(obj);
                }

            
            {
                this$0 = final_filteredcollection;
                collection = Collection.this;
                super();
            }
            }
;
            return Iterables.removeIf(unfiltered, predicate1);
        }

        public boolean retainAll(Collection collection)
        {
            Preconditions.checkNotNull(collection);
            Predicate predicate1 = collection. new Predicate() {

                final FilteredCollection this$0;
                final Collection val$collection;

                public boolean apply(Object obj)
                {
                    return predicate.apply(obj) && !collection.contains(obj);
                }

            
            {
                this$0 = final_filteredcollection;
                collection = Collection.this;
                super();
            }
            }
;
            return Iterables.removeIf(unfiltered, predicate1);
        }

        public int size()
        {
            return Iterators.size(iterator());
        }

        public Object[] toArray()
        {
            return Lists.newArrayList(iterator()).toArray();
        }

        public Object[] toArray(Object aobj[])
        {
            return Lists.newArrayList(iterator()).toArray(aobj);
        }

        public String toString()
        {
            return Iterators.toString(iterator());
        }

        FilteredCollection(Collection collection, Predicate predicate1)
        {
            unfiltered = collection;
            predicate = predicate1;
        }
    }

    static class TransformedCollection extends AbstractCollection
    {

        final Collection fromCollection;
        final Function function;

        public void clear()
        {
            fromCollection.clear();
        }

        public boolean isEmpty()
        {
            return fromCollection.isEmpty();
        }

        public Iterator iterator()
        {
            return Iterators.transform(fromCollection.iterator(), function);
        }

        public int size()
        {
            return fromCollection.size();
        }

        TransformedCollection(Collection collection, Function function1)
        {
            fromCollection = (Collection)Preconditions.checkNotNull(collection);
            function = (Function)Preconditions.checkNotNull(function1);
        }
    }


    static final Joiner STANDARD_JOINER = Joiner.on(", ");

    private Collections2()
    {
    }

    static Collection cast(Iterable iterable)
    {
        return (Collection)iterable;
    }

    static boolean containsAllImpl(Collection collection, Collection collection1)
    {
        Preconditions.checkNotNull(collection);
        for (Iterator iterator = collection1.iterator(); iterator.hasNext();)
            if (!collection.contains(iterator.next()))
                return false;

        return true;
    }

    public static Collection filter(Collection collection, Predicate predicate)
    {
        if (collection instanceof FilteredCollection)
            return ((FilteredCollection)collection).createCombined(predicate);
        else
            return new FilteredCollection((Collection)Preconditions.checkNotNull(collection), (Predicate)Preconditions.checkNotNull(predicate));
    }

    static StringBuilder newStringBuilderForCollection(int i)
    {
        boolean flag;
        if (i >= 0)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag, "size must be non-negative");
        return new StringBuilder((int)Math.min(8L * (long)i, 0x40000000L));
    }

    static boolean safeContains(Collection collection, Object obj)
    {
        boolean flag;
        try
        {
            flag = collection.contains(obj);
        }
        catch (ClassCastException classcastexception)
        {
            return false;
        }
        return flag;
    }

    static String toStringImpl(Collection collection)
    {
        StringBuilder stringbuilder = newStringBuilderForCollection(collection.size()).append('[');
        STANDARD_JOINER.appendTo(stringbuilder, Iterables.transform(collection, new Function(collection) {

            final Collection val$collection;

            public Object apply(Object obj)
            {
                if (obj == collection)
                    obj = "(this Collection)";
                return obj;
            }

            
            {
                collection = collection1;
                super();
            }
        }
));
        return stringbuilder.append(']').toString();
    }

    public static Collection transform(Collection collection, Function function)
    {
        return new TransformedCollection(collection, function);
    }

}
