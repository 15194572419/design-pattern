package designpattern.obervepattern.test;

/**
 * 观察者模式测试入口
 * @author 帆哥续写辉煌
 */
public class Test {
    public static void main(String[] args) {
        Checker checker = new Checker();

        Cook cook1 = new Cook();
        Cook cook2 = new Cook();
        Waiter waiter1 = new Waiter();
        Waiter waiter2 = new Waiter();

        cook1.setBindTrigger(checker);
        cook2.setBindTrigger(checker);
        waiter1.setBindTrigger(checker);
        waiter2.setBindTrigger(checker);

        System.out.println("领导到来前情况是："+checker.getStateChanged());
        checker.check();
        System.out.println("领导到后来情况是："+checker.getStateChanged());

        System.out.println("---------------------------------------------");

        cook1.unbindResponder();
        waiter1.unbindResponder();
        System.out.println("领导到来前情况是："+checker.getStateChanged());
        checker.changeState(true);
        System.out.println("领导到后来情况是："+checker.getStateChanged());

        System.out.println("---------------------------------------------");
        checker.clearAllResponders();
        System.out.println("领导到来前情况是："+checker.getStateChanged());
        checker.changeState(true);
        System.out.println("领导到后来情况是："+checker.getStateChanged());

    }
}
