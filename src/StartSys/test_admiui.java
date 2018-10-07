package StartSys;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Info.AccountInfo;
import SqlTran.SqlManage;

public class test_admiui {
	public JFrame frame;
	private JPanel contentpane;
	//private JTextField addtext_trainno;
	private JTextField addtext_startsta;
	private JTextField addtext_endsta;
	private JTextField addtext_price;
	
	private JTextField deletext_trainno;
	
	private JTextField seletext_trainno;
	
	private JTextField updatext_trainno;
	private JTextField updatext_startsta;
	private JTextField updatext_endsta;
	private JTextField updatext_price;
	
	public static void main(String[] args){
		test_admiui testui = new test_admiui();
		testui.frame.setVisible(true);
	}
	
	public test_admiui(){
		SqlManage.ConnectToServer();
		initialize();
	}
	
	private void initialize(){
		frame = new JFrame();
		frame.setTitle("����Ա����TicketMS���ݿ�");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/image/admi.png")));
		frame.setBounds(100,60,800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentpane = new JPanel();
		//��contentpane��������Ϊframe���������
		frame.setContentPane(contentpane);
		contentpane.setLayout(null);
		
		JLabel admi = new JLabel("����Ա����TicketMS");
		admi.setFont(new Font("����",Font.PLAIN,16));
		admi.setBounds(280,10,350,30);
		contentpane.add(admi);
		
		JLabel add_data = new JLabel("�����г���Ϣ");
		add_data.setFont(new Font("����",Font.PLAIN,14));
		add_data.setBounds(100,50,100,30);
		contentpane.add(add_data);
		
		//����ʼ��վ��Ϣ
		JLabel add_startsta = new JLabel("ʼ��վ");
		add_startsta.setFont(new Font("����",Font.PLAIN,14));
		add_startsta.setBounds(20,90,50,30);
		contentpane.add(add_startsta);
		
		addtext_startsta = new JTextField();
		addtext_startsta.setBounds(80,90,200,30);
		addtext_startsta.setColumns(10);
		contentpane.add(addtext_startsta);
		
		//�����յ�վ��Ϣ
		JLabel add_endsta = new JLabel("�յ�վ");
		add_endsta.setFont(new Font("����",Font.PLAIN,14));
		add_endsta.setBounds(20,130,50,30);
		contentpane.add(add_endsta);
		
		addtext_endsta = new JTextField();
		addtext_endsta.setBounds(80,130,200,30);
		addtext_endsta.setColumns(10);
		contentpane.add(addtext_endsta);
		
		//���Ӽ۸���Ϣ
		JLabel add_price = new JLabel("�۸�");
		add_price.setFont(new Font("����",Font.PLAIN,14));
		add_price.setBounds(20,170,50,30);
		contentpane.add(add_price);
		
		addtext_price = new JTextField();
		addtext_price.setBounds(80,170,200,30);
		addtext_price.setColumns(10);
		contentpane.add(addtext_price);
		
		//add_button
		JButton add_button = new JButton("ȷ�����");
		add_button.setBounds(80,220,120,30);
		contentpane.add(add_button);
		add_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				SqlWork isw = new SqlWork();
				String start=addtext_startsta.getText();
				String end = addtext_endsta.getText();
				String price = addtext_price.getText();
				isw.sql="exec add_traininfo \'"+start+"\',\'"+end+"\',"+price;
				System.out.println(isw.sql);
				if(true == SqlManage.ExecPro(isw)){
					JOptionPane.showMessageDialog(frame, "��ӳɹ���");
				}
				else{
					JOptionPane.showMessageDialog(frame, "���ʧ��");
				}
			}
				
		});
		
		JLabel dele_data = new JLabel("ɾ���г���Ϣ");
		dele_data.setFont(new Font("����",Font.PLAIN,14));
		dele_data.setBounds(460,50,100,30);
		contentpane.add(dele_data);
		
		JLabel dele_label = new JLabel("ɾ�����г���");
		dele_label.setFont(new Font("����",Font.PLAIN,14));
		dele_label.setBounds(400,90,100,30);
		contentpane.add(dele_label);
		
		deletext_trainno = new JTextField();
		deletext_trainno.setBounds(520,90,150,30);
		deletext_trainno.setColumns(20);
		contentpane.add(deletext_trainno);
		
		//ɾ����ʾ��Ϣ���г��Ŷ�Ӧ�������Ϣ��
		JLabel tip_label=new JLabel("tip:");
		tip_label.setFont(new Font("����",Font.PLAIN,12));
		tip_label.setBounds(400,140,30,30);
		contentpane.add(tip_label);
		
		final JLabel dele_tip = new JLabel("------nothing to show--------");
		dele_tip.setFont(new Font("����",Font.PLAIN,12));
		dele_tip.setBounds(440,140,360,30);
		contentpane.add(dele_tip);
		
		
		JButton tip_button = new JButton("�鿴��ʾ");
		tip_button.setBounds(400,180,100,30);
		contentpane.add(tip_button);
		
		tip_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				String trainno = deletext_trainno.getText();
				String tipstr = "";
				SqlWork ssw = new SqlWork();
				ssw.sql="select * from TrainInfo where Train_no=\'"+trainno+"\'";
				int count = SqlManage.Select(ssw);
				System.out.println("querying the tip infomation...............");
				if(count == 0){
					dele_tip.setText("û���ҵ���Ҫɾ�����г���");
				}
				else{
					tipstr=ssw.datalist.get(0);			
					dele_tip.setText(tipstr);					
				}	
				System.out.println("query ok...............");	
			}
		});

		JButton dele_button = new JButton("ȷ��ɾ��");
		dele_button.setBounds(520,180,100,30);
		contentpane.add(dele_button);
		
		dele_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				System.out.println("deleting date.....................");
				SqlWork dsw = new SqlWork();
				String trainno = deletext_trainno.getText();
				dsw.sql = "delete TrainInfo where Train_no=\'"+trainno+"\'";
				System.out.println(dsw.sql);
				if(true == SqlManage.Delete(dsw)){
					JOptionPane.showMessageDialog(frame, "ɾ���ɹ���");
				}
				else{
					JOptionPane.showMessageDialog(frame, "ɾ��ʧ��");
				}
			}
				
		});
		
		//��ѯ�г���Ϣ
		JLabel sele_data = new JLabel("��ѯ�г���Ϣ");
		sele_data.setFont(new Font("����",Font.PLAIN,14));
		sele_data.setBounds(100,280,100,30);
		contentpane.add(sele_data);
		
		JLabel sele_label = new JLabel("��ѯ�г���");
		sele_label.setFont(new Font("����",Font.PLAIN,14));
		sele_label.setBounds(20,320,100,30);
		contentpane.add(sele_label);
		
		seletext_trainno = new JTextField();
		seletext_trainno.setBounds(130,320,150,30);
		seletext_trainno.setColumns(20);
		contentpane.add(seletext_trainno);
		
		final JLabel selename_label = new JLabel("----------name-----------");
		selename_label.setFont(new Font("����",Font.PLAIN,12));
		selename_label.setBounds(20,360,300,30);
		contentpane.add(selename_label);
		
		final JLabel seledata_label = new JLabel("----------data------------");
		seledata_label.setFont(new Font("����",Font.PLAIN,12));
		seledata_label.setBounds(20,400,300,30);
		contentpane.add(seledata_label);
		
		JButton sele_button = new JButton("��ѯ");
		sele_button.setBounds(70,450,80,30);
		contentpane.add(sele_button);
		
		sele_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				System.out.println("querying data.....................");
				SqlWork ssw = new SqlWork();
				String trainno = seletext_trainno.getText();
				String name="û���ҵ�����ѯ������";
				String data="û���ҵ�����ѯ������";
				ssw.sql = "select * from TrainInfo where Train_no=\'"+trainno+"\'";
				System.out.println(ssw.sql);
				if(0 == SqlManage.Select(ssw)){
					selename_label.setText(name);
					seledata_label.setText(data);
					JOptionPane.showMessageDialog(frame, "û�������ҵ����ݣ�");
				}
				else{
					name=ssw.namelist.get(0);
					data=ssw.datalist.get(0);
					selename_label.setText(name);
					seledata_label.setText(data);
				}
			}
		});
		
		
		JLabel upda_data = new JLabel("�޸��г���Ϣ");
		upda_data.setFont(new Font("����",Font.PLAIN,14));
		upda_data.setBounds(460,280,100,30);
		contentpane.add(upda_data);
		
		JLabel updatrainno_label = new JLabel("�����г���");
		updatrainno_label.setBounds(400,320,100,30);
		updatrainno_label.setFont(new Font("����",Font.PLAIN,14));
		contentpane.add(updatrainno_label);
		
		updatext_trainno = new JTextField();
		updatext_trainno.setBounds(510,320,200,30);
		updatext_trainno.setColumns(20);
		contentpane.add(updatext_trainno);
		
		JLabel updastartsta_label = new JLabel("ʼ��վ");
		updastartsta_label.setBounds(400,360,100,30);
		updastartsta_label.setFont(new Font("����",Font.PLAIN,14));
		contentpane.add(updastartsta_label);
		
		updatext_startsta = new JTextField();
		updatext_startsta.setBounds(510,360,200,30);
		updatext_startsta.setColumns(20);
		contentpane.add(updatext_startsta);
		
		JLabel updaendsta_label = new JLabel("�յ�վ");
		updaendsta_label.setBounds(400,400,100,30);
		updaendsta_label.setFont(new Font("����",Font.PLAIN,14));
		contentpane.add(updaendsta_label);
		
		updatext_endsta = new JTextField();
		updatext_endsta.setBounds(500,400,200,30);
		updatext_endsta.setColumns(20);
		contentpane.add(updatext_endsta);
		
		JLabel updaprice_label = new JLabel("�۸�");
		updaprice_label.setBounds(400,440,100,30);
		updaprice_label.setFont(new Font("����",Font.PLAIN,14));
		contentpane.add(updaprice_label);
		
		updatext_price = new JTextField();
		updatext_price.setBounds(500,440,200,30);
		updatext_price.setColumns(20);
		contentpane.add(updatext_price);
		
		JButton update_button = new JButton("����");
		update_button.setBounds(520,480,100,30);
		contentpane.add(update_button);
		update_button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				System.out.println("updating data.....................");
				SqlWork usw = new SqlWork();
				String trainno = updatext_trainno.getText();
				String start = updatext_startsta.getText();
				String end = updatext_endsta.getText();
				String price = updatext_price.getText();
				
				usw.sql = "update TrainInfo "
						+ "set Start_sta=\'"+start+"\',End_sta=\'"+end
						+"\',price=\'"+price+"\' where Train_no=\'"+trainno+"\'";
				System.out.println(usw.sql);
				if(true == SqlManage.Update(usw)){
					JOptionPane.showMessageDialog(frame, "���³ɹ�");
				}
				else{
					JOptionPane.showMessageDialog(frame, "����ʧ��");
				}
			}
			
		});
	
	}
}
