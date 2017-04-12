package com.fastjrun.demo;

import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;

import com.fastjrun.helper.TimeHelper;

public class TimeHelperTest {
    
    @Test
    public void testOffsetDay(){
        Date date=new Date();
        for(int i=1;i<31;i++){
            Date invalidDate = TimeHelper.getOffsetDate(date, Calendar.DATE,
                    i);
            String invalidDateStr = TimeHelper.getFormatDate(invalidDate,
                    TimeHelper.DF17);
            System.out.println(invalidDateStr);
        }
    }

}
