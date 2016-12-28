package com.xinxinxuedai.Utils.NoHttp.task;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:09 . 2016年12月28日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

/**
 * 消息类 传递用的
 * @param <Param>
 * @param <Update>
 * @param <Result>
 */
public  class Messager<Param, Update, Result> {

    private final MultiAsynctask<Param, Update, Result> asynctask;

    private final Update update;

    private final Result result;

    public Messager(MultiAsynctask<Param, Update, Result> asynctask, Update update, Result result) {
        this.asynctask = asynctask;
        this.update = update;
        this.result = result;
    }

    /**
     * 调用当前MultiAsynctask的主线程更新方法
     */
    public void onUpdate() {
        asynctask.onUpdate(update);
    }

    /**
     * 调用当前MultiAsynctask的主线程结果方法
     */
    public void onResult() {
        asynctask.onResult(result);
    }

}
