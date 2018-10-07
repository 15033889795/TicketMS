package StartSys;
import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Info.AccountInfo;
import javax.swing.JRadioButton;
import SqlTran.SqlManage;

import Info.AccountInfo;

public class MainUI{
	public JFrame frame;
	private JTextField textfield_1;
	private JTextField textfield_2;
	private JPanel contentpane;
	private JRadioButton normaluser;
	private JRadioButton administrator;
	
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable(){
//			public void run(){
//				try{
//					MainUI window = new MainUI();
//					window.frame.setVisible(true);
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//		});
		//SqlManage.ConnectToServer();
		MainUI window = new MainUI();
		FrameManage.Mainframe = window.frame;
		window.frame.setVisible(true);
	}
	
	public MainUI(){
		//�������ݿ⡰TicketMS��
		SqlManage.ConnectToServer();
		//��ʼ��ͼ���û�����
		initialize();
	}
	
	private void initialize(){
		frame = new JFrame();
		frame.setTitle("���ã���¼ϵͳ");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/myicon.png")));
		frame.setBounds(100,60,800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentpane = new JPanel();
		//��contentpane��������Ϊframe���������
		frame.setContentPane(contentpane);
		contentpane.setLayout(null);
		
		JLabel login_title = new JLabel("��·��Ʊ����ϵͳ��¼");
		login_title.setFont(new Font("����",Font.PLAIN,16));
		login_title.setBounds(300,10,400,30);
		contentpane.add(login_title);
		
		ImageIcon img = new ImageIcon("./src/image/train.png");//����ͼƬ����
		JLabel image_label = new JLabel();
		image_label.setBounds(320,50,400,400);
		image_label.setIcon(img);
		contentpane.add(image_label);
		
		JLabel account = new JLabel("�û���");
		account.setFont(new Font("����",Font.PLAIN,16));
		account.setBounds(40,80,60,100);
		contentpane.add(account);
		
		JLabel password = new JLabel("����");
		password.setFont(new Font("����",Font.PLAIN,16));
		password.setBounds(40,200,60,100);
		contentpane.add(password);
		
		textfield_1 = new JTextField();
		textfield_1.setBounds(120,120,140,30);
		textfield_1.setColumns(10);
		contentpane.add(textfield_1);
		
		textfield_2 = new JTextField();
		textfield_2.setBounds(120,240,140,30);
		textfield_2.setColumns(10);
		contentpane.add(textfield_2);
		
		normaluser = new JRadioButton("��ͨ�û�");
		normaluser.setBounds(100,300,100,50);
		contentpane.add(normaluser);
		
		administrator = new JRadioButton("��Ʊ����Ա");
		administrator.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				normaluser.setSelected(false);
			}
		});
		administrator.setBounds(220,300,100,50);
		contentpane.add(administrator);
		normaluser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				administrator.setSelected(false);
			}
		});
		
		JButton regist = new JButton("ע��");
		regist.setBounds(120,400,80,30);
		contentpane.add(regist);
		regist.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				String account = textfield_1.getText();
				String password = textfield_2.getText();
				if(false == AccountInfo.Regist(account,password)){
					JOptionPane.showMessageDialog(frame, "���ã�ע��ʧ��");
				}
				else{
					JOptionPane.showMessageDialog(frame, "���ѳɹ�ע�ᣡ");
					if(true == normaluser.isSelected()){
						frame.dispose();
						NormalUserUI normaluser_ui = new NormalUserUI();
						//normaluser_ui.frame.setVisible(true);
						
					}
					else{
						frame.dispose();
						AdministratorUI administrator_ui = new AdministratorUI();
						//administrator_ui.frame.setVisible(true);
						
					}
				}
			}
				
		});
		
		
		JButton login = new JButton("��¼");
		login.setBounds(260,400,80,30);
		contentpane.add(login);
		login.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				if(!(normaluser.isSelected()) && !(administrator.isSelected())){
					JOptionPane.showMessageDialog(frame, "���ã���ѡ����ݣ���ͨ�û�/�г�����Ա");
					return ;
				}
				String account = textfield_1.getText();
				String password = textfield_2.getText();
				if(account.equals("")||account==null){
					JOptionPane.showMessageDialog(frame, "���ã��û�������Ϊ��");
					return ;
				}else if(password.equals("")||password==null){
					JOptionPane.showMessageDialog(frame, "���ã����벻��Ϊ��");
					return ;
				}
				
				if(false == AccountInfo.CheckAccount(account, password)){
					JOptionPane.showMessageDialog(frame, "�û����������");
				}
				else{
					JOptionPane.showMessageDialog(frame, "��¼�ɹ�!");
					if(true == normaluser.isSelected()){
						frame.dispose();
						NormalUserUI normaluser_ui = new NormalUserUI();
						//normaluser_ui.frame.setVisible(true);
						
					}
					else{
						frame.dispose();
						AdministratorUI administrator_ui = new AdministratorUI();
						//administrator_ui.frame.setVisible(true);
						
					}
				}
						
			}
		
		});
		
		//������Ϣ
		JLabel authorinfo = new JLabel();
		authorinfo.setBounds(400,480,300,30);
		authorinfo.setFont(new Font("����",Font.PLAIN,14));
		authorinfo.setText("����-2015302555-10011501-���ݿ����ҵ");
		contentpane.add(authorinfo);
	}

}
