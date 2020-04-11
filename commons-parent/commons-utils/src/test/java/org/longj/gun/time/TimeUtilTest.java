package org.longj.gun.time;

import org.junit.jupiter.api.Test;
import org.longj.gun.commons.utils.TimeUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

/**
 * 时间工具类测试
 * @author LongJ
 * @date 2020/04/11 16:16
 */
public class TimeUtilTest {

    @Test
    public void timeStrToLocalDateTime() {

        System.out.println( TimeUtil.timeStrToLocalDateTime( "2020-04-11 20:05:40", "yyyy-MM-dd HH:mm:ss" ) );
        System.out.println( TimeUtil.timeStrToLocalDate( "2020-04-11", "yyyy-MM-dd" ) );
        System.out.println( TimeUtil.timeStrToLocalTime( "20:05:40", "HH:mm:ss" ) );

        System.out.println( TimeUtil.timeStrToTimestampOfMilli( "2020-04-11 20:05:40", "yyyy-MM-dd HH:mm:ss" ) );
//        Instant.
//        System.out.println( Instant.parse( "2020-04-11 20:05:40" ) );
    }

    @Test
    public void testDateToLocalDateTime() throws ParseException {
        String dateString = "2020-04-11 20:05:40";
        Date date= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);

        System.out.println(TimeUtil.dateToLocalDateTime( date ));
        System.out.println(TimeUtil.dateToLocalDate( date ));
        System.out.println(TimeUtil.dateToLocalTime( date ));

    }

    @Test
    public void testLocalDateTimeToDate() {

        LocalDateTime localDateTime = LocalDateTime.of( 2020, 4, 11, 20, 5, 40 );
        System.out.println( TimeUtil.localDateTimeToDate( localDateTime ) );

        LocalDate localDate = LocalDate.of( 2020, 4, 11 );
        System.out.println( TimeUtil.localDateToDate( localDate ) );

        LocalTime localTime = LocalTime.of( 20, 5, 40 );
        System.out.println( TimeUtil.localTimeToDate( localTime ) );

    }

    @Test
    public void testGetCurrentTimestamp(){

        System.out.println( TimeUtil.timestampOfMilli2String( 1586581882256L ) );

        System.out.println( "\t\t"+TimeUtil.getCurrentTimestampOfMilli() );

        System.out.println( "\t\t"+TimeUtil.getCurrentTimestampOfSecond() );

        System.out.println( "\t\t"+ Timestamp.valueOf( LocalDateTime.now( TimeUtil.DEFAULT_CLOCK ) ).getTime() );

        System.out.println( "标准：\t"+System.currentTimeMillis() );

        System.out.println("1586589548984");
    }

    @Test
    public void testInstantAndLocalDateTime() {

        System.out.println( LocalDateTime.now());
        System.out.println( Instant.now() );
        System.out.println("---------------------");

        System.out.println( Clock.system( ZoneId.of( "Asia/Shanghai" ) ) );
        System.out.println( LocalDateTime.now( Clock.system( ZoneId.of( "Asia/Shanghai" ) ) ) );
        System.out.println( Instant.now( Clock.system( ZoneId.of( "Asia/Shanghai" ) ) ) );
        System.out.println("---------------------");

        System.out.println( Clock.system( ZoneId.of( "America/Los_Angeles" ) ) );
        System.out.println( LocalDateTime.now( Clock.system( ZoneId.of( "America/Los_Angeles" ) ) ) );
        System.out.println( Instant.now( Clock.system( ZoneId.of( "America/Los_Angeles" ) ) ) );
        System.out.println("---------------------");

        System.out.println( LocalDate.now(TimeUtil.DEFAULT_ZONE).atStartOfDay() );
        System.out.println( LocalDate.now(TimeUtil.DEFAULT_ZONE).atStartOfDay(TimeUtil.DEFAULT_ZONE) );

    }



}
