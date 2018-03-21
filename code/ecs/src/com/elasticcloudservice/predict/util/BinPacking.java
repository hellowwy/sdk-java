package com.elasticcloudservice.predict.util;

import com.elasticcloudservice.predict.bean.Input;
import com.elasticcloudservice.predict.bean.Server;
import com.elasticcloudservice.predict.bean.VirtualMachine;
import com.filetool.util.FileUtil;

import java.util.*;

/**
 * @Author Believening
 * @Description:
 * @Date: Created in 3:30 PM 3/18/18
 */
public class BinPacking {

    public static String[] bingPacking(String[] predictResult) {

        List<VirtualMachine> pendingList = new ArrayList<>();
        List<Server> serverList = new ArrayList<>();

        initPendingVms(predictResult, pendingList);

        switch (Input.getInstance().getOptimizedObject()) {
            case "CPU":
                pendingList.sort(new VmComparatorByCpu());
                for (VirtualMachine vm : pendingList) {
                    serverList.sort(new ServerComparatorByCpu());
                    packingVm(vm, serverList);
                }
                break;
            case "MEM":
                pendingList.sort(new VmComparatorByMem());
                for (VirtualMachine vm : pendingList) {
                    serverList.sort(new ServerComparatorByMem());
                    packingVm(vm, serverList);
                }
                break;
        }

        List<String> packingResult = new ArrayList<>();
        packingResult.add(String.valueOf(serverList.size()));
        for (int i = 1; i <= serverList.size(); i++) {
            packingResult.add(i + serverList.get(i-1).getVms());
        }

        return packingResult.toArray(new String[packingResult.size()]);

    }

    /**
     * 私有静态方法 初始化待分配虚拟机列表
     * @param predictResult 预测结果
     * @param pendingList 待分配虚拟机列表引用
     */
    private static void initPendingVms(String[] predictResult, List<VirtualMachine> pendingList) {
        int length = predictResult.length;

        for (int i = 1; i < length; i ++) {
            String[] strs = predictResult[i].trim().split(" ");
            VirtualMachine vm = getVmByType(strs[0]);
            int cnt = Integer.valueOf(strs[1]);
            for (int j = 0; j < cnt; j++) {
                pendingList.add(vm);
            }
        }
    }

    /**
     * 私有静态方法 获取特定类型的虚拟机实例化对象
     * @param vmType 虚拟机类型名
     * @return VirtualMachine 虚拟机实例化对象
     */
    private static VirtualMachine getVmByType(String vmType) {
        Map<String, VirtualMachine> vms = Input.getInstance().getVirtualMachines();
        VirtualMachine vm = null;
        try {
            vm = (VirtualMachine)vms.get(vmType).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return vm;
    }

    /**
     * 静态函数方法 用于将虚拟机分配到最合适的服务器上
     * @param vm 需要被分配的虚拟机
     * @param serverList 已被开辟的服务器列表
     */
    private static void packingVm(VirtualMachine vm, List<Server> serverList) {
        if (serverList.size() == 0) {
            Server server = newServer();
            serverList.add(server);
        }

        boolean packed = false;

        for (int i = 0; i < serverList.size(); i++) {
            try {
                Server server = (Server) serverList.get(i).clone();
                if (server.addVm(vm)){
                    serverList.remove(i);
                    serverList.add(server);
                    packed = true;
                    break;
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        if (!packed) {
            Server server = newServer();
            server.addVm(vm);
            serverList.add(server);
        }

    }

    /**
     * 私有静态方法
     * @return 返回一个全新的物理服务器
     */
    private static Server newServer() {
        int cpuCnt = Input.getInstance().getServer().getCpuCnt();
        int memGb = Input.getInstance().getServer().getMemoryGb();
        int hdGb = Input.getInstance().getServer().getHardDiskGb();

        return new Server(cpuCnt, memGb, hdGb);
    }

}
