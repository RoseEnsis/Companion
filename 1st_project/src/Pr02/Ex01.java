package Pr02;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Ex01 extends Frame{

	public static void main(String[] args) {
		final Frame frame=new Frame();
		
		Panel p=new Panel(new GridLayout(2,2));
		
		Button bt=new Button("Normal mode");
		bt.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				Pr02.Cardgame normStart=new Pr02.Cardgame();
			}
			
		});
		
		Button bt1=new Button("Easy mode");
		bt1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Pr02.Cardez easyStart=new Pr02.Cardez();
			}
			
		});
		
		Button bt2=new Button("Hard mode");
		bt2.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Pr02.CardHard easyStart=new Pr02.CardHard();
			}
			
		});
		
		
		p.add(bt);
		p.add(bt1);
		p.add(bt2);
		frame.add(p);
		frame.setTitle("Parent window...");
		frame.setBounds(200,100,500,400);
		frame.setVisible(true);
	}


}
