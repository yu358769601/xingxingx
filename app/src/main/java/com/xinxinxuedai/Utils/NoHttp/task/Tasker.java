package com.xinxinxuedai.Utils.NoHttp.task;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 17:23 . 2016年12月28日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

/**
 * 工作线程 可以是 调用 一个 抽象类然后获取的返回值 在传递在另一方法里
 */
class Tasker <Param,Update,Result>implements Runnable {
    MultiAsynctask asynctask;
    private Param[] params;

    public Tasker(MultiAsynctask asynctask,Param[] params) {
        this.params = params;
        this.asynctask = asynctask;
    }



    @Override
    public void run() {
        //给一个抽象类赋可变参数  返回一个结果泛型结果
        Result result = (Result) asynctask.onExecuteTask(params);
        asynctask.onPostResult(result);

    }
}