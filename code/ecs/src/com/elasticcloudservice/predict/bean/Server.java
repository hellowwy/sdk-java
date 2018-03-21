package com.elasticcloudservice.predict.bean;

import java.util.HashMap;
import java.util.Map;

public class Server implements Cloneable {

    private int cpuCnt;
    private int memoryGb;
    private int hardDiskGb;
    private Map<String, Integer> vmMap = new HashMap<>();
//    private float cmr;

    public Server(int serverCpuCnt, int serverMemoryGb, int serverHardDiskGb) {
        this.cpuCnt = serverCpuCnt;
        this.memoryGb = serverMemoryGb;
        this.hardDiskGb = serverHardDiskGb;
//        updateCMR();

    }

    public int getCpuCnt() {
        return cpuCnt;
    }

//    public void setCpuCnt(int cpuCnt) {
//        this.cpuCnt = cpuCnt;
//        updateCMR();
//    }

    public int getMemoryGb() {
        return memoryGb;
    }

//    public void setMemoryGb(int memoryGb) {
//        this.memoryGb = memoryGb;
//        updateCMR();
//    }

    public int getHardDiskGb() {
        return hardDiskGb;
    }

//    public void setHardDiskGb(int serverHardDiskGb) {
//        this.hardDiskGb = serverHardDiskGb;
//    }

    public boolean addVm(VirtualMachine vm) {
        if ( this.cpuCnt >= vm.getCpuCnt() && (this.memoryGb * 1024) >= vm.getMemoryMb() ) {
            String vmType = vm.getType();
            if (vmMap.containsKey(vmType)) {
                int cnt = vmMap.get(vmType) + 1;
                vmMap.put(vmType,cnt);
            } else {
                vmMap.put(vmType, 1);
            }
//            this.vmList.add(vmType);
            this.cpuCnt -= vm.getCpuCnt();
            this.memoryGb -= (vm.getMemoryMb() / 1024);
//            updateCMR();
            return true;
        }
        return false;
    }

    public String getVms() {
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<String, Integer> entry : vmMap.entrySet()) {
            buffer.append(" ").append(entry.getKey()).append(" ").append(entry.getValue());
        }

//        return vmList.toArray(new String[vmList.size()]);
        return buffer.toString();
    }

//    public float getCmr() {
//        return cmr;
//    }

//    private void updateCMR(){
//        this.cmr = (float)this.cpuCnt / this.memoryGb;
//    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Server newServer = (Server) super.clone();
        newServer.vmMap = new HashMap<>(vmMap);
//        newServer.vmList = new ArrayList<>(vmList);
        return newServer;
    }
}
