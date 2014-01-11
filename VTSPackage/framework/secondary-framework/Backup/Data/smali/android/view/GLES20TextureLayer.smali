.class Landroid/view/GLES20TextureLayer;
.super Landroid/view/GLES20Layer;
.source "GLES20TextureLayer.java"


# instance fields
.field private mSurface:Landroid/graphics/SurfaceTexture;

.field private mTexture:I


# direct methods
.method constructor <init>(Z)V
    .locals 3
    .parameter "isOpaque"

    .prologue
    .line 33
    invoke-direct {p0}, Landroid/view/GLES20Layer;-><init>()V

    .line 34
    const/4 v1, 0x2

    new-array v0, v1, [I

    .line 35
    .local v0, layerInfo:[I
    invoke-static {p1, v0}, Landroid/view/GLES20Canvas;->nCreateTextureLayer(Z[I)I

    move-result v1

    iput v1, p0, Landroid/view/GLES20Layer;->mLayer:I

    .line 37
    iget v1, p0, Landroid/view/GLES20Layer;->mLayer:I

    if-eqz v1, :cond_0

    .line 38
    const/4 v1, 0x0

    aget v1, v0, v1

    iput v1, p0, Landroid/view/GLES20TextureLayer;->mTexture:I

    .line 39
    new-instance v1, Landroid/view/GLES20Layer$Finalizer;

    iget v2, p0, Landroid/view/GLES20Layer;->mLayer:I

    invoke-direct {v1, v2}, Landroid/view/GLES20Layer$Finalizer;-><init>(I)V

    iput-object v1, p0, Landroid/view/GLES20Layer;->mFinalizer:Landroid/view/GLES20Layer$Finalizer;

    .line 43
    :goto_0
    return-void

    .line 41
    :cond_0
    const/4 v1, 0x0

    iput-object v1, p0, Landroid/view/GLES20Layer;->mFinalizer:Landroid/view/GLES20Layer$Finalizer;

    goto :goto_0
.end method


# virtual methods
.method end(Landroid/graphics/Canvas;)V
    .locals 0
    .parameter "currentCanvas"

    .prologue
    .line 67
    return-void
.end method

.method getCanvas()Landroid/view/HardwareCanvas;
    .locals 1

    .prologue
    .line 57
    const/4 v0, 0x0

    return-object v0
.end method

.method getSurfaceTexture()Landroid/graphics/SurfaceTexture;
    .locals 3

    .prologue
    .line 70
    iget-object v0, p0, Landroid/view/GLES20TextureLayer;->mSurface:Landroid/graphics/SurfaceTexture;

    if-nez v0, :cond_0

    .line 71
    new-instance v0, Landroid/graphics/SurfaceTexture;

    iget v1, p0, Landroid/view/GLES20TextureLayer;->mTexture:I

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Landroid/graphics/SurfaceTexture;-><init>(IZ)V

    iput-object v0, p0, Landroid/view/GLES20TextureLayer;->mSurface:Landroid/graphics/SurfaceTexture;

    .line 73
    :cond_0
    iget-object v0, p0, Landroid/view/GLES20TextureLayer;->mSurface:Landroid/graphics/SurfaceTexture;

    return-object v0
.end method

.method isValid()Z
    .locals 1

    .prologue
    .line 47
    iget v0, p0, Landroid/view/GLES20Layer;->mLayer:I

    if-eqz v0, :cond_0

    iget v0, p0, Landroid/view/GLES20TextureLayer;->mTexture:I

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method redrawLater(Landroid/view/DisplayList;Landroid/graphics/Rect;)V
    .locals 0
    .parameter "displayList"
    .parameter "dirtyRect"

    .prologue
    .line 102
    return-void
.end method

.method resize(II)Z
    .locals 1
    .parameter "width"
    .parameter "height"

    .prologue
    .line 52
    invoke-virtual {p0}, Landroid/view/GLES20TextureLayer;->isValid()Z

    move-result v0

    return v0
.end method

.method setOpaque(Z)V
    .locals 2
    .parameter "isOpaque"

    .prologue
    .line 92
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "Use update(int, int, boolean) instead"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method setSurfaceTexture(Landroid/graphics/SurfaceTexture;)V
    .locals 2
    .parameter "surfaceTexture"

    .prologue
    .line 77
    iget-object v0, p0, Landroid/view/GLES20TextureLayer;->mSurface:Landroid/graphics/SurfaceTexture;

    if-eqz v0, :cond_0

    .line 78
    iget-object v0, p0, Landroid/view/GLES20TextureLayer;->mSurface:Landroid/graphics/SurfaceTexture;

    invoke-virtual {v0}, Landroid/graphics/SurfaceTexture;->release()V

    .line 80
    :cond_0
    iput-object p1, p0, Landroid/view/GLES20TextureLayer;->mSurface:Landroid/graphics/SurfaceTexture;

    .line 81
    iget-object v0, p0, Landroid/view/GLES20TextureLayer;->mSurface:Landroid/graphics/SurfaceTexture;

    iget v1, p0, Landroid/view/GLES20TextureLayer;->mTexture:I

    invoke-virtual {v0, v1}, Landroid/graphics/SurfaceTexture;->attachToGLContext(I)V

    .line 82
    return-void
.end method

.method setTransform(Landroid/graphics/Matrix;)V
    .locals 2
    .parameter "matrix"

    .prologue
    .line 97
    iget v0, p0, Landroid/view/GLES20Layer;->mLayer:I

    iget v1, p1, Landroid/graphics/Matrix;->native_instance:I

    invoke-static {v0, v1}, Landroid/view/GLES20Canvas;->nSetTextureLayerTransform(II)V

    .line 98
    return-void
.end method

.method start(Landroid/graphics/Canvas;)Landroid/view/HardwareCanvas;
    .locals 1
    .parameter "currentCanvas"

    .prologue
    .line 62
    const/4 v0, 0x0

    return-object v0
.end method

.method update(IIZ)V
    .locals 2
    .parameter "width"
    .parameter "height"
    .parameter "isOpaque"

    .prologue
    .line 86
    invoke-super {p0, p1, p2, p3}, Landroid/view/GLES20Layer;->update(IIZ)V

    .line 87
    iget v0, p0, Landroid/view/GLES20Layer;->mLayer:I

    iget-object v1, p0, Landroid/view/GLES20TextureLayer;->mSurface:Landroid/graphics/SurfaceTexture;

    invoke-static {v0, p1, p2, p3, v1}, Landroid/view/GLES20Canvas;->nUpdateTextureLayer(IIIZLandroid/graphics/SurfaceTexture;)V

    .line 88
    return-void
.end method
