.class Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel$2;
.super Landroid/database/ContentObserver;
.source "QuickSettingsConnectionModel.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;


# direct methods
.method constructor <init>(Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;Landroid/os/Handler;)V
    .locals 0
    .parameter
    .parameter "x0"

    .prologue
    .line 180
    iput-object p1, p0, Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel$2;->this$0:Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;

    invoke-direct {p0, p2}, Landroid/database/ContentObserver;-><init>(Landroid/os/Handler;)V

    return-void
.end method


# virtual methods
.method public onChange(Z)V
    .locals 2
    .parameter "selfChange"

    .prologue
    .line 184
    iget-object v0, p0, Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel$2;->this$0:Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;

    #calls: Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;->isWifiOnlyDevice()Z
    invoke-static {v0}, Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;->access$100(Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 186
    iget-object v0, p0, Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel$2;->this$0:Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;

    #getter for: Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;->mMobileStateTracker:Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel$MobileStateTracker;
    invoke-static {v0}, Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;->access$200(Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;)Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel$MobileStateTracker;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel$MobileStateTracker;->getIsUserSwitching()Z

    move-result v0

    if-nez v0, :cond_0

    .line 187
    iget-object v0, p0, Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel$2;->this$0:Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;

    #getter for: Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;->mMobileStateTracker:Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel$MobileStateTracker;
    invoke-static {v0}, Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;->access$200(Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;)Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel$MobileStateTracker;

    move-result-object v0

    iget-object v1, p0, Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel$2;->this$0:Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;

    #getter for: Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;->mContext:Landroid/content/Context;
    invoke-static {v1}, Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;->access$000(Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel;)Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/android/systemui/statusbar/toolbar/QuickSettingsConnectionModel$MobileStateTracker;->setImageViewResources(Landroid/content/Context;)V

    .line 192
    :cond_0
    return-void
.end method
