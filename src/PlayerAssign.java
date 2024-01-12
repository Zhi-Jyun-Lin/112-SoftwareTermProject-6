import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class PlayerAssign extends JFrame implements ActionListener {
	//創建新帳號
	public void assign(String playerid, String password, String username, String email) {
		    Connection c = null;
		    Statement stmt = null;
		    
		    try {
		        Class.forName("org.postgresql.Driver");
		        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java", "postgres", "mars19363");
		        System.out.println("Opened database successfully");
		        stmt = c.createStatement();

		        // 檢查主鍵唯一性
		        String checkUnique = "SELECT playerid FROM player WHERE playerid = '" + playerid + "'";
		        ResultSet resultSet = stmt.executeQuery(checkUnique);
		        if (resultSet.next()) {
		            // 如果已存在相同的 playerid，這裡可以加入錯誤處理邏輯
		            System.out.println("playerid already exists.");
		        } else {
		            // 進行插入
		        	String sql = "INSERT INTO player VALUES (DEFAULT,'" + playerid + "', '" + password + "', '" + username + "','" + email + "' )";
		            int rowsAffected = stmt.executeUpdate(sql);
		        }

		        stmt.close();
		        c.close();
		    } catch (SQLException se) {
		        // 處理 JDBC 錯誤
		        se.printStackTrace();
		        
		        
		    }catch (Exception e) {
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
		}

	private static JFrame userframe = new JFrame("註冊 ");
	private static JLabel newid = new JLabel("新增帳號");
	private static JLabel newpass = new JLabel("新增密碼");
	private static JLabel newcheck = new JLabel("確認密碼");
	private static JLabel newemail = new JLabel("電子信箱");
	private static JLabel username = new JLabel("使用者名稱");
	private static JTextField useridtext = new JTextField(20);
	private static JPasswordField  passtext = new JPasswordField (20);
	private static JPasswordField  checktext = new JPasswordField (20);
	private static JTextField emailtext = new JTextField(20);
	private static JTextField usernametext = new JTextField(10);
	private static JButton ensure = new JButton("確認");
	private static JButton turnback = new JButton("返回");
	private final JLabel titleLabel = new JLabel("遊戲玩家註冊");
	private static JLabel messageLabel = new JLabel("申請成功");

	PlayerAssign() {
		userframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userframe.setSize(800, 520);
		userframe.getContentPane().setLayout(null);
		userframe.setLocationRelativeTo(null); // 置中顯示
		userframe.setVisible(true);
		newid.setFont(new Font("微軟正黑體", Font.PLAIN, 16));

		newid.setBounds(258, 232, 82, 25);
		newpass.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		newpass.setBounds(258, 262, 82, 25);
		newcheck.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		newcheck.setBounds(258, 292, 82, 25);
		newemail.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		newemail.setBounds(258, 202, 82, 25);
		username.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		username.setBounds(258, 171, 82, 25);
		useridtext.setBounds(341, 232, 180, 25);
		passtext.setBounds(341, 262, 180, 25);
		checktext.setBounds(341, 292, 180, 25);
		emailtext.setBounds(341, 202, 180, 25);
		usernametext.setBounds(341, 171, 180, 25);
		ensure.setBackground(new Color(0, 0, 0));
		ensure.setForeground(new Color(255, 255, 255));
		ensure.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		ensure.setBounds(447, 347, 74, 25);
		turnback.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		turnback.setBackground(new Color(0, 0, 0));
		turnback.setForeground(new Color(255, 255, 255));
		turnback.setBounds(12, 10, 80, 25);
		userframe.getContentPane().add(newid);
		userframe.getContentPane().add(newpass);
		userframe.getContentPane().add(newcheck);
		userframe.getContentPane().add(newemail);
		userframe.getContentPane().add(useridtext);
		userframe.getContentPane().add(passtext);
		userframe.getContentPane().add(checktext);
		userframe.getContentPane().add(emailtext);
		userframe.getContentPane().add(ensure);
		userframe.getContentPane().add(turnback);

		turnback.addActionListener(this);
		ensure.addActionListener(this);

		userframe.getContentPane().add(username);
		userframe.getContentPane().add(usernametext);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		titleLabel.setBounds(258, 98, 263, 41);
		
		userframe.getContentPane().add(titleLabel);
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setForeground(new Color(255, 0, 0));
		messageLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		messageLabel.setSize(263, 25);
		messageLabel.setLocation(258, 390);
		userframe.getContentPane().add(messageLabel);
		messageLabel.setVisible(false);

	}

	public void actionPerformed(ActionEvent e) {

	    String userid = useridtext.getText();
	    String password = passtext.getText();
	    String check = checktext.getText();
	    String username = usernametext.getText();
	    String email = emailtext.getText();

	    char[] unvaildChar = { ' ', '\'', '"', '?', '*', '~', ',', '!' };
	    boolean isNumeric = true;
	    boolean anything = true;
	    if (userid.equals("")) {
	        anything = false;
	    }
	    if (password.equals("")) {
	        anything = false;
	    }
	    if (check.equals("")) {
	        anything = false;
	    }
	    if (email.equals("")) {
	        anything = false;
	    }
	    if (usernametext.getText().equals("")) {
	        anything = false;
	    }
	   
	    if (anything == false && e.getSource() == ensure) {
	        messageLabel.setText("任一處不得為空白");
	        messageLabel.setVisible(true);
	    }

	    if (e.getSource() == turnback) {
	        userframe.dispose();
	        PlayerLogin log = new PlayerLogin();
	    }

	    a: if (!password.equals("") && !userid.equals("") && !check.equals("") && !email.equals("")
	            && !usernametext.getText().equals("") && e.getSource() == ensure) {
	        for (char ch : unvaildChar)
	            if (userid.contains(Character.toString(ch)) || password.contains(Character.toString(ch))
	                    || check.contains(Character.toString(ch)) || email.contains(Character.toString(ch))) {
	                messageLabel.setText("帳密電郵存在非法字元");
	                messageLabel.setVisible(true);
	                break a;
	            }
	      
	        if (userid.length() > 18 || userid.length() < 6 || password.length() > 18 || password.length() < 6
	                || check.length() >= 18 || check.length() < 6) {
	            messageLabel.setText("帳密字數需介於6~18字元");
	            messageLabel.setVisible(true);
	            break a;
	        }

	        else if (!password.equals(check)) {
	            messageLabel.setText("前後密碼不一致");
	            messageLabel.setVisible(true);
	            break a;
	        }

	        else {
	            messageLabel.setText("申請成功");
	            messageLabel.setForeground(Color.BLUE);
	            messageLabel.setVisible(true);
	            assign(userid, password,username, email);
	            break a;
	        }
	    }
	}
}