package settime;

import java.time.LocalDateTime;

public class SetTime {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Test program is running:"+ LocalDateTime.now());
        Thread thread = new Thread(()->{System.out.println("The setTime program has ran:"+ LocalDateTime.now());});
        thread.start();
        Thread.sleep(1200000);
        System.out.println("Test program is shutting down:"+ LocalDateTime.now());
    }
}
