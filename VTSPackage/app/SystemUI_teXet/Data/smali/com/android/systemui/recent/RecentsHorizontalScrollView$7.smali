.class Lcom/android/systemui/recent/RecentsHorizontalScrollView$7;
.super Landroid/database/DataSetObserver;
.source "RecentsHorizontalScrollView.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/android/systemui/recent/RecentsHorizontalScrollView;->setAdapter(Lcom/android/systemui/recent/RecentsPanelView$TaskDescriptionAdapter;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/android/systemui/recent/RecentsHorizontalScrollView;


# direct methods
.method constructor <init>(Lcom/android/systemui/recent/RecentsHorizontalScrollView;)V
    .registers 2
    .parameter

    .prologue
    .line 349
    iput-object p1, p0, Lcom/android/systemui/recent/RecentsHorizontalScrollView$7;->this$0:Lcom/android/systemui/recent/RecentsHorizontalScrollView;

    invoke-direct {p0}, Landroid/database/DataSetObserver;-><init>()V

    return-void
.end method


# virtual methods
.method public onChanged()V
    .registers 2

    .prologue
    .line 351
    iget-object v0, p0, Lcom/android/systemui/recent/RecentsHorizontalScrollView$7;->this$0:Lcom/android/systemui/recent/RecentsHorizontalScrollView;

    invoke-static {v0}, Lcom/android/systemui/recent/RecentsHorizontalScrollView;->access$300(Lcom/android/systemui/recent/RecentsHorizontalScrollView;)V

    .line 352
    return-void
.end method

.method public onInvalidated()V
    .registers 2

    .prologue
    .line 355
    iget-object v0, p0, Lcom/android/systemui/recent/RecentsHorizontalScrollView$7;->this$0:Lcom/android/systemui/recent/RecentsHorizontalScrollView;

    invoke-static {v0}, Lcom/android/systemui/recent/RecentsHorizontalScrollView;->access$300(Lcom/android/systemui/recent/RecentsHorizontalScrollView;)V

    .line 356
    return-void
.end method
