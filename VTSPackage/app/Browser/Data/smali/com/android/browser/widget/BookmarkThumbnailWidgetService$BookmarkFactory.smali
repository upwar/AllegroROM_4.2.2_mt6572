.class Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;
.super Ljava/lang/Object;
.source "BookmarkThumbnailWidgetService.java"

# interfaces
.implements Landroid/widget/RemoteViewsService$RemoteViewsFactory;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/browser/widget/BookmarkThumbnailWidgetService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "BookmarkFactory"
.end annotation


# instance fields
.field private mBookmarks:Landroid/database/Cursor;

.field private mContext:Landroid/content/Context;

.field private mCurrentFolder:J

.field private mPreferences:Landroid/content/SharedPreferences;

.field private mRootFolder:J

.field private mWidgetId:I


# direct methods
.method public constructor <init>(Landroid/content/Context;I)V
    .locals 2
    .parameter "context"
    .parameter "widgetId"

    .prologue
    const-wide/16 v0, -0x1

    .line 171
    invoke-direct/range {p0 .. p0}, Ljava/lang/Object;-><init>()V

    .line 167
    iput-wide v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mCurrentFolder:J

    .line 168
    iput-wide v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mRootFolder:J

    .line 169
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mPreferences:Landroid/content/SharedPreferences;

    .line 172
    invoke-virtual {p1}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    iput-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mContext:Landroid/content/Context;

    .line 173
    iput p2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mWidgetId:I

    .line 174
    return-void
.end method

.method private resetBookmarks()V
    .locals 1

    .prologue
    .line 333
    iget-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    if-eqz v0, :cond_0

    .line 334
    iget-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    invoke-interface {v0}, Landroid/database/Cursor;->close()V

    .line 335
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    .line 337
    :cond_0
    return-void
.end method


# virtual methods
.method public getCount()I
    .locals 1

    .prologue
    .line 200
    iget-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    if-nez v0, :cond_0

    .line 201
    const/4 v0, 0x0

    .line 202
    :goto_0
    return v0

    :cond_0
    iget-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    invoke-interface {v0}, Landroid/database/Cursor;->getCount()I

    move-result v0

    goto :goto_0
.end method

.method public getItemId(I)J
    .locals 2
    .parameter "position"

    .prologue
    .line 207
    int-to-long v0, p1

    return-wide v0
.end method

.method public getLoadingView()Landroid/widget/RemoteViews;
    .locals 3

    .prologue
    .line 212
    new-instance v0, Landroid/widget/RemoteViews;

    iget-object v1, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v1

    const v2, 0x7f04000f

    invoke-direct {v0, v1, v2}, Landroid/widget/RemoteViews;-><init>(Ljava/lang/String;I)V

    return-object v0
.end method

.method public getViewAt(I)Landroid/widget/RemoteViews;
    .locals 19
    .parameter "position"

    .prologue
    .line 218
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    move/from16 v0, p1

    invoke-interface {v2, v0}, Landroid/database/Cursor;->moveToPosition(I)Z

    move-result v2

    if-nez v2, :cond_0

    .line 219
    const/4 v1, 0x0

    .line 292
    :goto_0
    return-object v1

    .line 222
    :cond_0
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    const/4 v3, 0x0

    invoke-interface {v2, v3}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v12

    .line 223
    .local v12, id:J
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    const/4 v3, 0x1

    invoke-interface {v2, v3}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v17

    .line 224
    .local v17, title:Ljava/lang/String;
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    const/4 v3, 0x2

    invoke-interface {v2, v3}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v18

    .line 225
    .local v18, url:Ljava/lang/String;
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    const/4 v3, 0x4

    invoke-interface {v2, v3}, Landroid/database/Cursor;->getInt(I)I

    move-result v2

    if-eqz v2, :cond_2

    const/4 v14, 0x1

    .line 229
    .local v14, isFolder:Z
    :goto_1
    if-eqz v14, :cond_3

    .line 230
    new-instance v1, Landroid/widget/RemoteViews;

    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mContext:Landroid/content/Context;

    invoke-virtual {v2}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v2

    const v3, 0x7f040010

    invoke-direct {v1, v2, v3}, Landroid/widget/RemoteViews;-><init>(Ljava/lang/String;I)V

    .line 237
    .local v1, views:Landroid/widget/RemoteViews;
    :goto_2
    move-object/from16 v9, v17

    .line 238
    .local v9, displayTitle:Ljava/lang/String;
    invoke-static {v9}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 240
    move-object/from16 v9, v18

    .line 242
    :cond_1
    const v2, 0x7f0d0018

    invoke-virtual {v1, v2, v9}, Landroid/widget/RemoteViews;->setTextViewText(ILjava/lang/CharSequence;)V

    .line 243
    if-eqz v14, :cond_5

    .line 244
    move-object/from16 v0, p0

    iget-wide v2, v0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mCurrentFolder:J

    cmp-long v2, v12, v2

    if-nez v2, :cond_4

    .line 245
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    const/4 v3, 0x7

    invoke-interface {v2, v3}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v12

    .line 246
    const v2, 0x7f0d001e

    const v3, 0x7f020064

    invoke-virtual {v1, v2, v3}, Landroid/widget/RemoteViews;->setImageViewResource(II)V

    .line 250
    :goto_3
    const v2, 0x7f0d0006

    const v3, 0x7f020023

    invoke-virtual {v1, v2, v3}, Landroid/widget/RemoteViews;->setImageViewResource(II)V

    .line 251
    const v2, 0x7f0d001e

    const/4 v3, 0x1

    const/4 v4, 0x0

    const/4 v5, -0x1

    const/4 v6, 0x0

    const/4 v7, -0x1

    invoke-virtual/range {v1 .. v7}, Landroid/widget/RemoteViews;->setDrawableParameters(IZIILandroid/graphics/PorterDuff$Mode;I)V

    .line 278
    :goto_4
    if-eqz v14, :cond_8

    .line 279
    new-instance v2, Landroid/content/Intent;

    const-string v3, "com.android.browser.widget.CHANGE_FOLDER"

    invoke-direct {v2, v3}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    const-string v3, "appWidgetId"

    move-object/from16 v0, p0

    iget v4, v0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mWidgetId:I

    invoke-virtual {v2, v3, v4}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    move-result-object v2

    const-string v3, "_id"

    invoke-virtual {v2, v3, v12, v13}, Landroid/content/Intent;->putExtra(Ljava/lang/String;J)Landroid/content/Intent;

    move-result-object v11

    .line 291
    .local v11, fillin:Landroid/content/Intent;
    :goto_5
    const v2, 0x7f0d0017

    invoke-virtual {v1, v2, v11}, Landroid/widget/RemoteViews;->setOnClickFillInIntent(ILandroid/content/Intent;)V

    goto/16 :goto_0

    .line 225
    .end local v1           #views:Landroid/widget/RemoteViews;
    .end local v9           #displayTitle:Ljava/lang/String;
    .end local v11           #fillin:Landroid/content/Intent;
    .end local v14           #isFolder:Z
    :cond_2
    const/4 v14, 0x0

    goto :goto_1

    .line 233
    .restart local v14       #isFolder:Z
    :cond_3
    new-instance v1, Landroid/widget/RemoteViews;

    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mContext:Landroid/content/Context;

    invoke-virtual {v2}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v2

    const v3, 0x7f04000f

    invoke-direct {v1, v2, v3}, Landroid/widget/RemoteViews;-><init>(Ljava/lang/String;I)V

    .restart local v1       #views:Landroid/widget/RemoteViews;
    goto :goto_2

    .line 248
    .restart local v9       #displayTitle:Ljava/lang/String;
    :cond_4
    const v2, 0x7f0d001e

    const v3, 0x7f020065

    invoke-virtual {v1, v2, v3}, Landroid/widget/RemoteViews;->setImageViewResource(II)V

    goto :goto_3

    .line 254
    :cond_5
    new-instance v15, Landroid/graphics/BitmapFactory$Options;

    invoke-direct {v15}, Landroid/graphics/BitmapFactory$Options;-><init>()V

    .line 255
    .local v15, options:Landroid/graphics/BitmapFactory$Options;
    sget-object v2, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    iput-object v2, v15, Landroid/graphics/BitmapFactory$Options;->inPreferredConfig:Landroid/graphics/Bitmap$Config;

    .line 256
    const/16 v16, 0x0

    .local v16, thumbnail:Landroid/graphics/Bitmap;
    const/4 v10, 0x0

    .line 257
    .local v10, favicon:Landroid/graphics/Bitmap;
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    const/4 v3, 0x6

    invoke-interface {v2, v3}, Landroid/database/Cursor;->getBlob(I)[B

    move-result-object v8

    .line 258
    .local v8, blob:[B
    const v2, 0x7f0d001e

    const/4 v3, 0x1

    const/16 v4, 0xff

    const/4 v5, -0x1

    const/4 v6, 0x0

    const/4 v7, -0x1

    invoke-virtual/range {v1 .. v7}, Landroid/widget/RemoteViews;->setDrawableParameters(IZIILandroid/graphics/PorterDuff$Mode;I)V

    .line 259
    if-eqz v8, :cond_6

    array-length v2, v8

    if-lez v2, :cond_6

    .line 260
    const/4 v2, 0x0

    array-length v3, v8

    invoke-static {v8, v2, v3, v15}, Landroid/graphics/BitmapFactory;->decodeByteArray([BIILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    move-result-object v16

    .line 262
    const v2, 0x7f0d001e

    move-object/from16 v0, v16

    invoke-virtual {v1, v2, v0}, Landroid/widget/RemoteViews;->setImageViewBitmap(ILandroid/graphics/Bitmap;)V

    .line 267
    :goto_6
    move-object/from16 v0, p0

    iget-object v2, v0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    const/4 v3, 0x3

    invoke-interface {v2, v3}, Landroid/database/Cursor;->getBlob(I)[B

    move-result-object v8

    .line 268
    if-eqz v8, :cond_7

    array-length v2, v8

    if-lez v2, :cond_7

    .line 269
    const/4 v2, 0x0

    array-length v3, v8

    invoke-static {v8, v2, v3, v15}, Landroid/graphics/BitmapFactory;->decodeByteArray([BIILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    move-result-object v10

    .line 271
    const v2, 0x7f0d0006

    invoke-virtual {v1, v2, v10}, Landroid/widget/RemoteViews;->setImageViewBitmap(ILandroid/graphics/Bitmap;)V

    goto/16 :goto_4

    .line 264
    :cond_6
    const v2, 0x7f0d001e

    const v3, 0x7f02000e

    invoke-virtual {v1, v2, v3}, Landroid/widget/RemoteViews;->setImageViewResource(II)V

    goto :goto_6

    .line 273
    :cond_7
    const v2, 0x7f0d0006

    const v3, 0x7f020001

    invoke-virtual {v1, v2, v3}, Landroid/widget/RemoteViews;->setImageViewResource(II)V

    goto/16 :goto_4

    .line 283
    .end local v8           #blob:[B
    .end local v10           #favicon:Landroid/graphics/Bitmap;
    .end local v15           #options:Landroid/graphics/BitmapFactory$Options;
    .end local v16           #thumbnail:Landroid/graphics/Bitmap;
    :cond_8
    invoke-static/range {v18 .. v18}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_9

    .line 284
    new-instance v2, Landroid/content/Intent;

    const-string v3, "android.intent.action.VIEW"

    invoke-direct {v2, v3}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    const-string v3, "android.intent.category.BROWSABLE"

    invoke-virtual {v2, v3}, Landroid/content/Intent;->addCategory(Ljava/lang/String;)Landroid/content/Intent;

    move-result-object v2

    invoke-static/range {v18 .. v18}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/content/Intent;->setData(Landroid/net/Uri;)Landroid/content/Intent;

    move-result-object v11

    .restart local v11       #fillin:Landroid/content/Intent;
    goto/16 :goto_5

    .line 288
    .end local v11           #fillin:Landroid/content/Intent;
    :cond_9
    new-instance v11, Landroid/content/Intent;

    const-string v2, "show_browser"

    invoke-direct {v11, v2}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .restart local v11       #fillin:Landroid/content/Intent;
    goto/16 :goto_5
.end method

.method public getViewTypeCount()I
    .locals 1

    .prologue
    .line 297
    const/4 v0, 0x2

    return v0
.end method

.method public hasStableIds()Z
    .locals 1

    .prologue
    .line 302
    const/4 v0, 0x0

    return v0
.end method

.method loadBookmarks()V
    .locals 9

    .prologue
    const/4 v3, 0x0

    .line 340
    invoke-direct {p0}, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->resetBookmarks()V

    .line 342
    sget-object v0, Landroid/provider/BrowserContract$Bookmarks;->CONTENT_URI_DEFAULT_FOLDER:Landroid/net/Uri;

    iget-wide v4, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mCurrentFolder:J

    invoke-static {v0, v4, v5}, Landroid/content/ContentUris;->withAppendedId(Landroid/net/Uri;J)Landroid/net/Uri;

    move-result-object v1

    .line 345
    .local v1, uri:Landroid/net/Uri;
    iget-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    invoke-static {}, Lcom/android/browser/widget/BookmarkThumbnailWidgetService;->access$000()[Ljava/lang/String;

    move-result-object v2

    move-object v4, v3

    move-object v5, v3

    invoke-virtual/range {v0 .. v5}, Landroid/content/ContentResolver;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v0

    iput-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    .line 347
    iget-wide v4, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mCurrentFolder:J

    iget-wide v7, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mRootFolder:J

    cmp-long v0, v4, v7

    if-eqz v0, :cond_0

    .line 348
    sget-object v0, Landroid/provider/BrowserContract$Bookmarks;->CONTENT_URI:Landroid/net/Uri;

    iget-wide v4, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mCurrentFolder:J

    invoke-static {v0, v4, v5}, Landroid/content/ContentUris;->withAppendedId(Landroid/net/Uri;J)Landroid/net/Uri;

    move-result-object v1

    .line 351
    iget-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    invoke-static {}, Lcom/android/browser/widget/BookmarkThumbnailWidgetService;->access$000()[Ljava/lang/String;

    move-result-object v2

    move-object v4, v3

    move-object v5, v3

    invoke-virtual/range {v0 .. v5}, Landroid/content/ContentResolver;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v6

    .line 353
    .local v6, c:Landroid/database/Cursor;
    new-instance v0, Landroid/database/MergeCursor;

    const/4 v2, 0x2

    new-array v2, v2, [Landroid/database/Cursor;

    const/4 v3, 0x0

    aput-object v6, v2, v3

    const/4 v3, 0x1

    iget-object v4, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    aput-object v4, v2, v3

    invoke-direct {v0, v2}, Landroid/database/MergeCursor;-><init>([Landroid/database/Cursor;)V

    iput-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    .line 355
    .end local v6           #c:Landroid/database/Cursor;
    :cond_0
    return-void
.end method

.method public onCreate()V
    .locals 0

    .prologue
    .line 307
    return-void
.end method

.method public onDataSetChanged()V
    .locals 6

    .prologue
    const-wide/16 v4, 0x0

    .line 320
    invoke-static {}, Landroid/os/Binder;->clearCallingIdentity()J

    move-result-wide v0

    .line 321
    .local v0, token:J
    invoke-virtual {p0}, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->syncState()V

    .line 322
    iget-wide v2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mRootFolder:J

    cmp-long v2, v2, v4

    if-ltz v2, :cond_0

    iget-wide v2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mCurrentFolder:J

    cmp-long v2, v2, v4

    if-gez v2, :cond_1

    .line 324
    :cond_0
    const-wide/16 v2, 0x1

    iput-wide v2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mRootFolder:J

    .line 325
    iget-wide v2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mRootFolder:J

    iput-wide v2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mCurrentFolder:J

    .line 326
    invoke-virtual {p0}, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->saveState()V

    .line 328
    :cond_1
    invoke-virtual {p0}, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->loadBookmarks()V

    .line 329
    invoke-static {v0, v1}, Landroid/os/Binder;->restoreCallingIdentity(J)V

    .line 330
    return-void
.end method

.method public onDestroy()V
    .locals 2

    .prologue
    .line 311
    iget-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    if-eqz v0, :cond_0

    .line 312
    iget-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    invoke-interface {v0}, Landroid/database/Cursor;->close()V

    .line 313
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mBookmarks:Landroid/database/Cursor;

    .line 315
    :cond_0
    iget-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mContext:Landroid/content/Context;

    iget v1, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mWidgetId:I

    invoke-static {v0, v1}, Lcom/android/browser/widget/BookmarkThumbnailWidgetService;->deleteWidgetState(Landroid/content/Context;I)V

    .line 316
    return-void
.end method

.method saveState()V
    .locals 4

    .prologue
    .line 189
    iget-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mPreferences:Landroid/content/SharedPreferences;

    if-nez v0, :cond_0

    .line 190
    iget-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mContext:Landroid/content/Context;

    iget v1, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mWidgetId:I

    invoke-static {v0, v1}, Lcom/android/browser/widget/BookmarkThumbnailWidgetService;->getWidgetState(Landroid/content/Context;I)Landroid/content/SharedPreferences;

    move-result-object v0

    iput-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mPreferences:Landroid/content/SharedPreferences;

    .line 192
    :cond_0
    iget-object v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mPreferences:Landroid/content/SharedPreferences;

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "current_folder"

    iget-wide v2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mCurrentFolder:J

    invoke-interface {v0, v1, v2, v3}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "root_folder"

    iget-wide v2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mRootFolder:J

    invoke-interface {v0, v1, v2, v3}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->commit()Z

    .line 196
    return-void
.end method

.method syncState()V
    .locals 6

    .prologue
    const-wide/16 v4, -0x1

    .line 177
    iget-object v2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mPreferences:Landroid/content/SharedPreferences;

    if-nez v2, :cond_0

    .line 178
    iget-object v2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mContext:Landroid/content/Context;

    iget v3, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mWidgetId:I

    invoke-static {v2, v3}, Lcom/android/browser/widget/BookmarkThumbnailWidgetService;->getWidgetState(Landroid/content/Context;I)Landroid/content/SharedPreferences;

    move-result-object v2

    iput-object v2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mPreferences:Landroid/content/SharedPreferences;

    .line 180
    :cond_0
    iget-object v2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mPreferences:Landroid/content/SharedPreferences;

    const-string v3, "current_folder"

    invoke-interface {v2, v3, v4, v5}, Landroid/content/SharedPreferences;->getLong(Ljava/lang/String;J)J

    move-result-wide v0

    .line 181
    .local v0, currentFolder:J
    iget-object v2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mPreferences:Landroid/content/SharedPreferences;

    const-string v3, "root_folder"

    invoke-interface {v2, v3, v4, v5}, Landroid/content/SharedPreferences;->getLong(Ljava/lang/String;J)J

    move-result-wide v2

    iput-wide v2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mRootFolder:J

    .line 182
    iget-wide v2, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mCurrentFolder:J

    cmp-long v2, v0, v2

    if-eqz v2, :cond_1

    .line 183
    invoke-direct {p0}, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->resetBookmarks()V

    .line 184
    iput-wide v0, p0, Lcom/android/browser/widget/BookmarkThumbnailWidgetService$BookmarkFactory;->mCurrentFolder:J

    .line 186
    :cond_1
    return-void
.end method
