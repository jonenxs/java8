package com.nxs.time;


import org.junit.Test;

import java.time.*;

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
}
