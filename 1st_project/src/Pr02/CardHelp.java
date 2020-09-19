package Pr02;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CardHelp extends JFrame{

	public CardHelp() {
		JFrame frame=new JFrame();
		
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setTitle("짝 맞추기 설명");
		frame.setResizable(false);
		frame.setContentPane(new JLabel(new ImageIcon("help.jpg")));
		frame.setBounds(d.width/2-350,d.height/2-300,600,360);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowListener(){

			public void windowOpened(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				Client.Client.btn1.setEnabled(true);
				}
			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			
		});
	}

}
