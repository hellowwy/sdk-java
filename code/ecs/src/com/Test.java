package com;

import com.elasticcloudservice.predict.bean.Request;
import com.elasticcloudservice.predict.bean.VirtualMachine;
import com.elasticcloudservice.predict.util.Extract;
import com.elasticcloudservice.predict.bean.Input;
import com.filetool.util.FileUtil;

import java.io.File;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws Exception {

//        String ecsFilepath = "/home/believening/IdeaProjects/sdk-java/bin/data/TrainData.txt";
//        String inputFilepath = "/home/believening/IdeaProjects/sdk-java/bin/data/input.txt";
//        String[] ecsContent = FileUtil.read(ecsFilepath,null);
//        String[] inputContent = FileUtil.read(inputFilepath,null);
//
//        Input input = Extract.getInput(inputContent);
//        Request[] requests = Extract.getRequests(ecsContent);

//        File.separator;
//        String filePath = File.separator + "home" + File.separator +
//                "believening" + File.separator + "Desktop" + File.separator +
//                "data" + File.separator + "out.csv";
//        File file = new File(filePath);
//        if(!file.getParentFile().exists()){
//            file.getParentFile().mkdirs();
//        }
//        if(!file.exists()){
//            file.createNewFile();
//        } else {
//            file.delete();
//            file.createNewFile();
//        }
//
        System.out.println(Math.round(3.5) + " --- " + Math.round(3.4));
        System.out.println(Math.pow(10,2) );

    }
}
