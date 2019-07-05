package fatiny.myTest.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * 
 * 
 *
-----------------------------------------------------
Letter  Date or Time Component  Presentation  	Examples  
G  		Era designator  		Text  			AD  
y  		Year  					Year  			1996; 96  	
M  		Month in year  			Month  			July; Jul; 07  
w  		Week in year  			Number  		27  
W  		Week in month  			Number  		2  
D  		Day in year  			Number  		189  
d  		Day in month  			Number  		10  
F  		Day of week in month  	Number  		2  
E  		Day in week  			Text  			Tuesday; Tue  
a  		Am/pm marker  			Text  			PM  
H  		Hour in day (0-23)  	Number  		0  
k  		Hour in day (1-24)  	Number  		24  
K  		Hour in am/pm (0-11)  	Number  		0  
h  		Hour in am/pm (1-12)  	Number  		12  
m  		Minute in hour  		Number  		30  
s  		Second in minute  		Number  		55  
S  		Millisecond  			Number  		978  
z  		Time zone  General 		time zone  		Pacific Standard Time; PST; GMT-08:00  
Z  		Time zone  RFC 822 		time zone  		-0800  
-----------------------------------------------------
Quick Format
yyyy/MM/dd 	= 2008/09/12
yy-MM-dd 	= 08/09/12
-----------------------------------------------------
 * @author hj
 *
 */
public class DateUtil {
	public static final long MS = 1000L; //毫秒:1秒
	public static final long TOTAL_SEC_PER_MINUTE = 60; //秒:一分钟
	public static final long TOTAL_SEC_PER_HOUR = 60L*TOTAL_SEC_PER_MINUTE;//秒:一小时
	public static final long TOTAL_SEC_PER_DAY = 24L*TOTAL_SEC_PER_HOUR;//秒:一天
	public static final long TOTAL_MS_PER_DAY = 24L*TOTAL_SEC_PER_HOUR*MS;//毫秒:一天
	public static final long TOTAL_MS_PER_WEEK = 7L*24*TOTAL_SEC_PER_HOUR*MS;//毫秒:一周
	
	public static String getDateStr(String tag){
		Date date = getDate(tag);
		
		return format(date, "yyyy-MM-dd");
	}
	
	public static boolean isValidFormat(String tag, String value){
		String datePattern1 = "^\\d{8}$";
		String datetimePattern1 = "^\\d{8} \\d{4}$";
		
		if("date1".equals(tag)){
			return Pattern.matches(datePattern1, value);
		}else if("dtime1".equals(tag)){
			return Pattern.matches(datetimePattern1, value);
		}
		
		return true;
	}
	
	public static String formatTime(long timeVal){
		if(timeVal < 0){
			return null;
		}
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return f.format(new Date(timeVal));
	}
	
	public static String formatTime(long timeVal, String format){
		if(timeVal < 0){
			return null;
		}
		
		if(format == null || "".equals(format)){
			format = "yyyy-MM-dd HH:mm:ss";
		}
		
		SimpleDateFormat f = new SimpleDateFormat(format);
		
		return f.format(new Date(timeVal));
	}
	
	public static String formatTime(Date date){
		if(date == null){
			return null;
		}
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return f.format(date);
	}
	
	public static String formatHourTime(Date date){
		if(date == null){
			return null;
		}
		
		SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
		
		return f.format(date);
	}
	
	public static String formatTime(Date date, String nullValue){
		if(date == null){
			return nullValue;
		}
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return f.format(date);
	}
	
	public static int getHourNow(){
		Calendar cal = Calendar.getInstance();
		if(cal == null){
			return -1;
		}
		
		return cal.get(Calendar.HOUR_OF_DAY);
	}
	
	public static String formatMysqlTime(long timeVal){
		if(timeVal < 0){
			return "'null'";
		}
		
		return formatTime(new Date(timeVal), "'null'", true);
	}

	public static String formatMysqlDate(Date date){
		return "'" + format(date, "yyyy-MM-dd") + "'";
	}

	
	public static String formatMysqlTime(Date date){
		return formatTime(date, "'null'", true);
	}
	
	public static String formatTime(Date date, String nullValue, boolean withQuote){
		if(date == null){
			return nullValue;
		}
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(withQuote){
			return "'" + f.format(date) + "'";
		}
		
		// implicit else
		return f.format(date);
	}
	
	public static String format(Date date, String format){
		SimpleDateFormat f = new SimpleDateFormat(format);
		
		return f.format(date);
	}
	
	public static String format(long time, String format){
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date date = new Date(time);
		return f.format(date);
	}
	
	public static Date getDate(String tag){
		if(tag == null){
			return new Date();
		}
		
		if("now".equals(tag)){
			return new Date();
		}
		
		if("today".equals(tag)){
			return truncate(new Date());
		}
		
		if("yesterday".equals(tag)){
			return getDate(truncate(new Date()), -1);
		}

		if("tomorrow".equals(tag)){
			return getDate(truncate(new Date()), 1);
		}
		
		String[] token;
		
		token = tag.split("(last)|(day)");
		if(token.length > 1){
			int shift = intValue(token[1]); 
			return getDate(truncate(new Date()), -shift);
		}
		
		token = tag.split("(next)|(day)");
		if(token.length > 1){
			int shift = intValue(token[1]); 
			return getDate(truncate(new Date()), shift);
		}
		
		
		return new Date();
	}
	
	public static int intValue(String str){
		try{
			return Integer.parseInt(str);
		}catch(NumberFormatException e){
			return 0;
		}
	}
	
	/**
	 * Get the Date of month 
	 * 
	 * @param date
	 * @param monthShift
	 * @param day	-N to N  (the Nth day, -Nth last N day, 0 = last day of month)
	 * @return
	 */
	public static Date getDateByMonth(Date date, int monthShift, int day){
		if(date == null){
			return null;	// NULL in, NULL out
		}
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		

		int currentMonth = cal.get(Calendar.MONTH);
		int monthDiff = (currentMonth - monthShift) % 12;
		int yearDiff = - (monthShift - monthDiff) / 12;
		
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - yearDiff);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - monthDiff);
		
		int maxDay = cal.getMaximum(Calendar.DATE);
		if(day == 0){
			day = maxDay;
		}else if(day < 0){
			if(day < -maxDay){
				day = -maxDay+1;
			}
			day = maxDay + day; 
			// System.out.println("MaxDay:" + maxDay + " Day: " + day);
		}else if(day > maxDay){
			day = maxDay;
		}
		
		cal.set(Calendar.DATE, day);
		
				
		return cal.getTime();
		
	}
	
	/**
	 * Get the Date of week 
	 * 
	 * @param date
	 * @param monthShift
	 * @param day	-N to N  (the Nth day, -Nth last N day, -1 = last day of month)
	 * @return
	 */
	public static Date getDateByWeek(Date date, int monthShift, int day){
		return null;
	}

    public static Date deductDay(Date date, int day){
        long time = date.getTime();

        long newTime = time - (TOTAL_MS_PER_DAY * day);

        return new Date(newTime);
    }
    public static Date addDay(Date date, int day){
        long time = date.getTime();

        long newTime = time + (TOTAL_MS_PER_DAY * day);

        return new Date(newTime);
    }
	/**
	 * 得到 date 时间 + hour小时天后的时间
	 * @param date
	 * @param dayDiff
	 * @return
	 */
	public static Date addHour(Date date, int hour){
		long time = date.getTime();
		
		long newTime = time + (hour * TOTAL_SEC_PER_HOUR * 1000);
		
		return new Date(newTime);
	}
	
	/**
	 * 得到 date 时间 + minute分钟后的时间
	 * @param date
	 * @param dayDiff
	 * @return
	 */
	public static Date addMinute(Date date, int minute){
		long time = date.getTime();
		
		long newTime = time + (minute * TOTAL_SEC_PER_MINUTE * 1000);
		
		return new Date(newTime);
	}
	/**
	 * 得到 date 时间 + second秒后的时间
	 * @param date
	 * @param dayDiff
	 * @return
	 */
	public static Date addSecond(Date date, int second){
		long time = date.getTime();
		
		long newTime = time + (second * 1000);
		
		return new Date(newTime);
	}
	
	/**
	 * 得到 date 时间 + dayDiff天后的时间
	 * @param date
	 * @param dayDiff
	 * @return
	 */
	public static Date getDate(Date date, int dayDiff){
		long time = date.getTime();
		
		long newTime = time + (dayDiff * TOTAL_SEC_PER_DAY * 1000);
		
		return new Date(newTime);
	}
	
	public static Date getDate(String dateStr, int dayDiff){
		Date date = parseTime(dateStr);
		if(date == null){
			return null;
		}
		
		return getDate(date, dayDiff);
	}
	
	public static Date truncate(Date date){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		
		// Reset all time to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
    /**
     * 截取分钟以下的数据，即：秒和毫秒的数值为0
     * @return 如：2016-7-6 12:00:00 000
     */
    public static Date truncateMinute(Date date){
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
	/**
	 * 时间精确到分钟，截取秒以下的数据，即：秒和毫秒的数值为0
	 * @return 如：2016-7-6 12:24:00 000
	 */
	public static Date truncateSecond(){
		return truncateSecond(new Date());
	}
	/**
	 * 时间精确到分钟，截取秒以下的数据，即：秒和毫秒的数值为0
	 * @return 如：2016-7-6 12:24:00 000
	 */
	public static Date truncateSecond(Date date){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date parseTime(String dateStr) {
		return parse(dateStr, "yyyy-MM-dd HH:mm:ss");
	}


	public static Date parse(String dateStr) {
		return parse(dateStr, "yyyy-MM-dd");
	}

	public static Date parse(String dateStr, String format) {
		DateFormat formatter = new SimpleDateFormat(format);
		
        try {
			return (Date) formatter.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

    /**
     * 解析小时格式，当天某个时间
     * @param dateStr "20:50:05"
     * @return Tue Oct 20:50:05 CST 2016
     */
    public static Date parseHourFormat(String dateStr) {
        try{
//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//            long time = sdf.parse(dateStr).getTime();
//            Calendar cal = Calendar.getInstance();
//            cal.set(Calendar.HOUR_OF_DAY, 8);
//            cal.set(Calendar.MINUTE, 0);
//            cal.set(Calendar.SECOND, 0);
//            cal.set(Calendar.MILLISECOND, 0);
//            cal.add(Calendar.MILLISECOND,(int)time);
    		String data [] = dateStr.split(":");
    		Date localTime = new Date();
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(localTime);
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(data[0]));
            cal.set(Calendar.MINUTE, Integer.parseInt(data[1]));
            cal.set(Calendar.SECOND, Integer.parseInt(data[2]));
            return cal.getTime();
        }catch (Exception e){
            return null;
        }
    }
    
    /***
     * 解析小时格式，指定时间的某个时间点
     * @param date
     * @param dateStr "20:50:05"
     * @return
     */
    public static Date parseHourFormatEx(Date date,String  dateStr){
    	  try{
              SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
              long time = sdf.parse(dateStr).getTime()+8*60*60*1000;//因为0是从1970的8点开始的，所以加8
              
              Calendar cal = Calendar.getInstance();
              cal.setTime(date);
              cal.set(Calendar.HOUR_OF_DAY, 0);
              cal.set(Calendar.MINUTE, 0);
              cal.set(Calendar.SECOND, 0);
              cal.set(Calendar.MILLISECOND, 0);
              cal.add(Calendar.MILLISECOND,(int)time);
              return cal.getTime();
          }catch (Exception e){
              return null;
          }
    	
    }
	public static Date dateSub(Date reportDate, int day) {
		if(day == 0){
			return reportDate; 
		}
		
		long value = reportDate.getTime();
		
		return new Date(value - day*TOTAL_SEC_PER_DAY*1000);
	}
	
	public static long getTimeMillis(Date date){
		if(date == null){
			return 0;
		}
		return date.getTime(); 
	}
	
	/**
	 * 返回每天0点0分０秒的时间距标准基准时间的毫秒数
	 */
	public static long getTodayTimeval(){
		return truncate(new Date()).getTime();
	}
	/**
	 * 返回当天到第二天0点剩余的毫秒数
	 * @return
	 */
	public static long getTodayOverMs()
	{
		return (truncate(new Date()).getTime() + 24 * 60 * 60 * 1000) -  System.currentTimeMillis();
	}
	
	/**
	 * 检测传入时间是否为今天
	 * @param date
	 * @return true, 同一天,fasle 不是同一天
	 */
	public static boolean isToday(Date date)
	{
		if(truncate(new Date()).getTime() <= date.getTime() && date.getTime() < 
				truncate(new Date()).getTime() + 24 * 60 * 60 * 1000 )
		{
			return true;
		}
		return false;
	}

    /**
     * 检测传入时间是否为今天
     * @param date
     * @param hour 非0点，自定义分界点,比如5点
     * @return
     */
    public static boolean isToday(Date date,int hour)
    {
        //开始时间
        Date start = null;
        //结束时间
        Date end = null;
        //分隔时间点
        Date tmp = addHour(truncate(new Date()),hour);
        //当前时间已经超过分隔时间，结束时间+1天
        if (System.currentTimeMillis() >= tmp.getTime()){
            start = tmp;
            end = addDay(tmp,1);
        }else {
            start = deductDay(tmp,1);
            end = tmp;
        }
        return start.getTime() <= date.getTime() && date.getTime() < end.getTime();
    }

    /**
     * 检测传入时间是否为今天
     * @param date
     * @param format "05:00:00"
     * @return <tt>true</tt> 同一天.
     */
    public static boolean isToday(Date date,String format)
    {
        //开始时间
        Date start = null;
        //结束时间
        Date end = null;
        //分隔时间点
        Date tmp = parseHourFormat(format);
        //当前时间已经超过分隔时间，结束时间+1天
        if (System.currentTimeMillis() >= tmp.getTime()){
            start = tmp;
            end = addDay(tmp,1);
        }else {
            start = deductDay(tmp,1);
            end = tmp;
        }
        return start.getTime() <= date.getTime() && date.getTime() < end.getTime();
    }
    
    public static boolean isToday(Date Date, Date checkDate){
    	//开始时间
        Date start = null;
        //结束时间
        Date end = null;
        //分隔时间点
        Date tmp = checkDate;
        //当前时间已经超过分隔时间，结束时间+1天
        if (System.currentTimeMillis() >= tmp.getTime()){
            start = tmp;
            end = addDay(tmp,1);
        }else {
            start = deductDay(tmp,1);
            end = tmp;
        }
        return start.getTime() <= Date.getTime() && Date.getTime() < end.getTime();
    }
	
    
	public static int getHour(long warStartTime)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(warStartTime);
		return cal.get(Calendar.HOUR_OF_DAY);
	}
	
	public static int getMin(long warStartTime)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(warStartTime);
		return cal.get(Calendar.MINUTE);
	}

	public static int getWeek() {
		Calendar cal = Calendar.getInstance();
		if(cal == null){
			return -1;
		}
		
		return cal.get(Calendar.DAY_OF_WEEK);
	}
    public static int getDay() {
        Calendar cal = Calendar.getInstance();
        if(cal == null){
            return -1;
        }

        return cal.get(Calendar.DAY_OF_MONTH);
    }
    
	public static Date getWeekDayDate(int day){
		Calendar cal = Calendar.getInstance();
		if(cal==null){
			return null;
		}
		int weedDay = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DAY_OF_MONTH, day-weedDay);
		Date rs = truncate(cal.getTime());
		return rs;
	}
	
	public static int getDaysBetween(Date startDate, Date endDate) {  
        Calendar fromCalendar = Calendar.getInstance();  
        fromCalendar.setTime(startDate);  
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        fromCalendar.set(Calendar.MINUTE, 0);  
        fromCalendar.set(Calendar.SECOND, 0);  
        fromCalendar.set(Calendar.MILLISECOND, 0);  
  
        Calendar toCalendar = Calendar.getInstance();  
        toCalendar.setTime(endDate);  
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        toCalendar.set(Calendar.MINUTE, 0);  
        toCalendar.set(Calendar.SECOND, 0);  
        toCalendar.set(Calendar.MILLISECOND, 0);  
  
        return (int)((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));  
    }  
	/***
	 * 判断两个日期相差的天数
	 * @param startDate
	 * @param format
	 *            自定义分界点 (05:00:00)
	 * @return
	 */
	public static int getDaysBetweenAt(Date startDate,Date endDate, String format) {
		// 结束分隔时间点
		Date startTmp = parseHourFormatEx(startDate,format);
		Date endTmp = parseHourFormatEx(endDate,format);
		
		// 结束时间已经小余结束分隔时间，结束时间-1天
		if (endDate.getTime()<endTmp.getTime()) {
			endDate=deductDay(endTmp,1);
		} else {//结束时间大于结束分隔时间，结束时间+1天
			//endDate = addDay(endTmp, 1);
			endDate=endTmp;
		}
		// 开始时间小余结束分隔时间，开始时间-1天
		if (startDate.getTime()<startTmp.getTime()) {
			startDate=deductDay(startTmp,1);
		} else {//开始时间大于结束分隔时间，开始时间+1天
			//startDate = addDay(startTmp, 1);
			startDate = startTmp;
		}
		int daysBetween = getDaysBetween(startDate, endDate);
		return daysBetween;
	}

	/**
	 * 比较两个时间的分钟数是否相等
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isMinute(Date startDate, Date endDate) {  
		Calendar dateOne = Calendar.getInstance();
		dateOne.setTime(startDate); 
		dateOne.set(Calendar.SECOND, 0);
		dateOne.set(Calendar.MILLISECOND, 0);
		
		Calendar dateTwo = Calendar.getInstance();
		dateTwo.setTime(endDate); 
		dateTwo.set(Calendar.SECOND, 0);
		dateTwo.set(Calendar.MILLISECOND, 0);
		
//		long timeOne = dateOne.getTimeInMillis() / (1000*60) ;
//		long timeTwo = dateTwo.getTimeInMillis() / (1000*60);
//		long minute = timeOne-timeTwo;//转化minute
//		System.out.println("相隔"+minute+"分钟");
        return dateOne.getTimeInMillis() == dateTwo.getTimeInMillis() ;  
    }  
	/**
	 * 取得半年前的日期
	 * @return
	 */
	public static String getHalfyearDate(String format){
		DateFormat formatter = new SimpleDateFormat(format);
		Calendar calendar=Calendar.getInstance();   
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONDAY)-6);
		return formatter.format(calendar.getTime());
	}
	
	/**
	 * 判断 date 时间 与当天的hour小时 这个时间  是否达到可以刷新
	 * @param date -- 判断的时间
	 * @param hour -- 重置的 小时  ： 24小时制 1-24
	 * @return
	 */
	public static boolean isMayReset(Date date , int hour)
	{
		long time = date.getTime();
		
		long newTime = time + ((24 - hour) * TOTAL_SEC_PER_HOUR * 1000);

		if(isToday(new Date(newTime)))
		{
			return false;
		}else
		{
			return true;
		}
	}

	/**
	 * 获取传入的时间 + second秒  - 现在的时间 =  剩下的秒数 ： 负数时： 返回0
	 * @param date
	 * @param minute
	 * @return
	 */
	public static long getDiffSecond(Date date , int second)
	{
        Calendar fromCalendar = Calendar.getInstance();  
        fromCalendar.setTime(date);
        fromCalendar.set(Calendar.SECOND ,  second + fromCalendar.get(Calendar.SECOND));
        long ms = fromCalendar.getTime().getTime() -  new Date().getTime();
        if(ms < 0)
        	ms = 0;
        else
        	ms = ms/1000;
		return ms;
	}
	
	/**
	 * 判断是否在同一周
	 * @param d1
	 * @param d2
	 * @return (d1=null or d2= null) return false
	 */
	public static boolean isSameWeek(Date d1, Date d2) {
		
		if (d1 == null) return false;
		if (d2 == null) return false;
		
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(d1);
		int y1 = calendar.get(Calendar.YEAR);
		int m1 = calendar.get(Calendar.WEEK_OF_YEAR);
		
		calendar.setTime(d2);
		int y2 = calendar.get(Calendar.YEAR);
		int m2 = calendar.get(Calendar.WEEK_OF_YEAR);
		
		return y1==y2 && m1==m2;
		
	}
	/**
	 * 获取2个时间直接的整小时数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long getHoursBeteewn(Date d1, Date d2) {
		long t1 = d1.getTime();
		long t2 = d2.getTime();
		if (t1 > t2) {
			return (t1-t2)/(1000 * 60 * 60);
		} else {
			return (t2-t1)/(1000 * 60 * 60);
		}
	}
	
	static TimeZone timeZone = Calendar.getInstance().getTimeZone();//服务器默认时区
	/**
	 * 和客户端的统一绝对时间传送标准
	 */
	public static long getTimeInGMT0(long dateTime){
		long ti = timeZone.getOffset(dateTime) + dateTime;
//		System.out.println(dateTime + "-->" + ti + ",sub=" + (ti - dateTime));
		return ti;
	}
	
	/**
	 * 是否在指定时间段内
	 * @param start
	 * @param end
	 * @param now
	 * @return
	 */
	public static boolean isInTimeRange(Date start, Date end,Date now){
		if(start == null || end == null){
			return false;
		}
		return (start.getTime() <= now.getTime() && now.getTime() <= end.getTime());
	}
	/**
	 * setting表时间控制处理：2015112018（年+月+日+整点10位数）判断是否指定在日期范围内
	 * @param start 10位精确到整点活动开启时间
	 * @param end	10位精确到整点活动结束时间
	 * @param now	指定完整时间
	 * @return 
	 */
	public static boolean isInDigit10Time(Integer start, Integer end,Date now){
		StringBuffer startDate = new StringBuffer();
		StringBuffer endDate= new StringBuffer();
		startDate.append(start.toString().substring(0, 4)).append("-");
		startDate.append(start.toString().substring(4, 6)).append("-");
		startDate.append(start.toString().substring(6, 8)).append(" ");
		startDate.append(start.toString().substring(8, 10)).append(":").append("00:00");
		endDate.append(end.toString().substring(0, 4)).append("-");
		endDate.append(end.toString().substring(4, 6)).append("-");
		endDate.append(end.toString().substring(6, 8)).append(" ");
		endDate.append(end.toString().substring(8, 10)).append(":").append("00:00");
		return isInTimeRange(parseTime(startDate.toString()), parseTime(endDate.toString()), now);
	}
	
	/**
	 * 是否可以刷新数据
	 * @param resetHourStr 5:00:00
	 * @param reshTime
	 * @return (true 表示刷新，false 表示没有刷新)
	 */
	public static boolean isInDigitReshTime(String resetHour,Date reshTime){
		if(reshTime == null){
			return true;
		}
		String[] timeArr = resetHour.split(":");
		int hour = DateUtil.getHour(System.currentTimeMillis());
		String dateStr = null;
		if(Integer.valueOf(timeArr[0]) <= hour){
			dateStr = DateUtil.getDateStr("today")+" "+resetHour;
		}else{
			dateStr = DateUtil.getDateStr("yesterday")+" "+resetHour;
		}
		Date nowDate = DateUtil.parseTime(dateStr);
		if(reshTime.before(nowDate)){
			return true;
		}
		return false;
	}
	
	/**判断是否在同一天
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1,Date date2){
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(date1);
		int y1 = calendar.get(Calendar.YEAR);
		int m1 = calendar.get(Calendar.MONTH);
		int d1 = calendar.get(Calendar.DAY_OF_MONTH);
		
		calendar.setTime(date2);
		int y2 = calendar.get(Calendar.YEAR);
		int m2 = calendar.get(Calendar.MONTH);
		int d2 = calendar.get(Calendar.DAY_OF_MONTH);
		return y1==y2 && m1==m2 && d1==d2;
	}
	
	/**判断是否在同一月
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameMonth(Date date1,Date date2){
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(date1);
		int y1 = calendar.get(Calendar.YEAR);
		int m1 = calendar.get(Calendar.MONTH);
		
		calendar.setTime(date2);
		int y2 = calendar.get(Calendar.YEAR);
		int m2 = calendar.get(Calendar.MONTH);
		return y1==y2 && m1==m2;
	}
	
	/**
	 * 获取两个时间时间差（秒）
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getTimeDifference(Date d1, Date d2){
		long time1 = d1.getTime()/1000;
		long time2 = d2.getTime()/1000;
		return (int)(time1 - time2);
	}
	
	/**
	 * 判断两个时间是否相同
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isSameTime(Date d1, Date d2){
		return getTimeDifference(d1, d2) == 0;
	}
	
	/**
     * 此函数非原创，从网上搜索而来，timeZoneOffset原为int类型，为班加罗尔调整成float类型
     * timeZoneOffset表示时区，如中国一般使用东八区，因此timeZoneOffset就是8
     * @param timeZoneOffset
     * @return
     */
    public static String getFormatedDateString(float timeZoneOffset){
        if (timeZoneOffset > 13 || timeZoneOffset < -12) {
            timeZoneOffset = 0;
        }
        
        int newTime=(int)(timeZoneOffset * 60 * 60 * 1000);
        TimeZone timeZone;
        String[] ids = TimeZone.getAvailableIDs(newTime);
        if (ids.length == 0) {
            timeZone = TimeZone.getDefault();
        } else {
            timeZone = new SimpleTimeZone(newTime, ids[0]);
        }
    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(timeZone);
        return sdf.format(new Date());
    }
	
	
	public static final void main(String argv[]) throws ParseException{
//		TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles")); //设置时区  
//		TimeZone.setDefault(TimeZone.getTimeZone("GMT-1")); //设置时区  
		System.out.println("设置时区为:"+TimeZone.getDefault()); //输出验证  
		String data [] = "20:00:00".split(":");
		Date localTime = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(localTime);
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(data[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(data[1]));
        cal.set(Calendar.SECOND, Integer.parseInt(data[2]));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(cal.getTime()));
	}
	
}
