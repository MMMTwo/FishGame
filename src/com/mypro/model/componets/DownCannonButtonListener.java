package com.mypro.model.componets;

import com.mypro.manager.CannonManager;
import com.mypro.model.interfaces.OnClickListener;

/**
 * ���ʹ��������İ�ť�߼�
 * @author Xiloerfan
 *
 */
public class DownCannonButtonListener implements OnClickListener{

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		CannonManager.getCannonManager().downCannon();
	}

}
