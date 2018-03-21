package com.elasticcloudservice.predict.util;

import com.elasticcloudservice.predict.bean.Server;

import java.util.Comparator;

/**
 * @Author Believening
 * @Description:
 * @Date: Created in 5:29 PM 3/21/18
 */
public class ServerComparatorByCpu implements Comparator<Server> {

    @Override
    public int compare(Server o1, Server o2) {
        int cpuDiff = o1.getCpuCnt() - o2.getCpuCnt();
        if (cpuDiff != 0) {
            return cpuDiff;
        }
        return o1.getMemoryGb() - o2.getMemoryGb();
    }
}
