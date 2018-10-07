package SqlTran;

import java.sql.*;

import StartSys.SqlWork;

public class SqlManage {
	public static final String drivername = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String dburl = "jdbc:sqlserver://localhost:1433;DatabaseName=TicketMS";
	public static final String username = "kourui";
	public static final String userpwd = "123456";
	public static Connection dbconn = null;
	
	public static void ConnectToServer(){
		try{
			Class.forName(drivername);
			dbconn = DriverManager.getConnection(dburl,username,userpwd);
			System.out.println("���ݿ����ӳɹ�");
			
			//test
			Statement stmt = dbconn.createStatement();
			ResultSet rs = null;
			System.out.println("���ݿ����Ӳ��Բ�ѯAccountInfo");
			String sql = "select * from AccountInfo";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String Account= rs.getString("Account");
				String Pwd = rs.getString("Pwd");
				//int grade = rs.getInt("grade");
				System.out.println(Account+" "+Pwd);
			
			}
			System.out.println("���ݿ����Ӳ��Գɹ���");
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	 
	/*select
	 * return:�������
	 * sql:SQL���
	 * result:������� 
	 */
	public static int Select(SqlWork sw){
		try{
			Statement stmt = dbconn.createStatement();
			
			sw.rs = stmt.executeQuery(sw.sql);
			//sw.rs.last();
			//int count = sw.rs.getInt(1);
			//sw.rs.beforeFirst();
			//sw.count = count;
			sw.addtolist();
			int count = sw.count;
			
			stmt.close();
			return count;
		}catch(SQLException se){
			se.printStackTrace();
			return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	/*
	 * Insert();
	 * 
	 */
	public static boolean Insert(SqlWork sw) {
		// TODO �Զ����ɵķ������
		try{
			Statement stmt = dbconn.createStatement();
			
			stmt.executeUpdate(sw.sql);
						
			stmt.close();
			return true;
		}catch(SQLException se){
			System.out.println("---------------Insert �쳣");
			se.printStackTrace();
			return false;
		}
		
	}
	/*
	 * �洢����
	 */
	public static boolean ExecPro(SqlWork sw){
		try{
			Statement stmt = dbconn.createStatement();
			
			stmt.executeUpdate(sw.sql);
						
			stmt.close();
			return true;
		}catch(SQLException se){
			System.out.println("---------------ExecPro�쳣");
			se.printStackTrace();
			return false;
		}
	}
	/*
	 * Delete
	 */
	public static boolean Delete(SqlWork sw){
		try{
			Statement stmt = dbconn.createStatement();
			
			stmt.executeUpdate(sw.sql);
						
			stmt.close();
			return true;
		}catch(SQLException se){
			System.out.println("---------------Delete�쳣");
			se.printStackTrace();
			return false;
		}
	}
	/*
	 * Update()
	 */
	public static boolean Update(SqlWork sw){
		try{
			Statement stmt = dbconn.createStatement();
			
			stmt.executeUpdate(sw.sql);
						
			stmt.close();
			return true;
		}catch(SQLException se){
			System.out.println("---------------Update�쳣");
			se.printStackTrace();
			return false;
		}
	}
}
