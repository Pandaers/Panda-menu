package com.panda.service.time;

import com.panda.service.time.TimeUtil;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: 简单DI年华
 * @Date: 18-8-23 15:57
 * @Description:
 */
@Service
public class TimeUtilImpl implements TimeUtil {

    @Override
    public String getNowTime() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowtime = format.format(date);
        return nowtime;
    }
    /*
    * 返回年月日格式时间
    * */
    public String getYearToDay(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String nowtime = format.format(date);
        return nowtime;
    }
}
