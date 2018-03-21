package com.elasticcloudservice.predict.bean;

import java.util.Objects;

public class VirtualMachine implements Cloneable {
    private String type;
    private int cpuCnt;
    private int memoryMb;
//    private float cmr;

    public VirtualMachine(String type, int cpuCnt, int memoryMb) {
        this.type = type;
        this.cpuCnt = cpuCnt;
        this.memoryMb = memoryMb;
//        updateCMR();
    }

    public String getType() {
        return type;
    }

//    public void setType(String type) {
//        this.type = type;
//    }

    public int getCpuCnt() {
        return cpuCnt;
    }

//    public void setCpuCnt(int cpuCnt) {
//        this.cpuCnt = cpuCnt;
//    }

    public int getMemoryMb() {
        return memoryMb;
    }

//    public void setMemoryMb(int memoryMb) {
//        this.memoryMb = memoryMb;
//    }

//    public float getCmr() {
//        return cmr;
//    }

//    private void updateCMR(){
//        this.cmr = (float)this.cpuCnt * 1024 / this.memoryMb;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VirtualMachine that = (VirtualMachine) o;
        return cpuCnt == that.cpuCnt &&
                memoryMb == that.memoryMb &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, cpuCnt, memoryMb);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
