// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.os.Debug;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.Log;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// Referenced classes of package android.view:
//            View, IWindowSession, ViewGroup, ViewRootImpl

public class ViewDebug
{
    public static interface CapturedViewProperty
        extends Annotation
    {

        public abstract boolean retrieveReturn();
    }

    public static interface ExportedProperty
        extends Annotation
    {

        public abstract String category();

        public abstract boolean deepExport();

        public abstract FlagToString[] flagMapping();

        public abstract IntToString[] indexMapping();

        public abstract IntToString[] mapping();

        public abstract String prefix();

        public abstract boolean resolveId();
    }

    public static interface FlagToString
        extends Annotation
    {

        public abstract int equals();

        public abstract int mask();

        public abstract String name();

        public abstract boolean outputIf();
    }

    public static interface HierarchyHandler
    {

        public abstract void dumpViewHierarchyWithProperties(BufferedWriter bufferedwriter, int i);

        public abstract View findHierarchyView(String s, int i);
    }

    public static final class HierarchyTraceType extends Enum
    {

        private static final HierarchyTraceType $VALUES[];
        public static final HierarchyTraceType BUILD_CACHE;
        public static final HierarchyTraceType DRAW;
        public static final HierarchyTraceType INVALIDATE;
        public static final HierarchyTraceType INVALIDATE_CHILD;
        public static final HierarchyTraceType INVALIDATE_CHILD_IN_PARENT;
        public static final HierarchyTraceType ON_LAYOUT;
        public static final HierarchyTraceType ON_MEASURE;
        public static final HierarchyTraceType REQUEST_LAYOUT;

        public static HierarchyTraceType valueOf(String s)
        {
            return (HierarchyTraceType)Enum.valueOf(android/view/ViewDebug$HierarchyTraceType, s);
        }

        public static HierarchyTraceType[] values()
        {
            return (HierarchyTraceType[])$VALUES.clone();
        }

        static 
        {
            INVALIDATE = new HierarchyTraceType("INVALIDATE", 0);
            INVALIDATE_CHILD = new HierarchyTraceType("INVALIDATE_CHILD", 1);
            INVALIDATE_CHILD_IN_PARENT = new HierarchyTraceType("INVALIDATE_CHILD_IN_PARENT", 2);
            REQUEST_LAYOUT = new HierarchyTraceType("REQUEST_LAYOUT", 3);
            ON_LAYOUT = new HierarchyTraceType("ON_LAYOUT", 4);
            ON_MEASURE = new HierarchyTraceType("ON_MEASURE", 5);
            DRAW = new HierarchyTraceType("DRAW", 6);
            BUILD_CACHE = new HierarchyTraceType("BUILD_CACHE", 7);
            HierarchyTraceType ahierarchytracetype[] = new HierarchyTraceType[8];
            ahierarchytracetype[0] = INVALIDATE;
            ahierarchytracetype[1] = INVALIDATE_CHILD;
            ahierarchytracetype[2] = INVALIDATE_CHILD_IN_PARENT;
            ahierarchytracetype[3] = REQUEST_LAYOUT;
            ahierarchytracetype[4] = ON_LAYOUT;
            ahierarchytracetype[5] = ON_MEASURE;
            ahierarchytracetype[6] = DRAW;
            ahierarchytracetype[7] = BUILD_CACHE;
            $VALUES = ahierarchytracetype;
        }

        private HierarchyTraceType(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface IntToString
        extends Annotation
    {

        public abstract int from();

        public abstract String to();
    }

    public static final class RecyclerTraceType extends Enum
    {

        private static final RecyclerTraceType $VALUES[];
        public static final RecyclerTraceType BIND_VIEW;
        public static final RecyclerTraceType MOVE_FROM_ACTIVE_TO_SCRAP_HEAP;
        public static final RecyclerTraceType MOVE_TO_SCRAP_HEAP;
        public static final RecyclerTraceType NEW_VIEW;
        public static final RecyclerTraceType RECYCLE_FROM_ACTIVE_HEAP;
        public static final RecyclerTraceType RECYCLE_FROM_SCRAP_HEAP;

        public static RecyclerTraceType valueOf(String s)
        {
            return (RecyclerTraceType)Enum.valueOf(android/view/ViewDebug$RecyclerTraceType, s);
        }

        public static RecyclerTraceType[] values()
        {
            return (RecyclerTraceType[])$VALUES.clone();
        }

        static 
        {
            NEW_VIEW = new RecyclerTraceType("NEW_VIEW", 0);
            BIND_VIEW = new RecyclerTraceType("BIND_VIEW", 1);
            RECYCLE_FROM_ACTIVE_HEAP = new RecyclerTraceType("RECYCLE_FROM_ACTIVE_HEAP", 2);
            RECYCLE_FROM_SCRAP_HEAP = new RecyclerTraceType("RECYCLE_FROM_SCRAP_HEAP", 3);
            MOVE_TO_SCRAP_HEAP = new RecyclerTraceType("MOVE_TO_SCRAP_HEAP", 4);
            MOVE_FROM_ACTIVE_TO_SCRAP_HEAP = new RecyclerTraceType("MOVE_FROM_ACTIVE_TO_SCRAP_HEAP", 5);
            RecyclerTraceType arecyclertracetype[] = new RecyclerTraceType[6];
            arecyclertracetype[0] = NEW_VIEW;
            arecyclertracetype[1] = BIND_VIEW;
            arecyclertracetype[2] = RECYCLE_FROM_ACTIVE_HEAP;
            arecyclertracetype[3] = RECYCLE_FROM_SCRAP_HEAP;
            arecyclertracetype[4] = MOVE_TO_SCRAP_HEAP;
            arecyclertracetype[5] = MOVE_FROM_ACTIVE_TO_SCRAP_HEAP;
            $VALUES = arecyclertracetype;
        }

        private RecyclerTraceType(String s, int i)
        {
            super(s, i);
        }
    }

    static interface ViewOperation
    {

        public transient abstract void post(Object aobj[]);

        public abstract Object[] pre();

        public transient abstract void run(Object aobj[]);
    }


    private static final int CAPTURE_TIMEOUT = 4000;
    public static final boolean DEBUG_DRAG = false;
    private static final String REMOTE_COMMAND_CAPTURE = "CAPTURE";
    private static final String REMOTE_COMMAND_CAPTURE_LAYERS = "CAPTURE_LAYERS";
    private static final String REMOTE_COMMAND_DUMP = "DUMP";
    private static final String REMOTE_COMMAND_INVALIDATE = "INVALIDATE";
    private static final String REMOTE_COMMAND_OUTPUT_DISPLAYLIST = "OUTPUT_DISPLAYLIST";
    private static final String REMOTE_COMMAND_REQUEST_LAYOUT = "REQUEST_LAYOUT";
    private static final String REMOTE_PROFILE = "PROFILE";
    public static final boolean TRACE_HIERARCHY;
    public static final boolean TRACE_RECYCLER;
    private static HashMap mCapturedViewFieldsForClasses = null;
    private static HashMap mCapturedViewMethodsForClasses = null;
    private static HashMap sAnnotations;
    private static HashMap sFieldsForClasses;
    private static HashMap sMethodsForClasses;


    private static void capture(View view, OutputStream outputstream, String s)
        throws IOException
    {
        Bitmap bitmap;
        BufferedOutputStream bufferedoutputstream;
        bitmap = performViewCapture(findView(view, s), false);
        if (bitmap == null)
        {
            Log.w("View", "Failed to create capture bitmap!");
            bitmap = Bitmap.createBitmap(view.getResources().getDisplayMetrics(), 1, 1, android.graphics.Bitmap.Config.ARGB_8888);
        }
        bufferedoutputstream = null;
        BufferedOutputStream bufferedoutputstream1 = new BufferedOutputStream(outputstream, 32768);
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, bufferedoutputstream1);
        bufferedoutputstream1.flush();
        if (bufferedoutputstream1 != null)
            bufferedoutputstream1.close();
        bitmap.recycle();
        return;
        Exception exception;
        exception;
_L2:
        if (bufferedoutputstream != null)
            bufferedoutputstream.close();
        bitmap.recycle();
        throw exception;
        exception;
        bufferedoutputstream = bufferedoutputstream1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static void captureLayers(View view, DataOutputStream dataoutputstream)
        throws IOException
    {
        Rect rect = new Rect();
        Exception exception;
        try
        {
            view.mAttachInfo.mSession.getDisplayFrame(view.mAttachInfo.mWindow, rect);
        }
        catch (RemoteException remoteexception) { }
        dataoutputstream.writeInt(rect.width());
        dataoutputstream.writeInt(rect.height());
        captureViewLayer(view, dataoutputstream, true);
        dataoutputstream.write(2);
        dataoutputstream.close();
        return;
        exception;
        dataoutputstream.close();
        throw exception;
    }

    private static void captureViewLayer(View view, DataOutputStream dataoutputstream, boolean flag)
        throws IOException
    {
        boolean flag1;
        if (view.getVisibility() == 0 && flag)
            flag1 = true;
        else
            flag1 = false;
        if ((0x80 & view.mPrivateFlags) != 128)
        {
            int k = view.getId();
            String s = view.getClass().getSimpleName();
            if (k != -1)
                s = resolveId(view.getContext(), k).toString();
            dataoutputstream.write(1);
            dataoutputstream.writeUTF(s);
            int l;
            if (flag1)
                l = 1;
            else
                l = 0;
            dataoutputstream.writeByte(l);
            int ai[] = new int[2];
            view.getLocationInWindow(ai);
            dataoutputstream.writeInt(ai[0]);
            dataoutputstream.writeInt(ai[1]);
            dataoutputstream.flush();
            Bitmap bitmap = performViewCapture(view, true);
            if (bitmap != null)
            {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(2 * (bitmap.getWidth() * bitmap.getHeight()));
                bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, bytearrayoutputstream);
                dataoutputstream.writeInt(bytearrayoutputstream.size());
                bytearrayoutputstream.writeTo(dataoutputstream);
            }
            dataoutputstream.flush();
        }
        if (view instanceof ViewGroup)
        {
            ViewGroup viewgroup = (ViewGroup)view;
            int i = viewgroup.getChildCount();
            for (int j = 0; j < i; j++)
                captureViewLayer(viewgroup.getChildAt(j), dataoutputstream, flag1);

        }
    }

    private static String capturedViewExportFields(Object obj, Class class1, String s)
    {
        StringBuilder stringbuilder;
        Field afield[];
        int i;
        int j;
        if (obj == null)
            return "null";
        stringbuilder = new StringBuilder();
        afield = capturedViewGetPropertyFields(class1);
        i = afield.length;
        j = 0;
_L6:
        if (j >= i)
            break MISSING_BLOCK_LABEL_124;
        Field field = afield[j];
        Object obj1;
        try
        {
            obj1 = field.get(obj);
            stringbuilder.append(s);
            stringbuilder.append(field.getName());
            stringbuilder.append("=");
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            break MISSING_BLOCK_LABEL_129;
        }
        if (obj1 == null) goto _L2; else goto _L1
_L1:
        stringbuilder.append(obj1.toString().replace("\n", "\\n"));
_L4:
        stringbuilder.append(' ');
        break MISSING_BLOCK_LABEL_129;
_L2:
        stringbuilder.append("null");
        if (true) goto _L4; else goto _L3
_L3:
        return stringbuilder.toString();
        j++;
        if (true) goto _L6; else goto _L5
_L5:
    }

    private static String capturedViewExportMethods(Object obj, Class class1, String s)
    {
        StringBuilder stringbuilder;
        Method amethod[];
        int i;
        int j;
        if (obj == null)
            return "null";
        stringbuilder = new StringBuilder();
        amethod = capturedViewGetPropertyMethods(class1);
        i = amethod.length;
        j = 0;
_L5:
        Method method;
        if (j >= i)
            break MISSING_BLOCK_LABEL_194;
        method = amethod[j];
        Object obj1;
        obj1 = method.invoke(obj, (Object[])null);
        Class class2 = method.getReturnType();
        if (((CapturedViewProperty)method.getAnnotation(android/view/ViewDebug$CapturedViewProperty)).retrieveReturn())
        {
            stringbuilder.append(capturedViewExportMethods(obj1, class2, (new StringBuilder()).append(method.getName()).append("#").toString()));
            break MISSING_BLOCK_LABEL_199;
        }
        stringbuilder.append(s);
        stringbuilder.append(method.getName());
        stringbuilder.append("()=");
        if (obj1 == null) goto _L2; else goto _L1
_L1:
        stringbuilder.append(obj1.toString().replace("\n", "\\n"));
_L4:
        stringbuilder.append("; ");
        break MISSING_BLOCK_LABEL_199;
_L2:
        stringbuilder.append("null");
        if (true) goto _L4; else goto _L3
_L3:
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
        break MISSING_BLOCK_LABEL_199;
        return stringbuilder.toString();
_L6:
        j++;
          goto _L5
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
          goto _L6
    }

    private static Field[] capturedViewGetPropertyFields(Class class1)
    {
        if (mCapturedViewFieldsForClasses == null)
            mCapturedViewFieldsForClasses = new HashMap();
        HashMap hashmap = mCapturedViewFieldsForClasses;
        Field afield[] = (Field[])hashmap.get(class1);
        if (afield != null)
            return afield;
        ArrayList arraylist = new ArrayList();
        Field afield1[] = class1.getFields();
        int i = afield1.length;
        for (int j = 0; j < i; j++)
        {
            Field field = afield1[j];
            if (field.isAnnotationPresent(android/view/ViewDebug$CapturedViewProperty))
            {
                field.setAccessible(true);
                arraylist.add(field);
            }
        }

        Field afield2[] = (Field[])arraylist.toArray(new Field[arraylist.size()]);
        hashmap.put(class1, afield2);
        return afield2;
    }

    private static Method[] capturedViewGetPropertyMethods(Class class1)
    {
        if (mCapturedViewMethodsForClasses == null)
            mCapturedViewMethodsForClasses = new HashMap();
        HashMap hashmap = mCapturedViewMethodsForClasses;
        Method amethod[] = (Method[])hashmap.get(class1);
        if (amethod != null)
            return amethod;
        ArrayList arraylist = new ArrayList();
        Method amethod1[] = class1.getMethods();
        int i = amethod1.length;
        for (int j = 0; j < i; j++)
        {
            Method method = amethod1[j];
            if (method.getParameterTypes().length == 0 && method.isAnnotationPresent(android/view/ViewDebug$CapturedViewProperty) && method.getReturnType() != java/lang/Void)
            {
                method.setAccessible(true);
                arraylist.add(method);
            }
        }

        Method amethod2[] = (Method[])arraylist.toArray(new Method[arraylist.size()]);
        hashmap.put(class1, amethod2);
        return amethod2;
    }

    static void dispatchCommand(View view, String s, String s1, OutputStream outputstream)
        throws IOException
    {
        View view1 = view.getRootView();
        if ("DUMP".equalsIgnoreCase(s))
        {
            dump(view1, outputstream);
        } else
        {
            if ("CAPTURE_LAYERS".equalsIgnoreCase(s))
            {
                captureLayers(view1, new DataOutputStream(outputstream));
                return;
            }
            String as[] = s1.split(" ");
            if ("CAPTURE".equalsIgnoreCase(s))
            {
                capture(view1, outputstream, as[0]);
                return;
            }
            if ("OUTPUT_DISPLAYLIST".equalsIgnoreCase(s))
            {
                outputDisplayList(view1, as[0]);
                return;
            }
            if ("INVALIDATE".equalsIgnoreCase(s))
            {
                invalidate(view1, as[0]);
                return;
            }
            if ("REQUEST_LAYOUT".equalsIgnoreCase(s))
            {
                requestLayout(view1, as[0]);
                return;
            }
            if ("PROFILE".equalsIgnoreCase(s))
            {
                profile(view1, outputstream, as[0]);
                return;
            }
        }
    }

    private static void dump(View view, OutputStream outputstream)
        throws IOException
    {
        BufferedWriter bufferedwriter = null;
        BufferedWriter bufferedwriter1 = new BufferedWriter(new OutputStreamWriter(outputstream, "utf-8"), 32768);
        View view1 = view.getRootView();
        if (view1 instanceof ViewGroup)
        {
            ViewGroup viewgroup = (ViewGroup)view1;
            dumpViewHierarchyWithProperties(viewgroup.getContext(), viewgroup, bufferedwriter1, 0);
        }
        bufferedwriter1.write("DONE.");
        bufferedwriter1.newLine();
        if (bufferedwriter1 != null)
            bufferedwriter1.close();
_L2:
        return;
        Exception exception;
        exception;
_L5:
        Log.w("View", "Problem dumping the view:", exception);
        if (bufferedwriter == null) goto _L2; else goto _L1
_L1:
        bufferedwriter.close();
        return;
        Exception exception1;
        exception1;
_L4:
        if (bufferedwriter != null)
            bufferedwriter.close();
        throw exception1;
        exception1;
        bufferedwriter = bufferedwriter1;
        if (true) goto _L4; else goto _L3
_L3:
        exception;
        bufferedwriter = bufferedwriter1;
          goto _L5
    }

    public static void dumpCapturedView(String s, Object obj)
    {
        Class class1 = obj.getClass();
        StringBuilder stringbuilder = new StringBuilder((new StringBuilder()).append(class1.getName()).append(": ").toString());
        stringbuilder.append(capturedViewExportFields(obj, class1, ""));
        stringbuilder.append(capturedViewExportMethods(obj, class1, ""));
        Log.d(s, stringbuilder.toString());
    }

    private static void dumpViewHierarchyWithProperties(Context context, ViewGroup viewgroup, BufferedWriter bufferedwriter, int i)
    {
        if (dumpViewWithProperties(context, viewgroup, bufferedwriter, i))
        {
            int j = viewgroup.getChildCount();
            for (int k = 0; k < j; k++)
            {
                View view = viewgroup.getChildAt(k);
                if (view instanceof ViewGroup)
                    dumpViewHierarchyWithProperties(context, (ViewGroup)view, bufferedwriter, i + 1);
                else
                    dumpViewWithProperties(context, view, bufferedwriter, i + 1);
            }

            if (viewgroup instanceof HierarchyHandler)
            {
                ((HierarchyHandler)viewgroup).dumpViewHierarchyWithProperties(bufferedwriter, i + 1);
                return;
            }
        }
    }

    private static void dumpViewProperties(Context context, Object obj, BufferedWriter bufferedwriter)
        throws IOException
    {
        dumpViewProperties(context, obj, bufferedwriter, "");
    }

    private static void dumpViewProperties(Context context, Object obj, BufferedWriter bufferedwriter, String s)
        throws IOException
    {
        if (obj == null)
        {
            bufferedwriter.write((new StringBuilder()).append(s).append("=4,null ").toString());
            return;
        }
        Class class1 = obj.getClass();
        do
        {
            exportFields(context, obj, bufferedwriter, class1, s);
            exportMethods(context, obj, bufferedwriter, class1, s);
            class1 = class1.getSuperclass();
        } while (class1 != java/lang/Object);
    }

    private static boolean dumpViewWithProperties(Context context, View view, BufferedWriter bufferedwriter, int i)
    {
        int j = 0;
        do
        {
            if (j >= i)
                break;
            try
            {
                bufferedwriter.write(32);
            }
            catch (IOException ioexception)
            {
                Log.w("View", "Error while dumping hierarchy tree");
                return false;
            }
            j++;
        } while (true);
        bufferedwriter.write(view.getClass().getName());
        bufferedwriter.write(64);
        bufferedwriter.write(Integer.toHexString(view.hashCode()));
        bufferedwriter.write(32);
        dumpViewProperties(context, view, bufferedwriter);
        bufferedwriter.newLine();
        return true;
    }

    private static void exportFields(Context context, Object obj, BufferedWriter bufferedwriter, Class class1, String s)
        throws IOException
    {
        Field afield[];
        int i;
        int j;
        afield = getExportedPropertyFields(class1);
        i = afield.length;
        j = 0;
_L19:
        Field field;
        if (j >= i)
            break MISSING_BLOCK_LABEL_485;
        field = afield[j];
        Class class2;
        ExportedProperty exportedproperty;
        class2 = field.getType();
        exportedproperty = (ExportedProperty)sAnnotations.get(field);
        if (exportedproperty.category().length() == 0) goto _L2; else goto _L1
_L1:
        String s1 = (new StringBuilder()).append(exportedproperty.category()).append(":").toString();
_L20:
        if (class2 != Integer.TYPE) goto _L4; else goto _L3
_L3:
        if (!exportedproperty.resolveId() || context == null) goto _L6; else goto _L5
_L5:
        Object obj1 = resolveId(context, field.getInt(obj));
_L9:
        if (obj1 != null)
            break MISSING_BLOCK_LABEL_136;
        obj1 = field.get(obj);
        writeEntry(bufferedwriter, (new StringBuilder()).append(s1).append(s).toString(), field.getName(), "", obj1);
          goto _L7
_L6:
        IntToString ainttostring[];
        int k;
        FlagToString aflagtostring[] = exportedproperty.flagMapping();
        if (aflagtostring.length > 0)
            exportUnrolledFlags(bufferedwriter, aflagtostring, field.getInt(obj), (new StringBuilder()).append(s1).append(s).append(field.getName()).append('_').toString());
        ainttostring = exportedproperty.mapping();
        k = ainttostring.length;
        obj1 = null;
        if (k <= 0) goto _L9; else goto _L8
_L8:
        int l;
        int i1;
        l = field.getInt(obj);
        i1 = ainttostring.length;
        int j1 = 0;
_L21:
        int k1;
        k1 = j1;
        obj1 = null;
        if (k1 >= i1) goto _L11; else goto _L10
_L10:
        IntToString inttostring = ainttostring[j1];
        if (inttostring.from() != l) goto _L13; else goto _L12
_L12:
        obj1 = inttostring.to();
_L11:
        if (obj1 != null) goto _L9; else goto _L14
_L14:
        obj1 = Integer.valueOf(l);
          goto _L9
_L4:
        if (class2 != [I) goto _L16; else goto _L15
_L15:
        boolean flag;
        boolean flag1;
        try
        {
            exportUnrolledArray(context, bufferedwriter, exportedproperty, (int[])(int[])field.get(obj), (new StringBuilder()).append(s1).append(s).append(field.getName()).append('_').toString(), "");
            return;
        }
        catch (IllegalAccessException illegalaccessexception) { }
          goto _L7
_L16:
        flag = class2.isPrimitive();
        obj1 = null;
        if (flag) goto _L9; else goto _L17
_L17:
        flag1 = exportedproperty.deepExport();
        obj1 = null;
        if (!flag1) goto _L9; else goto _L18
_L18:
        dumpViewProperties(context, field.get(obj), bufferedwriter, (new StringBuilder()).append(s).append(exportedproperty.prefix()).toString());
_L7:
        j++;
          goto _L19
_L2:
        s1 = "";
          goto _L20
_L13:
        j1++;
          goto _L21
          goto _L9
    }

    private static void exportMethods(Context context, Object obj, BufferedWriter bufferedwriter, Class class1, String s)
        throws IOException
    {
        Method amethod[];
        int i;
        int j;
        amethod = getExportedPropertyMethods(class1);
        i = amethod.length;
        j = 0;
_L18:
        Method method;
        if (j >= i)
            break MISSING_BLOCK_LABEL_469;
        method = amethod[j];
        Object obj1;
        Class class2;
        ExportedProperty exportedproperty;
        obj1 = method.invoke(obj, (Object[])null);
        class2 = method.getReturnType();
        exportedproperty = (ExportedProperty)sAnnotations.get(method);
        if (exportedproperty.category().length() == 0) goto _L2; else goto _L1
_L1:
        String s1 = (new StringBuilder()).append(exportedproperty.category()).append(":").toString();
_L19:
        if (class2 != Integer.TYPE) goto _L4; else goto _L3
_L3:
        if (!exportedproperty.resolveId() || context == null) goto _L6; else goto _L5
_L5:
        obj1 = resolveId(context, ((Integer)obj1).intValue());
_L9:
        writeEntry(bufferedwriter, (new StringBuilder()).append(s1).append(s).toString(), method.getName(), "()", obj1);
          goto _L7
_L6:
        IntToString ainttostring[];
        FlagToString aflagtostring[] = exportedproperty.flagMapping();
        if (aflagtostring.length > 0)
            exportUnrolledFlags(bufferedwriter, aflagtostring, ((Integer)obj1).intValue(), (new StringBuilder()).append(s1).append(s).append(method.getName()).append('_').toString());
        ainttostring = exportedproperty.mapping();
        if (ainttostring.length <= 0) goto _L9; else goto _L8
_L8:
        int k;
        int l;
        k = ((Integer)obj1).intValue();
        l = ainttostring.length;
        int i1 = 0;
_L20:
        boolean flag = false;
        if (i1 >= l) goto _L11; else goto _L10
_L10:
        IntToString inttostring = ainttostring[i1];
        if (inttostring.from() != k) goto _L13; else goto _L12
_L12:
        obj1 = inttostring.to();
        flag = true;
_L11:
        if (flag) goto _L9; else goto _L14
_L14:
        obj1 = Integer.valueOf(k);
          goto _L9
_L4:
        if (class2 != [I) goto _L16; else goto _L15
_L15:
        try
        {
            exportUnrolledArray(context, bufferedwriter, exportedproperty, (int[])(int[])obj1, (new StringBuilder()).append(s1).append(s).append(method.getName()).append('_').toString(), "()");
            return;
        }
        catch (IllegalAccessException illegalaccessexception) { }
        catch (InvocationTargetException invocationtargetexception) { }
          goto _L7
_L16:
        if (class2.isPrimitive() || !exportedproperty.deepExport()) goto _L9; else goto _L17
_L17:
        String s2 = (new StringBuilder()).append(s).append(exportedproperty.prefix()).toString();
        dumpViewProperties(context, obj1, bufferedwriter, s2);
_L7:
        j++;
          goto _L18
_L2:
        s1 = "";
          goto _L19
_L13:
        i1++;
          goto _L20
          goto _L9
    }

    private static void exportUnrolledArray(Context context, BufferedWriter bufferedwriter, ExportedProperty exportedproperty, int ai[], String s, String s1)
        throws IOException
    {
        IntToString ainttostring[];
        boolean flag;
        IntToString ainttostring1[];
        boolean flag1;
        boolean flag2;
        int i;
        int j;
        ainttostring = exportedproperty.indexMapping();
        if (ainttostring.length > 0)
            flag = true;
        else
            flag = false;
        ainttostring1 = exportedproperty.mapping();
        if (ainttostring1.length > 0)
            flag1 = true;
        else
            flag1 = false;
        if (exportedproperty.resolveId() && context != null)
            flag2 = true;
        else
            flag2 = false;
        i = ai.length;
        j = 0;
_L16:
        if (j >= i) goto _L2; else goto _L1
_L1:
        int k;
        String s2;
        k = ai[j];
        s2 = String.valueOf(j);
        if (!flag) goto _L4; else goto _L3
_L3:
        int j1;
        int k1;
        j1 = ainttostring.length;
        k1 = 0;
_L14:
        if (k1 >= j1) goto _L4; else goto _L5
_L5:
        IntToString inttostring1 = ainttostring[k1];
        if (inttostring1.from() != j) goto _L7; else goto _L6
_L6:
        s2 = inttostring1.to();
_L4:
        String s3 = null;
        if (!flag1) goto _L9; else goto _L8
_L8:
        int l;
        int i1;
        l = ainttostring1.length;
        i1 = 0;
_L13:
        s3 = null;
        if (i1 >= l) goto _L9; else goto _L10
_L10:
        IntToString inttostring = ainttostring1[i1];
        if (inttostring.from() != k) goto _L12; else goto _L11
_L11:
        s3 = inttostring.to();
_L9:
        if (flag2)
        {
            if (s3 == null)
                s3 = (String)resolveId(context, k);
        } else
        {
            s3 = String.valueOf(k);
        }
        writeEntry(bufferedwriter, s, s2, s1, s3);
        j++;
        continue; /* Loop/switch isn't completed */
_L12:
        i1++;
        if (true) goto _L13; else goto _L7
_L7:
        k1++;
        if (true) goto _L14; else goto _L2
_L2:
        return;
        if (true) goto _L16; else goto _L15
_L15:
    }

    private static void exportUnrolledFlags(BufferedWriter bufferedwriter, FlagToString aflagtostring[], int i, String s)
        throws IOException
    {
        int j = aflagtostring.length;
        for (int k = 0; k < j; k++)
        {
            FlagToString flagtostring = aflagtostring[k];
            boolean flag = flagtostring.outputIf();
            int l = i & flagtostring.mask();
            boolean flag1;
            if (l == flagtostring.equals())
                flag1 = true;
            else
                flag1 = false;
            if (flag1 && flag || !flag1 && !flag)
                writeEntry(bufferedwriter, s, flagtostring.name(), "", (new StringBuilder()).append("0x").append(Integer.toHexString(l)).toString());
        }

    }

    private static View findView(View view, String s)
    {
        if (s.indexOf('@') != -1)
        {
            String as[] = s.split("@");
            String s1 = as[0];
            int j = (int)Long.parseLong(as[1], 16);
            View view1 = view.getRootView();
            boolean flag = view1 instanceof ViewGroup;
            View view2 = null;
            if (flag)
                view2 = findView((ViewGroup)view1, s1, j);
            return view2;
        } else
        {
            int i = view.getResources().getIdentifier(s, null, null);
            return view.getRootView().findViewById(i);
        }
    }

    private static View findView(ViewGroup viewgroup, String s, int i)
    {
label0:
        {
            Object obj;
            if (isRequestedView(viewgroup, s, i))
            {
                obj = viewgroup;
            } else
            {
                int j = viewgroup.getChildCount();
                int k = 0;
                do
                {
                    if (k >= j)
                        break label0;
                    View view = viewgroup.getChildAt(k);
                    if (view instanceof ViewGroup)
                    {
                        obj = findView((ViewGroup)view, s, i);
                        if (obj != null)
                            break;
                    } else
                    if (isRequestedView(view, s, i))
                        return view;
                    if (view instanceof HierarchyHandler)
                    {
                        obj = ((HierarchyHandler)view).findHierarchyView(s, i);
                        if (obj != null)
                            break;
                    }
                    k++;
                } while (true);
            }
            return ((View) (obj));
        }
        return null;
    }

    private static Field[] getExportedPropertyFields(Class class1)
    {
        if (sFieldsForClasses == null)
            sFieldsForClasses = new HashMap();
        if (sAnnotations == null)
            sAnnotations = new HashMap(512);
        HashMap hashmap = sFieldsForClasses;
        Field afield[] = (Field[])hashmap.get(class1);
        if (afield != null)
            return afield;
        ArrayList arraylist = new ArrayList();
        Field afield1[] = class1.getDeclaredFields();
        int i = afield1.length;
        for (int j = 0; j < i; j++)
        {
            Field field = afield1[j];
            if (field.isAnnotationPresent(android/view/ViewDebug$ExportedProperty))
            {
                field.setAccessible(true);
                arraylist.add(field);
                sAnnotations.put(field, field.getAnnotation(android/view/ViewDebug$ExportedProperty));
            }
        }

        Field afield2[] = (Field[])arraylist.toArray(new Field[arraylist.size()]);
        hashmap.put(class1, afield2);
        return afield2;
    }

    private static Method[] getExportedPropertyMethods(Class class1)
    {
        if (sMethodsForClasses == null)
            sMethodsForClasses = new HashMap(100);
        if (sAnnotations == null)
            sAnnotations = new HashMap(512);
        HashMap hashmap = sMethodsForClasses;
        Method amethod[] = (Method[])hashmap.get(class1);
        if (amethod != null)
            return amethod;
        ArrayList arraylist = new ArrayList();
        Method amethod1[] = class1.getDeclaredMethods();
        int i = amethod1.length;
        for (int j = 0; j < i; j++)
        {
            Method method = amethod1[j];
            if (method.getParameterTypes().length == 0 && method.isAnnotationPresent(android/view/ViewDebug$ExportedProperty) && method.getReturnType() != java/lang/Void)
            {
                method.setAccessible(true);
                arraylist.add(method);
                sAnnotations.put(method, method.getAnnotation(android/view/ViewDebug$ExportedProperty));
            }
        }

        Method amethod2[] = (Method[])arraylist.toArray(new Method[arraylist.size()]);
        hashmap.put(class1, amethod2);
        return amethod2;
    }

    public static long getViewInstanceCount()
    {
        return Debug.countInstancesOfClass(android/view/View);
    }

    public static long getViewRootImplCount()
    {
        return Debug.countInstancesOfClass(android/view/ViewRootImpl);
    }

    private static void invalidate(View view, String s)
    {
        View view1 = findView(view, s);
        if (view1 != null)
            view1.postInvalidate();
    }

    private static boolean isRequestedView(View view, String s, int i)
    {
        return view.getClass().getName().equals(s) && view.hashCode() == i;
    }

    private static void outputDisplayList(View view, String s)
        throws IOException
    {
        View view1 = findView(view, s);
        view1.getViewRootImpl().outputDisplayList(view1);
    }

    private static Bitmap performViewCapture(View view, boolean flag)
    {
        CountDownLatch countdownlatch;
        Bitmap abitmap[];
        if (view == null)
            break MISSING_BLOCK_LABEL_87;
        countdownlatch = new CountDownLatch(1);
        abitmap = new Bitmap[1];
        view.post(new Runnable(abitmap, view, flag, countdownlatch) {

            final Bitmap val$cache[];
            final View val$captureView;
            final CountDownLatch val$latch;
            final boolean val$skpiChildren;

            public void run()
            {
                cache[0] = captureView.createSnapshot(android.graphics.Bitmap.Config.ARGB_8888, 0, skpiChildren);
                CountDownLatch countdownlatch1 = latch;
_L2:
                countdownlatch1.countDown();
                return;
                OutOfMemoryError outofmemoryerror;
                outofmemoryerror;
                Log.w("View", "Out of memory for bitmap");
                countdownlatch1 = latch;
                if (true) goto _L2; else goto _L1
_L1:
                Exception exception;
                exception;
                latch.countDown();
                throw exception;
            }

            
            {
                cache = abitmap;
                captureView = view;
                skpiChildren = flag;
                latch = countdownlatch;
                super();
            }
        }
);
        Bitmap bitmap;
        countdownlatch.await(4000L, TimeUnit.MILLISECONDS);
        bitmap = abitmap[0];
        return bitmap;
        InterruptedException interruptedexception;
        interruptedexception;
        Log.w("View", (new StringBuilder()).append("Could not complete the capture of the view ").append(view).toString());
        Thread.currentThread().interrupt();
        return null;
    }

    private static void profile(View view, OutputStream outputstream, String s)
        throws IOException
    {
        View view1;
        BufferedWriter bufferedwriter;
        view1 = findView(view, s);
        bufferedwriter = null;
        BufferedWriter bufferedwriter1 = new BufferedWriter(new OutputStreamWriter(outputstream), 32768);
        if (view1 == null) goto _L2; else goto _L1
_L1:
        profileViewAndChildren(view1, bufferedwriter1);
_L3:
        bufferedwriter1.write("DONE.");
        bufferedwriter1.newLine();
        if (bufferedwriter1 != null)
            bufferedwriter1.close();
_L5:
        return;
_L2:
        bufferedwriter1.write("-1 -1 -1");
        bufferedwriter1.newLine();
          goto _L3
        Exception exception1;
        exception1;
        bufferedwriter = bufferedwriter1;
_L8:
        Log.w("View", "Problem profiling the view:", exception1);
        if (bufferedwriter == null) goto _L5; else goto _L4
_L4:
        bufferedwriter.close();
        return;
        Exception exception;
        exception;
_L7:
        if (bufferedwriter != null)
            bufferedwriter.close();
        throw exception;
        exception;
        bufferedwriter = bufferedwriter1;
        if (true) goto _L7; else goto _L6
_L6:
        exception1;
        bufferedwriter = null;
          goto _L8
    }

    private static void profileViewAndChildren(View view, BufferedWriter bufferedwriter)
        throws IOException
    {
        profileViewAndChildren(view, bufferedwriter, true);
    }

    private static void profileViewAndChildren(View view, BufferedWriter bufferedwriter, boolean flag)
        throws IOException
    {
        long l = 0L;
        long l1;
        if (!flag && (0x800 & view.mPrivateFlags) == 0)
            l1 = l;
        else
            l1 = profileViewOperation(view, new ViewOperation(view) {

                final View val$view;

                private void forceLayout(View view1)
                {
                    view1.forceLayout();
                    if (view1 instanceof ViewGroup)
                    {
                        ViewGroup viewgroup1 = (ViewGroup)view1;
                        int k = viewgroup1.getChildCount();
                        for (int i1 = 0; i1 < k; i1++)
                            forceLayout(viewgroup1.getChildAt(i1));

                    }
                }

                public volatile void post(Object aobj[])
                {
                    post((Void[])aobj);
                }

                public transient void post(Void avoid[])
                {
                }

                public volatile Object[] pre()
                {
                    return pre();
                }

                public Void[] pre()
                {
                    forceLayout(view);
                    return null;
                }

                public volatile void run(Object aobj[])
                {
                    run((Void[])aobj);
                }

                public transient void run(Void avoid[])
                {
                    view.measure(view.mOldWidthMeasureSpec, view.mOldHeightMeasureSpec);
                }

            
            {
                view = view1;
                super();
            }
            }
);
        long l2;
        if (!flag && (0x2000 & view.mPrivateFlags) == 0)
            l2 = l;
        else
            l2 = profileViewOperation(view, new ViewOperation(view) {

                final View val$view;

                public volatile void post(Object aobj[])
                {
                    post((Void[])aobj);
                }

                public transient void post(Void avoid[])
                {
                }

                public volatile Object[] pre()
                {
                    return pre();
                }

                public Void[] pre()
                {
                    return null;
                }

                public volatile void run(Object aobj[])
                {
                    run((Void[])aobj);
                }

                public transient void run(Void avoid[])
                {
                    view.layout(view.mLeft, view.mTop, view.mRight, view.mBottom);
                }

            
            {
                view = view1;
                super();
            }
            }
);
        if (flag || !view.willNotDraw() || (0x20 & view.mPrivateFlags) != 0)
            l = profileViewOperation(view, new ViewOperation(view) {

                final View val$view;

                public transient void post(Object aobj[])
                {
                    if (aobj[1] != null)
                        ((Canvas)aobj[1]).setBitmap(null);
                    if (aobj[0] != null)
                        ((Bitmap)aobj[0]).recycle();
                }

                public Object[] pre()
                {
                    DisplayMetrics displaymetrics;
                    if (view != null && view.getResources() != null)
                        displaymetrics = view.getResources().getDisplayMetrics();
                    else
                        displaymetrics = null;
                    Bitmap bitmap;
                    if (displaymetrics != null)
                        bitmap = Bitmap.createBitmap(displaymetrics, displaymetrics.widthPixels, displaymetrics.heightPixels, android.graphics.Bitmap.Config.RGB_565);
                    else
                        bitmap = null;
                    Canvas canvas = null;
                    if (bitmap != null)
                        canvas = new Canvas(bitmap);
                    return (new Object[] {
                        bitmap, canvas
                    });
                }

                public transient void run(Object aobj[])
                {
                    if (aobj[1] != null)
                        view.draw((Canvas)aobj[1]);
                }

            
            {
                view = view1;
                super();
            }
            }
);
        bufferedwriter.write(String.valueOf(l1));
        bufferedwriter.write(32);
        bufferedwriter.write(String.valueOf(l2));
        bufferedwriter.write(32);
        bufferedwriter.write(String.valueOf(l));
        bufferedwriter.newLine();
        if (view instanceof ViewGroup)
        {
            ViewGroup viewgroup = (ViewGroup)view;
            int i = viewgroup.getChildCount();
            for (int j = 0; j < i; j++)
                profileViewAndChildren(viewgroup.getChildAt(j), bufferedwriter, false);

        }
    }

    private static long profileViewOperation(View view, ViewOperation viewoperation)
    {
        long al[];
label0:
        {
            CountDownLatch countdownlatch = new CountDownLatch(1);
            al = new long[1];
            view.post(new Runnable(viewoperation, al, countdownlatch) {

                final long val$duration[];
                final CountDownLatch val$latch;
                final ViewOperation val$operation;

                public void run()
                {
                    Object aobj[] = operation.pre();
                    long l = Debug.threadCpuTimeNanos();
                    operation.run(aobj);
                    duration[0] = Debug.threadCpuTimeNanos() - l;
                    operation.post(aobj);
                    latch.countDown();
                    return;
                    Exception exception;
                    exception;
                    latch.countDown();
                    throw exception;
                }

            
            {
                operation = viewoperation;
                duration = al;
                latch = countdownlatch;
                super();
            }
            }
);
            try
            {
                if (countdownlatch.await(4000L, TimeUnit.MILLISECONDS))
                    break label0;
                Log.w("View", (new StringBuilder()).append("Could not complete the profiling of the view ").append(view).toString());
            }
            catch (InterruptedException interruptedexception)
            {
                Log.w("View", (new StringBuilder()).append("Could not complete the profiling of the view ").append(view).toString());
                Thread.currentThread().interrupt();
                return -1L;
            }
            return -1L;
        }
        return al[0];
    }

    private static void requestLayout(View view, String s)
    {
        View view1 = findView(view, s);
        if (view1 != null)
            view.post(new Runnable(view1) {

                final View val$view;

                public void run()
                {
                    view.requestLayout();
                }

            
            {
                view = view1;
                super();
            }
            }
);
    }

    static Object resolveId(Context context, int i)
    {
        Resources resources = context.getResources();
        if (i >= 0)
        {
            String s;
            try
            {
                s = (new StringBuilder()).append(resources.getResourceTypeName(i)).append('/').append(resources.getResourceEntryName(i)).toString();
            }
            catch (android.content.res.Resources.NotFoundException notfoundexception)
            {
                return (new StringBuilder()).append("id/0x").append(Integer.toHexString(i)).toString();
            }
            return s;
        } else
        {
            return "NO_ID";
        }
    }

    public static void startHierarchyTracing(String s, View view)
    {
    }

    public static void startRecyclerTracing(String s, View view)
    {
    }

    public static void stopHierarchyTracing()
    {
    }

    public static void stopRecyclerTracing()
    {
    }

    public static void trace(View view, HierarchyTraceType hierarchytracetype)
    {
    }

    public static transient void trace(View view, RecyclerTraceType recyclertracetype, int ai[])
    {
    }

    private static void writeEntry(BufferedWriter bufferedwriter, String s, String s1, String s2, Object obj)
        throws IOException
    {
        bufferedwriter.write(s);
        bufferedwriter.write(s1);
        bufferedwriter.write(s2);
        bufferedwriter.write("=");
        writeValue(bufferedwriter, obj);
        bufferedwriter.write(32);
    }

    private static void writeValue(BufferedWriter bufferedwriter, Object obj)
        throws IOException
    {
        if (obj == null)
            break MISSING_BLOCK_LABEL_72;
        String s = obj.toString().replace("\n", "\\n");
        bufferedwriter.write(String.valueOf(s.length()));
        bufferedwriter.write(",");
        bufferedwriter.write(s);
        return;
        Exception exception;
        exception;
        bufferedwriter.write(String.valueOf("[EXCEPTION]".length()));
        bufferedwriter.write(",");
        bufferedwriter.write("[EXCEPTION]");
        throw exception;
        bufferedwriter.write("4,null");
        return;
    }

}
