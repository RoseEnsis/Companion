package MainServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Client.SendThr;

public class Server {

	public static ArrayList<PrintWriter> cl_List;
	
	public static void main(String[] args) {
		cl_List=new ArrayList<PrintWriter>();
		
		try{
			ServerSocket s_Socket=new ServerSocket(7777);
			while(true){
				Socket c_Socket=s_Socket.accept();
				ChatRoomThr crt_thr=new ChatRoomThr();
				crt_thr.setSocket(c_Socket);
				crt_thr.setUserList(UserList.getUserList());
				
				cl_List.add(new PrintWriter(c_Socket.getOutputStream()));
				
				crt_thr.start();
			}
			
		} catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
