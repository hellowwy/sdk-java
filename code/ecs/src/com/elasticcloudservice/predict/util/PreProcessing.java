package com.elasticcloudservice.predict.util;

import com.elasticcloudservice.predict.bean.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author Believening
 * @Description: 用于训练数据的预处理的工具类
 * @Date: Modified in 4:00 PM 3/18/18
 */

public class PreProcessing {

    /**
     * 静态方法 用于训练数据的预提取
     * @param ecsContent 训练数据字符串数组
     * @return dataMap  Map<String,Integer[]> 虚拟机类型 —> 训练集日期段日申请量数组
     *
     */
    public static Map<String,Integer[]> preExtractData(String[] ecsContent){

        Date startDay = getDateOnly(ecsContent[0]);
        Date endDay = getDateOnly(ecsContent[ecsContent.length-1]);
        int MICROSECOND_IN_DAY = 24 * 3600 * 1000;
        int dayCnt = (int)((endDay.getTime() - startDay.getTime()) / MICROSECOND_IN_DAY) + 1;

        Map<Date,Map<String, Integer>> preMap = initDateMapMap(dayCnt,startDay);

        for (String requestString : ecsContent) {
            updateDateMapMap(requestString,preMap);
        }

        return getTypeIntegersMap(Input.getInstance().getVmTypes(), dayCnt, startDay, preMap);
    }

    /**
     * 私有静态方法 初始化中间数据 Map 容器对象
     * @param length 训练集覆盖天数
     * @param startDay 训练集起始记录日期
     * @return dataMaps Map<Date,Map<String, Integer>>
     *                  Date 为键， Map<String, Integer> 虚拟机类型 —> 当日申请量 为值
     */
    private static Map<Date,Map<String, Integer>> initDateMapMap(int length, Date startDay) {

        Map<Date,Map<String, Integer>> dataMaps = new HashMap<>();

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDay);

        for(int i = 0; i< length; i++) {
            Date date = calendar.getTime();
            dataMaps.put(date,initCntMap());
            calendar.add(Calendar.DATE,1);
        }

        return dataMaps;
    }

    /**
     * 私有静态方法 初始化各类型虚拟机日申请量为 0 的 Map对象
     * @return Map<String, Integer> 虚拟机类型 —> 当日申请量
     */
    private static Map<String,Integer> initCntMap() {

        Set<String> typeSet = Input.getInstance().getVmTypes();
        Map<String,Integer> map = new HashMap<>();
        for (String type : typeSet) {
            map.put(type,0);
        }
        return map;
    }

    /**
     * 私有静态方法 获取请求记录的日期 Date 对象
     * @param requestString 虚拟机请求记录
     * @return date Date 该请求发生的日期，时分秒归零
     */
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

    /**
     * 私有静态方法 根据请求更新数据 Map 容器对象的数据
     * @param requestString  String 虚拟机请求记录
     */
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

    /**
     *
     * @param vmTypes Set<String> 需要预测的虚拟机类型集合
     * @return dataMap  Map<String,Integer[]> 虚拟机类型 —> 训练集日期段日申请量数组
     */
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
