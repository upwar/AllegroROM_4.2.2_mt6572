// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package com.google.common.annotations;

import java.lang.annotation.Annotation;

public interface GwtCompatible
    extends Annotation
{

    public abstract boolean emulated();

    public abstract boolean serializable();
}