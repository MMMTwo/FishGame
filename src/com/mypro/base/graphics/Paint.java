package com.mypro.base.graphics;

/*
 * ��������
 */
public interface Paint {
	public void setTypeface(Object obj); // ����Paint������
	public void setAntiAlias(boolean tf);
	public void setFilterBitmap(boolean tf);
	public void setDither(boolean tf);
	public void setTextSize(int size);  // ���ݲ�ͬ�ֱ������������С
	public void setColor(int color);
}
