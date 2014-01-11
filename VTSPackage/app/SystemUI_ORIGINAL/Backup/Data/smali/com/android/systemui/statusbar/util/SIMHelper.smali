.class public Lcom/android/systemui/statusbar/util/SIMHelper;
.super Ljava/lang/Object;
.source "SIMHelper.java"


# static fields
.field private static final MOBILE_ICON_COUNT:I = 0x4

.field private static final SIM_STATUS_COUNT:I = 0x9

.field public static final TAG:Ljava/lang/String; = "SIMHelper"

.field private static mGeminiSimNum:I

.field private static mRegistry:Lcom/android/internal/telephony/ITelephonyRegistry;

.field private static mRegistry2:Lcom/android/internal/telephony/ITelephonyRegistry;

.field private static mTMEx:Lcom/mediatek/telephony/TelephonyManagerEx;

.field private static sBaseband:Ljava/lang/String;

.field private static sITelephony:Lcom/android/internal/telephony/ITelephony;

.field private static sIsOptr:Ljava/lang/String;

.field private static sMobileIconResIds:[I

.field private static sSimInfos:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List",
            "<",
            "Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;",
            ">;"
        }
    .end annotation
.end field

.field private static sSimStatusViews:[I

.field private static simInserted:[Z


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 40
    sput-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sIsOptr:Ljava/lang/String;

    .line 41
    sput-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sBaseband:Ljava/lang/String;

    .line 45
    sput-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->mTMEx:Lcom/mediatek/telephony/TelephonyManagerEx;

    .line 46
    sget v0, Lcom/android/internal/telephony/PhoneConstants;->GEMINI_SIM_NUM:I

    sput v0, Lcom/android/systemui/statusbar/util/SIMHelper;->mGeminiSimNum:I

    .line 328
    const-string v0, "telephony.registry"

    invoke-static {v0}, Landroid/os/ServiceManager;->getService(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object v0

    invoke-static {v0}, Lcom/android/internal/telephony/ITelephonyRegistry$Stub;->asInterface(Landroid/os/IBinder;)Lcom/android/internal/telephony/ITelephonyRegistry;

    move-result-object v0

    sput-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->mRegistry:Lcom/android/internal/telephony/ITelephonyRegistry;

    .line 329
    const-string v0, "telephony.registry2"

    invoke-static {v0}, Landroid/os/ServiceManager;->getService(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object v0

    invoke-static {v0}, Lcom/android/internal/telephony/ITelephonyRegistry$Stub;->asInterface(Landroid/os/IBinder;)Lcom/android/internal/telephony/ITelephonyRegistry;

    move-result-object v0

    sput-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->mRegistry2:Lcom/android/internal/telephony/ITelephonyRegistry;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .prologue
    .line 49
    invoke-direct/range {p0 .. p0}, Ljava/lang/Object;-><init>()V

    .line 50
    return-void
.end method

.method public static checkSimCardDataConn()Z
    .locals 2

    .prologue
    .line 238
    invoke-static {}, Lcom/android/systemui/statusbar/util/SIMHelper;->getSimIndicatorState()I

    move-result v0

    .line 239
    .local v0, simState:I
    const/4 v1, 0x6

    if-eq v0, v1, :cond_0

    const/4 v1, 0x7

    if-eq v0, v1, :cond_0

    const/16 v1, 0x8

    if-eq v0, v1, :cond_0

    const/4 v1, 0x5

    if-ne v0, v1, :cond_1

    .line 243
    :cond_0
    const/4 v1, 0x1

    .line 245
    :goto_0
    return v1

    :cond_1
    const/4 v1, 0x0

    goto :goto_0
.end method

.method public static checkSimCardDataConnBySlotId(Landroid/content/Context;I)Z
    .locals 4
    .parameter "context"
    .parameter "slotId"

    .prologue
    const/4 v2, 0x0

    .line 210
    invoke-static {p0, p1}, Lcom/android/systemui/statusbar/util/SIMHelper;->getSIMInfoBySlot(Landroid/content/Context;I)Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;

    move-result-object v0

    .line 211
    .local v0, simInfo:Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;
    if-nez v0, :cond_1

    .line 221
    :cond_0
    :goto_0
    return v2

    .line 214
    :cond_1
    iget v3, v0, Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;->mSimSlotId:I

    invoke-static {v3}, Lcom/android/systemui/statusbar/util/SIMHelper;->getSimIndicatorStateGemini(I)I

    move-result v1

    .line 215
    .local v1, simState:I
    const/4 v3, 0x6

    if-eq v1, v3, :cond_2

    const/4 v3, 0x7

    if-eq v1, v3, :cond_2

    const/16 v3, 0x8

    if-eq v1, v3, :cond_2

    const/4 v3, 0x5

    if-ne v1, v3, :cond_0

    .line 219
    :cond_2
    const/4 v2, 0x1

    goto :goto_0
.end method

.method public static getDataConnectionIconIdBySlotId(Landroid/content/Context;I)I
    .locals 3
    .parameter "context"
    .parameter "slotId"

    .prologue
    const/4 v1, -0x1

    .line 195
    invoke-static {p0, p1}, Lcom/android/systemui/statusbar/util/SIMHelper;->getSIMInfoBySlot(Landroid/content/Context;I)Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;

    move-result-object v0

    .line 196
    .local v0, simInfo:Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;
    if-nez v0, :cond_1

    .line 205
    :cond_0
    :goto_0
    return v1

    .line 199
    :cond_1
    sget-object v2, Lcom/android/systemui/statusbar/util/SIMHelper;->sMobileIconResIds:[I

    if-nez v2, :cond_2

    .line 200
    invoke-static {}, Lcom/android/systemui/statusbar/util/SIMHelper;->initMobileIcons()V

    .line 202
    :cond_2
    iget v2, v0, Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;->mColor:I

    if-eq v2, v1, :cond_0

    .line 205
    sget-object v1, Lcom/android/systemui/statusbar/util/SIMHelper;->sMobileIconResIds:[I

    iget v2, v0, Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;->mColor:I

    aget v1, v1, v2

    goto :goto_0
.end method

.method public static getDefault(Landroid/content/Context;)Lcom/mediatek/telephony/TelephonyManagerEx;
    .locals 1
    .parameter "context"

    .prologue
    .line 318
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->mTMEx:Lcom/mediatek/telephony/TelephonyManagerEx;

    if-nez v0, :cond_0

    .line 319
    new-instance v0, Lcom/mediatek/telephony/TelephonyManagerEx;

    invoke-direct {v0, p0}, Lcom/mediatek/telephony/TelephonyManagerEx;-><init>(Landroid/content/Context;)V

    sput-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->mTMEx:Lcom/mediatek/telephony/TelephonyManagerEx;

    .line 321
    :cond_0
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->mTMEx:Lcom/mediatek/telephony/TelephonyManagerEx;

    return-object v0
.end method

.method public static getDefaultSIM(Landroid/content/Context;Ljava/lang/String;)J
    .locals 3
    .parameter "context"
    .parameter "businessType"

    .prologue
    .line 60
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    const-wide/16 v1, -0x1

    invoke-static {v0, p1, v1, v2}, Landroid/provider/Settings$System;->getLong(Landroid/content/ContentResolver;Ljava/lang/String;J)J

    move-result-wide v0

    return-wide v0
.end method

.method public static getITelephony()Lcom/android/internal/telephony/ITelephony;
    .locals 1

    .prologue
    .line 325
    const-string v0, "phone"

    invoke-static {v0}, Landroid/os/ServiceManager;->getService(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object v0

    invoke-static {v0}, Lcom/android/internal/telephony/ITelephony$Stub;->asInterface(Landroid/os/IBinder;)Lcom/android/internal/telephony/ITelephony;

    move-result-object v0

    sput-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sITelephony:Lcom/android/internal/telephony/ITelephony;

    return-object v0
.end method

.method public static getSIMColorIdBySlot(Landroid/content/Context;I)I
    .locals 2
    .parameter "context"
    .parameter "slotId"

    .prologue
    .line 173
    invoke-static {p0, p1}, Lcom/android/systemui/statusbar/util/SIMHelper;->getSIMInfoBySlot(Landroid/content/Context;I)Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;

    move-result-object v0

    .line 174
    .local v0, simInfo:Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;
    if-nez v0, :cond_0

    .line 175
    const/4 v1, -0x1

    .line 177
    :goto_0
    return v1

    :cond_0
    iget v1, v0, Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;->mColor:I

    goto :goto_0
.end method

.method public static getSIMIdBySlot(Landroid/content/Context;I)J
    .locals 3
    .parameter "context"
    .parameter "slotId"

    .prologue
    .line 165
    invoke-static {p0, p1}, Lcom/android/systemui/statusbar/util/SIMHelper;->getSIMInfoBySlot(Landroid/content/Context;I)Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;

    move-result-object v0

    .line 166
    .local v0, simInfo:Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;
    if-nez v0, :cond_0

    .line 167
    const-wide/16 v1, 0x0

    .line 169
    :goto_0
    return-wide v1

    :cond_0
    iget-wide v1, v0, Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;->mSimInfoId:J

    goto :goto_0
.end method

.method public static getSIMInfo(Landroid/content/Context;J)Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;
    .locals 4
    .parameter "context"
    .parameter "simId"

    .prologue
    .line 82
    sget-object v2, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimInfos:Ljava/util/List;

    if-eqz v2, :cond_0

    sget-object v2, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimInfos:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v2

    if-nez v2, :cond_1

    .line 83
    :cond_0
    invoke-static {p0}, Lcom/android/systemui/statusbar/util/SIMHelper;->getSIMInfoList(Landroid/content/Context;)Ljava/util/List;

    .line 85
    :cond_1
    sget-object v2, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimInfos:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    .local v0, i$:Ljava/util/Iterator;
    :cond_2
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_3

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;

    .line 86
    .local v1, info:Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;
    iget-wide v2, v1, Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;->mSimInfoId:J

    cmp-long v2, v2, p1

    if-nez v2, :cond_2

    .line 90
    .end local v1           #info:Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;
    :goto_0
    return-object v1

    :cond_3
    const/4 v1, 0x0

    goto :goto_0
.end method

.method public static getSIMInfoBySlot(Landroid/content/Context;I)Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;
    .locals 4
    .parameter "context"
    .parameter "slotId"

    .prologue
    const/4 v2, 0x0

    .line 101
    invoke-static {p1}, Lcom/android/systemui/statusbar/util/SIMHelper;->isSimInserted(I)Z

    move-result v3

    if-nez v3, :cond_0

    move-object v1, v2

    .line 116
    :goto_0
    return-object v1

    .line 104
    :cond_0
    sget-object v3, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimInfos:Ljava/util/List;

    if-eqz v3, :cond_1

    sget-object v3, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimInfos:Ljava/util/List;

    invoke-interface {v3}, Ljava/util/List;->size()I

    move-result v3

    if-nez v3, :cond_2

    .line 105
    :cond_1
    invoke-static {p0}, Lcom/android/systemui/statusbar/util/SIMHelper;->getSIMInfoList(Landroid/content/Context;)Ljava/util/List;

    .line 107
    :cond_2
    sget-object v3, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimInfos:Ljava/util/List;

    if-nez v3, :cond_3

    move-object v1, v2

    .line 108
    goto :goto_0

    .line 111
    :cond_3
    sget-object v3, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimInfos:Ljava/util/List;

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    .local v0, i$:Ljava/util/Iterator;
    :cond_4
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_5

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;

    .line 112
    .local v1, info:Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;
    iget v3, v1, Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;->mSimSlotId:I

    if-ne v3, p1, :cond_4

    goto :goto_0

    .end local v1           #info:Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;
    :cond_5
    move-object v1, v2

    .line 116
    goto :goto_0
.end method

.method public static getSIMInfoList(Landroid/content/Context;)Ljava/util/List;
    .locals 1
    .parameter "context"
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            ")",
            "Ljava/util/List",
            "<",
            "Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;",
            ">;"
        }
    .end annotation

    .prologue
    .line 68
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimInfos:Ljava/util/List;

    if-eqz v0, :cond_0

    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimInfos:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-nez v0, :cond_1

    .line 69
    :cond_0
    invoke-static {p0}, Lcom/android/systemui/statusbar/util/SIMHelper;->getSortedSIMInfoList(Landroid/content/Context;)Ljava/util/List;

    move-result-object v0

    sput-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimInfos:Ljava/util/List;

    .line 71
    :cond_1
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimInfos:Ljava/util/List;

    return-object v0
.end method

.method public static getSIMStateIcon(I)I
    .locals 2
    .parameter "simStatus"

    .prologue
    const/4 v0, -0x1

    .line 185
    if-le p0, v0, :cond_0

    const/16 v1, 0x9

    if-lt p0, v1, :cond_1

    .line 191
    :cond_0
    :goto_0
    return v0

    .line 188
    :cond_1
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimStatusViews:[I

    if-nez v0, :cond_2

    .line 189
    invoke-static {}, Lcom/android/systemui/statusbar/util/SIMHelper;->initStatusIcons()V

    .line 191
    :cond_2
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimStatusViews:[I

    aget v0, v0, p0

    goto :goto_0
.end method

.method public static getSIMStateIcon(Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;)I
    .locals 1
    .parameter "simInfo"

    .prologue
    .line 181
    iget v0, p0, Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;->mSimSlotId:I

    invoke-static {v0}, Lcom/android/systemui/statusbar/util/SIMHelper;->getSimIndicatorStateGemini(I)I

    move-result v0

    invoke-static {v0}, Lcom/android/systemui/statusbar/util/SIMHelper;->getSIMStateIcon(I)I

    move-result v0

    return v0
.end method

.method public static getSimIndicatorState()I
    .locals 4

    .prologue
    const/4 v2, -0x1

    .line 264
    :try_start_0
    const-string v3, "phone"

    invoke-static {v3}, Landroid/os/ServiceManager;->getService(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object v3

    invoke-static {v3}, Lcom/android/internal/telephony/ITelephony$Stub;->asInterface(Landroid/os/IBinder;)Lcom/android/internal/telephony/ITelephony;

    move-result-object v1

    .line 265
    .local v1, telephony:Lcom/android/internal/telephony/ITelephony;
    if-eqz v1, :cond_0

    .line 266
    invoke-interface {v1}, Lcom/android/internal/telephony/ITelephony;->getSimIndicatorState()I
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_1

    move-result v2

    .line 275
    :cond_0
    :goto_0
    return v2

    .line 271
    :catch_0
    move-exception v0

    .line 273
    .local v0, ex:Landroid/os/RemoteException;
    goto :goto_0

    .line 274
    .end local v0           #ex:Landroid/os/RemoteException;
    :catch_1
    move-exception v0

    .line 275
    .local v0, ex:Ljava/lang/NullPointerException;
    goto :goto_0
.end method

.method public static getSimIndicatorStateGemini(I)I
    .locals 7
    .parameter "simId"

    .prologue
    const/4 v3, -0x1

    .line 281
    :try_start_0
    const-string v4, "phone"

    invoke-static {v4}, Landroid/os/ServiceManager;->getService(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object v4

    invoke-static {v4}, Lcom/android/internal/telephony/ITelephony$Stub;->asInterface(Landroid/os/IBinder;)Lcom/android/internal/telephony/ITelephony;

    move-result-object v2

    .line 282
    .local v2, telephony:Lcom/android/internal/telephony/ITelephony;
    if-eqz v2, :cond_1

    .line 283
    invoke-interface {v2, p0}, Lcom/android/internal/telephony/ITelephony;->getSimIndicatorStateGemini(I)I

    move-result v1

    .line 284
    .local v1, mState:I
    const/4 v4, 0x7

    if-ne v1, v4, :cond_0

    const/4 v4, 0x2

    invoke-static {}, Lcom/mediatek/telephony/TelephonyManagerEx;->getDefault()Lcom/mediatek/telephony/TelephonyManagerEx;

    move-result-object v5

    invoke-virtual {v5, p0}, Lcom/mediatek/telephony/TelephonyManagerEx;->getDataState(I)I

    move-result v5

    if-eq v4, v5, :cond_0

    .line 287
    const-string v4, "SIMHelper"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "getSimIndicatorStateGemini called, fallback to normal and simId is "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Lcom/mediatek/xlog/Xlog;->d(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_1

    .line 288
    const/4 v1, 0x5

    .line 299
    .end local v1           #mState:I
    .end local v2           #telephony:Lcom/android/internal/telephony/ITelephony;
    :cond_0
    :goto_0
    return v1

    .restart local v2       #telephony:Lcom/android/internal/telephony/ITelephony;
    :cond_1
    move v1, v3

    .line 293
    goto :goto_0

    .line 295
    .end local v2           #telephony:Lcom/android/internal/telephony/ITelephony;
    :catch_0
    move-exception v0

    .local v0, ex:Landroid/os/RemoteException;
    move v1, v3

    .line 297
    goto :goto_0

    .line 298
    .end local v0           #ex:Landroid/os/RemoteException;
    :catch_1
    move-exception v0

    .local v0, ex:Ljava/lang/NullPointerException;
    move v1, v3

    .line 299
    goto :goto_0
.end method

.method private static getSortedSIMInfoList(Landroid/content/Context;)Ljava/util/List;
    .locals 2
    .parameter "context"
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            ")",
            "Ljava/util/List",
            "<",
            "Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;",
            ">;"
        }
    .end annotation

    .prologue
    .line 120
    invoke-static {p0}, Lcom/mediatek/telephony/SimInfoManager;->getInsertedSimInfoList(Landroid/content/Context;)Ljava/util/List;

    move-result-object v0

    .line 121
    .local v0, simInfoList:Ljava/util/List;,"Ljava/util/List<Lcom/mediatek/telephony/SimInfoManager$SimInfoRecord;>;"
    new-instance v1, Lcom/android/systemui/statusbar/util/SIMHelper$1;

    invoke-direct {v1}, Lcom/android/systemui/statusbar/util/SIMHelper$1;-><init>()V

    invoke-static {v0, v1}, Ljava/util/Collections;->sort(Ljava/util/List;Ljava/util/Comparator;)V

    .line 133
    return-object v0
.end method

.method public static initMobileIcons()V
    .locals 3

    .prologue
    .line 155
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sMobileIconResIds:[I

    if-nez v0, :cond_0

    .line 156
    const/4 v0, 0x4

    new-array v0, v0, [I

    sput-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sMobileIconResIds:[I

    .line 157
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sMobileIconResIds:[I

    const/4 v1, 0x0

    const v2, 0x7f020067

    aput v2, v0, v1

    .line 158
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sMobileIconResIds:[I

    const/4 v1, 0x1

    const v2, 0x7f02006c

    aput v2, v0, v1

    .line 159
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sMobileIconResIds:[I

    const/4 v1, 0x2

    const v2, 0x7f02006a

    aput v2, v0, v1

    .line 160
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sMobileIconResIds:[I

    const/4 v1, 0x3

    const v2, 0x7f02006d

    aput v2, v0, v1

    .line 162
    :cond_0
    return-void
.end method

.method public static initStatusIcons()V
    .locals 3

    .prologue
    .line 142
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimStatusViews:[I

    if-nez v0, :cond_0

    .line 143
    const/16 v0, 0x9

    new-array v0, v0, [I

    sput-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimStatusViews:[I

    .line 144
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimStatusViews:[I

    const/4 v1, 0x1

    const v2, 0x2020112

    aput v2, v0, v1

    .line 145
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimStatusViews:[I

    const/4 v1, 0x2

    const v2, 0x20200ff

    aput v2, v0, v1

    .line 146
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimStatusViews:[I

    const/4 v1, 0x3

    const v2, 0x20200f8

    aput v2, v0, v1

    .line 147
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimStatusViews:[I

    const/4 v1, 0x4

    const v2, 0x2020119

    aput v2, v0, v1

    .line 148
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimStatusViews:[I

    const/4 v1, 0x6

    const v2, 0x2020117

    aput v2, v0, v1

    .line 149
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimStatusViews:[I

    const/4 v1, 0x7

    const v2, 0x20200ef

    aput v2, v0, v1

    .line 150
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimStatusViews:[I

    const/16 v1, 0x8

    const v2, 0x2020118

    aput v2, v0, v1

    .line 152
    :cond_0
    return-void
.end method

.method public static is3GSupported()Z
    .locals 2

    .prologue
    .line 251
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sBaseband:Ljava/lang/String;

    if-nez v0, :cond_0

    .line 252
    const-string v0, "gsm.baseband.capability"

    invoke-static {v0}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sBaseband:Ljava/lang/String;

    .line 254
    :cond_0
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sBaseband:Ljava/lang/String;

    if-eqz v0, :cond_1

    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sBaseband:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    if-eqz v0, :cond_1

    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sBaseband:Ljava/lang/String;

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    const/4 v1, 0x3

    if-gt v0, v1, :cond_1

    .line 256
    const/4 v0, 0x0

    .line 258
    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x1

    goto :goto_0
.end method

.method public static isSimInserted(I)Z
    .locals 4
    .parameter "slotId"

    .prologue
    const/4 v0, 0x0

    .line 352
    sget-object v1, Lcom/android/systemui/statusbar/util/SIMHelper;->simInserted:[Z

    if-nez v1, :cond_0

    .line 353
    invoke-static {}, Lcom/android/systemui/statusbar/util/SIMHelper;->updateSimInsertedStatus()V

    .line 355
    :cond_0
    sget-object v1, Lcom/android/systemui/statusbar/util/SIMHelper;->simInserted:[Z

    if-eqz v1, :cond_2

    .line 356
    sget-object v1, Lcom/android/systemui/statusbar/util/SIMHelper;->simInserted:[Z

    array-length v1, v1

    add-int/lit8 v1, v1, -0x1

    if-gt p0, v1, :cond_1

    .line 357
    const-string v0, "SIMHelper"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "isSimInserted("

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "), SimInserted="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    sget-object v2, Lcom/android/systemui/statusbar/util/SIMHelper;->simInserted:[Z

    aget-boolean v2, v2, p0

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/mediatek/xlog/Xlog;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 358
    sget-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->simInserted:[Z

    aget-boolean v0, v0, p0

    .line 365
    :goto_0
    return v0

    .line 360
    :cond_1
    const-string v1, "SIMHelper"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "isSimInserted("

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "), indexOutOfBound, arraysize="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    sget-object v3, Lcom/android/systemui/statusbar/util/SIMHelper;->simInserted:[Z

    array-length v3, v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/mediatek/xlog/Xlog;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 364
    :cond_2
    const-string v1, "SIMHelper"

    const-string v2, "isSimInserted, simInserted is null"

    invoke-static {v1, v2}, Lcom/mediatek/xlog/Xlog;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0
.end method

.method public static isTelephonyDataConnected(Landroid/content/Context;)Z
    .locals 4
    .parameter "context"

    .prologue
    .line 227
    const-string v1, "phone"

    invoke-virtual {p0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/telephony/TelephonyManager;

    .line 228
    .local v0, tm:Landroid/telephony/TelephonyManager;
    if-eqz v0, :cond_0

    const/4 v1, 0x2

    invoke-virtual {v0}, Landroid/telephony/TelephonyManager;->getDataState()I

    move-result v2

    if-ne v1, v2, :cond_0

    .line 229
    const-string v1, "SIMHelper"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "isTelephonyDataConnected called, the data state is "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v0}, Landroid/telephony/TelephonyManager;->getDataState()I

    move-result v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/mediatek/xlog/Xlog;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 230
    const/4 v1, 0x1

    .line 232
    :goto_0
    return v1

    :cond_0
    const/4 v1, 0x0

    goto :goto_0
.end method

.method public static isTelephonyDataConnectedBySimId(I)Z
    .locals 5
    .parameter "simId"

    .prologue
    const/4 v1, 0x0

    .line 305
    const/4 v2, 0x2

    :try_start_0
    invoke-static {}, Lcom/mediatek/telephony/TelephonyManagerEx;->getDefault()Lcom/mediatek/telephony/TelephonyManagerEx;

    move-result-object v3

    invoke-virtual {v3, p0}, Lcom/mediatek/telephony/TelephonyManagerEx;->getDataState(I)I

    move-result v3

    if-ne v2, v3, :cond_0

    .line 306
    const-string v2, "SIMHelper"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "isTelephonyDataConnectedBySimId called, data is connected and simId is "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Lcom/mediatek/xlog/Xlog;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 307
    const/4 v1, 0x1

    .line 313
    :goto_0
    return v1

    .line 309
    :cond_0
    const-string v2, "SIMHelper"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "isTelephonyDataConnectedBySimId called, data is not connected and simId is "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Lcom/mediatek/xlog/Xlog;->d(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 312
    :catch_0
    move-exception v0

    .line 313
    .local v0, ex:Ljava/lang/NullPointerException;
    goto :goto_0
.end method

.method public static listen(Landroid/telephony/PhoneStateListener;II)V
    .locals 5
    .parameter "listener"
    .parameter "events"
    .parameter "slotId"

    .prologue
    const/4 v3, 0x3

    const/4 v2, 0x1

    .line 333
    :try_start_0
    invoke-static {}, Lcom/android/systemui/statusbar/util/SIMHelper;->getITelephony()Lcom/android/internal/telephony/ITelephony;

    move-result-object v1

    if-eqz v1, :cond_1

    move v1, v2

    :goto_0
    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    .line 335
    .local v0, notifyNow:Ljava/lang/Boolean;
    if-nez p2, :cond_2

    .line 336
    sget-object v1, Lcom/android/systemui/statusbar/util/SIMHelper;->mRegistry:Lcom/android/internal/telephony/ITelephonyRegistry;

    const-string v2, "SystemUI SIMHelper"

    invoke-virtual {p0}, Landroid/telephony/PhoneStateListener;->getCallback()Lcom/android/internal/telephony/IPhoneStateListener;

    move-result-object v3

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v4

    invoke-interface {v1, v2, v3, p1, v4}, Lcom/android/internal/telephony/ITelephonyRegistry;->listen(Ljava/lang/String;Lcom/android/internal/telephony/IPhoneStateListener;IZ)V

    .line 349
    .end local v0           #notifyNow:Ljava/lang/Boolean;
    :cond_0
    :goto_1
    return-void

    .line 333
    :cond_1
    const/4 v1, 0x0

    goto :goto_0

    .line 337
    .restart local v0       #notifyNow:Ljava/lang/Boolean;
    :cond_2
    if-ne v2, p2, :cond_3

    .line 338
    sget-object v1, Lcom/android/systemui/statusbar/util/SIMHelper;->mRegistry2:Lcom/android/internal/telephony/ITelephonyRegistry;

    const-string v2, "SystemUI SIMHelper"

    invoke-virtual {p0}, Landroid/telephony/PhoneStateListener;->getCallback()Lcom/android/internal/telephony/IPhoneStateListener;

    move-result-object v3

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v4

    invoke-interface {v1, v2, v3, p1, v4}, Lcom/android/internal/telephony/ITelephonyRegistry;->listen(Ljava/lang/String;Lcom/android/internal/telephony/IPhoneStateListener;IZ)V

    goto :goto_1

    .line 344
    .end local v0           #notifyNow:Ljava/lang/Boolean;
    :catch_0
    move-exception v1

    goto :goto_1

    .line 339
    .restart local v0       #notifyNow:Ljava/lang/Boolean;
    :cond_3
    const/4 v1, 0x2

    if-ne v1, p2, :cond_4

    sget v1, Lcom/android/internal/telephony/PhoneConstants;->GEMINI_SIM_NUM:I

    if-lt v1, v3, :cond_4

    .line 340
    const-string v1, "telephony.registry3"

    invoke-static {v1}, Landroid/os/ServiceManager;->getService(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object v1

    invoke-static {v1}, Lcom/android/internal/telephony/ITelephonyRegistry$Stub;->asInterface(Landroid/os/IBinder;)Lcom/android/internal/telephony/ITelephonyRegistry;

    move-result-object v1

    const-string v2, "SystemUI SIMHelper"

    invoke-virtual {p0}, Landroid/telephony/PhoneStateListener;->getCallback()Lcom/android/internal/telephony/IPhoneStateListener;

    move-result-object v3

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v4

    invoke-interface {v1, v2, v3, p1, v4}, Lcom/android/internal/telephony/ITelephonyRegistry;->listen(Ljava/lang/String;Lcom/android/internal/telephony/IPhoneStateListener;IZ)V

    goto :goto_1

    .line 346
    .end local v0           #notifyNow:Ljava/lang/Boolean;
    :catch_1
    move-exception v1

    goto :goto_1

    .line 341
    .restart local v0       #notifyNow:Ljava/lang/Boolean;
    :cond_4
    if-ne v3, p2, :cond_0

    sget v1, Lcom/android/internal/telephony/PhoneConstants;->GEMINI_SIM_NUM:I

    const/4 v2, 0x4

    if-lt v1, v2, :cond_0

    .line 342
    const-string v1, "telephony.registry4"

    invoke-static {v1}, Landroid/os/ServiceManager;->getService(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object v1

    invoke-static {v1}, Lcom/android/internal/telephony/ITelephonyRegistry$Stub;->asInterface(Landroid/os/IBinder;)Lcom/android/internal/telephony/ITelephonyRegistry;

    move-result-object v1

    const-string v2, "SystemUI SIMHelper"

    invoke-virtual {p0}, Landroid/telephony/PhoneStateListener;->getCallback()Lcom/android/internal/telephony/IPhoneStateListener;

    move-result-object v3

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v4

    invoke-interface {v1, v2, v3, p1, v4}, Lcom/android/internal/telephony/ITelephonyRegistry;->listen(Ljava/lang/String;Lcom/android/internal/telephony/IPhoneStateListener;IZ)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_1

    goto :goto_1
.end method

.method public static setDefaultSIM(Landroid/content/Context;Ljava/lang/String;J)V
    .locals 1
    .parameter "context"
    .parameter "businessType"
    .parameter "simId"

    .prologue
    .line 64
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    invoke-static {v0, p1, p2, p3}, Landroid/provider/Settings$System;->putLong(Landroid/content/ContentResolver;Ljava/lang/String;J)Z

    .line 65
    return-void
.end method

.method public static updateSIMInfos(Landroid/content/Context;)V
    .locals 1
    .parameter "context"

    .prologue
    .line 137
    const/4 v0, 0x0

    sput-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimInfos:Ljava/util/List;

    .line 138
    invoke-static {p0}, Lcom/android/systemui/statusbar/util/SIMHelper;->getSortedSIMInfoList(Landroid/content/Context;)Ljava/util/List;

    move-result-object v0

    sput-object v0, Lcom/android/systemui/statusbar/util/SIMHelper;->sSimInfos:Ljava/util/List;

    .line 139
    return-void
.end method

.method public static updateSimInsertedStatus()V
    .locals 6

    .prologue
    .line 371
    const-string v3, "phone"

    invoke-static {v3}, Landroid/os/ServiceManager;->checkService(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object v3

    invoke-static {v3}, Lcom/android/internal/telephony/ITelephony$Stub;->asInterface(Landroid/os/IBinder;)Lcom/android/internal/telephony/ITelephony;

    move-result-object v2

    .line 372
    .local v2, phone:Lcom/android/internal/telephony/ITelephony;
    if-eqz v2, :cond_2

    .line 374
    :try_start_0
    sget-object v3, Lcom/android/systemui/statusbar/util/SIMHelper;->simInserted:[Z

    if-nez v3, :cond_0

    .line 375
    sget v3, Lcom/android/systemui/statusbar/util/SIMHelper;->mGeminiSimNum:I

    new-array v3, v3, [Z

    sput-object v3, Lcom/android/systemui/statusbar/util/SIMHelper;->simInserted:[Z

    .line 377
    :cond_0
    const/4 v1, 0x0

    .local v1, i:I
    :goto_0
    sget v3, Lcom/android/systemui/statusbar/util/SIMHelper;->mGeminiSimNum:I

    if-ge v1, v3, :cond_1

    .line 378
    sget-object v3, Lcom/android/systemui/statusbar/util/SIMHelper;->simInserted:[Z

    invoke-interface {v2, v1}, Lcom/android/internal/telephony/ITelephony;->isSimInsert(I)Z

    move-result v4

    aput-boolean v4, v3, v1

    .line 379
    const-string v3, "SIMHelper"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "updateSimInsertedStatus, simInserted("

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, ") = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    sget-object v5, Lcom/android/systemui/statusbar/util/SIMHelper;->simInserted:[Z

    aget-boolean v5, v5, v1

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Lcom/mediatek/xlog/Xlog;->d(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    .line 377
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 381
    .end local v1           #i:I
    :catch_0
    move-exception v0

    .line 382
    .local v0, e:Landroid/os/RemoteException;
    invoke-virtual {v0}, Landroid/os/RemoteException;->printStackTrace()V

    .line 387
    .end local v0           #e:Landroid/os/RemoteException;
    :cond_1
    :goto_1
    return-void

    .line 385
    :cond_2
    const-string v3, "SIMHelper"

    const-string v4, "updateSimInsertedStatus, phone is null"

    invoke-static {v3, v4}, Lcom/mediatek/xlog/Xlog;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1
.end method
