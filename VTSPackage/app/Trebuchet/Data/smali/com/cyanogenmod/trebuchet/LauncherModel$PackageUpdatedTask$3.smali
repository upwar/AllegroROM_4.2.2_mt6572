.class Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask$3;
.super Ljava/lang/Object;
.source "LauncherModel.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask;

.field final synthetic val$callbacks:Lcom/cyanogenmod/trebuchet/LauncherModel$Callbacks;

.field final synthetic val$permanent:Z

.field final synthetic val$removedPackageNames:Ljava/util/ArrayList;


# direct methods
.method constructor <init>(Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask;Lcom/cyanogenmod/trebuchet/LauncherModel$Callbacks;Ljava/util/ArrayList;Z)V
    .locals 0
    .parameter
    .parameter
    .parameter
    .parameter

    .prologue
    .line 2141
    iput-object p1, p0, Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask$3;->this$1:Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask;

    iput-object p2, p0, Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask$3;->val$callbacks:Lcom/cyanogenmod/trebuchet/LauncherModel$Callbacks;

    iput-object p3, p0, Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask$3;->val$removedPackageNames:Ljava/util/ArrayList;

    iput-boolean p4, p0, Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask$3;->val$permanent:Z

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 4

    .prologue
    .line 2143
    iget-object v1, p0, Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask$3;->this$1:Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask;

    iget-object v1, v1, Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask;->this$0:Lcom/cyanogenmod/trebuchet/LauncherModel;

    #getter for: Lcom/cyanogenmod/trebuchet/LauncherModel;->mCallbacks:Ljava/lang/ref/WeakReference;
    invoke-static {v1}, Lcom/cyanogenmod/trebuchet/LauncherModel;->access$600(Lcom/cyanogenmod/trebuchet/LauncherModel;)Ljava/lang/ref/WeakReference;

    move-result-object v1

    if-eqz v1, :cond_1

    iget-object v1, p0, Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask$3;->this$1:Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask;

    iget-object v1, v1, Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask;->this$0:Lcom/cyanogenmod/trebuchet/LauncherModel;

    #getter for: Lcom/cyanogenmod/trebuchet/LauncherModel;->mCallbacks:Ljava/lang/ref/WeakReference;
    invoke-static {v1}, Lcom/cyanogenmod/trebuchet/LauncherModel;->access$600(Lcom/cyanogenmod/trebuchet/LauncherModel;)Ljava/lang/ref/WeakReference;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/cyanogenmod/trebuchet/LauncherModel$Callbacks;

    move-object v0, v1

    .line 2144
    .local v0, cb:Lcom/cyanogenmod/trebuchet/LauncherModel$Callbacks;
    :goto_0
    iget-object v1, p0, Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask$3;->val$callbacks:Lcom/cyanogenmod/trebuchet/LauncherModel$Callbacks;

    if-ne v1, v0, :cond_0

    .line 2145
    iget-object v1, p0, Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask$3;->val$callbacks:Lcom/cyanogenmod/trebuchet/LauncherModel$Callbacks;

    iget-object v2, p0, Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask$3;->val$removedPackageNames:Ljava/util/ArrayList;

    iget-boolean v3, p0, Lcom/cyanogenmod/trebuchet/LauncherModel$PackageUpdatedTask$3;->val$permanent:Z

    invoke-interface {v1, v2, v3}, Lcom/cyanogenmod/trebuchet/LauncherModel$Callbacks;->bindAppsRemoved(Ljava/util/ArrayList;Z)V

    .line 2147
    :cond_0
    return-void

    .line 2143
    .end local v0           #cb:Lcom/cyanogenmod/trebuchet/LauncherModel$Callbacks;
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method
