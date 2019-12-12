package com.mypro.manager;

import java.util.ArrayList;

import com.mypro.model.Ammo;
import com.mypro.model.GamingInfo;
import com.mypro.model.fish.Fish;
import com.mypro.tools.CircleRectangleIntersect;
import com.mypro.tools.LogTools;

/**
 * ��׽������
 * @author Xiloerfan
 *
 */
public class CatchFishManager {
	private static CatchFishManager manager;
	private CatchFishManager(){
		
	}
	public static CatchFishManager getCatchFishManager(){
		if(manager==null){
			manager = new CatchFishManager();
		}
		return manager;
	}
	/**
	 * �����ӵ��Լ���ײ��������׽
	 * @param netX
	 * @param netY
	 * @param ammo
	 */
	public void catchFishByAmmo(final float netX,final float netY,final Ammo ammo){
		new Thread(new Runnable() {
			@Override
			public void run() {
				showNet(netX,netY,ammo);
				//���ò�׽��ⷽ��
				catchFish(netX,netY,ammo);
			}
		}).start();
	}
	/**
	 * ��ʾ����
	 * @param netX
	 * @param netY
	 * @param ammo
	 */
	private void showNet(final float netX,final float netY,final Ammo ammo){
		new Thread(new Runnable() {				
			@Override
			public void run() {
				try {
					SoundManager.playSound(SoundManager.SOUND_BGM_NET);//����������Ч
					ammo.getNet().playNetAct(netX, netY);//��ʾ����
				} catch (Exception e) {
					LogTools.doLogForException(e);
				}
			}
		}).start();			
		
	}
	/**
	 * ��׽��ⷽ����������������������Ļ�е����Ƿ��н�����Ȼ��֪ͨ�����н�������Ĳ�׽����
	 * @param netX
	 * @param netY
	 * @param ammo
	 */
	private synchronized void catchFish(float netX,float netY,Ammo ammo){
		ArrayList<Fish> allFish = (ArrayList<Fish>)GamingInfo.getGamingInfo().getFish().clone();
		for(Fish fish:allFish){
			if(!fish.isAlive()){
				continue;
			}
			if(CircleRectangleIntersect.isIntersect(netX, netY, fish.getHeadFish().getFish_X()-fish.getDistanceHeadFishX()+fish.getPicWidth()/2, fish.getHeadFish().getFish_Y()-fish.getDistanceHeadFishY()+fish.getPicHeight()/2, fish.getPicHeight(), fish.getPicWidth(), ammo.getNet().getPicWidth()/2)){
				if(checkCatch(ammo,fish)){
					//�������Ѿ�����׽�ɹ��ķ���
					fish.onCatched(ammo,netX,netY);
				}else{
					//������û�в�׽�ɹ��Ĳ�׽����
					fish.onCatch(ammo,netX,netY);
				}				
				//���������׽��ֹͣ�ƶ��߳�
				if(checkAllCatch(fish)){
					fish.getHeadFish().getFishRunThread().setRun(false);
					//֪ͨ��Ⱥ���������������Ѿ��뿪��Ļ
					GamingInfo.getGamingInfo().getShoalManager().notifyFishIsOutOfScreen();
				}
			}
		}
	}
	/**
	 * ������Ƿ񱻲�׽�ɹ�
	 * @param ammo		��Ӧ���ӵ�
	 * @param fish		����׽����
	 * @return			true:����׽�ɹ�		false:û�в�׽�ɹ�
	 */
	private boolean checkCatch(Ammo ammo,Fish fish){
		double probability = ammo.getAmmoQuality()*10+fish.getFishInfo().getCatchProbability();
		if(Math.random()*1000+1<=probability){
			return true;
		}
		return false;
	}
	/**
	 * �жϵ�ǰ�����ڵ���Ⱥ�Ƿ��Ѿ���������
	 * @param fish
	 * @return
	 */
	private synchronized boolean checkAllCatch(Fish fish){
		for(Fish f:fish.getHeadFish().getShoal()){
			if(f.isAlive()){
				return false;
			}
		}
		return true;
	}
}
