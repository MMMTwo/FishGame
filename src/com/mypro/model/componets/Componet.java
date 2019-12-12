package com.mypro.model.componets;

import com.mypro.base.graphics.Canvas;
import com.mypro.base.graphics.Paint;
import com.mypro.model.DrawableAdapter;
/**
 * ����ĸ���
 * @author Xiloerfan
 *
 */
public abstract class Componet extends DrawableAdapter{
	/**
	 * ���������Ļ��x,y����
	 */
	private float layout_x;
	private float layout_y;
	
	/**
	 * ��ȡ�������Ļ��X����
	 * @return
	 */
	public float getLayout_x() {
		return layout_x;
	}
	/**
	 * �����������Ļ��X����
	 * @param layout_x
	 */
	public void setLayout_x(float layout_x) {
		this.layout_x = layout_x;
	}
	/**
	 * ��ȡ�������Ļ��Y����
	 * @return
	 */
	public float getLayout_y() {
		return layout_y;
	}
	/**
	 * �����������Ļ��Y����
	 * @param layout_y
	 */
	public void setLayout_y(float layout_y) {
		this.layout_y = layout_y;
	}
	
	public void onDraw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(this.getCurrentPic(),
				layout_x,layout_y, paint);		
	}
}
