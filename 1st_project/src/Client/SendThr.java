package Client;

import java.awt.TextField;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JPanel;

// ä�ñ����� ����� ���� �ʾ� �α��ν� �г��Ӽ������θ�...
public class SendThr extends Thread{

	private Socket mainSocket;
	public static String msg;
	OutputStream os;
//	static TextField tf;
	
	
	public void run(){
		super.run();
		try {
			
			os=mainSocket.getOutputStream();
			
//			BufferedReader bfRead=new BufferedReader(new InputStreamReader(null));
			PrintWriter sendMsg=new PrintWriter(os);
			
			sendMsg.println("IDq1w2e3r4"+Client.userID);
			sendMsg.flush();
			
			
			
//			// Ŭ�� ���� Ȯ��
//			while(true){
//				
//				if(Client.stopNum==5){break;}
//				
//			}
//			
//			
//			sendMsg.close();
//			os.close();
//			mainSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void setSocket(Socket socket){
		mainSocket=socket;
	}
	
	
	
}
