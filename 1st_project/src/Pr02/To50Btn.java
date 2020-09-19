package Pr02;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

class To50Btn{
	int num;
	String imgNum;
	JButton btn;
	int idx;
	Icon icon;
	
	public To50Btn(int num, String imgNum) {
		this.num = num;
		this.imgNum = imgNum;
		Toolkit kit=Toolkit.getDefaultToolkit();
		Image img=kit.getImage(imgNum);
		icon=new ImageIcon(img);
		btn=new JButton(icon);
		btnbgHide(btn);
	}
	
	//버튼 배경 테두리 없애기
	public static void btnbgHide(JButton btn){
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
	}
}