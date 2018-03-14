package com.elasticcloudservice.predict.bean;

import java.util.Date;

public class Request {
    private String vmId;
    private VirtualMachine vmType;
    private Date buildTime;

    public Request(String vmId, VirtualMachine vmType, Date buildTime) {
        this.vmId = vmId;
        this.vmType = vmType;
        this.buildTime = buildTime;
    }

    public String getVmId() {
        return vmId;
    }

    public VirtualMachine getVmType() {
        return vmType;
    }

    public Date getBuildTime() {
        return buildTime;
    }
}
