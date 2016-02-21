package com.fuxuemingzhu.wechoice.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.*;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class BitmapHelper {

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     *
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /*
     * bitmap to string(64)
     */
    public static String bitmap2StrByBase64(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, outputStream);
        byte[] bytes = outputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /*
     * bitmap to bytes
     */
    public static byte[] bitmap2StrByBase64ByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, outputStream);
        byte[] bytes = outputStream.toByteArray();
        return bytes;
    }

    /*
     * str to bitmap
     */
    public static Bitmap str2BitmapByBase64(String bitData) {
        byte[] data = Base64.decode(bitData, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    /**
     * Drawable转化为Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable
                .getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
                : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;

    }

    /**
     * Bitmap to Drawable
     *
     * @param bitmap
     * @param mcontext
     *
     * @return
     */
    public static Drawable bitmapToDrawble(Bitmap bitmap, Context mcontext) {
        Drawable drawable = new BitmapDrawable(mcontext.getResources(), bitmap);
        return drawable;
    }

    /**
     * 获取图片名称获取图片的资源id的方法
     *
     * @param imageName
     *
     * @return
     */
    public static int getResource(String imageName, Context context) {
        int resId = context.getResources().getIdentifier(imageName, "drawable",
                context.getPackageName());
        return resId;
    }

    /**
     * 加载本地图片
     *
     * @param url
     *
     * @return
     */
    public static Bitmap getLoacalBitmapByAssets(Context context, String url) {
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            is = context.getResources().getAssets().open(url);
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 判断asset是否存在图片
     *
     * @param context
     * @param pt
     *
     * @return
     */
    public static boolean hasAssetsFile(Context context, String pt) {
        AssetManager am = context.getAssets();
        try {
            String[] names = am.list("");
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(pt.trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * <p>
     * Title: loadImageFromNetwork
     * </p>
     * <p>
     * Description: 根据连接地址，下载图片，返回Drawable
     * </p>
     *
     * @param urladdr
     *
     * @return
     */
    public static Drawable loadImageFromNetwork(String urladdr) {
        Drawable drawable = null;
        try {
            // judge if has picture locate or not according to filename
            drawable = Drawable.createFromStream(new URL(urladdr).openStream(),
                    "WBHeadImage.jpg");
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
        if (drawable == null) {
            Log.d("loadImage error", "no this image from URL");
        } else {
            Log.d("loadImage success", "get image from URL success");
        }
        return drawable;
    }

    public static synchronized Bitmap decodeBitmapFromFile(String filename,
                                                           int reqWidth, int reqHeight) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filename, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filename, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
            final float totalPixels = width * height;
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }

    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     *
     * @return degree旋转的角度
     */
    @SuppressLint("NewApi")
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 转换图片成圆形
     *
     * @param bitmap 传入Bitmap对象
     *
     * @return
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
                (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
                (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }

    /**
     * Save image to the SD card *
     */
    public static void savePhotoToSDCard(String path, String photoName,
                                         Bitmap photoBitmap) {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File photoFile = new File(path, photoName); // 在指定路径下创建文件
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(photoFile);
                if (photoBitmap != null) {
                    if (photoBitmap.compress(CompressFormat.PNG, 100,
                            fileOutputStream)) {
                        fileOutputStream.flush();
                    }
                }
            } catch (FileNotFoundException e) {
                photoFile.delete();
                e.printStackTrace();
            } catch (IOException e) {
                photoFile.delete();
                e.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 转换图片成圆形
     *
     * @param bitmap
     *            传入Bitmap对象
     * @return
     */

}
