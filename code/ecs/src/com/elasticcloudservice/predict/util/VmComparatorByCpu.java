package com.elasticcloudservice.predict.util;

import com.elasticcloudservice.predict.bean.VirtualMachine;

import java.util.Comparator;

/**
 * @Author Believening
 * @Description: 优化目标为 CPU 时，对待分配虚拟机进行排序用。
 *               Cpu 多则大， Mem 小则优先
 *               CMR 大则对服务器内存占用少，有望进一步降低服务器 CMP，CMR 大则大
 *               因为使用 sort() 方法逆序排列，故返回值符号相反
 * @Date: Created in 10:16 PM 3/20/18
 */
public class VmComparatorByCpu implements Comparator<VirtualMachine> {

//    @Override
//    public int compare(VirtualMachine o1, VirtualMachine o2) {
//        int cpuDiff = o1.getCpuCnt() - o2.getCpuCnt();
//        if (cpuDiff != 0) {
//            return cpuDiff;
//        }
//        int cmrDiff = Float.compare(o1.getCmr(),o2.getCmr());
//        if (cmrDiff != 0) {
//            return cmrDiff;
//        }
//        return o1.getMemoryMb() - o2.getMemoryMb();
//
//    }
    @Override
    public int compare(VirtualMachine o1, VirtualMachine o2) {
        int cpuDiff = o2.getCpuCnt() - o1.getCpuCnt();
        if (cpuDiff != 0) {
            return cpuDiff;
        }
        return o1.getMemoryMb() - o2.getMemoryMb();
    }
}
