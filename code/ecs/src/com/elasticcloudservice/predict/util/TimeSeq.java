package com.elasticcloudservice.predict;

/**
 * 时间序列拟合工具类
 * Map里面的String已经筛选过，是Input指定的规格，并且升序排序
 * */
class TimeSeq {
	
	/**
	 * @param type 虚拟机规格
	 * @param trainData 虚拟机训练集
	 * @param len 要预测虚拟机日申请量的时间集的长度，时间集紧跟着训练时间集
	 * @return 预测时间集的虚拟机总申请量
	 * */
	public static int predict (String type, Integer[] trainData, int length) {
		
		/**
		 * 预测并得到平均误差
		 * */
		int step = 3;//时间序列步长
		int trainLen = trainData.length;//训练集长度
		double err = 0;//总误差
		double[] predict = new double[length+1];//预测值数组
		double predictSum = 0;//预测值的和，用来算总误差
		double actualSum = 0;//实际值的和，用来算总误差
		for (int i = 0; i < trainLen - step; i++) {
			double sum = 0;
			for (int j = i; j < i + step; j++) {
				sum += trainData[j] * (j - i + 1);//加权
			}
			predict[i + step] = sum / ((1+i) * i / 2);
			actualSum += trainData[i+step];
			predictSum += predict[i+step];
		}
		err = (predictSum - actualSum) / actualSum; 
		/**
		 * 计算预测集
		 * */
		double res = 0;
		for (int i = trainLen - step; i < trainLen + length - step; i++) {
			double sum = 0;
			for (int j = i; j < i+step; j++) {
				sum += trainData[j] * (j - i + 1);
			}
			predict[i + step] = sum / ((i + 1) * i /2) / (1 + err);//除以平均误差
			res += predict[i+step];
		}
		/**
		 * 让预测集取整
		 * */
		return Math.round(res);
	}
	
	public static predictAll (Map<String,Integer[]> map, Date startDate, Date endDate) {
		
	}
	
}