package com.elasticcloudservice.predict;

/**
 * ʱ��������Ϲ�����
 * Map�����String�Ѿ�ɸѡ������Inputָ���Ĺ�񣬲�����������
 * */
class TimeSeq {
	
	/**
	 * @param type ��������
	 * @param trainData �����ѵ����
	 * @param len ҪԤ�����������������ʱ�伯�ĳ��ȣ�ʱ�伯������ѵ��ʱ�伯
	 * @return Ԥ��ʱ�伯���������������
	 * */
	public static int predict (String type, Integer[] trainData, int length) {
		
		/**
		 * Ԥ�Ⲣ�õ�ƽ�����
		 * */
		int step = 3;//ʱ�����в���
		int trainLen = trainData.length;//ѵ��������
		double err = 0;//�����
		double[] predict = new double[length+1];//Ԥ��ֵ����
		double predictSum = 0;//Ԥ��ֵ�ĺͣ������������
		double actualSum = 0;//ʵ��ֵ�ĺͣ������������
		for (int i = 0; i < trainLen - step; i++) {
			double sum = 0;
			for (int j = i; j < i + step; j++) {
				sum += trainData[j] * (j - i + 1);//��Ȩ
			}
			predict[i + step] = sum / ((1+i) * i / 2);
			actualSum += trainData[i+step];
			predictSum += predict[i+step];
		}
		err = (predictSum - actualSum) / actualSum; 
		/**
		 * ����Ԥ�⼯
		 * */
		double res = 0;
		for (int i = trainLen - step; i < trainLen + length - step; i++) {
			double sum = 0;
			for (int j = i; j < i+step; j++) {
				sum += trainData[j] * (j - i + 1);
			}
			predict[i + step] = sum / ((i + 1) * i /2) / (1 + err);//����ƽ�����
			res += predict[i+step];
		}
		/**
		 * ��Ԥ�⼯ȡ��
		 * */
		return Math.round(res);
	}
	
	public static predictAll (Map<String,Integer[]> map, Date startDate, Date endDate) {
		
	}
	
}