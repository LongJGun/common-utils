package org.longj.gun.commons.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * 时间工具类
 *
 * @author LongJ
 * @date 2020/04/06 08:38
 */
public class TimeUtil {

    /** time.properties配置文件默认时区的key */
    private static final String ZONE_ID = "zone.id";
    /** 指定默认时区为亚洲上海 */
    public static ZoneId DEFAULT_ZONE ;
    /** 指定默认时钟为亚洲上海 */
    public static Clock DEFAULT_CLOCK ;

    /** 标准的日期时间格式 */
    private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /** 标准的日期格式 */
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    /** 标准的时间格式 */
    private static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER
            = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN);

    static {
        try {
            String zoneId = PropertiesUtil.getPropertiesInJar( "/time.properties" ).getProperty( ZONE_ID );
            DEFAULT_ZONE = ZoneId.of( zoneId );
            DEFAULT_CLOCK = Clock.system( DEFAULT_ZONE );
        } catch ( Exception e ){
            e.printStackTrace();
            DEFAULT_ZONE = ZoneId.systemDefault();
            DEFAULT_CLOCK = Clock.systemDefaultZone();
        }
    }

    /**
     * 格式化时间字符串转 <code>LocalDateTime</code><br/>
     *
     * @param timeStr 时间字符串
     * @param dateTimePattern 时间格式化字符串
     * @return <code>LocalDateTime</code>
     */
    public static LocalDateTime timeStrToLocalDateTime(String timeStr,String dateTimePattern) {
        Preconditions.checkArgument( StringUtils.isNotBlank( timeStr ) );
        Preconditions.checkArgument( StringUtils.isNotBlank( dateTimePattern ) );

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
        return LocalDateTime.parse(timeStr, dateTimeFormatter);
    }

    /**
     * 格式化时间字符串转 <code>LocalDate</code><br/>
     *
     * @param timeStr 时间字符串
     * @param datePattern 时间格式化字符串
     * @return <code>LocalDate</code>
     */
    public static LocalDate timeStrToLocalDate(String timeStr,String datePattern) {
        Preconditions.checkArgument( StringUtils.isNotBlank( timeStr ) );
        Preconditions.checkArgument( StringUtils.isNotBlank( datePattern ) );

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);
        return LocalDate.parse(timeStr, dateTimeFormatter);
    }

    /**
     * 格式化时间字符串转 <code>LocalTime</code><br/>
     *
     * @param timeStr 时间字符串
     * @param timePattern 时间格式化字符串
     * @return <code>LocalTime</code>
     */
    public static LocalTime timeStrToLocalTime(String timeStr,String timePattern) {
        Preconditions.checkArgument( StringUtils.isNotBlank( timeStr ) );
        Preconditions.checkArgument( StringUtils.isNotBlank( timePattern ) );

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timePattern);
        return LocalTime.parse(timeStr, dateTimeFormatter);
    }

    /**
     * 格式化时间字符串转时间戳（毫秒级别）<br/>
     * <b>例如：yyyy-MM-dd HH:mm:ss</b><br/>
     * <code>2020-04-11 20:05:40 = 1586606740000</code>
     *
     * @param timeStr 时间字符串
     * @param dateTimePattern 时间格式化字符串
     * @return Long 时间戳
     */
    public static Long timeStrToTimestampOfMilli(String timeStr,String dateTimePattern) {
        Preconditions.checkArgument( StringUtils.isNotBlank( timeStr ) );
        Preconditions.checkArgument( StringUtils.isNotBlank( dateTimePattern ) );

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
        LocalDateTime parse = LocalDateTime.parse( timeStr, dateTimeFormatter );
        return parse.atZone(DEFAULT_ZONE).toInstant().toEpochMilli();
    }

    /**
     * 格式化时间字符串转时间戳（秒级别）<br/>
     * <b>例如：yyyy-MM-dd HH:mm:ss</b><br/>
     * <code>2020-04-11 20:05:40 = 1586606740</code>
     *
     * @param timeStr 时间字符串
     * @param dateTimePattern 时间格式化字符串
     * @return Long 时间戳
     */
    public static Long timeStrToTimestampOfSecond(String timeStr,String dateTimePattern) {
        Preconditions.checkArgument( StringUtils.isNotBlank( timeStr ) );
        Preconditions.checkArgument( StringUtils.isNotBlank( dateTimePattern ) );

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
        LocalDateTime parse = LocalDateTime.parse( timeStr, dateTimeFormatter );
        return parse.atZone(DEFAULT_ZONE).toInstant().getEpochSecond();
    }


    /**
     * 时间戳（毫秒）转字符串<br/>
     * 例如：1586581882256
     *
     * @param timestamp 时间戳
     * @param dateTimePattern 时间格式化字符串
     * @return 时间字符串
     */
    public static String timestampOfMilli2String(long timestamp,String dateTimePattern){

        Preconditions.checkArgument( timestamp > 0L );
        Preconditions.checkArgument( StringUtils.isNotBlank( dateTimePattern ) );

        return DateTimeFormatter.ofPattern(dateTimePattern)
                .format( LocalDateTime.ofInstant( Instant.ofEpochMilli(timestamp), DEFAULT_ZONE) );
    }

    /**
     * 时间戳（毫秒）转字符串（默认格式 <code>yyyy-MM-dd HH:mm:ss</code>）<br/>
     * 例如：1586581882256
     *
     * @param timestamp 时间戳
     * @return 时间字符串
     */
    public static String timestampOfMilli2String(long timestamp){
        Preconditions.checkArgument( timestamp > 0L );

        return timestampOfMilli2String(timestamp,DEFAULT_DATETIME_PATTERN);
    }

    /**
     * 时间戳（秒）转字符串<br/>
     * 例如：1586581882
     *
     * @param timestamp 时间戳
     * @param dateTimePattern 时间格式化字符串
     * @return 时间字符串
     */
    public static String timestampOfSecond2String(long timestamp,String dateTimePattern){

        Preconditions.checkArgument( timestamp > 0L );
        Preconditions.checkArgument( StringUtils.isNotBlank( dateTimePattern ) );

        return DateTimeFormatter.ofPattern(dateTimePattern)
                .format( LocalDateTime.ofInstant( Instant.ofEpochSecond(timestamp), DEFAULT_ZONE) );
    }

    /**
     * 时间戳（秒）转字符串（默认格式 <code>yyyy-MM-dd HH:mm:ss</code>）<br/>
     * 例如：1586581882
     *
     * @param timestamp 时间戳
     * @return 时间字符串
     */
    public static String timestampOfSecond2String(long timestamp){
        Preconditions.checkArgument( timestamp > 0L );

        return timestampOfSecond2String(timestamp,DEFAULT_DATETIME_PATTERN);
    }

    /**
     * 获取当前时间戳（毫秒级别）
     *
     * @return 当前时间戳
     */
    public static long getCurrentTimestampOfMilli(){
        return Timestamp.valueOf(LocalDateTime.now( DEFAULT_CLOCK )).toInstant().toEpochMilli();
    }

    /**
     * 获取当前时间戳（秒级别）
     *
     * @return 当前时间戳
     */
    public static long getCurrentTimestampOfSecond(){
        return Timestamp.valueOf(LocalDateTime.now( DEFAULT_CLOCK )).toInstant().getEpochSecond();
    }

    /**
     * 获取当前日期时间（默认格式 <code>yyyy-MM-dd HH:mm:ss</code>）
     *
     * @return 当前日期时间
     */
    public static String getCurrentDateTime(){
        return getCurrentDateTime(DEFAULT_DATETIME_PATTERN);
    }

    /**
     * 获取当前日期时间
     *
     * @param dateTimePattern 时间格式化字符串
     * @return 当前日期时间
     */
    public static String getCurrentDateTime(String dateTimePattern){

        Preconditions.checkArgument( StringUtils.isNotBlank( dateTimePattern ) );

        return DateTimeFormatter.ofPattern( dateTimePattern )
                .format( LocalDateTime.now( DEFAULT_CLOCK ) );
    }

    /**
     * 获取当前日期（默认格式 <code>yyyy-MM-dd</code>）
     *
     * @return 当前日期
     */
    public static String getCurrentDate(){
        return getCurrentDate( DEFAULT_DATE_PATTERN );
    }

    /**
     * 获取当前日期
     *
     * @param datePattern 时间格式化字符串
     * @return 当前日期
     */
    public static String getCurrentDate(String datePattern){

        Preconditions.checkArgument( StringUtils.isNotBlank( datePattern ) );

        return DateTimeFormatter.ofPattern( datePattern ).format( LocalDateTime.now( DEFAULT_CLOCK ) );
    }

    /**
     * 获取当前时间（默认格式 <code>HH:mm:ss</code>）
     *
     * @return 当前时间
     */
    public static String getCurrentTime(){
        return getCurrentTime( DEFAULT_TIME_PATTERN );
    }

    /**
     * 获取当前时间
     *
     * @param timePattern 时间格式化字符串
     * @return 当前时间
     */
    public static String getCurrentTime(String timePattern){

        Preconditions.checkArgument( StringUtils.isNotBlank( timePattern ) );

        return DateTimeFormatter.ofPattern( timePattern ).format( LocalDateTime.now( DEFAULT_CLOCK ) );
    }

    /**
     * 旧的日期类转成 <code>LocalDateTime</code>
     *
     * @param oldDate 旧的日期类
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime( Date oldDate ){
        Objects.requireNonNull( oldDate, "参数不允许为空" );

        Instant instant = oldDate.toInstant();
        return LocalDateTime.ofInstant( instant, DEFAULT_ZONE );
    }

    /**
     * 旧的日期类转成 <code>LocalDate</code>
     *
     * @param oldDate 旧的日期类
     * @return LocalDate
     */
    public static LocalDate dateToLocalDate( Date oldDate ){
        return dateToLocalDateTime(oldDate).toLocalDate();
    }

    /**
     * 旧的日期类转成 <code>LocalTime</code>
     *
     * @param oldDate 旧的日期类
     * @return LocalTime
     */
    public static LocalTime dateToLocalTime( Date oldDate ){
        return dateToLocalDateTime(oldDate).toLocalTime();
    }

    /**
     * <code>LocalDateTime</code> 转成旧的日期类
     * @param localDateTime <code>LocalDateTime</code>
     * @return Date
     */
    public static Date localDateTimeToDate( LocalDateTime localDateTime){
        Objects.requireNonNull( localDateTime ,"参数不允许为空" );
        Instant instant = localDateTime.atZone( DEFAULT_ZONE ).toInstant();
        return Date.from( instant );
    }

    /**
     * <code>LocalDate</code> 转成旧的日期类
     *
     * @param localDate <code>LocalDate</code>
     * @return Date
     */
    public static Date localDateToDate( LocalDate localDate){
        Objects.requireNonNull( localDate ,"参数不允许为空" );
        return localDateTimeToDate( localDate.atStartOfDay() );
    }

    /**
     * <code>LocalTime</code> 转成旧的日期类
     *
     * @param localTime <code>LocalTime</code>
     * @return Date
     */
    public static Date localTimeToDate( LocalTime localTime){
        Objects.requireNonNull( localTime ,"参数不允许为空" );
        return localDateTimeToDate( LocalDateTime.of( LocalDate.now(),localTime ) );
    }



    public static void main( String[] args ) {




//        System.out.println(Instant.EPOCH);
//        System.out.println(Instant.now().atZone( ZoneId.of( "Asia/Shanghai" ) ));
//        System.out.println(Instant.now().atZone( ZoneId.systemDefault() ));
//        System.out.println(Instant.now().atZone( ZoneId.systemDefault() ).toEpochSecond());
//        System.out.println(TimeUtil.getCurrentTimestamp());
//        System.out.println(Instant.now());
//        System.out.println(Instant.now().atZone( ZoneId.systemDefault() ).toLocalDateTime());
//        System.out.println(Instant.now().toEpochMilli());
//        System.out.println(Instant.now().atZone( ZoneId.systemDefault() ).getOffset());
//        System.out.println(LocalDateTime.now());
//        System.out.println(TimeUtil.timestamp2String( 1586338500000L ));
//        System.out.println(TimeUtil.timestamp2String( Instant.now().toEpochMilli() ));
//        System.out.println(ZoneId.systemDefault());
//        System.out.println( ZoneId.of( "Asia/Shanghai" ) );
//        System.out.println( ZoneId.of( "America/Anchorage" ) );


//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( "EEE MMM dd HH:mm:ss Z yyyy" );
//        System.out.println( LocalDateTime.parse( "Tue Mar 20 20:11:14 CST 2018", dateTimeFormatter ) );
    }
}
