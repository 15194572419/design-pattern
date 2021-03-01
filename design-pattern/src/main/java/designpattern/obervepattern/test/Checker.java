package designpattern.obervepattern.test;

import designpattern.obervepattern.BaseTrigger;

/**
 * 检查人员类：检查人员的检查触发工作人员的紧急动作
 * @author 帆哥续写辉煌
 */
public class Checker extends BaseTrigger {

    /**
     * 检查人员检查函数
     */
    public void check(){
        this.changeState(false);
    }
}
