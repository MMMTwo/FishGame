package com.mypro.tools;

import com.mypro.model.GamingInfo;

/**
 * ��־��¼��
 * @author Xiloerfan
 *
 */
public class LogTools {
	/**
	 * ���쳣��Ϣ��¼��־
	 * @param e
	 */
	public static void doLogForException(Exception e){
		e.printStackTrace();
		//�����Ϸ�����ˣ���ô�������쳣
		if(!GamingInfo.getGamingInfo().isGaming()){
			return;
		}
		for(StackTraceElement ste:e.getStackTrace()){
//			Log.e(ste.getClassName()+":", e.toString());
//			Log.e("line:", ste.getLineNumber()+"");
//			Log.e("method:", ste.getMethodName());
//			Log.e("file:", ste.getFileName());
		}
	}
}
