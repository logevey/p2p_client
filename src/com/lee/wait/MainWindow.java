package com.lee.wait;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainWindow implements ActionListener {
	int portNo = 3333;
	int fileServerPort = 6666;
	long upFileSize = 0;
	String upFileName = null;

	JFrame frameIndex;

	JLabel labelFlagLogin;
	JLabel labelFlagRegister;
	JLabel labelFlagSearch;
	JLabel labelFlagMyUp;
	JLabel labelFlagMain;
	JLabel labelFlagUp;
	JLabel labelLoginName;
	JLabel labelLoginPasswd;
	JLabel labelRegisterName;
	JLabel labelRegisterPasswd;
	JLabel labelFileName;
	JLabel labelFilePath;

	JButton btnRegister;
	JButton btnLogin;
	JButton btnRegisterOk;
	JButton btnBack;
	JButton btnLogout;
	JButton btnSearchSubmit;
	JButton btnSearch;
	JButton btnMyUp;
	JButton btnUp;
	JButton btnSearchBackMain;
	JButton btnMyUpBackMain;
	JButton btnUpBackMain;
	JButton btnMyUpDelete;
	JButton btnSearchDownloadFile;
	JButton btnSelectFile;
	JButton btnBeginUp;

	JTextField textFieldLoginName;
	JPasswordField textFieldLoginPasswd;
	JTextField textFieldRegisterName;
	JPasswordField textFieldRegisterPasswd;
	JTextField textFieldSearchInput;
	JTextField textFieldFilePath;

	JTable tableSearchResultShow;
	JTable tableMyUpShow;

	JPanel panelLogin;
	JPanel panelRegister;
	JPanel panelMain;
	JPanel panelSearch;
	JPanel panelMyUp;
	JPanel panelUp;

	Socket socket;
	BufferedReader in;
	PrintWriter out;

	class NotEditTableModel extends DefaultTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NotEditTableModel() {
			super();
		}

		public NotEditTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	public void LoginShow() {
		frameIndex.setContentPane(panelLogin);
	}

	public void RegisterShow() {
		frameIndex.setContentPane(panelRegister);
		// frameIndex.getContentPane().add(panelRegister);
	}

	public void MainShow() {
		frameIndex.setContentPane(panelMain);
	}

	private void UpShow() {
		// TODO Auto-generated method stub
		frameIndex.setContentPane(panelUp);
	}

	private void MyUpShow() {
		// TODO Auto-generated method stub
		frameIndex.setContentPane(panelMyUp);
	}

	private void SearchShow() {
		// TODO Auto-generated method stub
		frameIndex.setContentPane(panelSearch);
	}

	public MainWindow() throws IOException {
		 Random rdm = new Random(System.currentTimeMillis());
		fileServerPort=2048+Math.abs(rdm.nextInt())%10+1;
		InetAddress addr = null;
		try {
			addr = InetAddress.getByName("localhost");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		socket = new Socket(addr, portNo);

		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream())), true);
		
		labelRegisterName = new JLabel("用户名：");
		labelRegisterName.setSize(100, 30);
		labelRegisterName.setLocation(75, 100);

		labelRegisterPasswd = new JLabel("密  码：");
		labelRegisterPasswd.setSize(100, 30);
		labelRegisterPasswd.setLocation(75, 200);

		labelLoginName = new JLabel("用户名：");
		labelLoginName.setSize(100, 30);
		labelLoginName.setLocation(75, 100);

		labelLoginPasswd = new JLabel("密  码：");
		labelLoginPasswd.setSize(100, 30);
		labelLoginPasswd.setLocation(75, 200);

		labelFlagLogin = new JLabel("登 录 界 面");
		labelFlagLogin.setSize(200, 50);
		labelFlagLogin.setLocation(150, 25);

		labelFlagRegister = new JLabel("注 册 界 面");
		labelFlagRegister.setSize(200, 50);
		labelFlagRegister.setLocation(150, 25);

		labelFlagSearch = new JLabel("搜 索 界 面");
		labelFlagSearch.setSize(200, 50);
		labelFlagSearch.setLocation(150, 25);

		labelFlagUp = new JLabel("上 传 界 面");
		labelFlagUp.setSize(200, 50);
		labelFlagUp.setLocation(150, 25);

		labelFlagMyUp = new JLabel("我 的 上 传 界 面");
		labelFlagMyUp.setSize(200, 50);
		labelFlagMyUp.setLocation(150, 25);

		labelFlagMain = new JLabel("        主 界 面");
		labelFlagMain.setSize(200, 50);
		labelFlagMain.setLocation(150, 25);

		labelFileName = new JLabel("文件名：");
		labelFileName.setSize(200, 50);
		labelFileName.setLocation(25, 90);

		labelFilePath = new JLabel("文件路径：");
		labelFilePath.setSize(150, 50);
		labelFilePath.setLocation(25, 90);

		btnLogin = new JButton("登录");
		btnLogin.setSize(60, 30);
		btnLogin.addActionListener(this);
		btnLogin.setLocation(200, 300);

		btnLogout = new JButton("注销");
		btnLogout.setSize(60, 30);
		btnLogout.addActionListener(this);
		btnLogout.setLocation(175, 500);

		btnSearchSubmit = new JButton("搜索");
		btnSearchSubmit.setSize(60, 30);
		btnSearchSubmit.addActionListener(this);
		btnSearchSubmit.setLocation(300, 100);

		btnSearch = new JButton("搜索资源");
		btnSearch.setSize(100, 30);
		btnSearch.addActionListener(this);
		btnSearch.setLocation(160, 100);

		btnMyUp = new JButton("我的上传");
		btnMyUp.setSize(100, 30);
		btnMyUp.addActionListener(this);
		btnMyUp.setLocation(160, 200);

		btnUp = new JButton("上传文件");
		btnUp.setSize(100, 30);
		btnUp.addActionListener(this);
		btnUp.setLocation(160, 300);

		btnSearchBackMain = new JButton("主界面");
		btnSearchBackMain.setSize(100, 30);
		btnSearchBackMain.addActionListener(this);
		btnSearchBackMain.setLocation(100, 525);

		btnMyUpBackMain = new JButton("主界面");
		btnMyUpBackMain.setSize(100, 30);
		btnMyUpBackMain.addActionListener(this);
		btnMyUpBackMain.setLocation(100, 500);

		btnUpBackMain = new JButton("主界面");
		btnUpBackMain.setSize(100, 30);
		btnUpBackMain.addActionListener(this);
		btnUpBackMain.setLocation(150, 500);

		btnRegister = new JButton("注册");
		btnRegister.setSize(60, 30);
		btnRegister.addActionListener(this);
		btnRegister.setLocation(100, 300);

		btnRegisterOk = new JButton("确定");
		btnRegisterOk.setSize(60, 30);
		btnRegisterOk.addActionListener(this);
		btnRegisterOk.setLocation(200, 300);

		btnBack = new JButton("返回");
		btnBack.setSize(60, 30);
		btnBack.addActionListener(this);
		btnBack.setLocation(100, 300);

		btnMyUpDelete = new JButton("删除");
		btnMyUpDelete.setSize(60, 30);
		btnMyUpDelete.addActionListener(this);
		btnMyUpDelete.setLocation(225, 500);

		btnSearchDownloadFile = new JButton("下载");
		btnSearchDownloadFile.setSize(60, 30);
		btnSearchDownloadFile.addActionListener(this);
		btnSearchDownloadFile.setLocation(225, 525);

		btnBeginUp = new JButton("上传");
		btnBeginUp.setSize(60, 30);
		btnBeginUp.addActionListener(this);
		btnBeginUp.setLocation(150, 150);

		btnSelectFile = new JButton("选择");
		btnSelectFile.setSize(60, 30);
		btnSelectFile.addActionListener(this);
		btnSelectFile.setLocation(325, 100);

		textFieldLoginName = new JTextField();
		textFieldLoginName.setSize(150, 30);
		textFieldLoginName.setLocation(125, 100);

		textFieldLoginPasswd = new JPasswordField();
		textFieldLoginPasswd.setSize(150, 30);
		textFieldLoginPasswd.setLocation(125, 200);

		textFieldRegisterName = new JTextField();
		textFieldRegisterName.setSize(150, 30);
		textFieldRegisterName.setLocation(125, 100);

		textFieldRegisterPasswd = new JPasswordField();
		textFieldRegisterPasswd.setSize(150, 30);
		textFieldRegisterPasswd.setLocation(125, 200);

		textFieldSearchInput = new JTextField();
		textFieldSearchInput.setSize(200, 30);
		textFieldSearchInput.setLocation(75, 100);

		textFieldFilePath = new JTextField();
		textFieldFilePath.setSize(200, 30);
		textFieldFilePath.setLocation(100, 100);

		tableSearchResultShow = new JTable();
		NotEditTableModel tableSearchModel = new NotEditTableModel();
		tableSearchModel.addColumn("文件名");
		tableSearchModel.addColumn("文件大小");
		tableSearchModel.addColumn("上传用户");
		tableSearchModel.addColumn("是否在线");
		tableSearchResultShow.setModel(tableSearchModel);
		JScrollPane spSearchTable = new JScrollPane(tableSearchResultShow);
		JPanel panelTable = new JPanel();
		panelTable.setLayout(new GridLayout(0, 1));
		panelTable.setBounds(50, 150, 300, 350);
		panelTable.add(spSearchTable);

		tableMyUpShow = new JTable();
		NotEditTableModel tableMyUpModel = new NotEditTableModel();
		tableMyUpModel.addColumn("文件名");
		tableMyUpModel.addColumn("文件路径");
		tableMyUpModel.addColumn("文件大小");
		tableMyUpShow.setModel(tableMyUpModel);
		JScrollPane spMyUpTable = new JScrollPane(tableMyUpShow);
		JPanel panelTable2 = new JPanel();
		panelTable2.setLayout(new GridLayout(0, 1));
		panelTable2.setBounds(50, 100, 300, 350);
		panelTable2.add(spMyUpTable);

		panelLogin = new JPanel();
		panelLogin.setLayout(null);
		panelLogin.setBounds(0, 0, 400, 600);
		panelLogin.add(btnLogin);
		panelLogin.add(btnRegister);
		panelLogin.add(textFieldLoginName);
		panelLogin.add(textFieldLoginPasswd);
		panelLogin.add(labelLoginPasswd);
		panelLogin.add(labelLoginName);
		panelLogin.add(labelFlagLogin);

		panelRegister = new JPanel();
		panelRegister.setLayout(null);
		panelRegister.setBounds(0, 0, 400, 600);
		panelRegister.add(btnBack);
		panelRegister.add(btnRegisterOk);
		panelRegister.add(textFieldRegisterName);
		panelRegister.add(textFieldRegisterPasswd);
		panelRegister.add(labelRegisterPasswd);
		panelRegister.add(labelRegisterName);
		panelRegister.add(labelFlagRegister);

		panelMain = new JPanel();
		panelMain.setLayout(null);
		panelMain.setBounds(0, 0, 400, 600);
		panelMain.add(btnSearch);
		panelMain.add(btnMyUp);
		panelMain.add(btnUp);
		panelMain.add(btnLogout);
		panelMain.add(labelFlagMain);

		panelUp = new JPanel();
		panelUp.setLayout(null);
		panelUp.setBounds(0, 0, 400, 600);
		panelUp.add(labelFlagUp);
		panelUp.add(btnUpBackMain);
		panelUp.add(labelFilePath);
		panelUp.add(textFieldFilePath);
		panelUp.add(btnBeginUp);
		panelUp.add(btnSelectFile);

		panelMyUp = new JPanel();
		panelMyUp.setLayout(null);
		panelMyUp.setBounds(0, 0, 400, 600);
		panelMyUp.add(labelFlagMyUp);
		panelMyUp.add(panelTable2);
		panelMyUp.add(btnMyUpBackMain);
		panelMyUp.add(btnMyUpDelete);

		panelSearch = new JPanel();
		panelSearch.setLayout(null);
		panelSearch.setBounds(0, 0, 400, 600);
		panelSearch.add(labelFlagSearch);
		panelSearch.add(labelFileName);
		panelSearch.add(textFieldSearchInput);
		panelSearch.add(btnSearchSubmit);
		panelSearch.add(panelTable);
		panelSearch.add(btnSearchBackMain);
		panelSearch.add(btnSearchDownloadFile);

		frameIndex = new JFrame("客户端");
		frameIndex.setLocation(350, 100);
		frameIndex.setLayout(null);
		frameIndex.setSize(400, 600);
		frameIndex.setVisible(true);
		LoginShow();

		frameIndex.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("客户端退出");
				out.println(5 + "%" + " " + "%" + " ");
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String strBtnClick = e.getActionCommand();
		if (strBtnClick.equals("登录")) {
			String name = textFieldLoginName.getText();
			String passwd = textFieldLoginPasswd.getText();

			String myIp = null;
			try {
				if (System.getProperty("os.name").charAt(0) == 'W') {
					myIp = InetAddress.getLocalHost().getHostAddress();
				} else {
					myIp = getMyIp();
				}

			} catch (SocketException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println(myIp);
			if (name.equals("") || passwd.equals("") || myIp.length() < 1) {

			} else {
				out.println(1 + "%" + name + "%" + passwd + "%"
						+ fileServerPort + "%" + myIp);
				try {
					String str = in.readLine();
					if (str.equals("yes")) {
						System.out.println("登录成功");
						MainShow();
					} else if (str.equals("no")) {
						JOptionPane.showMessageDialog(null,
								"登录失败请检查您的帐号的密码是否正确", "waring",
								JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "系统错误请重新登录",
								"waring", JOptionPane.WARNING_MESSAGE);
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (strBtnClick.equals("确定")) {

			String name = textFieldRegisterName.getText();
			String passwd = textFieldRegisterPasswd.getText();
			if (name.equals("") || passwd.equals("")) {

			} else {
				String myIp = null;
				try {
					myIp = getMyIp();
				} catch (SocketException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				out.println(2 + "%" + name + "%" + passwd + "%"
						+ fileServerPort + "%" + myIp);
				try {
					String str = in.readLine();
					if (str.equals("yes")) {
						JOptionPane.showMessageDialog(null, "注册成功",
								"waring", JOptionPane.WARNING_MESSAGE);
						LoginShow();
					} else if (str.equals("no")) {
						JOptionPane.showMessageDialog(null, "该用户名已存在，请请重新注册",
								"waring", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "系统错误请请重新注册",
								"waring", JOptionPane.WARNING_MESSAGE);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (strBtnClick.equals("返回")) {
			LoginShow();
		} else if (strBtnClick.equals("主界面")) {
			MainShow();
		} else if (strBtnClick.equals("上传")) {
			String filepath = textFieldFilePath.getText();
			if (upFileName == null || upFileSize == 0 ) {
				JOptionPane.showMessageDialog(null, "请正确选择文件", "waring",
						JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				out.println(7 + "%" + upFileName + "%" + filepath + "%"
						+ upFileSize);
				try {
					String resultRec = in.readLine();
					if (resultRec.equals("yes")) {
						JOptionPane.showMessageDialog(null, "上传成功", "waring",
								JOptionPane.WARNING_MESSAGE);
					} else if (resultRec.equals("no")) {
						JOptionPane.showMessageDialog(null, "您已经上传该文件，请不要重复上传",
								"waring", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "系统错误，请重新上传",
								"waring", JOptionPane.WARNING_MESSAGE);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (strBtnClick.equals("选择")) {
			JFileChooser jf = new JFileChooser("Title...");
			jf.setDialogTitle("Chose...");
			int result = jf.showOpenDialog(frameIndex);
			jf.setVisible(true);
			File selectedFile = null;
			if (result == JFileChooser.APPROVE_OPTION) {
				selectedFile = jf.getSelectedFile();
				if (selectedFile.exists()) {
					textFieldFilePath.setText(selectedFile.getPath());
					upFileSize = selectedFile.length() / 1024;
					upFileName = selectedFile.getName();
					System.out.println(upFileSize);
				} else {
					System.out
							.println("No! You did not select the right file.");
				}
			} else if (result == JFileChooser.CANCEL_OPTION) {
				// System.out.println("Cancel button is pushed.");
			} else if (result == JFileChooser.ERROR_OPTION) {
				// System.err.println("Error when select file.");
			}
		} else if (strBtnClick.equals("注册")) {
			RegisterShow();
		} else if (strBtnClick.equals("删除")) {
			int selectRow = tableMyUpShow.getSelectedRow();
			NotEditTableModel tableModel = (NotEditTableModel) tableMyUpShow
					.getModel();
			String filename = (String) tableModel.getValueAt(selectRow, 0);
			System.out.println(filename);
			out.println(8 + "%" + filename + "%" + " ");
			try {
				String resultRec = in.readLine();
				if (resultRec.equals("yes")) {
					JOptionPane.showMessageDialog(null, "删除成功", "waring",
							JOptionPane.WARNING_MESSAGE);
					tableModel.setRowCount(0);
					while (true) {
						try {

							String rec = in.readLine();
							if (rec.equals("eof")) {
								break;
							} else {

								String[] resultArray = rec.split("%");
								tableModel.addRow(resultArray);
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else if (resultRec.equals("no")) {
					JOptionPane.showMessageDialog(null, "您选择的文件不存在", "waring",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "系统错误，请重新上传", "waring",
							JOptionPane.WARNING_MESSAGE);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (strBtnClick.equals("下载")) {
			int selectRow = tableSearchResultShow.getSelectedRow();
			NotEditTableModel tableModel = (NotEditTableModel) tableSearchResultShow
					.getModel();
			String online = (String) tableModel.getValueAt(selectRow, 3);
			if (online.equals("offline")) {
				JOptionPane.showMessageDialog(null, "您选择的文件上传用户不在线，请稍候下载",
						"waring", JOptionPane.WARNING_MESSAGE);
			} else {
				String filename = (String) tableModel.getValueAt(selectRow, 0);
				String username = (String) tableModel.getValueAt(selectRow, 2);
				out.println(9 + "%" + filename + "%" + username);
				System.out.println(filename);
				try {
					String rec = in.readLine();
					String[] recArray = rec.split("%");
					String downloadIp = recArray[0];
					String downloadPort = recArray[1];
					String downloadFilePath = recArray[2];
					System.out.println(downloadIp + ":" + downloadPort + ":"
							+ downloadFilePath);
					int port = Integer.parseInt(downloadPort);
					DownloadFile(downloadIp, port, downloadFilePath, filename);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (strBtnClick.equals("注销")) {
			out.println(6 + "%" + " " + "%" + " ");
			LoginShow();
		} else if (strBtnClick.equals("搜索资源")) {
			SearchShow();
		} else if (strBtnClick.equals("我的上传")) {
			out.println(4 + "%" + " " + "%" + " ");
			NotEditTableModel tableModel = (NotEditTableModel) tableMyUpShow
					.getModel();
			tableModel.setRowCount(0);
			while (true) {
				try {

					String rec = in.readLine();
					if (rec.equals("eof")) {
						break;
					} else {

						String[] resultArray = rec.split("%");
						tableModel.addRow(resultArray);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			MyUpShow();
		} else if (strBtnClick.equals("上传文件")) {
			UpShow();
		} else if (strBtnClick.equals("搜索")) {
			String filename = textFieldSearchInput.getText();
			if (filename.length() > 0) {
				out.println(3 + "%" + filename + "%" + " ");
				NotEditTableModel tableModel = (NotEditTableModel) tableSearchResultShow
						.getModel();
				tableModel.setRowCount(0);
				while (true) {
					try {

						String rec = in.readLine();
						if (rec.equals("eof")) {
							break;
						} else {

							String[] resultArray = rec.split("%");
							tableModel.addRow(resultArray);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}

	public static String getMyIp() throws SocketException {
		String ip = "";
		Enumeration<NetworkInterface> netInterfaces = null;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				if (ni.getName().equals("eth0")) {
					Enumeration<InetAddress> ips = ni.getInetAddresses();
					while (ips.hasMoreElements()) {
						String ipHost = ips.nextElement().getHostAddress();
						if (ipHost.indexOf(":") == -1) {
							ip = ipHost;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip;
	}

	private void DownloadFile(String ip, int port, String filepath,
			String filename) throws IOException {
		InetAddress addr = null;
		try {
			addr = InetAddress.getByName(ip);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Socket fileReceiveSocket = new Socket(addr, port);

		String savePath = "/home/wait/Desktop/" + filename;
		DataOutputStream fileOut = new DataOutputStream(
				new BufferedOutputStream(new BufferedOutputStream(
						new FileOutputStream(savePath))));
		DataInputStream dis = new DataInputStream(new BufferedInputStream(
				fileReceiveSocket.getInputStream()));
		PrintWriter fileout = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(fileReceiveSocket.getOutputStream())),
				true);
		fileout.println(filepath);

		int bufferSize = 8192;
		byte[] buf = new byte[bufferSize];

		while (true) {

			int read = 0;
			if (dis != null) {
				read = dis.read(buf);
			}
			if (read == -1) {
				break;
			}

			fileOut.write(buf, 0, read);
		}
		JOptionPane.showMessageDialog(null, "接收完成，文件存为" + savePath, "waring",
				JOptionPane.WARNING_MESSAGE);
		fileOut.close();
		fileReceiveSocket.close();
	}

	public static void main(String[] args) throws IOException {
		MainWindow mw = new MainWindow();
		ServerSocket fileServer = new ServerSocket(mw.fileServerPort);
		System.out.println("The File Server is start: " + fileServer);
		// 阻塞,直到有客户端连接
		try {
			for (;;) {
				Socket socket = fileServer.accept();
				new ClientThread(socket);
			}
		} finally {
			fileServer.close();
		}
	}
}