package cn.dszx;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class dateTest {
    @Test
    public void test(){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,10);
        Date time = instance.getTime();
//        long timeInMillis = instance.getTimeInMillis();
        Date date = new Date(instance.getTimeInMillis());

//        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        String format1 = format.format(date);
        System.out.println(format.format(time));
        System.out.println(format.format(date));
    }

    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        Date time = instance.getTime();
        long timeInMillis = instance.getTimeInMillis();
        Timer timer = new Timer();

    }
}
