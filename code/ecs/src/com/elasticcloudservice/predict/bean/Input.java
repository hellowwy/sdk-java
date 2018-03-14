package com.elasticcloudservice.predict.bean;

import java.util.Date;
import java.util.Map;

public class Input {

    private static Input input;

    private Server server;

    private int virtualMachineCnt;
    private Map<String, VirtualMachine> virtualMachines;

    private String optimizedObject;

    private Date startDate;
    private Date endDate;

    private Input() {
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public int getVirtualMachineCnt() {
        return virtualMachineCnt;
    }

    public void setVirtualMachineCnt(int virtualMachineCnt) {
        this.virtualMachineCnt = virtualMachineCnt;
    }

    public Map<String, VirtualMachine> getVirtualMachines() {
        return virtualMachines;
    }

    public void setVirtualMachines(Map<String, VirtualMachine> virtualMachines) {
        this.virtualMachines = virtualMachines;
    }

    public String getOptimizedObject() {
        return optimizedObject;
    }

    public void setOptimizedObject(String optimizedObject) {
        this.optimizedObject = optimizedObject;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public static Input getInstance(){
        if (input == null) {
            input = new Input();
        }
        return input;
    }

}
