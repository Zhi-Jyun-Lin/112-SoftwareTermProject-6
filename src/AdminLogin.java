import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

import java.sql.*;
import java.awt.Font;
import java.awt.Color;

public class AdminLogin implements ActionListener {
	// 查詢帳號，進行認證
	public String check(String username, String userpassword) {
		Connection c = null;
		Statement stmt = null;
		String answer = "false";
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java", "postgres", "mars19363");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String sql = "SELECT \"adminId\", \"password\" FROM \"Admin\"";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String adminId = rs.getString("adminId");
				String password = rs.getString("password");

				if ((adminId.equals(username) && password.equals(userpassword))) {
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

	protected static JLabel fail = new JLabel("登入失敗! 請檢查帳號或密碼");
	private static JLabel nothing = new JLabel();
	private static JLabel noright = new JLabel();
	private static JLabel forgetLabel = new JLabel("忘記密碼");
	private static JTextField usertext = new JTextField(20);
	private static JPasswordField passwordtext = new JPasswordField(20);
	private static JButton login = new JButton("登入");
	private static JFrame frame = new JFrame("登入介面");
	private static JPanel panel = new JPanel();
	private static JButton turnback = new JButton("返回");
	public static int check = 0;
	private static JPanel bump = new JPanel();
	private static JButton testButton = new JButton("進入");

	AdminLogin() {

		frame.setSize(800, 520);
		frame.setLocationRelativeTo(null); // 置中顯示
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(panel);
		panel.setLayout(null);

		frame.getContentPane().add(bump);
		bump.setLayout(null);

		usertext.setBounds(321, 180, 172, 25);
		frame.getContentPane().add(usertext);

		passwordtext.setBounds(321, 220, 172, 25);
		frame.getContentPane().add(passwordtext);
		login.setForeground(new Color(255, 255, 255));
		login.setBackground(new Color(0, 0, 0));
		login.setFont(new Font("微軟正黑體", Font.BOLD, 16));

		login.setBounds(321, 265, 172, 25);
		frame.getContentPane().add(login);
		nothing.setHorizontalAlignment(SwingConstants.CENTER);

		nothing.setBounds(270, 370, 223, 25);
		frame.getContentPane().add(nothing);
		nothing.setVisible(true);

		noright.setBounds(155, 300, 150, 25);
		frame.getContentPane().add(noright);
		noright.setVisible(true);
		turnback.setForeground(new Color(255, 255, 255));
		turnback.setBackground(new Color(0, 0, 0));
		turnback.setFont(new Font("微軟正黑體", Font.BOLD, 16));

		turnback.setBounds(12, 10, 80, 25);
		frame.getContentPane().add(turnback);
		turnback.setVisible(true);
		fail.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		fail.setForeground(new Color(255, 0, 0));
		fail.setHorizontalAlignment(SwingConstants.CENTER);

		fail.setBounds(321, 335, 172, 25);
		frame.getContentPane().add(fail);
		fail.setVisible(false);

		login.addActionListener(this);

		turnback.addActionListener(this);

		forgetLabel.setForeground(new Color(128, 128, 128));
		forgetLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		forgetLabel.setBounds(375, 300, 65, 25);
		frame.getContentPane().add(forgetLabel);

		// 添加鼠標事件監聽器
		forgetLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 處理點選事件，可以在這裡執行忘記密碼相關的操作
				frame.dispose();
				AdminForgetPassword tf = new AdminForgetPassword();
			}
		});

		passwordtext.setEchoChar('*');

		JLabel title = new JLabel("你好，管理員");
		title.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		title.setBounds(307, 105, 186, 31);
		frame.getContentPane().add(title);

		JLabel account = new JLabel("帳號：");
		account.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		account.setBounds(261, 180, 48, 25);
		frame.getContentPane().add(account);

		JLabel password = new JLabel("密碼：");
		password.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		password.setBounds(261, 220, 54, 25);
		frame.getContentPane().add(password);

		testButton = new JButton("進入");
		testButton.setForeground(new Color(128, 128, 128));
		testButton.setBackground(new Color(192, 192, 192));
		testButton.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		testButton.setBounds(685, 450, 89, 23);
		frame.getContentPane().add(testButton);
		testButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = usertext.getText();
		String userpassword = passwordtext.getText();

		if (e.getSource() == login) {
			String type = check(username, userpassword);

			if (type.equals("true")) {
				frame.dispose();
				AdminPage sys = new AdminPage();
				sys.setVisible(true);
			} else {
				fail.setVisible(true);
			}
		}
		if (e.getSource() == turnback) {
			frame.dispose();
			CharacterGui cha = new CharacterGui();
		} else if (e.getSource() == forgetLabel) {
			frame.dispose();
			AdminForgetPassword tf = new AdminForgetPassword();
		} else if (e.getSource() == testButton) {
			 AdminPage sys = new AdminPage();
			    sys.setVisible(true);
			    frame.dispose();
		}
	}

	

}