package com.elasticcloudservice.predict.util;

import com.elasticcloudservice.predict.bean.Input;
import com.elasticcloudservice.predict.bean.Server;
import com.elasticcloudservice.predict.bean.VirtualMachine;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Believening
 * @Description:
 * @Date: Created in 3:30 PM 3/18/18
 */
public class BinPacking {

    public static void bingPacking(String[] predictResult) {

        Map<VirtualMachine,Integer> pendingPackingVm = new HashMap<>();


        int length = predictResult.length;


        for (int i = 1; i < length; i ++) {
            String[] strs = predictResult[i].trim().split(" ");
            VirtualMachine vm = new VirtualMachine("s",2,2);
        }

    }

    /**
     * 私有静态方法
     * @return 返回一个全新的物理服务器
     */
    private static Server newServer() {
        int cpuCnt = Input.getInstance().getServer().getServerCpuCnt();
        int memGb = Input.getInstance().getServer().getServerMemoryGb();
        int hdGb = Input.getInstance().getServer().getServerHardDiskGb();

        return new Server(cpuCnt, memGb, hdGb);
    }
}
