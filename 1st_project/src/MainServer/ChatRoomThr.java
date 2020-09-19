package MainServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Client.Client;

public class ChatRoomThr extends Thread{

	private Socket mainSocket;
	private String mainID;
	private String userList;
	
	
	public void run(){
		super.run();
		
		try{
			String msg;
			BufferedReader bfRead=new BufferedReader(new InputStreamReader(mainSocket.getInputStream()));
			
			while(true){
				msg=bfRead.readLine();
				
				
				if(msg==null){
					System.out.println(mainID+"이(가) 나갔습니다.");
					userList=UserList.getUserList();
					String[] temp=userList.split(" ");
					String result="";
					for(int i=0; i<temp.length; i++){
						if(temp[i].equals(mainID)){
							temp[i]="";
							continue;
						}
						result+=temp[i]+" ";
					}
					UserList.setUserList("");
					UserList.setUserList(result);
					for(int i=0; i<Server.cl_List.size(); ++i){
						Server.cl_List.get(i).println(result);
						Server.cl_List.get(i).println(mainID+"이(가) 나갔습니다.");
						Server.cl_List.get(i).flush();
					}
					break;
				}
				
				// 접속?
				String[] split=msg.split("q1w2e3r4");
				if(split.length==2 && split[0].equals("ID")){
					mainID=split[1];
					userList+=mainID+" ";
					UserList.setUserList(userList);
					System.out.println(mainID+"이(가) 입장하였습니다.");
					for(int i=0; i<Server.cl_List.size(); ++i){
						Server.cl_List.get(i).println(userList);
						Server.cl_List.get(i).println(mainID+"이(가) 입장하였습니다.");
						Server.cl_List.get(i).flush();
					}
					continue;
				}				
				
				// 메세지 뿌리기
				for(int i=0; i<Server.cl_List.size(); ++i){
					Server.cl_List.get(i).println(mainID+" : "+msg);
					Server.cl_List.get(i).flush();
				}
				
				
			}
			
			Server.cl_List.remove(new PrintWriter(mainSocket.getOutputStream()));
			mainSocket.close();
			
		} catch(IOException e){
			e.printStackTrace();
		} 
	}
	
	public void setSocket(Socket socket){
		mainSocket=socket;
	}
	public void setUserList(String userList){
		this.userList=userList;
	}
}
