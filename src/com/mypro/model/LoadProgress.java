package com.mypro.model;

import java.awt.Graphics;

import com.mypro.base.graphics.Bitmap;
import com.mypro.base.graphics.Canvas;
import com.mypro.base.graphics.Paint;
import com.mypro.base.tools.Log;
import com.mypro.manager.ImageManager;
import com.mypro.tools.LogTools;

/**
 * ���ؽ�����
 * @author Xiloer
 *
 */
public class LoadProgress extends DrawableAdapter{
	private static LoadProgress obj;
	/**
	 * ����ͼ
	 */
	private Bitmap progressBg;
	/**
	 * ���ؿ�
	 */
	private Bitmap loadProgress;
	/**
	 * ������
	 */
	private Bitmap load;
	/**
	 * ��ǰ������
	 */
	private Bitmap currentLoad;
	/**
	 * ���ؿ����ڵ�λ��
	 */
	private int progress_x;
	private int progress_y;
	/**
	 * ���������ڵ�λ��
	 */
	private int load_x = 203;
	private int load_y = 46;
	/**
	 * ��ǰ����
	 */
	private int currentProgress;
	private LoadProgress(){
		try{
			while((this.loadProgress = ImageManager.getImageMnagaer().getBitmapByAssets("progress/login_bg.png"))==null);
			while((this.load = ImageManager.getImageMnagaer().getBitmapByAssets("progress/login_jd.png"))==null);
			while((this.progressBg = ImageManager.getImageMnagaer().sacleImageByWidthAndHeight(ImageManager.getImageMnagaer().getBitmapByAssets("progress/progress_bg.jpg"), GamingInfo.getGamingInfo().getScreenWidth(), GamingInfo.getGamingInfo().getScreenHeight()))==null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ���ȿ�
	 * @return
	 */
	public static LoadProgress getLoadProgress(){
		try{
			if(obj==null){
				obj = new LoadProgress();
				obj.setProgress(0);
				obj.progress_x = GamingInfo.getGamingInfo().getScreenWidth()/2-obj.currentLoad.getWidth()/2;
				obj.progress_y = GamingInfo.getGamingInfo().getScreenHeight()/2-obj.currentLoad.getHeight()/2; 					
			}	
		}catch(Exception e){
			Log.e("Loadrogress", e.toString());
		}
		return obj;
	}
	
	@Override
	public void onDraw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(progressBg, 0, 0, paint);
		canvas.drawBitmap(currentLoad,progress_x,progress_y, paint);
		
	}
	/**
	 * ���õ�ǰ����ֵ�����ֵΪ100%
	 * @param current	0-100֮�������
	 */
	public void setProgress(int current){
		try{
			Bitmap currentLoadFlag = loadProgress.copy();
			Graphics g = currentLoadFlag.getImage().getGraphics();
			currentProgress=current;
			//͸������ʼX����
			int startX = load.getWidth()*currentProgress/100;
			Bitmap flag = load.copy();
			for(int r =0;r<flag.getWidth();r++){
				for(int c = 0;c<flag.getHeight();c++){
					if(r>startX){
						flag.setPixel(r, c, 0x00000000);
					}			
				}
			}
			g.drawImage(flag.getImage(), load_x,load_y, null);
			currentLoad = ImageManager.getImageMnagaer().scaleImageByScreen(currentLoadFlag);	
		}catch(Exception e){
			LogTools.doLogForException(e);
			e.printStackTrace();
		}
	}	
	/**
	 * ע����ǰ������
	 * �������һ������²�����ʹ�ã������˳�����ʱ����
	 */
	public void destroy(){
		obj = null;
	}
	@Override
	public Bitmap getCurrentPic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPicWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPicHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
