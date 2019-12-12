package com.mypro.model.interfaces;
import com.mypro.base.graphics.Bitmap;
import com.mypro.base.graphics.Canvas;
import com.mypro.base.graphics.Matrix;
import com.mypro.base.graphics.Paint;

public interface Drawable {
	public Matrix getPicMatrix();//��ȡͼƬ��ת�ľ����ʾ
	public Bitmap getCurrentPic();//��ȡ��ǰ����ͼƬ����Դ
	public int getPicWidth();//����ͼƬ�Ŀ��
	public int getPicHeight();//����ͼƬ�ĸ߶�
	public void onDraw(Canvas canvas,Paint paint);//���ƵĻص�����
}
