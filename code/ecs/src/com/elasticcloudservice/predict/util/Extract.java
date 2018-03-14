package com.elasticcloudservice.predict.util;

import com.elasticcloudservice.predict.bean.Input;
import com.elasticcloudservice.predict.bean.Request;
import com.elasticcloudservice.predict.bean.Server;
import com.elasticcloudservice.predict.bean.VirtualMachine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Extract {

    public static Input getInput(String[] inputContent) {
        Input input = Input.getInstance();


        input.setServer(getServerByString(inputContent[0]));

        int vmCnt = Integer.valueOf(inputContent[2]);
        input.setVirtualMachineCnt(vmCnt);

        Map<String, VirtualMachine> virtualMachines = new HashMap<>();
        if (vmCnt != 0) {
            for (int i = 0; i < vmCnt; i++) {
                String[] info = inputContent[i+3].split(" ");
                virtualMachines.put(info[0],new VirtualMachine(info[0],
                        Integer.valueOf(info[1]), Integer.valueOf(info[2])));
            }
        }
        input.setVirtualMachines(virtualMachines);

        input.setOptimizedObject(inputContent[vmCnt+4]);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            input.setStartDate(simpleDateFormat.parse(inputContent[vmCnt+6]));
            input.setEndDate(simpleDateFormat.parse(inputContent[vmCnt+7]));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return input;
    }

    private static Server getServerByString(String serverInfo){
        String[] info = serverInfo.split(" ");
        return new Server(Integer.valueOf(info[0]),
                Integer.valueOf(info[1]), Integer.valueOf(info[2]));

    }

    public static Request[] getRequests(String[] ecsContent){
       int cnt = ecsContent.length;
       List<Request> requestList = new ArrayList<>();
       for (int i = 0; i < cnt; i++) {
           Request temp = getRequestByString(ecsContent[i]);
           if (temp != null) {
               requestList.add(temp);
           }
       }

       return requestList.toArray(new Request[requestList.size()]);
    }

    private static Request getRequestByString(String requestInfo) {

        String[] info = requestInfo.split("\t");
        Input input = Input.getInstance();

//        String vmType = info[1];
        VirtualMachine virtualMachine = input.getVirtualMachines().get(info[1]);
        if (virtualMachine == null) {
            return null;
        }

        Date buildTime = null;
        try {
            buildTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(info[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        String vmId = info[0];
        return new Request(info[0], virtualMachine, buildTime);
    }

}
