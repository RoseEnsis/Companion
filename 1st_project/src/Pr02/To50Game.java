package Pr02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class To50Game extends JFrame{
	String[] imgNum={".\\1to50\\1.png",".\\1to50\\2.png",".\\1to50\\3.png",".\\1to50\\4.png",".\\1to50\\5.png",
			".\\1to50\\6.png",".\\1to50\\7.png",".\\1to50\\8.png",".\\1to50\\9.png",".\\1to50\\10.png",
			".\\1to50\\11.png",".\\1to50\\12.png",".\\1to50\\13.png",".\\1to50\\14.png",".\\1to50\\15.png",
			".\\1to50\\16.png",".\\1to50\\17.png",".\\1to50\\18.png",".\\1to50\\19.png",".\\1to50\\20.png",
			".\\1to50\\21.png",".\\1to50\\22.png",".\\1to50\\23.png",".\\1to50\\24.png",".\\1to50\\25.png",
			".\\1to50\\26.png",".\\1to50\\27.png",".\\1to50\\28.png",".\\1to50\\29.png",".\\1to50\\30.png",
			".\\1to50\\31.png",".\\1to50\\32.png",".\\1to50\\33.png",".\\1to50\\34.png",".\\1to50\\35.png",
			".\\1to50\\36.png",".\\1to50\\37.png",".\\1to50\\38.png",".\\1to50\\39.png",".\\1to50\\40.png",
			".\\1to50\\41.png",".\\1to50\\42.png",".\\1to50\\43.png",".\\1to50\\44.png",".\\1to50\\45.png",
			".\\1to50\\46.png",".\\1to50\\47.png",".\\1to50\\48.png",".\\1to50\\49.png",".\\1to50\\50.png",
	};//1~50 버튼이미지
	To50Btn[] btnsFront=new To50Btn[25];//1~25버튼
	To50Btn[] btnsBack=new To50Btn[25];//26~50버튼
	int game=25;//25칸
	int sqc=1;//1++부터 50까지 초기값
	static String timerBuffer; //경과 시간 문자열이 저장될 버퍼 정의
	static int oldTime;//타이머가 ON 되었을 때의 시각을 기억하고 있는 변수

	Random ran=new Random();
	JFrame frame;
	JPanel panelGame;//버튼 들어가는 panel
	JButton btnStart;
	JLabel laNextNum;//숫자 넘어가는 라벨
	ImageIcon icon;

    //게임 스타트 버튼생성 > 섞기 > add
	public void gameStart(){
		for (int i = 0; i < game; i++) {
			To50Btn btnFront=new To50Btn(i+1, imgNum[i]);//버튼 num, 이미지 num 전달
			To50Btn btnBack=new To50Btn(i+26, imgNum[i+25]);

			btnFront.btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					click(e);
				}
			});
			btnBack.btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					click(e);
				}
			});
			btnsFront[i]=btnFront;
			btnsBack[i]=btnBack;
		}
		
		//1~50버튼 섞기
		for (int i = 0; i < 1000; i++) {
			int r=ran.nextInt(game);
			To50Btn temp=btnsFront[r];
			btnsFront[r]=btnsFront[0];
			btnsFront[0]=temp;
			
			r=ran.nextInt(game);
			temp=btnsBack[r];
			btnsBack[r]=btnsBack[0];
			btnsBack[0]=temp;
		}
		//버튼 add
		for (int i = 0; i < game; i++) {
			panelGame.add(btnsFront[i].btn);
		}
		panelGame.revalidate();
	}

	//	//1~25 > 26~50 > x 버튼으로 바꾸는 기능
	public void click(ActionEvent e){
		JButton bx=(JButton)e.getSource();
		for (int i = 0; i < game; i++){
			if (btnsFront[i].btn==bx && sqc==btnsFront[i].num){
					panelGame.remove(bx);
					panelGame.add(btnsBack[sqc-1].btn, i);
					btnsBack[sqc-1].idx=i;
					sqc++;
			}
			if (btnsBack[i].btn==bx && sqc==btnsBack[i].num){
					panelGame.remove(bx);
					JButton btnX=new JButton();
					panelGame.add(btnX, btnsBack[i].idx);
					btnX.setEnabled(false);
					To50Btn.btnbgHide(btnX);
					sqc++;
			}
		}
		if(sqc==51){
			btnStart.setEnabled(true);
			sqc=1;
			gameTimerEnd();//타이머 종료 메서드 호출
		}
		panelGame.revalidate();
	}

	//타이머 시작 메서드
	public void gameTimerStart(){
		oldTime = (int)System.currentTimeMillis()/1000;// 타이머 시작
	}
	
	//시분초 변환 메서드
	public static void secToHHMMSS(int secs) {
        int hour, min, sec;
        sec  = secs % 60;
        min  = secs / 60 % 60;
        hour = secs / 3600;
        timerBuffer = String.format("%02d:%02d:%02d", hour, min, sec);
	}
	
	//타이머 종료 메서드
	public void gameTimerEnd(){
		Font font=new Font("Fixedsys", Font.BOLD , 20);
		secToHHMMSS(((int)System.currentTimeMillis()/1000)-oldTime);//타이머 스탑
		final Dialog diaEnd=new Dialog(frame,"게임결과");
		diaEnd.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				diaEnd.dispose();
			}
		});
		
		Panel diaPanel=new Panel();
		JLabel diaPanelLbTimeEnd=new JLabel("기록 : "+timerBuffer);
		
		diaPanel.add(diaPanelLbTimeEnd);
		diaEnd.add(diaPanel);
		diaEnd.setBounds(frame.getX()+frame.getWidth()/2-100,
				frame.getY()+frame.getHeight()/2-35,200,80);
		diaEnd.setVisible(true);
//		System.out.format("경과 시간: [%s]%n", timerBuffer);//경과 시간 화면에 출력
	}
	
	//UI 생성자
	public To50Game() {
		
		
		//frame
		frame=new JFrame();
		frame.setLayout(new BorderLayout());
		
		panelGame=new JPanel();
		icon=new ImageIcon(Toolkit.getDefaultToolkit().getImage(".\\1to50\\main.png"));
		JLabel main=new JLabel(icon);
		panelGame.add(main);
		
		//p2
		JPanel pn2=new JPanel(new GridLayout(1,2));
		
		//시작버튼
		icon=new ImageIcon(Toolkit.getDefaultToolkit().getImage(".\\1to50\\start.png"));
		btnStart=new JButton(icon); To50Btn.btnbgHide(btnStart);		
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GridLayout grid=new GridLayout(5,5);
				panelGame.setLayout(grid);
				panelGame.removeAll();
				btnStart.setEnabled(false);
				gameTimerStart();
				gameStart();
			}
		});
		
		//종료버튼
		icon=new ImageIcon(Toolkit.getDefaultToolkit().getImage(".\\1to50\\exit.png"));
		JButton btnEnd=new JButton(icon); To50Btn.btnbgHide(btnEnd);
		btnEnd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				}
		});
		
		//배경색
		Color color=new Color(255,255,255);
		pn2.setBackground(color);
		panelGame.setBackground(color);
		
		pn2.add(btnStart);
		pn2.add(btnEnd);
		frame.add(panelGame,BorderLayout.CENTER);
		frame.add(pn2,BorderLayout.SOUTH);
		frame.setTitle("1 to 50");
		frame.setBounds(100,100,350,400);
		frame.setResizable(false);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowListener(){
			
			public void windowOpened(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				frame.dispose();
				Client.Client.btn1_50.setEnabled(true);
			}
			public void windowClosed(WindowEvent e) {
				Client.Client.btn1_50.setEnabled(true);
			}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
		});
	}
	
//	public static void main(String[] args) {
//		new To50Game();
//	}
	
}
