package com.elasticcloudservice.predict.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Input {

    private static Input input;

    private Server server;

    private int VmTypeCnt;
    private Map<String, VirtualMachine> virtualMachines;

    private String optimizedObject;

    private Date startDate;
    private Date endDate;

    private Input() {
    }

    public Server getServer() {
        return server;
    }

    private void setServer(Server server) {
        this.server = server;
    }

    public int getVmTypeCnt() {
        return VmTypeCnt;
    }

    private void setVmTypeCnt(int vmTypeCnt) {
        this.VmTypeCnt = vmTypeCnt;
    }

    public Map<String, VirtualMachine> getVirtualMachines() {
        return virtualMachines;
    }

    private void setVirtualMachines(Map<String, VirtualMachine> virtualMachines) {
        this.virtualMachines = virtualMachines;
    }

    public String getOptimizedObject() {
        return optimizedObject;
    }

    private void setOptimizedObject(String optimizedObject) {
        this.optimizedObject = optimizedObject;
    }

    public Date getStartDate() {
        return startDate;
    }

    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    private void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<String> getVmTypes() {
        return this.getVirtualMachines().keySet();
    }

    public static Input getInstance(){
        if (input == null) {
            input = new Input();
        }
        return input;
    }

    public static Input getInstance(String[] inputContent) {

        input = getInstance();

        input.setServer(getServerByString(inputContent[0]));

        int vmCnt = Integer.valueOf(inputContent[2]);
        input.setVmTypeCnt(vmCnt);

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

}
