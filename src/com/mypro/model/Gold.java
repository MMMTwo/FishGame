package com.mypro.model;

import com.mypro.base.graphics.Bitmap;

/**
 * ���
 * @author Xiloerfan
 *
 */
public class Gold extends DrawableAdapter{
	private Bitmap[] gold;
	private int currentPicId;
	//�Ƿ񲥷Ž�Ҷ���
	private boolean playGoldPicAct;
	/**
	 * ��ҵĶ����߳�
	 */
	private Runnable goldActThread; 
	public Gold(Bitmap[] gold){
		this.gold = gold;
	}
	public void setCurrentPicId(int currentPicId){
		this.currentPicId = currentPicId;
	}
	public int getPicLength() {
		// TODO Auto-generated method stub
		return gold.length;
	}
	@Override
	public Bitmap getCurrentPic() {
		// TODO Auto-generated method stub
		return gold[currentPicId];
	}

	@Override
	public int getPicWidth() {
		// TODO Auto-generated method stub
		return getCurrentPic().getWidth();
	}

	@Override
	public int getPicHeight() {
		// TODO Auto-generated method stub
		return getCurrentPic().getHeight();
	}
	public Runnable getGoldActThread() {
		return goldActThread;
	}
	public void setGoldActThread(Runnable goldActThread) {
		this.goldActThread = goldActThread;
	}
	public boolean isPlayGoldPicAct() {
		return playGoldPicAct;
	}
	public void setPlayGoldPicAct(boolean playGoldPicAct) {
		this.playGoldPicAct = playGoldPicAct;
	}
	
}
