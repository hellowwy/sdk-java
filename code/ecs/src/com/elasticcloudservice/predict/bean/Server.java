package com.elasticcloudservice.predict.bean;

public class Server {

    private int serverCpuCnt;
    private int serverMemoryGb;
    private int serverHardDiskGb;

    public Server(int serverCpuCnt, int serverMemoryGb, int serverHardDiskGb) {
        this.serverCpuCnt = serverCpuCnt;
        this.serverMemoryGb = serverMemoryGb;
        this.serverHardDiskGb = serverHardDiskGb;
    }

    public int getServerCpuCnt() {
        return serverCpuCnt;
    }

//    public void setServerCpuCnt(int serverCpuCnt) {
//        this.serverCpuCnt = serverCpuCnt;
//    }

    public int getServerMemoryGb() {
        return serverMemoryGb;
    }

//    public void setServerMemoryGb(int serverMemoryGb) {
//        this.serverMemoryGb = serverMemoryGb;
//    }

    public int getServerHardDiskGb() {
        return serverHardDiskGb;
    }

//    public void setServerHardDiskGb(int serverHardDiskGb) {
//        this.serverHardDiskGb = serverHardDiskGb;
//    }
}
