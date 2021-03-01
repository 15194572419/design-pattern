package designpattern.obervepattern;



/**
 * @discription 响应者类(观察者)
 * @author 帆哥续写辉煌
 */
public abstract class BaseResponder {

    /**
     * 与之绑定的触发者（被观察者）
     */
    private BaseTrigger bindTrigger;

    /**
     * 在当前没有触发者的情况下，设置触发者.
     * @param bindTrigger
     * @return true:成功；false:失败
     */
    public boolean setBindTrigger(BaseTrigger bindTrigger) {
        if(this.bindTrigger==null){
            bindTrigger.addOneResponder(this);
            this.bindTrigger = bindTrigger;
            return true;
        }else {
            return false;
        }
    }


    /**
     *在具有触发者时与触发者解绑
     * @return true:成功；false:失败
     */
    public boolean unbindResponder(){
        if(this.bindTrigger==null){
            return false;
        }else {
            this.bindTrigger.deleteOneResponder(this);
            this.bindTrigger=null;
            return true;
        }
    }

    /**
     *  响应者接收到触发者消息后的相应动作
     */
    public  abstract void response();

}
