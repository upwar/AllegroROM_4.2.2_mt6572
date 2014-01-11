// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package android.app;

import android.content.*;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.util.TypedValue;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import com.android.internal.app.ActionBarImpl;
import com.android.internal.policy.PolicyManager;
import java.lang.ref.WeakReference;

// Referenced classes of package android.app:
//            Activity, SearchManager, ActionBar

public class Dialog
    implements DialogInterface, android.view.Window.Callback, android.view.KeyEvent.Callback, android.view.View.OnCreateContextMenuListener
{
    private static final class ListenersHandler extends Handler
    {

        private WeakReference mDialog;

        public void handleMessage(Message message)
        {
            switch (message.what)
            {
            case 69: // 'E'
                ((android.content.DialogInterface.OnShowListener)message.obj).onShow((DialogInterface)mDialog.get());
                return;

            case 68: // 'D'
                ((android.content.DialogInterface.OnCancelListener)message.obj).onCancel((DialogInterface)mDialog.get());
                return;

            case 67: // 'C'
                ((android.content.DialogInterface.OnDismissListener)message.obj).onDismiss((DialogInterface)mDialog.get());
                return;
            }
        }

        public ListenersHandler(Dialog dialog)
        {
            mDialog = new WeakReference(dialog);
        }
    }


    private static final int CANCEL = 68;
    private static final String DIALOG_HIERARCHY_TAG = "android:dialogHierarchy";
    private static final String DIALOG_SHOWING_TAG = "android:dialogShowing";
    private static final int DISMISS = 67;
    private static final int SHOW = 69;
    private static final String TAG = "Dialog";
    private ActionBarImpl mActionBar;
    private ActionMode mActionMode;
    private String mCancelAndDismissTaken;
    private Message mCancelMessage;
    protected boolean mCancelable;
    private boolean mCanceled;
    final Context mContext;
    private boolean mCreated;
    View mDecor;
    private final Runnable mDismissAction;
    private Message mDismissMessage;
    private final Handler mHandler;
    private Handler mListenersHandler;
    private android.content.DialogInterface.OnKeyListener mOnKeyListener;
    private Activity mOwnerActivity;
    private Message mShowMessage;
    private boolean mShowing;
    Window mWindow;
    final WindowManager mWindowManager;

    public Dialog(Context context)
    {
        this(context, 0, true);
    }

    public Dialog(Context context, int i)
    {
        this(context, i, true);
    }

    Dialog(Context context, int i, boolean flag)
    {
        mCancelable = true;
        mCreated = false;
        mShowing = false;
        mCanceled = false;
        mHandler = new Handler();
        mDismissAction = new Runnable() {

            final Dialog this$0;

            public void run()
            {
                dismissDialog();
            }

            
            {
                this$0 = Dialog.this;
                super();
            }
        }
;
        if (flag)
        {
            if (i == 0)
            {
                TypedValue typedvalue = new TypedValue();
                context.getTheme().resolveAttribute(0x1010308, typedvalue, true);
                i = typedvalue.resourceId;
            }
            mContext = new ContextThemeWrapper(context, i);
        } else
        {
            mContext = context;
        }
        mWindowManager = (WindowManager)context.getSystemService("window");
        Window window = PolicyManager.makeNewWindow(mContext);
        mWindow = window;
        window.setCallback(this);
        window.setWindowManager(mWindowManager, null, null);
        window.setGravity(17);
        mListenersHandler = new ListenersHandler(this);
    }

    protected Dialog(Context context, boolean flag, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        this(context);
        mCancelable = flag;
        setOnCancelListener(oncancellistener);
    }

    protected Dialog(Context context, boolean flag, Message message)
    {
        this(context);
        mCancelable = flag;
        mCancelMessage = message;
    }

    private ComponentName getAssociatedActivity()
    {
        Activity activity = mOwnerActivity;
        for (Context context = getContext(); activity == null && context != null;)
            if (context instanceof Activity)
                activity = (Activity)context;
            else
            if (context instanceof ContextWrapper)
                context = ((ContextWrapper)context).getBaseContext();
            else
                context = null;

        if (activity == null)
            return null;
        else
            return activity.getComponentName();
    }

    private void sendDismissMessage()
    {
        if (mDismissMessage != null)
            Message.obtain(mDismissMessage).sendToTarget();
    }

    private void sendShowMessage()
    {
        if (mShowMessage != null)
            Message.obtain(mShowMessage).sendToTarget();
    }

    public void addContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        mWindow.addContentView(view, layoutparams);
    }

    public void cancel()
    {
        if (!mCanceled && mCancelMessage != null)
        {
            mCanceled = true;
            Message.obtain(mCancelMessage).sendToTarget();
        }
        dismiss();
    }

    public void closeOptionsMenu()
    {
        mWindow.closePanel(0);
    }

    public void dismiss()
    {
        if (Looper.myLooper() == mHandler.getLooper())
        {
            dismissDialog();
            return;
        } else
        {
            mHandler.post(mDismissAction);
            return;
        }
    }

    void dismissDialog()
    {
        if (mDecor == null || !mShowing)
            return;
        if (mWindow.isDestroyed())
        {
            Log.e("Dialog", "Tried to dismissDialog() but the Dialog's window was already destroyed!");
            return;
        }
        mWindowManager.removeView(mDecor);
        if (mActionMode != null)
            mActionMode.finish();
        mDecor = null;
        mWindow.closeAllPanels();
        onStop();
        mShowing = false;
        sendDismissMessage();
        return;
        Exception exception;
        exception;
        if (mActionMode != null)
            mActionMode.finish();
        mDecor = null;
        mWindow.closeAllPanels();
        onStop();
        mShowing = false;
        sendDismissMessage();
        throw exception;
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionevent)
    {
        if (mWindow.superDispatchGenericMotionEvent(motionevent))
            return true;
        else
            return onGenericMotionEvent(motionevent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        if ((mOnKeyListener == null || !mOnKeyListener.onKey(this, keyevent.getKeyCode(), keyevent)) && !mWindow.superDispatchKeyEvent(keyevent))
        {
            android.view.KeyEvent.DispatcherState dispatcherstate;
            if (mDecor != null)
                dispatcherstate = mDecor.getKeyDispatcherState();
            else
                dispatcherstate = null;
            return keyevent.dispatch(this, dispatcherstate, this);
        } else
        {
            return true;
        }
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyevent)
    {
        if (mWindow.superDispatchKeyShortcutEvent(keyevent))
            return true;
        else
            return onKeyShortcut(keyevent.getKeyCode(), keyevent);
    }

    void dispatchOnCreate(Bundle bundle)
    {
        if (!mCreated)
        {
            onCreate(bundle);
            mCreated = true;
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        accessibilityevent.setClassName(getClass().getName());
        accessibilityevent.setPackageName(mContext.getPackageName());
        android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
        boolean flag;
        if (((android.view.ViewGroup.LayoutParams) (layoutparams)).width == -1 && ((android.view.ViewGroup.LayoutParams) (layoutparams)).height == -1)
            flag = true;
        else
            flag = false;
        accessibilityevent.setFullScreen(flag);
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        if (mWindow.superDispatchTouchEvent(motionevent))
            return true;
        else
            return onTouchEvent(motionevent);
    }

    public boolean dispatchTrackballEvent(MotionEvent motionevent)
    {
        if (mWindow.superDispatchTrackballEvent(motionevent))
            return true;
        else
            return onTrackballEvent(motionevent);
    }

    public View findViewById(int i)
    {
        return mWindow.findViewById(i);
    }

    public ActionBar getActionBar()
    {
        return mActionBar;
    }

    public final Context getContext()
    {
        return mContext;
    }

    public View getCurrentFocus()
    {
        if (mWindow != null)
            return mWindow.getCurrentFocus();
        else
            return null;
    }

    public LayoutInflater getLayoutInflater()
    {
        return getWindow().getLayoutInflater();
    }

    public final Activity getOwnerActivity()
    {
        return mOwnerActivity;
    }

    public final int getVolumeControlStream()
    {
        return getWindow().getVolumeControlStream();
    }

    public Window getWindow()
    {
        return mWindow;
    }

    public void hide()
    {
        if (mDecor != null)
            mDecor.setVisibility(8);
    }

    public void invalidateOptionsMenu()
    {
        mWindow.invalidatePanelMenu(0);
    }

    public boolean isShowing()
    {
        return mShowing;
    }

    public void onActionModeFinished(ActionMode actionmode)
    {
        if (actionmode == mActionMode)
            mActionMode = null;
    }

    public void onActionModeStarted(ActionMode actionmode)
    {
        mActionMode = actionmode;
    }

    public void onAttachedToWindow()
    {
    }

    public void onBackPressed()
    {
        if (mCancelable)
            cancel();
    }

    public void onContentChanged()
    {
    }

    public boolean onContextItemSelected(MenuItem menuitem)
    {
        return false;
    }

    public void onContextMenuClosed(Menu menu)
    {
    }

    protected void onCreate(Bundle bundle)
    {
    }

    public void onCreateContextMenu(ContextMenu contextmenu, View view, android.view.ContextMenu.ContextMenuInfo contextmenuinfo)
    {
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }

    public boolean onCreatePanelMenu(int i, Menu menu)
    {
        if (i == 0)
            return onCreateOptionsMenu(menu);
        else
            return false;
    }

    public View onCreatePanelView(int i)
    {
        return null;
    }

    public void onDetachedFromWindow()
    {
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            keyevent.startTracking();
            return true;
        } else
        {
            return false;
        }
    }

    public boolean onKeyLongPress(int i, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyMultiple(int i, int j, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyShortcut(int i, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if (i == 4 && keyevent.isTracking() && !keyevent.isCanceled())
        {
            onBackPressed();
            return true;
        } else
        {
            return false;
        }
    }

    public boolean onMenuItemSelected(int i, MenuItem menuitem)
    {
        return false;
    }

    public boolean onMenuOpened(int i, Menu menu)
    {
        if (i == 8)
            mActionBar.dispatchMenuVisibilityChanged(true);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        return false;
    }

    public void onOptionsMenuClosed(Menu menu)
    {
    }

    public void onPanelClosed(int i, Menu menu)
    {
        if (i == 8)
            mActionBar.dispatchMenuVisibilityChanged(false);
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        return true;
    }

    public boolean onPreparePanel(int i, View view, Menu menu)
    {
        return i != 0 || menu == null || onPrepareOptionsMenu(menu) && menu.hasVisibleItems();
    }

    public void onRestoreInstanceState(Bundle bundle)
    {
        Bundle bundle1 = bundle.getBundle("android:dialogHierarchy");
        if (bundle1 != null)
        {
            dispatchOnCreate(bundle);
            mWindow.restoreHierarchyState(bundle1);
            if (bundle.getBoolean("android:dialogShowing"))
            {
                show();
                return;
            }
        }
    }

    public Bundle onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        bundle.putBoolean("android:dialogShowing", mShowing);
        if (mCreated)
            bundle.putBundle("android:dialogHierarchy", mWindow.saveHierarchyState());
        return bundle;
    }

    public boolean onSearchRequested()
    {
        SearchManager searchmanager = (SearchManager)mContext.getSystemService("search");
        ComponentName componentname = getAssociatedActivity();
        boolean flag = false;
        if (componentname != null)
        {
            SearchableInfo searchableinfo = searchmanager.getSearchableInfo(componentname);
            flag = false;
            if (searchableinfo != null)
            {
                searchmanager.startSearch(null, false, componentname, null, false);
                dismiss();
                flag = true;
            }
        }
        return flag;
    }

    protected void onStart()
    {
        if (mActionBar != null)
            mActionBar.setShowHideAnimationEnabled(true);
    }

    protected void onStop()
    {
        if (mActionBar != null)
            mActionBar.setShowHideAnimationEnabled(false);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (mCancelable && mShowing && mWindow.shouldCloseOnTouch(mContext, motionevent))
        {
            cancel();
            return true;
        } else
        {
            return false;
        }
    }

    public boolean onTrackballEvent(MotionEvent motionevent)
    {
        return false;
    }

    public void onWindowAttributesChanged(android.view.WindowManager.LayoutParams layoutparams)
    {
        if (mDecor != null)
            mWindowManager.updateViewLayout(mDecor, layoutparams);
    }

    public void onWindowFocusChanged(boolean flag)
    {
    }

    public ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback)
    {
        if (mActionBar != null)
            return mActionBar.startActionMode(callback);
        else
            return null;
    }

    public void openContextMenu(View view)
    {
        view.showContextMenu();
    }

    public void openOptionsMenu()
    {
        mWindow.openPanel(0, null);
    }

    public void registerForContextMenu(View view)
    {
        view.setOnCreateContextMenuListener(this);
    }

    public final boolean requestWindowFeature(int i)
    {
        return getWindow().requestFeature(i);
    }

    public void setCancelMessage(Message message)
    {
        mCancelMessage = message;
    }

    public void setCancelable(boolean flag)
    {
        mCancelable = flag;
    }

    public void setCanceledOnTouchOutside(boolean flag)
    {
        if (flag && !mCancelable)
            mCancelable = true;
        mWindow.setCloseOnTouchOutside(flag);
    }

    public void setContentView(int i)
    {
        mWindow.setContentView(i);
    }

    public void setContentView(View view)
    {
        mWindow.setContentView(view);
    }

    public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        mWindow.setContentView(view, layoutparams);
    }

    public void setDismissMessage(Message message)
    {
        mDismissMessage = message;
    }

    public final void setFeatureDrawable(int i, Drawable drawable)
    {
        getWindow().setFeatureDrawable(i, drawable);
    }

    public final void setFeatureDrawableAlpha(int i, int j)
    {
        getWindow().setFeatureDrawableAlpha(i, j);
    }

    public final void setFeatureDrawableResource(int i, int j)
    {
        getWindow().setFeatureDrawableResource(i, j);
    }

    public final void setFeatureDrawableUri(int i, Uri uri)
    {
        getWindow().setFeatureDrawableUri(i, uri);
    }

    public void setOnCancelListener(android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        if (mCancelAndDismissTaken != null)
            throw new IllegalStateException((new StringBuilder()).append("OnCancelListener is already taken by ").append(mCancelAndDismissTaken).append(" and can not be replaced.").toString());
        if (oncancellistener != null)
        {
            mCancelMessage = mListenersHandler.obtainMessage(68, oncancellistener);
            return;
        } else
        {
            mCancelMessage = null;
            return;
        }
    }

    public void setOnDismissListener(android.content.DialogInterface.OnDismissListener ondismisslistener)
    {
        if (mCancelAndDismissTaken != null)
            throw new IllegalStateException((new StringBuilder()).append("OnDismissListener is already taken by ").append(mCancelAndDismissTaken).append(" and can not be replaced.").toString());
        if (ondismisslistener != null)
        {
            mDismissMessage = mListenersHandler.obtainMessage(67, ondismisslistener);
            return;
        } else
        {
            mDismissMessage = null;
            return;
        }
    }

    public void setOnKeyListener(android.content.DialogInterface.OnKeyListener onkeylistener)
    {
        mOnKeyListener = onkeylistener;
    }

    public void setOnShowListener(android.content.DialogInterface.OnShowListener onshowlistener)
    {
        if (onshowlistener != null)
        {
            mShowMessage = mListenersHandler.obtainMessage(69, onshowlistener);
            return;
        } else
        {
            mShowMessage = null;
            return;
        }
    }

    public final void setOwnerActivity(Activity activity)
    {
        mOwnerActivity = activity;
        getWindow().setVolumeControlStream(mOwnerActivity.getVolumeControlStream());
    }

    public void setTitle(int i)
    {
        setTitle(mContext.getText(i));
    }

    public void setTitle(CharSequence charsequence)
    {
        mWindow.setTitle(charsequence);
        mWindow.getAttributes().setTitle(charsequence);
    }

    public final void setVolumeControlStream(int i)
    {
        getWindow().setVolumeControlStream(i);
    }

    public void show()
    {
        android.view.WindowManager.LayoutParams layoutparams;
        if (mShowing)
        {
            if (mDecor != null)
            {
                if (mWindow.hasFeature(8))
                    mWindow.invalidatePanelMenu(8);
                mDecor.setVisibility(0);
            }
            return;
        }
        mCanceled = false;
        if (!mCreated)
            dispatchOnCreate(null);
        onStart();
        mDecor = mWindow.getDecorView();
        if (mActionBar == null && mWindow.hasFeature(8))
            mActionBar = new ActionBarImpl(this);
        layoutparams = mWindow.getAttributes();
        if ((0x100 & layoutparams.softInputMode) == 0)
        {
            android.view.WindowManager.LayoutParams layoutparams1 = new android.view.WindowManager.LayoutParams();
            layoutparams1.copyFrom(layoutparams);
            layoutparams1.softInputMode = 0x100 | layoutparams1.softInputMode;
            layoutparams = layoutparams1;
        }
        mWindowManager.addView(mDecor, layoutparams);
        mShowing = true;
        sendShowMessage();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean takeCancelAndDismissListeners(String s, android.content.DialogInterface.OnCancelListener oncancellistener, android.content.DialogInterface.OnDismissListener ondismisslistener)
    {
        if (mCancelAndDismissTaken != null)
            mCancelAndDismissTaken = null;
        else
        if (mCancelMessage != null || mDismissMessage != null)
            return false;
        setOnCancelListener(oncancellistener);
        setOnDismissListener(ondismisslistener);
        mCancelAndDismissTaken = s;
        return true;
    }

    public void takeKeyEvents(boolean flag)
    {
        mWindow.takeKeyEvents(flag);
    }

    public void unregisterForContextMenu(View view)
    {
        view.setOnCreateContextMenuListener(null);
    }
}
