package com.elasticcloudservice.predict.util;

import com.elasticcloudservice.predict.bean.VirtualMachine;

import java.util.Comparator;

/**
 * @Author Believening
 * @Description: 优化目标为 MEM 时，对待分配虚拟机进行排序用。
 *               Mem 多则大， Cpu 小则优先
 *               CMR 小则对服务器核数占用少，有望进一步提升服务器 CMP，CMR 小则优先
 *               因为使用 sort() 方法逆序排列，故返回值符号相反
 * @Date: Created in 11:09 PM 3/20/18
 */
public class VmComparatorByMem implements Comparator<VirtualMachine> {
    @Override
    public int compare(VirtualMachine o1, VirtualMachine o2) {
        int memDiff = o2.getMemoryMb() - o1.getMemoryMb();
        if (memDiff != 0) {
            return memDiff;
        }
        return o1.getCpuCnt() - o2.getCpuCnt();

    }
}
