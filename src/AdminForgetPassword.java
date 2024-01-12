import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class AdminForgetPassword implements ActionListener {

	private static JFrame userframe = new JFrame("忘記密碼 ");
	private static JLabel user = new JLabel("帳號：");
	private static JLabel pass = new JLabel("新密碼：");
	private static JTextField usertext = new JTextField(20);
	private static JTextField passText = new JTextField(20);
	private static JButton ensure = new JButton("確認");
	private static JButton backaway = new JButton("返回");
	private final JLabel lblNewLabel = new JLabel("忘記密碼了嗎？");
	private static JFrame bumpframe = new JFrame("密碼重設");
	protected static JLabel result = new JLabel("密碼修改成功");
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminForgetPassword window = new AdminForgetPassword();
					window.userframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminForgetPassword() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		userframe.getContentPane().setLayout(null);
		userframe.setLocationRelativeTo(null); // 置中顯示
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(301, 140, 206, 41);
		
		userframe.getContentPane().add(lblNewLabel);
		ensure.setBounds(314, 306, 180, 25);
		userframe.getContentPane().add(ensure);
		pass.setBounds(234, 251, 80, 25);
		userframe.getContentPane().add(pass);		
		user.setBounds(251, 214, 48, 25);
		userframe.getContentPane().add(user);
		passText.setBounds(314, 251, 180, 25);
		userframe.getContentPane().add(passText);
		usertext.setBounds(314, 214, 180, 25);
		userframe.getContentPane().add(usertext);
		result.setHorizontalAlignment(SwingConstants.CENTER);
		result.setForeground(new Color(0, 0, 255));
		result.setBounds(315, 357, 179, 15);
		userframe.getContentPane().add(result);
		
		userframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userframe.setSize(800, 520);
		user.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		ensure.setBackground(new Color(0, 0, 0));
		ensure.setForeground(new Color(255, 255, 255));
		ensure.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		backaway.setBounds(12, 10, 80, 25);
		
		
		backaway.setBackground(new Color(0, 0, 0));
		backaway.setForeground(new Color(255, 255, 255));
		backaway.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		
		userframe.getContentPane().add(backaway);
		pass.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		userframe.getContentPane().add(pass);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 28));
		
		userframe.setVisible(true);
		result.setVisible(false);

		ensure.addActionListener(this);
		backaway.addActionListener(this);
		

	}
	// 更改密碼
		public void alterpass(String adminId, String password) {
			Connection c = null;
			Statement stmt = null;
			try {
				Class.forName("org.postgresql.Driver");
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java", "postgres", "mars19363");
				System.out.println("Opened database successfully");
				stmt = c.createStatement();
				String sql = "UPDATE \"Admin\" SET \"password\"='" + password + "' WHERE \"adminId\"='" + adminId + "'";
				ResultSet rs = stmt.executeQuery(sql);

				rs.close();
				stmt.close();
				c.close();
			} catch (SQLException se) {
				// 處理 JDBC 錯誤
			} catch (Exception e) {
				// 處理 Class.forName 錯誤
				e.printStackTrace();
			} finally {
				// 關閉資源
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
				} // 什么都不做
				try {
					if (c != null)
						c.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

		// 搜尋帳號
		public String check(String userid) {
			Connection c = null;
			Statement stmt = null;
			String answer = "false";
			try {
				Class.forName("org.postgresql.Driver");
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java", "postgres", "mars19363");
				System.out.println("Opened database successfully");
				stmt = c.createStatement();
				String sql;
				sql = "SELECT adminId FROM adminAccount";
				ResultSet rs = stmt.executeQuery(sql);

			
				while (rs.next()) {
					String adminId = rs.getString("adminId");

					if (adminId.equals(userid)) {
						answer = "true";
						break;
					}

				}
				rs.close();
				stmt.close();
				c.close();
			} catch (SQLException se) {
				// 處理 JDBC 錯誤
				se.printStackTrace();
			} catch (Exception e) {
				// 處理 Class.forName 錯誤
				e.printStackTrace();
			} finally {
				// 關閉資源
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
				} // 什麼都不做
				try {
					if (c != null)
						c.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			return answer;
		}

		@Override
	    public void actionPerformed(ActionEvent e) {
	        String user = usertext.getText();
	        String password = passText.getText();

	        if (e.getSource() == ensure) {// 確認修改密碼
	            if (user.isEmpty() || password.isEmpty()) {
	                result.setText("未輸入帳號或新密碼");
	                result.setForeground(Color.RED);
	                result.setVisible(true);
	            } else {
	                alterpass(user, password);
	                result.setText("密碼修改成功");
	                result.setForeground(Color.BLUE);
	                result.setVisible(true);
	                userframe.dispose();
	                AdminLogin sl = new AdminLogin();
	            }
	        } else if (e.getSource() == backaway) {// 返回
	            userframe.dispose();
	            AdminLogin sl = new AdminLogin();
	        }
	    }

}
