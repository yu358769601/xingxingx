package com.xinxinxuedai.Utils.NoHttp.task;

import android.os.SystemClock;

import com.xinxinxuedai.Utils.LogUtils;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 9:28 . 2017年01月09日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class DownTimers extends MultiAsynctask<Integer, Integer, String> {
    NetworkInterface anInterface;
    /**
     * 如果默认形式参数创建的对象的话就 设置一个,默认的线程池大小为5
     */
    public DownTimers(NetworkInterface anInterface) {
        this.anInterface =anInterface;
        //创建就调用开始工作
        execute();
    }

    /**
     * 子类去实现的方法
     * 子线程
     * @param voids 这个可变参数 不一定用 但是得有
     * @return 这个返回值 是 给  结果的 那个方法的
     */
    @Override
    protected String onExecuteTask(Integer[] voids) {
        int count = 8;
        while (count>1){
            SystemClock.sleep(1*1000);
            count--;
            onUpdate(count);
            LogUtils.i("现在的数量是"+count);
        }

        return count+"";
    }

    /**
     * 当返回进度更新的时候
     *
     * @param integer
     */
    @Override
    protected void onUpdate(Integer integer) {
        anInterface.onUpData(integer);
    }

    /**
     * 当返回执行结果的时候
     *
     * @param s
     */
    @Override
    protected void onResult(String s) {
        anInterface.onResult(s);
    }
}
