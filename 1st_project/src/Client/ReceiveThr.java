package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThr extends Thread{

	private Socket mainSocket;
	private String userList;
	
	public void run(){
		super.run();
		
		try{
			String msg;
			BufferedReader bfRead=new BufferedReader(new InputStreamReader(mainSocket.getInputStream()));
			
			while(true){
				msg=bfRead.readLine();
				
				String[] split=msg.split("uiop7890 ");
				if(split.length==2 && split[0].equals("UserList")){
					userList=split[1];
					String[] temp=userList.split(" ");
					String result="";
					for (int i = 0; i < temp.length; i++) {
						result+=temp[i]+"\n";
					}
					Client.taUList.setText(result);
					continue;
				}
				Client.ta.append("\n"+msg);
				Client.ta.setCaretPosition(Client.ta.getDocument().getLength());

			}
			
			
		} catch (IOException e){
			
		}
		
	}
	
	public void setSocket(Socket socket){
		mainSocket=socket;
	}

}