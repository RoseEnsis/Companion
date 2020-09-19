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

public class Cardgame extends JFrame {
	
	public static int success=0; // 카드 맞춘 개수
	public static int a=0;	// 카드 판별용 숫자
	public static int click=0;		// 클릭 횟수
	
	static Panel main=new Panel(new GridLayout(4,4));
	static JFrame game=new JFrame();
	static Timer time=new Timer();
	
	static long startTime;
	static long playTime;
	
	public Cardgame(){
		final Thread cgthr=new Thread(){
			public void run(){
				play();
			}
		};		
		
//		Cardgame cg=new Cardgame();		
		
		Table waiting=new Table(main);
				
		cgthr.start();
			
		this.game.setTitle("짝 맞추기 게임 -normal mode");
		this.game.add(this.main,BorderLayout.CENTER);
		this.game.setBounds(100,100,410,435);
		this.game.setVisible(true);
		this.game.setResizable(false);
		
		this.game.addWindowListener(new WindowListener(){

			public void windowOpened(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				Client.Client.btn2.setEnabled(true);
				Client.Client.btn3.setEnabled(true);
				Client.Client.btn4.setEnabled(true);
				Pr02.Cardgame.success=0;
				Pr02.Cardgame.game.dispose();
			}
			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
		});
	}
	
	public static void main(String[] args) {
		
//		final Thread cgthr=new Thread(){
//			public void run(){				
//				play();
//			}
//		};		
//		
//		Cardgame cg=new Cardgame();		
//		
//		Table waiting=new Table(main_1);
//				
//		cgthr.start();
//			
//		cg.game.setTitle("짝 맞추기 게임");
//		cg.main.add(main_1);
//		cg.game.add(cg.main,BorderLayout.CENTER);
//		cg.game.setBounds(500,200,610,635);
//		cg.game.setVisible(true);
//		cg.game.setResizable(false);
	}
	
	public static void play(){
		Pr02.Cardgame.startTime=System.currentTimeMillis();
		main.removeAll();
		Table setting=new Table(main);
		Pr02.Cardgame.game.add(setting.p,BorderLayout.CENTER);
		Pr02.Cardgame.game.revalidate();
		
		
//		while(true){
//			long endTime=System.currentTimeMillis();
//			playTime=(endTime-Pr02.Cardgame.startTime)/1000;
//			if(Pr02.Cardgame.success==8){
//				Dialog end=new Dialog(Pr02.Cardgame.game,"게임결과");
//				end.setVisible(true);
//				end.setBounds(Pr02.Cardgame.game.getX()+Pr02.Cardgame.game.getWidth()/2-50,
//						Pr02.Cardgame.game.getY()+Pr02.Cardgame.game.getHeight()/2-25,100,50);
//				JLabel ending=new JLabel(playTime+"초");
//				ending.setFont(new Font("궁서",Font.BOLD,20));
//				end.add(ending);
//				break;
//			}
//		}
//		Thread.interrupted();
	}

}

// 메인사진을 생성자로 갖는 카드클래스.
class Card{
	
	Toolkit kit=Toolkit.getDefaultToolkit();
	Image wait=kit.getImage("wait.jpg");
	Icon front=new ImageIcon(wait);
	
	String picName;
	Image img;
	Icon icon;
	
	Panel p=new Panel();
	CardLayout cl=new CardLayout();
	
	
	int num;
	
	
	public Card(final String picName){
		
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
		
		head.addActionListener(new ActionListener(){
			
			public synchronized void actionPerformed(ActionEvent e) {
				flip();
				Pr02.Cardgame.click++;
				
				// 클릭 두번째일때 버튼 2초간 못누르게하기.
				TimerTask clickTwice=new TimerTask(){

					public void run() {
						Pr02.Cardgame.main.setEnabled(true);
					}
					
				};
				// 카드 넘겼을때 시간재는용.
				TimerTask timechk=new TimerTask(){
					
					public void run() {
						flip();
						Pr02.Cardgame.click=0;
						Pr02.Cardgame.a=0;
					}
					
				};
				
				if(Pr02.Cardgame.click==1){
					Pr02.Cardgame.a+=num;
					Pr02.Cardgame.time.schedule(timechk, 2000);
					
				} else if(Pr02.Cardgame.click==2){
					Pr02.Cardgame.main.setEnabled(false);
					Pr02.Cardgame.time.schedule(clickTwice, 2000);
					Pr02.Cardgame.a+=num;
					if(Pr02.Cardgame.a==(num*2)){
						Pr02.Cardgame.time.cancel();
						Pr02.Cardgame.main.setEnabled(true);
						Pr02.Cardgame.click=0;
						Pr02.Cardgame.success+=1;
						Pr02.Cardgame.a=0;
						Pr02.Cardgame.time=new Timer();
						
						if(Pr02.Cardgame.success==8){
							long endTime=System.currentTimeMillis();
							Pr02.Cardgame.playTime=(endTime-Pr02.Cardgame.startTime)/1000;
							Dialog end=new Dialog(Pr02.Cardgame.game,"게임결과");
							
							end.addWindowListener(new WindowListener(){

								public void windowOpened(WindowEvent e) {}
								public void windowClosing(WindowEvent e) {
									Client.Client.btn2.setEnabled(true);
									Client.Client.btn3.setEnabled(true);
									Client.Client.btn4.setEnabled(true);
									Pr02.Cardgame.success=0;
									Pr02.Cardgame.game.dispose();
								}
								public void windowClosed(WindowEvent e) {}
								public void windowIconified(WindowEvent e) {}
								public void windowDeiconified(WindowEvent e) {}
								public void windowActivated(WindowEvent e) {}
								public void windowDeactivated(WindowEvent e) {}
								
							});
							
							end.setVisible(true);
							end.setBounds(Pr02.Cardgame.game.getX()+Pr02.Cardgame.game.getWidth()/2-100,
									Pr02.Cardgame.game.getY()+Pr02.Cardgame.game.getHeight()/2-35,200,80);
							int a=(int)Pr02.Cardgame.playTime;
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
						Pr02.Cardgame.a=0;
						Pr02.Cardgame.time.schedule(timechk, 1000);
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
class Table {
	
	ArrayList<Panel> dummy=new ArrayList<>();
	Panel p;
	// 카드 뒷면의 이미지리스트.
	String[] list=new String[]{"1.jpg","2.jpg","3.jpg","4.jpg",
						"5.jpg","6.jpg","7.jpg","8.jpg"};
	
	public Table(Panel p){
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
				Card temp=new Card(list[i]);
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