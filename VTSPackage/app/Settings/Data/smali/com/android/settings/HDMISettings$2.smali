.class Lcom/android/settings/HDMISettings$2;
.super Landroid/content/BroadcastReceiver;
.source "HDMISettings.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/settings/HDMISettings;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/android/settings/HDMISettings;


# direct methods
.method constructor <init>(Lcom/android/settings/HDMISettings;)V
    .locals 0
    .parameter

    .prologue
    .line 60
    iput-object p1, p0, Lcom/android/settings/HDMISettings$2;->this$0:Lcom/android/settings/HDMISettings;

    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    return-void
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 2
    .parameter "context"
    .parameter "intent"

    .prologue
    .line 63
    invoke-virtual {p2}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v0

    .line 64
    .local v0, action:Ljava/lang/String;
    const-string v1, "com.mediatek.hdmi.localservice.action.CABLE_STATE_CHANGED"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 65
    iget-object v1, p0, Lcom/android/settings/HDMISettings$2;->this$0:Lcom/android/settings/HDMISettings;

    #calls: Lcom/android/settings/HDMISettings;->updateSettingsItemEnableStatus()V
    invoke-static {v1}, Lcom/android/settings/HDMISettings;->access$100(Lcom/android/settings/HDMISettings;)V

    .line 72
    :cond_0
    return-void
.end method
