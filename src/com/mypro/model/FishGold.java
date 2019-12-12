package com.mypro.model;

import com.mypro.base.graphics.Bitmap;
import com.mypro.base.graphics.Canvas;
import com.mypro.base.graphics.Paint;
import com.mypro.tools.LogTools;

/**
 * ��ʾ��׽������õĽ������
 * @author Xiloerfan
 *
 */
public class FishGold extends DrawableAdapter{
	private int gold;//���ֵ��¼��ǰ���Ӧ��ʾ�Ľ����
	private int[] num_index = new int[1];//�������ֵ������������һ��Ԫ�ش���÷ֵ����λ������������
	private Bitmap[] num;
	private int layout_Y;				//Y�������ƶ���������
	private float numShowX,numShowY;//������ʾ��X��Y����
	private int numPicWidth;	 //���ֿ�ȣ��������ֿ����һ����
	public FishGold(Bitmap[] num,int gold,float numShowX,float numShowY){
		try {
			this.numShowX = numShowX;
			this.numShowY = numShowY;
			this.num = num;
			this.gold = gold;
			numPicWidth = num[0].getWidth();
			updateNumIndex();
		} catch (Exception e) {
			e.printStackTrace();
			LogTools.doLogForException(e);
		}
	
	}
	@Override
	public void onDraw(Canvas canvas, Paint paint) {
		try {
			canvas.drawBitmap(num[10], numShowX, numShowY-layout_Y, paint);
			for(int i=1;i<=num_index.length;i++){
				canvas.drawBitmap(num[num_index[i-1]], numShowX+(i*numPicWidth), numShowY-layout_Y, paint);
			}
			layout_Y++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * ������������
	 */
	private void updateNumIndex(){
		String num = gold+"";
		num_index = new int[num.length()];
		int index = 0;
		for(char n:num.toCharArray()){
			num_index[index] = n-48;
			index++;
		}		
	}	
	
	public Bitmap getCurrentPic() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPicWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPicHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
