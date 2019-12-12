package com.mypro.base.graphics;

/*
 * ����
 */
public interface Canvas {
	public void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint);

	public void drawBitmap(Bitmap bitmap, float x, float y, Paint paint);
}
