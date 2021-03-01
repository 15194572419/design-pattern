package designpattern.obervepattern.test;

import designpattern.obervepattern.BaseResponder;

/**
 * 工作人员1：厨师
 * @author 帆哥续写辉煌
 */
public class Cook extends BaseResponder {



    @Override
    public void response() {
        System.out.println("由于领导的检查，厨师开始抓紧时间做饭");
    }
}
