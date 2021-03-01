package designpattern.obervepattern;

import util.threadpool.LinCatThreadPool;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;


/**
 * @discription 触发者类(被观察者)
 * @author 帆哥续写辉煌
 */
public abstract class BaseTrigger {
    /**
     * 触发者状态是否改变的标志
     */
    protected boolean stateChanged;
    /**
     * 与触发者绑定的响应者（观察者）
     */
    protected ArrayList<BaseResponder> bindResponders;

    /**
     * 触发者构造函数
     */
    protected BaseTrigger(){
        this.stateChanged=false;
        this.bindResponders = new ArrayList<>();
    }

    /**
     * 获取所有与之绑定的触发者的数量
     * @return
     */
    public int getResponderNumber(){
        return this.bindResponders.size();
    }

    /**
     * 增加一名触发者（观察者）
     * @param baseResponder
     */
    public void addOneResponder(BaseResponder baseResponder){
        this.bindResponders.add(baseResponder);
    }

    /**
     * 删除一名触发者
     * @param baseResponder
     */
    public void deleteOneResponder(BaseResponder baseResponder){
        this.bindResponders.remove(baseResponder);
    }

    /**
     * 清空绑定的所有触发者
     */
    public void clearAllResponders(){
        this.bindResponders.clear();
    }

    /**
     * 获取当前状态是否改变：true改变，false未改变
     * @return
     */
    public boolean getStateChanged(){
        return this.stateChanged;
    }


    /**
     * 改变状态为true
     * @param notifyMethod 通知触发者的方法：true异步，false同步
     */
    public void changeState(boolean notifyMethod){
        if(this.getStateChanged()){
            System.out.println("改变状态为已改变，设置失败");
        }else {
            this.stateChanged=true;

            if(notifyMethod) {
                asyncNotifyBindResponders();
            } else {
                notifyBindResponders();

            }
        }
    }



    /**
     * 异步告知所有的响应者
     */
    private void asyncNotifyBindResponders(){

        ExecutorService threadPool = LinCatThreadPool.getThreadPool();

        for(BaseResponder baseResponder :this.bindResponders){
            threadPool.execute(()-> baseResponder.response());
        }
        threadPool.shutdown();//shutdown会在全部线程直接结束后再关闭，否则程序会一直运行。
        //异步通知完全部触发者后将改变状态恢复至false
        this.stateChanged=false;

    }
    /**
     * 同步告知所有的响应者
     */
    private void notifyBindResponders(){
        for(BaseResponder baseResponder :this.bindResponders){
            baseResponder.response();
        }
        //同步通知完全部触发者后将改变状态恢复至false
        this.stateChanged=false;
    }
}
