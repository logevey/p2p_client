package com.lee.wait;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {
	private Socket clientSocket;
	// IO句柄
	private BufferedReader sin;
	private DataOutputStream ps;

	// 默认的构造函数
	public ClientThread() {

	}

	public ClientThread(Socket s) throws IOException {

		clientSocket = s;
		sin = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		ps = new DataOutputStream(clientSocket.getOutputStream());
		// 开启线程
		start();
	}

	public void run() {

		String name = null;
		// 用循环来监听通讯内容

		try {
			String str = sin.readLine();
			DataInputStream fis = new DataInputStream(new BufferedInputStream(
					new FileInputStream(str)));
			int bufferSize = 8192;
			byte[] buf = new byte[bufferSize];
			while (true) {
				int read = 0;
				if (fis != null) {
					read = fis.read(buf);
				}

				if (read == -1) {
					break;
				}
				ps.write(buf, 0, read);
			}
			ps.flush();
			clientSocket.close();
			System.out.println("文件传输完成\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
