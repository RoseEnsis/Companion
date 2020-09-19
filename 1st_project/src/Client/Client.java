package Client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class Client extends JFrame implements ActionListener{

	String path="192.168.35.240";

	int port=7777;
	public static String userID;
	static JTextField tf;
	static JTextArea ta;
	static JTextArea taUList;
	OutputStream os;
	public static JPanel guest;
	
	public final static JButton btn1=new JButton("설명");
	public final static JButton btn2=new JButton("쉬움");
	public final static JButton btn3=new JButton("보통");
	public final static JButton btn4=new JButton("어려움");
	public final static JButton btn1_50_1=new JButton("설명");
	public final static JButton btn1_50=new JButton("시작");

	public void actionPerformed(ActionEvent e) {
		try {
			while(true){
				String msg=tf.getText()+"\n";
				os.write(msg.getBytes());
				break;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		tf.setText(" ");
	}

	public Client(){
		try{
			Socket c_Socket=new Socket(path,port);


			// 수신소켓
			final ReceiveThr rcv_thr=new ReceiveThr();
			rcv_thr.setSocket(c_Socket);

			// 송신소켓
			final SendThr sd_thr=new SendThr();
			sd_thr.setSocket(c_Socket);

			os=c_Socket.getOutputStream();

			final CardLayout cl=new CardLayout();
			final JPanel main=new JPanel();
			main.setLayout(cl);
			/////////////////////////////////////////////////////
			final JPanel chatArea=new JPanel(new BorderLayout()){
				public void paintComponent(Graphics g){
					Dimension d=getSize();
					ImageIcon image=new ImageIcon("chat.jpg");
					g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
				}
			};
			chatArea.setOpaque(false);
			

			tf=new JTextField();
			tf.addActionListener(this);
			ta=new JTextArea();	ta.setEditable(false);

			Border taLine=BorderFactory.createLineBorder(Color.BLACK,1);
			ta.setBorder(taLine);	// TextArea 테두리. 검정 1픽셀.
			
			ta.setOpaque(false);
			ta.setFont(new Font("Fixedsys",Font.BOLD,16));
			JScrollPane temp1=new JScrollPane(ta);
			temp1.setOpaque(false);
			temp1.getViewport().setOpaque(false);
			
			chatArea.add(temp1,BorderLayout.CENTER);
			chatArea.add(tf,BorderLayout.SOUTH);
			/////////////////////////////////////////////////////
			
			guest=new JPanel();
			GridBagLayout gridBagLayout=new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.5, 1.0, 0.5, 1.0, 0.5, 5.0, Double.MIN_VALUE};
			guest.setLayout(gridBagLayout);
			guest.setOpaque(false);
			
			JPanel p1=new JPanel();
			p1.setOpaque(false);
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.insets = new Insets(0, 0, 5, 0);
			gbc_panel_1.fill = GridBagConstraints.BOTH;
			gbc_panel_1.gridx = 0;
			gbc_panel_1.gridy = 0;
			
			JLabel game1_name=new JLabel("짝 맞추기 게임");
			game1_name.setFont(new Font("Fixedsys",Font.BOLD,20));
			p1.add(game1_name);
			
			guest.add(p1, gbc_panel_1);

			CardLayout fl=new CardLayout();
			
			JPanel p2=new JPanel();
			p2.setOpaque(false);
			GridBagConstraints gbc_panel_2 = new GridBagConstraints();
			gbc_panel_2.insets = new Insets(0, 0, 5, 0);
			gbc_panel_2.fill = GridBagConstraints.BOTH;
			gbc_panel_2.gridx = 0;
			gbc_panel_2.gridy = 1;
			
			btn1.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					Pr02.CardHelp help=new Pr02.CardHelp();
					btn1.setEnabled(false);
				}

			});

			btn2.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					Pr02.Cardez easyStart=new Pr02.Cardez();
					btn2.setEnabled(false);
					btn3.setEnabled(false);
					btn4.setEnabled(false);
				}

			});

			btn3.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					Pr02.Cardgame normStart=new Pr02.Cardgame();
					btn2.setEnabled(false);
					btn3.setEnabled(false);
					btn4.setEnabled(false);
				}

			});

			btn4.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					Pr02.CardHard easyStart=new Pr02.CardHard();
					btn2.setEnabled(false);
					btn3.setEnabled(false);
					btn4.setEnabled(false);
				}

			});

			p2.add(btn1);
			p2.add(btn2);
			p2.add(btn3);
			p2.add(btn4);
			
			guest.add(p2, gbc_panel_2);
			

			JPanel p3=new JPanel();
			p3.setOpaque(false);
			GridBagConstraints gbc_panel_3 = new GridBagConstraints();
			gbc_panel_3.insets = new Insets(0, 0, 5, 0);
			gbc_panel_3.fill = GridBagConstraints.BOTH;
			gbc_panel_3.gridx = 0;
			gbc_panel_3.gridy = 2;
			
			JLabel game2_name=new JLabel("1 to 50");
			game2_name.setFont(new Font("Fixedsys",Font.BOLD,20));
			p3.add(game2_name);
			
			guest.add(p3, gbc_panel_3);
			
			JPanel p4=new JPanel();
			p4.setOpaque(false);
			GridBagConstraints gbc_panel_4 = new GridBagConstraints();
			gbc_panel_4.insets = new Insets(0, 0, 5, 0);
			gbc_panel_4.fill = GridBagConstraints.BOTH;
			gbc_panel_4.gridx = 0;
			gbc_panel_4.gridy = 3;
			
			btn1_50_1.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					Pr02.To50BtnHelp help=new Pr02.To50BtnHelp();
					btn1_50_1.setEnabled(false);
				}
				
			});
			
			btn1_50.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					Pr02.To50Game gameStart=new Pr02.To50Game(); 
					btn1_50.setEnabled(false);
				}
				
			});
			p4.add(btn1_50_1);
			p4.add(btn1_50);
			
			guest.add(p4, gbc_panel_4);
			
			JPanel p5=new JPanel();
			p5.setOpaque(false);
			JLabel list=new JLabel("현재 접속 인원");
			p5.add(list);
			GridBagConstraints gbc_panel_5 = new GridBagConstraints();
			gbc_panel_5.insets = new Insets(0, 0, 5, 0);
			gbc_panel_5.fill = GridBagConstraints.BOTH;
			gbc_panel_5.gridx = 0;
			gbc_panel_5.gridy = 4;
			guest.add(p5, gbc_panel_5);
			
			//////////////////////////////////////////////////////
			//유저목록 UI
			taUList=new JTextArea();
			taUList.setOpaque(false);
			taUList.setEditable(false);
			taUList.setFont(new Font("Fixedsys",Font.BOLD,16));
			JScrollPane p6=new JScrollPane(taUList);
			p6.setOpaque(false);
			p6.getViewport().setOpaque(false);
			GridBagConstraints gbc_textArea = new GridBagConstraints();
			gbc_textArea.fill = GridBagConstraints.BOTH;
			gbc_textArea.gridx = 0;
			gbc_textArea.gridy = 5;
			guest.add(p6, gbc_textArea);
			
			
			chatArea.add(guest,BorderLayout.EAST);		
			//////////////////////////////////////////////////////

			JPanel login=new JPanel(new GridLayout(16,1)){
				public void paintComponent(Graphics g){
					Dimension d=getSize();
					ImageIcon image=new ImageIcon("login.jpg");
					g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
				}
			};

			final JTextField writeID=new JTextField(20);
			JLabel a=new JLabel("사용할 닉네임을 입력해 주세요.");
			a.setFont(new Font("Fixedsys",Font.BOLD,20));
			a.setForeground(Color.BLUE);


			writeID.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					userID=writeID.getText();
					cl.next(main);

					rcv_thr.start();
					sd_thr.start();
				}

			});

			for(int i=0; i<16; i++){
				JPanel p=new JPanel();
				p.setOpaque(false);
				if(i==7){p.add(a);}
				if(i==8){p.add(writeID);}
				login.add(p);
			}
			/////////////////////////////////////////////////////
			main.add(login);
			main.add(chatArea);


			add(main);

			setBounds(200,100,600,500);
			setVisible(true);

			this.addWindowListener(new WindowListener(){
				public void windowOpened(WindowEvent e) {}
				public void windowClosing(WindowEvent e) {
					try {
						os.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					dispose();
				}
				public void windowClosed(WindowEvent e) {}
				public void windowIconified(WindowEvent e) {}
				public void windowDeiconified(WindowEvent e) {}
				public void windowActivated(WindowEvent e) {}
				public void windowDeactivated(WindowEvent e) {}
			});


		}catch (UnknownHostException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Client();

	}

}
