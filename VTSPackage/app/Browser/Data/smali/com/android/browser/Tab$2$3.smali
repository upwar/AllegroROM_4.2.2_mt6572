.class Lcom/android/browser/Tab$2$3;
.super Ljava/lang/Object;
.source "Tab.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/android/browser/Tab$2;->onFormResubmission(Landroid/webkit/WebView;Landroid/os/Message;Landroid/os/Message;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/android/browser/Tab$2;


# direct methods
.method constructor <init>(Lcom/android/browser/Tab$2;)V
    .locals 0
    .parameter

    .prologue
    .line 526
    iput-object p1, p0, Lcom/android/browser/Tab$2$3;->this$1:Lcom/android/browser/Tab$2;

    invoke-direct/range {p0 .. p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 2
    .parameter "dialog"
    .parameter "which"

    .prologue
    const/4 v1, 0x0

    .line 529
    iget-object v0, p0, Lcom/android/browser/Tab$2$3;->this$1:Lcom/android/browser/Tab$2;

    #getter for: Lcom/android/browser/Tab$2;->mResend:Landroid/os/Message;
    invoke-static {v0}, Lcom/android/browser/Tab$2;->access$1300(Lcom/android/browser/Tab$2;)Landroid/os/Message;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 530
    iget-object v0, p0, Lcom/android/browser/Tab$2$3;->this$1:Lcom/android/browser/Tab$2;

    #getter for: Lcom/android/browser/Tab$2;->mResend:Landroid/os/Message;
    invoke-static {v0}, Lcom/android/browser/Tab$2;->access$1300(Lcom/android/browser/Tab$2;)Landroid/os/Message;

    move-result-object v0

    invoke-virtual {v0}, Landroid/os/Message;->sendToTarget()V

    .line 531
    iget-object v0, p0, Lcom/android/browser/Tab$2$3;->this$1:Lcom/android/browser/Tab$2;

    #setter for: Lcom/android/browser/Tab$2;->mResend:Landroid/os/Message;
    invoke-static {v0, v1}, Lcom/android/browser/Tab$2;->access$1302(Lcom/android/browser/Tab$2;Landroid/os/Message;)Landroid/os/Message;

    .line 532
    iget-object v0, p0, Lcom/android/browser/Tab$2$3;->this$1:Lcom/android/browser/Tab$2;

    #setter for: Lcom/android/browser/Tab$2;->mDontResend:Landroid/os/Message;
    invoke-static {v0, v1}, Lcom/android/browser/Tab$2;->access$1202(Lcom/android/browser/Tab$2;Landroid/os/Message;)Landroid/os/Message;

    .line 534
    :cond_0
    return-void
.end method
