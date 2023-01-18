package my.uum;

import java.time.Duration;
import java.util.*;

/**
 * This class is to implement and run Thread method
 * @author koko
 **/
public class MyThread extends Thread{

    /**
     * This method is used to check the time at 12:01 a.m. daily
     **/
    private static Date get12am(){
        Calendar tomorrow = new GregorianCalendar();
        Calendar result = new GregorianCalendar(
                tomorrow.get(Calendar.YEAR),
                tomorrow.get(Calendar.MONTH),
                tomorrow.get(Calendar.DATE),
                00,
                01,
                00
        );
        return result.getTime();
    }

    /**
     * This method is used to run delete data task at 12:01 a.m. daily
     **/
    @Override
    public void run() {
        SQLite sql = new SQLite();
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                sql.deleteData();
            }
        };

        timer.scheduleAtFixedRate(task, get12am(),  Duration.ofDays(1).toMillis());
    }
}