import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class CharacterGui implements ActionListener {
	private static JFrame chaframe = new JFrame("身份選擇");
	private static JLabel title = new JLabel("Welcome to LBN");
	private static JLabel title1 = new JLabel("tab your identity");
	private static JButton chabut1 = new JButton("管理員");
	private static JButton chabut2 = new JButton("聯盟者");
	private static JButton chabut3 = new JButton("廣告商");
	private static JButton chabut4 = new JButton("遊戲玩家");
	private static JButton chabut5 = new JButton("訪客進入");
	private static JLabel tips = new JLabel("");
	protected static int x = 0;
	

	CharacterGui() {

		chaframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		chaframe.setSize(800, 520);
		chaframe.getContentPane().setLayout(null);
		chaframe.setLocationRelativeTo(null); // 置中顯示
		chaframe.setVisible(true);
		title.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		title1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));

		title.setBounds(274, 70, 243, 30);
		title1.setBounds(316, 120, 159, 30);
		chabut1.setForeground(new Color(255, 255, 255));
		chabut1.setBackground(new Color(0, 0, 0));
		chabut1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		chabut1.setBounds(306, 160, 171, 30);
		chabut2.setBackground(new Color(0, 0, 0));
		chabut2.setForeground(new Color(255, 255, 255));
		chabut2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		chabut2.setBounds(306, 210, 171, 30);
		chabut3.setBackground(new Color(0, 0, 0));
		chabut3.setForeground(new Color(255, 255, 255));
		chabut3.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		chabut3.setBounds(306, 260, 171, 30);
		chabut4.setBackground(new Color(0, 0, 0));
		chabut4.setForeground(new Color(255, 255, 255));
		chabut4.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		chabut4.setBounds(306, 310, 171, 30);
		chabut5.setBackground(new Color(0, 0, 0));
		chabut5.setForeground(new Color(255, 255, 255));
		chabut5.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		chabut5.setBounds(306, 360, 171, 30);
		tips.setBounds(55, 30, 260, 30);

		chaframe.getContentPane().add(title);
		chaframe.getContentPane().add(title1);
		chaframe.getContentPane().add(chabut1);
		chaframe.getContentPane().add(chabut2);
		chaframe.getContentPane().add(chabut3);
		chaframe.getContentPane().add(chabut4);
		chaframe.getContentPane().add(chabut5);

		chaframe.getContentPane().add(tips);

		chabut1.addActionListener(this);
		chabut2.addActionListener(this);
		chabut3.addActionListener(this);
		chabut4.addActionListener(this);
		chabut5.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    try {
	        if (e.getSource() == chabut1) {// 管理員
	            x = 1;
	            chaframe.dispose();
	            AdminLogin login = new AdminLogin();
	        } else if (e.getSource() == chabut2) {// 聯盟者
	            x = 2;
	            chaframe.dispose();
	            LeagueOwnerLogin login = new LeagueOwnerLogin();
	        } else if (e.getSource() == chabut3) {// 廣告商
	            x = 3;
	            chaframe.dispose();
	            AdvertiserLogin login = new AdvertiserLogin();
	        } else if (e.getSource() == chabut4) {// 遊戲玩家
	            x = 4;
	            chaframe.dispose();
	            PlayerLogin login = new PlayerLogin();
	        } else if (e.getSource() == chabut5) {// 免登進入
	            x = 5;
	            chaframe.dispose();
                PublicPage publicPage = new PublicPage(); // Pass the reference to CharacterGui
                publicPage.showPublicPage();
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
}