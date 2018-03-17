package com.elasticcloudservice.predict.util;

import com.elasticcloudservice.predict.bean.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Extract {

    private static int MICROSECOND_IN_DAY = 24 * 3600 * 1000;

    public static void preExtractData(String[] ecsContent){

        Date startDay = getDateOnly(ecsContent[0]);
        Date endDay = getDateOnly(ecsContent[ecsContent.length-1]);
        int dayCnt = (int)((endDay.getTime() - startDay.getTime()) / MICROSECOND_IN_DAY) + 1;

        Map<Date,Map<String, Integer>> preMap = initDateMapMap(dayCnt,startDay);

        for (String requestString : ecsContent) {
            updateDateMapMap(requestString,preMap);
        }

        Map<String,Integer[]> dataMap = getTypeIntegersMap(Input.getInstance().getVmTypes(), dayCnt, startDay, preMap);

    }

    private static Map<Date,Map<String, Integer>> initDateMapMap(int length, Date startDay) {

        Map<Date,Map<String, Integer>> dataMaps = new TreeMap<>();

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDay);

        for(int i = 0; i< length; i++) {
            Date date = calendar.getTime();
            dataMaps.put(date,initCntMap());
            calendar.add(Calendar.DATE,1);
        }

        return dataMaps;
    }

    private static Map<String,Integer> initCntMap() {

        Set<String> typeSet = Input.getInstance().getVmTypes();
        Map<String,Integer> map = new HashMap<>();
        for (String type : typeSet) {
            map.put(type,0);
        }
        return map;
    }

    private static Date getDateOnly(String requestString) {
        String[] info = requestString.split("\t");
        String dateStr = info[2].substring(0,10);
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static void updateDateMapMap(String requestString, Map<Date,Map<String,Integer>> dateMapMap) {

        String[] info = requestString.split("\t");

        String vmType = info[1];
        Set<String> vmTypes = Input.getInstance().getVmTypes();

        Date date = getDateOnly(requestString);

        if (vmTypes.contains(vmType)) {

            Map<String, Integer> cntMap;
            cntMap = dateMapMap.get(date);
            cntMap.put(vmType,cntMap.get(vmType) + 1);
            dateMapMap.put(date,cntMap);
        }

    }

    private static Map<String,Integer[]> getTypeIntegersMap(Set<String> vmTypes, int length, Date startDay,
                                                            Map<Date,Map<String, Integer>> dateMapMap) {

        Map<String,Integer[]> map = new HashMap<>();

        Integer[][] integers = new Integer[vmTypes.size()][length];
        for (int i = 0; i < vmTypes.size(); i++) {
            for (int j = 0; j < length; j++) {
                integers[i][j] = 0;
            }
        }

        int position  = 0;
        for (String type : vmTypes) {
            map.put(type,integers[position++]);
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDay);

        for (int i = 0; i < length; i++) {
            Date date = calendar.getTime();
            Map<String, Integer> dayMap = dateMapMap.get(date);
            for (Map.Entry<String, Integer> entry : dayMap.entrySet()) {
                String vmType = entry.getKey();
                Integer[] ints = map.get(vmType);
                ints[i] = entry.getValue();
                map.put(vmType, ints);
            }
            calendar.add(Calendar.DATE,1);
        }

        return map;
    }


}
