package designpattern.obervepattern.test;

import designpattern.obervepattern.BaseResponder;

/**
 * 工作人员2：服务员
 * @author 帆哥续写辉煌
 */
public class Waiter extends BaseResponder {
    @Override
    public void response() {
        System.out.println("由于领导的检查，服务员抓紧时间上菜");
    }
}
