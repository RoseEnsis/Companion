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
	};//1~50 ��ư�̹���
	To50Btn[] btnsFront=new To50Btn[25];//1~25��ư
	To50Btn[] btnsBack=new To50Btn[25];//26~50��ư
	int game=25;//25ĭ
	int sqc=1;//1++���� 50���� �ʱⰪ
	static String timerBuffer; //��� �ð� ���ڿ��� ����� ���� ����
	static int oldTime;//Ÿ�̸Ӱ� ON �Ǿ��� ���� �ð��� ����ϰ� �ִ� ����

	Random ran=new Random();
	JFrame frame;
	JPanel panelGame;//��ư ���� panel
	JButton btnStart;
	JLabel laNextNum;//���� �Ѿ�� ��
	ImageIcon icon;

    //���� ��ŸƮ ��ư���� > ���� > add
	public void gameStart(){
		for (int i = 0; i < game; i++) {
			To50Btn btnFront=new To50Btn(i+1, imgNum[i]);//��ư num, �̹��� num ����
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
		
		//1~50��ư ����
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
		//��ư add
		for (int i = 0; i < game; i++) {
			panelGame.add(btnsFront[i].btn);
		}
		panelGame.revalidate();
	}

	//	//1~25 > 26~50 > x ��ư���� �ٲٴ� ���
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
			gameTimerEnd();//Ÿ�̸� ���� �޼��� ȣ��
		}
		panelGame.revalidate();
	}

	//Ÿ�̸� ���� �޼���
	public void gameTimerStart(){
		oldTime = (int)System.currentTimeMillis()/1000;// Ÿ�̸� ����
	}
	
	//�ú��� ��ȯ �޼���
	public static void secToHHMMSS(int secs) {
        int hour, min, sec;
        sec  = secs % 60;
        min  = secs / 60 % 60;
        hour = secs / 3600;
        timerBuffer = String.format("%02d:%02d:%02d", hour, min, sec);
	}
	
	//Ÿ�̸� ���� �޼���
	public void gameTimerEnd(){
		Font font=new Font("Fixedsys", Font.BOLD , 20);
		secToHHMMSS(((int)System.currentTimeMillis()/1000)-oldTime);//Ÿ�̸� ��ž
		final Dialog diaEnd=new Dialog(frame,"���Ӱ��");
		diaEnd.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				diaEnd.dispose();
			}
		});
		
		Panel diaPanel=new Panel();
		JLabel diaPanelLbTimeEnd=new JLabel("��� : "+timerBuffer);
		
		diaPanel.add(diaPanelLbTimeEnd);
		diaEnd.add(diaPanel);
		diaEnd.setBounds(frame.getX()+frame.getWidth()/2-100,
				frame.getY()+frame.getHeight()/2-35,200,80);
		diaEnd.setVisible(true);
//		System.out.format("��� �ð�: [%s]%n", timerBuffer);//��� �ð� ȭ�鿡 ���
	}
	
	//UI ������
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
		
		//���۹�ư
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
		
		//�����ư
		icon=new ImageIcon(Toolkit.getDefaultToolkit().getImage(".\\1to50\\exit.png"));
		JButton btnEnd=new JButton(icon); To50Btn.btnbgHide(btnEnd);
		btnEnd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				}
		});
		
		//����
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
