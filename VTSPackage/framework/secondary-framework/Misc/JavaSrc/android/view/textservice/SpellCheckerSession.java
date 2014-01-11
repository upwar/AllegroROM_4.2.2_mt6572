// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst noctor space 

package android.view.textservice;

import android.os.*;
import android.util.Log;
import com.android.internal.textservice.*;
import java.util.LinkedList;
import java.util.Queue;

// Referenced classes of package android.view.textservice:
//            TextInfo, SpellCheckerInfo, SpellCheckerSubtype, SuggestionsInfo, 
//            SentenceSuggestionsInfo

public class SpellCheckerSession
{
    private static class InternalListener extends com.android.internal.textservice.ITextServicesSessionListener.Stub
    {

        private final SpellCheckerSessionListenerImpl mParentSpellCheckerSessionListenerImpl;

        public void onServiceConnected(ISpellCheckerSession ispellcheckersession)
        {
            mParentSpellCheckerSessionListenerImpl.onServiceConnected(ispellcheckersession);
        }

        public InternalListener(SpellCheckerSessionListenerImpl spellcheckersessionlistenerimpl)
        {
            mParentSpellCheckerSessionListenerImpl = spellcheckersessionlistenerimpl;
        }
    }

    public static interface SpellCheckerSessionListener
    {

        public abstract void onGetSentenceSuggestions(SentenceSuggestionsInfo asentencesuggestionsinfo[]);

        public abstract void onGetSuggestions(SuggestionsInfo asuggestionsinfo[]);
    }

    private static class SpellCheckerSessionListenerImpl extends com.android.internal.textservice.ISpellCheckerSessionListener.Stub
    {

        private static final int TASK_CANCEL = 1;
        private static final int TASK_CLOSE = 3;
        private static final int TASK_GET_SUGGESTIONS_MULTIPLE = 2;
        private static final int TASK_GET_SUGGESTIONS_MULTIPLE_FOR_SENTENCE = 4;
        private Handler mAsyncHandler;
        private Handler mHandler;
        private ISpellCheckerSession mISpellCheckerSession;
        private boolean mOpened;
        private final Queue mPendingTasks = new LinkedList();
        private HandlerThread mThread;

        private void processOrEnqueueTask(SpellCheckerParams spellcheckerparams)
        {
            this;
            JVM INSTR monitorenter ;
            ISpellCheckerSession ispellcheckersession = mISpellCheckerSession;
            if (ispellcheckersession != null)
                break MISSING_BLOCK_LABEL_99;
            int i = spellcheckerparams.mWhat;
            SpellCheckerParams spellcheckerparams1 = null;
            if (i != 1) goto _L2; else goto _L1
_L1:
            SpellCheckerParams spellcheckerparams2;
            if (mPendingTasks.isEmpty())
                break; /* Loop/switch isn't completed */
            spellcheckerparams2 = (SpellCheckerParams)mPendingTasks.poll();
            if (spellcheckerparams2.mWhat == 3)
                spellcheckerparams1 = spellcheckerparams2;
            if (true) goto _L1; else goto _L2
_L2:
            mPendingTasks.offer(spellcheckerparams);
            if (spellcheckerparams1 == null)
                break MISSING_BLOCK_LABEL_96;
            mPendingTasks.offer(spellcheckerparams1);
            this;
            JVM INSTR monitorexit ;
            return;
            this;
            JVM INSTR monitorexit ;
            processTask(ispellcheckersession, spellcheckerparams, false);
            return;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private void processTask(ISpellCheckerSession ispellcheckersession, SpellCheckerParams spellcheckerparams, boolean flag)
        {
            if (!flag && mAsyncHandler != null) goto _L2; else goto _L1
_L1:
            spellcheckerparams.mWhat;
            JVM INSTR tableswitch 1 4: default 44
        //                       1 92
        //                       2 133
        //                       3 235
        //                       4 186;
               goto _L3 _L4 _L5 _L6 _L7
_L3:
            if (spellcheckerparams.mWhat != 3) goto _L9; else goto _L8
_L8:
            this;
            JVM INSTR monitorenter ;
            mISpellCheckerSession = null;
            mHandler = null;
            if (mThread != null)
                mThread.quit();
            mThread = null;
            mAsyncHandler = null;
            this;
            JVM INSTR monitorexit ;
_L9:
            return;
_L4:
            try
            {
                ispellcheckersession.onCancel();
            }
            catch (RemoteException remoteexception3)
            {
                Log.e(SpellCheckerSession.TAG, (new StringBuilder()).append("Failed to cancel ").append(remoteexception3).toString());
            }
            continue; /* Loop/switch isn't completed */
_L5:
            try
            {
                ispellcheckersession.onGetSuggestionsMultiple(spellcheckerparams.mTextInfos, spellcheckerparams.mSuggestionsLimit, spellcheckerparams.mSequentialWords);
            }
            catch (RemoteException remoteexception2)
            {
                Log.e(SpellCheckerSession.TAG, (new StringBuilder()).append("Failed to get suggestions ").append(remoteexception2).toString());
            }
            continue; /* Loop/switch isn't completed */
_L7:
            try
            {
                ispellcheckersession.onGetSentenceSuggestionsMultiple(spellcheckerparams.mTextInfos, spellcheckerparams.mSuggestionsLimit);
            }
            catch (RemoteException remoteexception1)
            {
                Log.e(SpellCheckerSession.TAG, (new StringBuilder()).append("Failed to get suggestions ").append(remoteexception1).toString());
            }
            continue; /* Loop/switch isn't completed */
_L6:
            try
            {
                ispellcheckersession.onClose();
            }
            catch (RemoteException remoteexception)
            {
                Log.e(SpellCheckerSession.TAG, (new StringBuilder()).append("Failed to close ").append(remoteexception).toString());
            }
            continue; /* Loop/switch isn't completed */
_L2:
            spellcheckerparams.mSession = ispellcheckersession;
            mAsyncHandler.sendMessage(Message.obtain(mAsyncHandler, 1, spellcheckerparams));
            if (true) goto _L3; else goto _L10
_L10:
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void cancel()
        {
            processOrEnqueueTask(new SpellCheckerParams(1, null, 0, false));
        }

        public void close()
        {
            processOrEnqueueTask(new SpellCheckerParams(3, null, 0, false));
        }

        public void getSentenceSuggestionsMultiple(TextInfo atextinfo[], int i)
        {
            processOrEnqueueTask(new SpellCheckerParams(4, atextinfo, i, false));
        }

        public void getSuggestionsMultiple(TextInfo atextinfo[], int i, boolean flag)
        {
            processOrEnqueueTask(new SpellCheckerParams(2, atextinfo, i, flag));
        }

        public boolean isDisconnected()
        {
            return mOpened && mISpellCheckerSession == null;
        }

        public void onGetSentenceSuggestions(SentenceSuggestionsInfo asentencesuggestionsinfo[])
        {
            mHandler.sendMessage(Message.obtain(mHandler, 2, asentencesuggestionsinfo));
        }

        public void onGetSuggestions(SuggestionsInfo asuggestionsinfo[])
        {
            this;
            JVM INSTR monitorenter ;
            if (mHandler != null)
                mHandler.sendMessage(Message.obtain(mHandler, 1, asuggestionsinfo));
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onServiceConnected(ISpellCheckerSession ispellcheckersession)
        {
            this;
            JVM INSTR monitorenter ;
            this;
            JVM INSTR monitorenter ;
            mISpellCheckerSession = ispellcheckersession;
            if ((ispellcheckersession.asBinder() instanceof Binder) && mThread == null)
            {
                mThread = new HandlerThread("SpellCheckerSession", 10);
                mThread.start();
                mAsyncHandler = new Handler(mThread.getLooper()) {

                    final SpellCheckerSessionListenerImpl this$0;

                    public void handleMessage(Message message)
                    {
                        SpellCheckerSessionListenerImpl.SpellCheckerParams spellcheckerparams = (SpellCheckerSessionListenerImpl.SpellCheckerParams)message.obj;
                        processTask(spellcheckerparams.mSession, spellcheckerparams, true);
                    }

            
            {
                this$0 = SpellCheckerSessionListenerImpl.this;
                super(looper);
            }
                }
;
            }
            mOpened = true;
            this;
            JVM INSTR monitorexit ;
            for (; !mPendingTasks.isEmpty(); processTask(ispellcheckersession, (SpellCheckerParams)mPendingTasks.poll(), false));
            break MISSING_BLOCK_LABEL_119;
            Exception exception;
            exception;
            throw exception;
            Exception exception1;
            exception1;
            this;
            JVM INSTR monitorexit ;
            throw exception1;
            this;
            JVM INSTR monitorexit ;
        }


        public SpellCheckerSessionListenerImpl(Handler handler)
        {
            mOpened = false;
            mHandler = handler;
        }
    }

    private static class SpellCheckerSessionListenerImpl.SpellCheckerParams
    {

        public final boolean mSequentialWords;
        public ISpellCheckerSession mSession;
        public final int mSuggestionsLimit;
        public final TextInfo mTextInfos[];
        public final int mWhat;

        public SpellCheckerSessionListenerImpl.SpellCheckerParams(int i, TextInfo atextinfo[], int j, boolean flag)
        {
            mWhat = i;
            mTextInfos = atextinfo;
            mSuggestionsLimit = j;
            mSequentialWords = flag;
        }
    }


    private static final boolean DBG = false;
    private static final int MSG_ON_GET_SUGGESTION_MULTIPLE = 1;
    private static final int MSG_ON_GET_SUGGESTION_MULTIPLE_FOR_SENTENCE = 2;
    public static final String SERVICE_META_DATA = "android.view.textservice.scs";
    private static final String TAG = android/view/textservice/SpellCheckerSession.getSimpleName();
    private final Handler mHandler = new Handler() {

        final SpellCheckerSession this$0;

        public void handleMessage(Message message)
        {
            switch (message.what)
            {
            case 2: // '\002'
                handleOnGetSentenceSuggestionsMultiple((SentenceSuggestionsInfo[])(SentenceSuggestionsInfo[])message.obj);
                return;

            case 1: // '\001'
                handleOnGetSuggestionsMultiple((SuggestionsInfo[])(SuggestionsInfo[])message.obj);
                return;
            }
        }

            
            {
                this$0 = SpellCheckerSession.this;
                super();
            }
    }
;
    private final InternalListener mInternalListener;
    private boolean mIsUsed;
    private final SpellCheckerInfo mSpellCheckerInfo;
    private SpellCheckerSessionListener mSpellCheckerSessionListener;
    private final SpellCheckerSessionListenerImpl mSpellCheckerSessionListenerImpl;
    private final SpellCheckerSubtype mSubtype;
    private final ITextServicesManager mTextServicesManager;

    public SpellCheckerSession(SpellCheckerInfo spellcheckerinfo, ITextServicesManager itextservicesmanager, SpellCheckerSessionListener spellcheckersessionlistener, SpellCheckerSubtype spellcheckersubtype)
    {
        if (spellcheckerinfo != null && spellcheckersessionlistener != null && itextservicesmanager != null)
        {
            mSpellCheckerInfo = spellcheckerinfo;
            mSpellCheckerSessionListenerImpl = new SpellCheckerSessionListenerImpl(mHandler);
            mInternalListener = new InternalListener(mSpellCheckerSessionListenerImpl);
            mTextServicesManager = itextservicesmanager;
            mIsUsed = true;
            mSpellCheckerSessionListener = spellcheckersessionlistener;
            mSubtype = spellcheckersubtype;
            return;
        } else
        {
            throw new NullPointerException();
        }
    }

    private void handleOnGetSentenceSuggestionsMultiple(SentenceSuggestionsInfo asentencesuggestionsinfo[])
    {
        mSpellCheckerSessionListener.onGetSentenceSuggestions(asentencesuggestionsinfo);
    }

    private void handleOnGetSuggestionsMultiple(SuggestionsInfo asuggestionsinfo[])
    {
        mSpellCheckerSessionListener.onGetSuggestions(asuggestionsinfo);
    }

    public void cancel()
    {
        mSpellCheckerSessionListenerImpl.cancel();
    }

    public void close()
    {
        mIsUsed = false;
        try
        {
            mSpellCheckerSessionListenerImpl.close();
            mTextServicesManager.finishSpellCheckerService(mSpellCheckerSessionListenerImpl);
            return;
        }
        catch (RemoteException remoteexception)
        {
            return;
        }
    }

    protected void finalize()
        throws Throwable
    {
        super.finalize();
        if (mIsUsed)
        {
            Log.e(TAG, "SpellCheckerSession was not finished properly.You should call finishShession() when you finished to use a spell checker.");
            close();
        }
    }

    public void getSentenceSuggestions(TextInfo atextinfo[], int i)
    {
        mSpellCheckerSessionListenerImpl.getSentenceSuggestionsMultiple(atextinfo, i);
    }

    public SpellCheckerInfo getSpellChecker()
    {
        return mSpellCheckerInfo;
    }

    public ISpellCheckerSessionListener getSpellCheckerSessionListener()
    {
        return mSpellCheckerSessionListenerImpl;
    }

    public void getSuggestions(TextInfo textinfo, int i)
    {
        getSuggestions(new TextInfo[] {
            textinfo
        }, i, false);
    }

    public void getSuggestions(TextInfo atextinfo[], int i, boolean flag)
    {
        mSpellCheckerSessionListenerImpl.getSuggestionsMultiple(atextinfo, i, flag);
    }

    public ITextServicesSessionListener getTextServicesSessionListener()
    {
        return mInternalListener;
    }

    public boolean isSessionDisconnected()
    {
        return mSpellCheckerSessionListenerImpl.isDisconnected();
    }




}
