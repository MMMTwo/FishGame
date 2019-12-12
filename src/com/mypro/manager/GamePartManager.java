package com.mypro.manager;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import com.mypro.base.graphics.Bitmap;
import com.mypro.base.tools.Log;
import com.mypro.constant.Constant;
import com.mypro.model.BackGround;
import com.mypro.model.GamingInfo;
import com.mypro.model.componets.BottomTime;
import com.mypro.tools.LogTools;

/**
 * ��Ϸ�ؿ�������
 * @author Xiloerfan
 *
 */
public class GamePartManager {
	/**
	 * ����ģʽʹ��
	 */
	private	static GamePartManager manager;
	/**
	 * ��������йؿ�
	 * key		Ϊ�ؿ���
	 * value	Ϊ�ؿ�����
	 */
	private ArrayList<GamePartInfo> games = new ArrayList<GamePartInfo>();
	/**
	 * ��ǰ���еĹؿ�
	 */
	private GamePartInfo part;
	/**
	 * ��ǰ���еĹؿ��ı���ͼƬ
	 */
	private BackGround background;
	/**
	 * �Ƿ�׼�����
	 */
	private boolean prepared;
	/**
	 * ������
	 */
	private GamePartManager(){
		try {
			XmlPullParser xml = XmlManager.getXmlParser("config/GamePart", "UTF-8");
			initGamePart(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ׼��
	 */
	public void prepare(){
		try{
			//���ñ���
			setBg();
			//���������
			FishManager.getFishMananger().updateFish(this.part.getFishName());
			prepared = true;
		}catch(Exception e){
			LogTools.doLogForException(e);
		}
		
	}
	/**
	 * �����ؿ�������
	 */
	public void start(){
		if(!prepared){
			Log.e("GamePartManager", "������û��׼�����Ƿ���ù�prepare������");
			return;
		}
		//���ű�������
		MusicManager.getMusicManager().playMusicByR(this.part.getBgMusic(), true);
		//������Ⱥ��������֪ͨ�����ɵ���Ⱥ����
		GamingInfo.getGamingInfo().getShoalManager().start(this.part);	
		//����������߳�
		startGiveGoldThrad();
	}
	/**
	 * ������ʱ������߳�
	 */
	private void startGiveGoldThrad(){
		new Thread(new Runnable() {			
			@Override
			public void run() {
				try {
					int time = Constant.GIVE_GOLD_TIME;
					BottomTime bt = LayoutManager.getLayoutManager().getBottomTime();
					while(GamingInfo.getGamingInfo().isGaming()){
						while(!GamingInfo.getGamingInfo().isPause()){
							if(time==0){
								giveGold();
								time = Constant.GIVE_GOLD_TIME;
							}
							bt.updateNumIndex(time);
							time--;
							Thread.sleep(1000);
						}
						break;
					}					
				} catch (Exception e) {
					LogTools.doLogForException(e);
				}
				
			}
			private void giveGold(){
				if(GamingInfo.getGamingInfo().getScore()<Constant.GIVE_GOLD_LESS){
					GamingInfo.getGamingInfo().setScore(Constant.GIVE_GOLD);
				}				
			}
		}).start();
	}
	/**
	 * ���ñ���
	 */
	private void setBg(){
		try {
			if(background==null){
				background = new BackGround();
				try {
					background.setCurrentPic(ImageManager.getImageMnagaer().sacleImageByWidthAndHeight(ImageManager.getImageMnagaer().getBitmapByAssets(this.part.getBackground()), GamingInfo.getGamingInfo().getScreenWidth(), GamingInfo.getGamingInfo().getScreenHeight()));				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					LogTools.doLogForException(e);
				}
				GamingInfo.getGamingInfo().getSurface().putDrawablePic(Constant.BACK_GROUND_LAYER, background);
			}else{
				try {
					background.setCurrentPic(Bitmap.createScaledBitmap(ImageManager.getImageMnagaer().getBitmapByAssets(this.part.getBackground()), GamingInfo.getGamingInfo().getScreenWidth(), GamingInfo.getGamingInfo().getScreenHeight(), false));				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					LogTools.doLogForException(e);
				}
			}
		} catch (Exception e) {
			LogTools.doLogForException(e);
		}
		
	}
	
	/**
	 * ��ʼ�����еĹؿ�
	 * @param xml	��Ҫ������xml�ļ�
	 */
	private void initGamePart(XmlPullParser xml){
		//ѭ�����еĹؿ�
		while(GamingInfo.getGamingInfo().isGaming()&&XmlManager.gotoTagByTagName(xml, "key")){
			//�����ؿ�������
			GamePartInfo gamePartInfo = new GamePartInfo();
			//��ȡ�ؿ�����
			XmlManager.gotoTagByTagName(xml, "string");
			gamePartInfo.setPartName(XmlManager.getValueByCurrentTag(xml));		
			//��ȡ�ؿ����ֵ���
			XmlManager.gotoTagByTagName(xml, "string");
			gamePartInfo.setFishName(XmlManager.getValueByCurrentTag(xml).split(";"));
			//��ĳ��ָ���
			XmlManager.gotoTagByTagName(xml, "string");
			String probability[] = XmlManager.getValueByCurrentTag(xml).split(";");
			int[] showProbability = new int[probability.length];
			for(int i = 0;i<probability.length;i++){
				showProbability[i] = Integer.parseInt(probability[i]);
			}
			gamePartInfo.setShowProbability(showProbability);
			//��ȡ�ɳ��ֵ���Ⱥ����
			XmlManager.gotoTagByTagName(xml, "integer");
			gamePartInfo.setShoalSumInScreen(Integer.parseInt(XmlManager.getValueByCurrentTag(xml)));
			//��ȡ�ؿ�ʱ��
			XmlManager.gotoTagByTagName(xml, "integer");
			gamePartInfo.setPartTime(Integer.parseInt(XmlManager.getValueByCurrentTag(xml)));
			//��ȡ��һ�ص�����
			XmlManager.gotoTagByTagName(xml, "string");
			gamePartInfo.setNextPart(XmlManager.getValueByCurrentTag(xml));
			//��ȡ��������
			XmlManager.gotoTagByTagName(xml, "string");
			gamePartInfo.setBgMusic(XmlManager.getValueByCurrentTag(xml));
			//��ȡ����ͼƬ
			XmlManager.gotoTagByTagName(xml, "string");
			gamePartInfo.setBackground(XmlManager.getValueByCurrentTag(xml));
			this.games.add(gamePartInfo);
		}
		//����йؿ���Ĭ�ϵ�һ��Ԫ��Ϊ��ʼ�ؿ���������ϲ�Ӧ��Ϊ�ռ���
		if(this.games.size()>0){
			this.part = this.games.get(0);
		}
		
	}
	/**
	 * ��ȡ�ؿ�������ʵ��
	 * @return
	 */
	public static GamePartManager getManager(){
		if(manager==null){
			manager = new GamePartManager();
		}
		return manager;
	}
	/**
	 * ע������
	 */
	public void destroy(){
		manager = null;
		System.gc();
	}
	
}
