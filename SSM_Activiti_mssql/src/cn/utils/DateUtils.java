package cn.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	
	/**
	 * 字符串格式：yyyy-MM-dd HH:mm:ss.sss
	 */
	public static final String formatter_yyyyMMddHHmmsssss = "yyyy-MM-dd HH:mm:ss.sss";
	/**
	 * 字符串格式：yyyy-MM-dd HH:mm:ss
	 */
	public static final String formatter_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 字符串格式：yyyy-MM-dd HH:mm
	 */
	public static final String formatter_yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
	/**
	 * 字符串格式：yyyy-MM-dd
	 */
	public static final String formatter_yyyyMMdd = "yyyy-MM-dd";
	/**
	 * 字符串格式：yyyy-MM
	 */
	public static final String formatter_yyyyMM = "yyyy-MM";
	/**
	 * 字符串格式：yyyy
	 */
	public static final String formatter_yyyy = "yyyy";
	/**
	 * 字符串格式：MM-dd
	 */
	public static final String formatter_MMdd = "MM-dd";
	/**
	 * 字符串格式：HH:mm:ss
	 */
	public static final String formatter_HHmmss = "HH:mm:ss";
	/**
	 * 字符串格式：HH:mm
	 */
	public static final String formatter_HHmm = "HH:mm";
	/**
	 * 字符串格式：返回星期X(一、二、三、四、五、六、日)
	 */
	public static final String formatter_EEEE = "EEEE";
	
	public static SimpleDateFormat formatter_yMdHms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static SimpleDateFormat formatter_yMdHm = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static SimpleDateFormat formatter_yMd = new SimpleDateFormat("yyyy-MM-dd");
	
	public static SimpleDateFormat formatter_yM = new SimpleDateFormat("yyyy-MM");
	
	public static SimpleDateFormat formatter_Hm = new SimpleDateFormat("HH:mm");
	
	
	
	 public static int daysBetween(String smdate,String bdate) throws Exception{   
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(formatter_yMdHm.parse(smdate));    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(formatter_yMdHm.parse(bdate));    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24)+1;     
	        return Integer.parseInt(String.valueOf(between_days));     
	    }  
	 
	 
	 public static int compareString(String begin,String end) throws Exception{   
	       return begin.compareTo(end); 
	    }  

	 public static String getAheah(String beginString) throws Exception{   
		 	Calendar cal = Calendar.getInstance();    
	        cal.setTime(formatter_yMdHm.parse(beginString));    
	        cal.add(Calendar.DATE, -1);    //得到前一天
	        String  yestedayDate= new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	        return  yestedayDate;
	    } 
	
	 public static float hoursBetween(String smdate,String bdate) throws Exception{   
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(formatter_yMdHm.parse(smdate));    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(formatter_yMdHm.parse(bdate));    
	        long time2 = cal.getTimeInMillis();         
	        float between_hours=(float) ((time2-time1)/(1000*3600.0));     
	        return between_hours;   
	    }  
	 
	 
	 public static float minuteBetween(String smdate,String bdate) throws Exception{   
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(formatter_yMdHm.parse(smdate));    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(formatter_yMdHm.parse(bdate));    
	        long time2 = cal.getTimeInMillis();         
	        float between_minutes=(float) ((time2-time1)/(1000*60.0));     
	        return between_minutes;   
	    }  
	
	 
	 /**
		 * @Description: 将日期转换为指定格式的字符串
		 * @param @param date
		 * @param @param format
		 * @param @return   
		 * @return String  
		 * @author Jie.xiaojun
		 * @date 2017-12-4  下午03:58:17
		 */
		public static String dateToString(java.util.Date date, String format){
			String result = "";
			SimpleDateFormat formater = new SimpleDateFormat(format);
			try {
				result = formater.format(date);
			} catch (Exception e) {
			}
			return result;
		}
	 
	 
	 /**
		 * 
		 * @Description: 获取给定时间第几个月第一天的指定格式字符串
		 * @param date
		 * @param format
		 * @param month
		 * @return String  
		 * @author Jie.xiaojun
		 * @date 2017-12-5  上午08:18:27
		 */
		public static String getFirstDayOfMonth(Date date, String format, int month) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, month);
			cal.set(Calendar.DATE, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			return dateToString(cal.getTime(), format);
		}	
		
		/**
		 * 
		 * @Description: 获取给定时间第几个月最后一天的指定格式字符串
		 * @param date
		 * @param format
		 * @return String  
		 * @author Jie.xiaojun
		 * @date 2017-12-5  上午08:29:48
		 */
		public static String getLastDayOfMonth(Date date, String format,int month) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.DATE, 1);
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DATE, -1);
			cal.add(Calendar.MONTH, month);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			return dateToString(cal.getTime(), format);
		}
		
		
		/**
		 * @Description: PC端—根据一个月份数字 自动返回其前一个月的值
		 * @param month
		 * @return int  
		 * @author Jie.xiaojun
		 * @date 2018-1-13  下午02:52:03
		 */
		 public static int AheadOneMonth(int month) {
			 int OneMonth =month-1;
			 if(OneMonth==0)
			 {
				 return 12;
			 }
			 else
			 {
				 return OneMonth;
			 }
		 }
		
	 public static void main(String[] args) throws Exception
	 {
		 System.out.println(minuteBetween("2017-10-08 08:00", "2017-10-08 08:59")>=60);
	 }

}
