package com.mypro.manager;

import java.util.ArrayList;

import com.mypro.constant.Constant;
import com.mypro.model.GamingInfo;
import com.mypro.model.componets.Bottom;
import com.mypro.model.componets.BottomGold;
import com.mypro.model.componets.BottomTime;
import com.mypro.model.componets.ButtonAdapter;
import com.mypro.model.componets.Cannon;
import com.mypro.model.componets.DownCannonButtonListener;
import com.mypro.model.componets.UpCannonButtonListener;
import com.mypro.model.interfaces.Button;
import com.mypro.tools.LogTools;

/**
 * ���ֹ�����
 * @author Xiloerfan
 *
 */
public class LayoutManager {
	private static LayoutManager manager;
	/**
	 *	���а�ť
	 */
	private ArrayList<LayoutInfo> allButton = new ArrayList<LayoutInfo>();
	/**
	 * �Ʒְ壬����Ǹ�ScoreManagerʹ�õ�
	 */
	private BottomGold bottomGold;
	/**
	 * ��ʱ�壬����Ǹ����ӽ��ʹ�õ�
	 */
	private BottomTime bottomTime;
	/**
	 * ��ǰʹ�õĴ���
	 */
	private Cannon cannon;
	public void initCannon(Cannon cannon){
		try{
			this.cannon = cannon;
			if(GamingInfo.getGamingInfo().getSurface()!=null){
				GamingInfo.getGamingInfo().getSurface().putDrawablePic(Constant.GUN_LAYER, cannon);
			}		
		}catch(Exception e){
			LogTools.doLogForException(e);
		}
	}
	public void updateCannon(final Cannon newCannon){
		new Thread(new Runnable() {
			public void run() {
				try {
					float scale;
					float pos;
					for(int i=5;i>=1;i--){
						scale = i*0.2f;
						pos = (5-i)*0.2f;
						cannon.getPicMatrix().setTranslate(cannon.getX()+cannon.getPicWidth()/2*pos, cannon.getY()+cannon.getPicHeight()/2*pos);
						cannon.getPicMatrix().preScale(scale, scale);
						Thread.sleep(60);
					}
					GamingInfo.getGamingInfo().getSurface().removeDrawablePic(Constant.GUN_LAYER, cannon);
					cannon = newCannon;
					GamingInfo.getGamingInfo().getSurface().putDrawablePic(Constant.GUN_LAYER, cannon);
					for(int i=1;i<=5;i++){
						scale = i*0.2f;
						pos = (5-i)*0.2f;
						cannon.getPicMatrix().setTranslate(cannon.getX()+cannon.getPicWidth()/2*pos, cannon.getY()+cannon.getPicHeight()/2*pos);
						cannon.getPicMatrix().preScale(scale, scale);
						Thread.sleep(60);
					}
				} catch (Exception e) {
					LogTools.doLogForException(e);
				}
			}
		}).start();
		
	}
	/**
	 * ��ȡ�Ʒְ�
	 * @return
	 */
	public BottomGold getBottomGold() {
		return bottomGold;
	}
	/**
	 * ��ȡ��ʱ��
	 * @return
	 */
	public BottomTime getBottomTime() {
		return bottomTime;
	}
	/**
	 * ���һ����ť
	 * @param button
	 */
	public void addButton(ButtonAdapter button,float x,float y){
		button.getPicMatrix().setTranslate(x, y);
		button.setLayout_x(x);
		button.setLayout_y(y);
		allButton.add(new LayoutInfo(button,x,y));
		
	}
	/**
	 *  ��Ӧ�����¼�
	 * @param x	������x��
	 * @param y ������y��
	 * @return
	 */
	public boolean onClick(float x,float y){
		for(LayoutInfo button:allButton){
			if(x>=button.getLayout_x()&&x<=button.getLayout_x()+button.getDrawable().getPicWidth()
					&&y>=button.getLayout_y()&&y<=button.getLayout_y()+button.getDrawable().getPicHeight()){
				/**
				 * ��������ǰ�ť��͸��λ�ã�����ԡ�����Ϊ����˰�ť
				 */
				if(button.getDrawable().getCurrentPic().getPixel((int)(x-button.getLayout_x()), (int)(y-button.getLayout_y()))!=0x00000000){
					((Button)button.getDrawable()).onClick(); 
					return true;
				}				
			}
		}
		return false;
	}
	
	private LayoutManager(){}
	/**
	 * ��ȡһ�����ֹ�����,����
	 * @return
	 */
	public static LayoutManager getLayoutManager(){
		if(manager == null){
			manager = new LayoutManager();
		}
		return manager;
	}
	/**
	 * ��ʼ������
	 * ������ʼ���ı���ڵİ�ť���Ƿְ壬��ͣ��ť��
	 */
	public void init(){
		try {			
			//��ʼ�����ڵ���
			Bottom bottom = new Bottom();
			GamingInfo.getGamingInfo().setCannonLayoutX(bottom.getLayout_x()+bottom.getPicWidth()/2);
			GamingInfo.getGamingInfo().setCannonLayoutY(bottom.getLayout_y()+bottom.getPicHeight()/2);
			GamingInfo.getGamingInfo().getSurface().putDrawablePic(Constant.COMPONENTS_LAYER, bottom);		
			//��ʼ����ߴ��������İ�ť
			ButtonAdapter upCannon = new ButtonAdapter(ImageManager.getImageMnagaer().getscaleImageByScreenFromAssets("cannon/add.png"),new UpCannonButtonListener());
			addButton(upCannon,bottom.getLayout_x()+bottom.getPicWidth(),GamingInfo.getGamingInfo().getScreenHeight()-upCannon.getPicHeight());
			GamingInfo.getGamingInfo().getSurface().putDrawablePic(Constant.COMPONENTS_LAYER, upCannon);	
			//��ʼ�����ʹ��������İ�ť
			ButtonAdapter downCannon = new ButtonAdapter(ImageManager.getImageMnagaer().getscaleImageByScreenFromAssets("cannon/sub.png"),new DownCannonButtonListener());
			addButton(downCannon,bottom.getLayout_x()-downCannon.getPicWidth(),GamingInfo.getGamingInfo().getScreenHeight()-downCannon.getPicHeight());
			GamingInfo.getGamingInfo().getSurface().putDrawablePic(Constant.COMPONENTS_LAYER, downCannon);	
			//��ʼ���Ʒְ�
			//�Ʒְ�����������������ť�ұ���Ļ���1/30,1/3��ť�߶ȵ�λ��
			bottomGold = new BottomGold((int)(upCannon.getLayout_x()+upCannon.getPicWidth()+GamingInfo.getGamingInfo().getScreenWidth()/30),(int)upCannon.getLayout_y()+upCannon.getPicHeight()/3);
			GamingInfo.getGamingInfo().getSurface().putDrawablePic(Constant.COMPONENTS_LAYER, bottomGold);	
			//��ʼ���Ʒְ�
			//�Ʒְ��ڽ��ʹ���������ť��߱���Ļ���1/30��������,1/3��ť�߶ȵ�λ��
			bottomTime = new BottomTime();
			bottomTime.setPosition((int)(downCannon.getLayout_x()-GamingInfo.getGamingInfo().getScreenWidth()/30-bottomTime.getPicWidth()),(int)downCannon.getLayout_y()+downCannon.getPicHeight()/3);
			GamingInfo.getGamingInfo().getSurface().putDrawablePic(Constant.COMPONENTS_LAYER, bottomTime);	
		} catch (Exception e) {
			LogTools.doLogForException(e);
		}
	}
}
