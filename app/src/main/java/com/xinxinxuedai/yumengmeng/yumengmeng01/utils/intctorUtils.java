package com.xinxinxuedai.yumengmeng.yumengmeng01.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;

import java.util.ArrayList;

/**
 * Created by 35876 于萌萌
 * 创建日期: 9:47 . 2016年07月09日
 * 描述:滑动小方点
 *
 * <p>
 * <p>
 * 备注:
 */
public class intctorUtils {
    // 小红点移动距离 可以外部传进来  写在startDrawable 的形式参数里面
    private static int mPointDis;

    /**
     *
     * 开始画 一体完成 小圆点
     * @param context 上下文
     * @param arrayList 欢迎页集合长度
     * @param rootView 欢迎页ViewPager 的跟布局
     * @param vp_welcome 欢迎页ViewPager对象
     * @param iv_red 初始化小点的对象
     */
    public static void startDrawable(Context context, ArrayList arrayList, LinearLayout rootView, ViewPager vp_welcome, ImageView iv_red){
      //  rootView.removeAllViews();
        for (int i = 0; i < arrayList.size(); i++) {

            // 初始化小圆点
            ImageView point = new ImageView(context);
            point.setImageResource(R.drawable.shape_point_white);// 设置图片(shape形状)
            LogUtils.i("画了i"+i+"个小圆点了");
            // 初始化布局参数, 宽高包裹内容,父控件是谁,就是谁声明的布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            if (i > 0) {
                // 从第二个点开始设置左边距
                params.leftMargin = AdaptationUtils.Dp2Px(context, 5);
            }

            point.setLayoutParams(params);// 设置布局参数

            rootView.addView(point);// 给容器添加圆点
            intctorListener(rootView,vp_welcome,iv_red);

        }
    }

    /**
     *
     * 这个方法 不对外  其实 就是 改变滑动的
     * @param rootView 根布局
     * @param mViewPager 轮播图对象
     * @param iv_red 原始出现的小点的对象
     */
    private static   void intctorListener(final LinearLayout rootView, ViewPager mViewPager, final ImageView iv_red){

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
//                // 某个页面被选中
//                if (position == mImageViewList.size() - 1) {// 最后一个页面显示开始体验的按钮
//                    btnStart.setVisibility(View.VISIBLE);
//                } else {
//                    btnStart.setVisibility(View.INVISIBLE);
//                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // 当页面滑动过程中的回调
                System.out.println("当前位置:" + position + ";移动偏移百分比:"
                        + positionOffset);
                // 更新小红点距离
                int leftMargin = (int) (mPointDis * positionOffset) + position
                        * mPointDis;// 计算小红点当前的左边距
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red
                        .getLayoutParams();
                params.leftMargin = leftMargin;// 修改左边距

                // 重新设置布局参数
                iv_red.setLayoutParams(params);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 页面状态发生变化的回调
            }
        });

        // 计算两个圆点的距离
        // 移动距离=第二个圆点left值 - 第一个圆点left值
        // measure->layout(确定位置)->draw(activity的onCreate方法执行结束之后才会走此流程)
        // mPointDis = llContainer.getChildAt(1).getLeft()
        // - llContainer.getChildAt(0).getLeft();
        // System.out.println("圆点距离:" + mPointDis);

        // 监听layout方法结束的事件,位置确定好之后再获取圆点间距
        // 视图树
        iv_red.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        // 移除监听,避免重复回调
                        iv_red.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                        // ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        // layout方法执行结束的回调
                        mPointDis = rootView.getChildAt(1).getLeft()
                                - rootView.getChildAt(0).getLeft();
                        System.out.println("圆点距离:" + mPointDis);
                    }
                });
    }
}
