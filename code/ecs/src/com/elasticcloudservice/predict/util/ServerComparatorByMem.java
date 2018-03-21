package com.elasticcloudservice.predict.util;

import com.elasticcloudservice.predict.bean.Server;

import java.util.Comparator;

/**
 * @Author Believening
 * @Description:
 * @Date: Created in 5:33 PM 3/21/18
 */
public class ServerComparatorByMem implements Comparator<Server> {
    @Override
    public int compare(Server o1, Server o2) {
        int memDiff = o1.getMemoryGb() - o2.getMemoryGb();
        if (memDiff != 0) {
            return memDiff;
        }
        return o1.getCpuCnt() - o2.getCpuCnt();
    }
}
