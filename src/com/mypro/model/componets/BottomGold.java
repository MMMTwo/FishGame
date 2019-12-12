package com.mypro.model.componets;

import java.util.HashMap;

import com.mypro.base.graphics.Bitmap;
import com.mypro.base.graphics.Canvas;
import com.mypro.base.graphics.Paint;
import com.mypro.manager.ImageManager;
import com.mypro.model.GamingInfo;
import com.mypro.tools.LogTools;

/**
 * �����ʾ���
 * @author Xiloerfan
 *
 */
public class BottomGold extends Componet{
	private int gold;//���ֵ��¼��ǰ���Ӧ��ʾ�Ľ����
	private int[] num_index = new int[1];//�������ֵ������������һ��Ԫ�ش���÷ֵ����λ������������
	private Bitmap pic;
	private Bitmap[] num;
	private int numShowX,numShowY;//������ʾ��X��Y����
	private int numPicWidth;	 //���ֿ�ȣ��������ֿ����һ����
	public BottomGold(int layoutX,int layoutY){
		try {
			initNum();
			pic = ImageManager.getImageMnagaer().getscaleImageByScreenFromAssets("componet/bottom_gold.png");
			this.setLayout_x(layoutX);
			this.setLayout_y(layoutY);
			numShowX = layoutX+pic.getWidth()/3;
			numShowY = layoutY+pic.getHeight()/4;
			numPicWidth = num[0].getWidth();
			this.getPicMatrix().setTranslate(this.getLayout_x(),this.getLayout_y());
		} catch (Exception e) {
			LogTools.doLogForException(e);
		}
	
	}
	/**
	 * ��ʼ����ʾ������
	 */
	private void initNum(){
		HashMap<String,Bitmap> allNum = ImageManager.getImageMnagaer().getImagesMapByImageConfig(ImageManager.getImageMnagaer().createImageConfigByPlist("componet/num_gold"),ImageManager.getImageMnagaer().scaleNum);
		//Ч��ͼȫ��(num_0.png)
		StringBuffer numFullName = new StringBuffer();
		String numName = "num_";
		num = new Bitmap[10];
		for(int num = 0;num<10&&GamingInfo.getGamingInfo().isGaming();num++){
			numFullName.delete(0, numFullName.length());
			numFullName.append(numName+num+".png");
			this.num[num] =  allNum.get(numFullName.toString());
		}
	}
	@Override
	public void onDraw(Canvas canvas, Paint paint) {
		super.onDraw(canvas, paint);
		if(GamingInfo.getGamingInfo().getScore()!=gold){
			gold = GamingInfo.getGamingInfo().getScore();
			updateNumIndex();
		}
		for(int i=0;i<num_index.length;i++){
			canvas.drawBitmap(num[num_index[i]], numShowX+(i*numPicWidth), numShowY, paint);
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
		return pic;
	}

	public int getPicWidth() {
		// TODO Auto-generated method stub
		return pic.getWidth();
	}

	public int getPicHeight() {
		// TODO Auto-generated method stub
		return pic.getHeight();
	}

}
