package com.mypro.model.componets;

import com.mypro.manager.CannonManager;
import com.mypro.model.interfaces.OnClickListener;

/**
 * �������������İ�ť�߼�
 * @author Xiloerfan
 *
 */
public class UpCannonButtonListener implements OnClickListener{

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		CannonManager.getCannonManager().upCannon();
	}

}
