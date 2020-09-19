package Pr02;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardHard extends JFrame {
	
	public static int success=0; // 카드 맞춘 개수
	public static int a=0;	// 카드 판별용 숫자
	public static int click=0;		// 클릭 횟수
	
	static Panel main=new Panel(new GridLayout(6,6));
	static JFrame game=new JFrame();
	static Timer time=new Timer();
	
	static long startTime;
	static long playTime;
	
	public CardHard(){
		final Thread cgthr=new Thread(){
			public void run(){
				play();
			}
		};		
		
				
		cgthr.start();
			
		this.game.setTitle("짝 맞추기 게임 -hard mode");
		this.game.add(this.main,BorderLayout.CENTER);
		this.game.setBounds(100,100,610,635);
		this.game.setVisible(true);
		this.game.setResizable(false);
		
		this.game.addWindowListener(new WindowListener(){

			public void windowOpened(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				Client.Client.btn2.setEnabled(true);
				Client.Client.btn3.setEnabled(true);
				Client.Client.btn4.setEnabled(true);
				Pr02.CardHard.success=0;
				Pr02.CardHard.game.dispose();
			}
			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
		});
	}
	
	public static void main(String[] args) {}
	
	public static void play(){
		Pr02.CardHard.startTime=System.currentTimeMillis();
		main.removeAll();
		TableHd setting=new TableHd(main);
		Pr02.CardHard.game.add(setting.p,BorderLayout.CENTER);
		Pr02.CardHard.game.revalidate();
	}

}

// 메인사진을 생성자로 갖는 카드클래스.
class CardHd{
	
	Toolkit kit=Toolkit.getDefaultToolkit();
	Image wait=kit.getImage("wait.jpg");
	Icon front=new ImageIcon(wait);
	
	String picName;
	Image img;
	Icon icon;
	
	Panel p=new Panel();
	CardLayout cl=new CardLayout();
	
	
	int num;
	
	
	public CardHd(final String picName){
		
		JButton head=new JButton(front);
		head.setPreferredSize(new Dimension(100,100));
		this.img=kit.getImage(picName);
		this.icon=new ImageIcon(img);
		JButton tail=new JButton(this.icon);
		tail.setPreferredSize(new Dimension(100,100));
		
		if(picName.equals("1.jpg")){this.num=1;}
		else if(picName.equals("2.jpg")){this.num=2;}
		else if(picName.equals("3.jpg")){this.num=3;}
		else if(picName.equals("4.jpg")){this.num=4;}
		else if(picName.equals("5.jpg")){this.num=5;}
		else if(picName.equals("6.jpg")){this.num=6;}
		else if(picName.equals("7.jpg")){this.num=7;}
		else if(picName.equals("8.jpg")){this.num=8;}
		else if(picName.equals("9.jpg")){this.num=9;}
		else if(picName.equals("10.jpg")){this.num=10;}
		else if(picName.equals("11.jpg")){this.num=11;}
		else if(picName.equals("12.jpg")){this.num=12;}
		else if(picName.equals("13.jpg")){this.num=13;}
		else if(picName.equals("14.jpg")){this.num=14;}
		else if(picName.equals("15.jpg")){this.num=15;}
		else if(picName.equals("16.jpg")){this.num=16;}
		else if(picName.equals("17.jpg")){this.num=17;}
		else if(picName.equals("18.jpg")){this.num=18;}
		
		head.addActionListener(new ActionListener(){
			
			public synchronized void actionPerformed(ActionEvent e) {
				flip();
				Pr02.CardHard.click++;
				
				// 클릭 두번째일때 버튼 2초간 못누르게하기.
				TimerTask clickTwice=new TimerTask(){

					public void run() {
						Pr02.CardHard.main.setEnabled(true);
					}
					
				};
				// 카드 넘겼을때 시간재는용.
				TimerTask timechk=new TimerTask(){
					
					public void run() {
						flip();
						Pr02.CardHard.click=0;
						Pr02.CardHard.a=0;
					}
					
				};
				
				if(Pr02.CardHard.click==1){
					Pr02.CardHard.a+=num;
					Pr02.CardHard.time.schedule(timechk, 1500);
					
				} else if(Pr02.CardHard.click==2){
					Pr02.CardHard.main.setEnabled(false);
					Pr02.CardHard.time.schedule(clickTwice, 2000);
					Pr02.CardHard.a+=num;
					if(Pr02.CardHard.a==(num*2)){
						Pr02.CardHard.time.cancel();
						Pr02.CardHard.main.setEnabled(true);
						Pr02.CardHard.click=0;
						Pr02.CardHard.success+=1;
						Pr02.CardHard.a=0;
						Pr02.CardHard.time=new Timer();
						
						if(Pr02.CardHard.success==18){
							long endTime=System.currentTimeMillis();
							Pr02.CardHard.playTime=(endTime-Pr02.CardHard.startTime)/1000;
							Dialog end=new Dialog(Pr02.CardHard.game,"게임결과");
							
							end.addWindowListener(new WindowListener(){

								public void windowOpened(WindowEvent e) {}
								public void windowClosing(WindowEvent e) {
									Client.Client.btn2.setEnabled(true);
									Client.Client.btn3.setEnabled(true);
									Client.Client.btn4.setEnabled(true);
									Pr02.CardHard.success=0;
									Pr02.CardHard.game.dispose();
								}
								public void windowClosed(WindowEvent e) {}
								public void windowIconified(WindowEvent e) {}
								public void windowDeiconified(WindowEvent e) {}
								public void windowActivated(WindowEvent e) {}
								public void windowDeactivated(WindowEvent e) {}
								
							});
							
							end.setVisible(true);
							end.setBounds(Pr02.CardHard.game.getX()+Pr02.CardHard.game.getWidth()/2-100,
									Pr02.CardHard.game.getY()+Pr02.CardHard.game.getHeight()/2-35,200,80);
							int a=(int)Pr02.CardHard.playTime;
							int hour,min,sec;
							hour=a/3600;	min=a/60%60;	sec=a%60;
							String result=String.format("%02d:%02d:%02d", hour, min, sec);
							JLabel ending=new JLabel("기록 : "+result);
							ending.setFont(new Font("Fixedsys",Font.BOLD,20));
							JPanel record=new JPanel();
							record.add(ending);
							end.add(record);
							Thread.interrupted();
						}
					}else{
						Pr02.CardHard.a=0;
						Pr02.CardHard.time.schedule(timechk, 1000);
					}
				}
				
			}
			
		});
		
		this.p.setLayout(cl);
		this.p.add(head);
		this.p.add(tail);		
	}
	

	// 카드 뒤집기
	public void flip(){
		cl.next(p);
	}
	
	
	
		

}

// 게임판을 생성할 패널을 입력하면 해당 패널에 게임판을 깔아주는 클래스
class TableHd {
	
	ArrayList<Panel> dummy=new ArrayList<>();
	Panel p;
	// 카드 뒷면의 이미지리스트.
	String[] list=new String[]{"1.jpg","2.jpg","3.jpg","4.jpg",
						"5.jpg","6.jpg","7.jpg","8.jpg",
						"9.jpg","10.jpg","11.jpg","12.jpg",
						"13.jpg","14.jpg","15.jpg","16.jpg",
						"17.jpg","18.jpg"};
	
	public TableHd(Panel p){
		create();
		mix();
		this.p=p;	
		for(int i=0; i<dummy.size(); i++){
			p.add(dummy.get(i));
		}
	}
	
	
	// 한 이미지당 2장씩 생성
	public void create(){
		for(int i=0; i<list.length; i++){
			for(int j=0; j<2; j++){
				CardHd temp=new CardHd(list[i]);
				Panel card=temp.p;
				dummy.add(card);
			}
		}
	}
	
	public void mix(){
		int a=dummy.size();		
		Random random=new Random();
		for(int i=0; i<1000; i++){
			int ran=random.nextInt(a);
			
			Panel temp = dummy.get(0);
			dummy.set(0, dummy.get(ran));
			dummy.set(ran, temp);
		}
	}
}