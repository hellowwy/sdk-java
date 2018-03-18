package com;

import com.elasticcloudservice.predict.util.PreProcessing;
import com.elasticcloudservice.predict.bean.Input;
import com.filetool.util.FileUtil;

public class Test {
    public static void main(String[] args) throws Exception {

        String ecsFilepath = "/home/believening/IdeaProjects/sdk-java/bin/data/TrainData.txt";
        String inputFilepath = "/home/believening/IdeaProjects/sdk-java/bin/data/input.txt";
        String[] ecsContent = FileUtil.read(ecsFilepath,null);
        String[] inputContent = FileUtil.read(inputFilepath,null);

        Input input = Input.getInstance(inputContent);
        PreProcessing.preExtractData(ecsContent);


    }

}
