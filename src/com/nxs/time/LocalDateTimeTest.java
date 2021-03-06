package com.nxs.time;


import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @author 57014
 */
public class LocalDateTimeTest {

    /**
     * LocalDate
     */
    @Test
    public void test1(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime time = LocalDateTime.of(2017, 11, 11, 11, 11);
        System.out.println(time);

        LocalDateTime year = localDateTime.plusYears(2).minusMonths(2);
        System.out.println(year);
        System.out.println(year.getYear());
        System.out.println(year.getDayOfMonth());
    }

    /**
     * Instant
     */
    @Test
    public void test2(){
        Instant instant = Instant.now();//默认UTC时区
        System.out.println(instant);
        OffsetDateTime dateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(dateTime);
        System.out.println(instant.toEpochMilli());
        System.out.println(dateTime.toEpochSecond());
    }

    @Test
    public void test3(){
        Instant instant1 = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant instant2 = Instant.now();
        Duration between = Duration.between(instant1, instant2);
        System.out.println(between);
        System.out.println(between.toMillis());

        LocalTime localTime1 = LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime localTime2 = LocalTime.now();
        System.out.println(Duration.between(localTime1,localTime2).toMillis());
    }

    @Test
    public void test4() {
        LocalDate localDate1 = LocalDate.of(2013, 1, 1);
        LocalDate localDate2 = LocalDate.now();
        Period period = Period.between(localDate1, localDate2);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(period.getUnits());
        System.out.println(period.getChronology());

    }

    /**
     * 时间矫正器
     */
    @Test
    public void test5() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime dateTime = localDateTime.withDayOfMonth(10);
        System.out.println(dateTime);

        LocalDateTime sunday = localDateTime.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(sunday);

        LocalDateTime time = localDateTime.with(l -> {
            LocalDateTime ldt = (LocalDateTime) l;
            DayOfWeek dayOfWeek = ldt.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt.plusDays(2);
            } else {
                return ldt.plusDays(1);
            }
        });
        System.out.println(time);

    }

    /**
     * 格式化日期
     */
    @Test
    public void test6(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(dateTimeFormatter);
        System.out.println(format);

        System.out.println("-----------------------");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String s = formatter.format(now);
        System.out.println(s);
        LocalDateTime parse = now.parse(s, formatter);
        System.out.println(parse);
        TemporalAccessor newDate = formatter.parse(s);
        System.out.println(newDate);
    }

    /**
     * 时区
     */
    @Test
    public void test7() {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);
    }

    @Test
    public void test8() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Malta"));
        System.out.println(now);
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);
    }
}
