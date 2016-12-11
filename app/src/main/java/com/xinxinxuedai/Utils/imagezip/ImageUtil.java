package com.xinxinxuedai.Utils.imagezip;

import android.graphics.Bitmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/6/29.
 */
public class ImageUtil {

    public static InputStream compressImage(Bitmap image) {


       // int options = 100;
//        while ( baos.toByteArray().length / 1024>250) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
//            Log.i("ImageUtil", "compressImage:  baos.toByteArray().length"+baos.toByteArray().length / 1024);
//            baos.reset();//重置baos即清空baos
//            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
//            options -= 50;//每次都减少10
//            Log.i("ImageUtil", "compressImage: options"+options);
//        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);

        InputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        return isBm;
    }

}
