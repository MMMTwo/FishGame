package com.mypro.basecomponet;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import com.mypro.mainsurface.MainSurface;
import com.mypro.manager.CannonManager;
import com.mypro.manager.GameInitManager;
import com.mypro.manager.LayoutManager;
import com.mypro.model.GamingInfo;

public class AwtMainComponet{
	public static void main(String[] args) throws Exception  {
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		JFrame frame = new JFrame();
        GamingInfo.getGamingInfo().setGaming(true);
        GamingInfo.getGamingInfo().setScreenWidth(900);
    	GamingInfo.getGamingInfo().setScreenHeight(600);  
    	frame.setSize(GamingInfo.getGamingInfo().getScreenWidth(), GamingInfo.getGamingInfo().getScreenHeight());
    	frame.setUndecorated(true); // ȥ�����ڵ�װ�� 
//    	frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//����ָ���Ĵ���װ�η�� 
//		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		MainSurface pane = new MainSurface();
		GamingInfo.getGamingInfo().setSurface(pane);
		frame.setContentPane(pane);
//		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(GameInitManager.getGameInitManager().isIniting()){
					return ;
				}
				//��Ļ������
				//�ȿ����ֹ������Ƿ�����Ӧ
				if(!LayoutManager.getLayoutManager().onClick(e.getX(), e.getY())){
					//�����ӵ�
					CannonManager.getCannonManager().shot(e.getX(),  e.getY());
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
//		frame.pack();
		pane.action();
		/**
    	 * ����һ���߳����첽��ʼ����Ϸ����
    	 */
    	new Thread(new Runnable(){

			public void run() {
				//ʹ����Ϸ��ʼ����������ʼ����Ϸ
				GameInitManager.getGameInitManager().init();
			}
    		
    	}).start();  	
	}

}
