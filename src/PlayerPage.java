import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PlayerPage {

	private JFrame frame;
	private static String playerAccount; // 新增 playerAccount 成員變數
	private static JLabel accountLabel = new JLabel("遊戲玩家"); // 初始化 JLabel

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerPage window = new PlayerPage(playerAccount);
					window.frame.setVisible(true);
					/*window.showGameCards();  // 顯示遊戲卡片*/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PlayerPage(String userid) {
		initialize();
		setPlayerAccount(userid); // 在建構子中設定玩家帳號
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setLocationRelativeTo(null); // 置中顯示
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createAdvertisementArea();
		createButtons();

		createAccountLabel(); // 新增呼叫建立帳號標籤的方法

		frame.setVisible(true);
	}
	

	private void createButtons() {

		JButton logoutButton = new JButton("登出");
		logoutButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		logoutButton.setBackground(new Color(0, 0, 0));
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.setBounds(12, 10, 75, 23);
		frame.getContentPane().add(logoutButton);

		JButton viewRecordButton = new JButton("觀賽紀錄");
		viewRecordButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		viewRecordButton.setBackground(new Color(0, 0, 0));
		viewRecordButton.setForeground(new Color(255, 255, 255));
		viewRecordButton.setBounds(541, 326, 135, 172);
		frame.getContentPane().add(viewRecordButton);

		JButton allianceButton = new JButton("聯盟介紹");
		allianceButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		allianceButton.setBackground(new Color(0, 0, 0));
		allianceButton.setForeground(new Color(255, 255, 255));
		allianceButton.setBounds(332, 326, 135, 172);
		frame.getContentPane().add(allianceButton);

		JButton gameIntroButton = new JButton("遊戲介紹");
		gameIntroButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		gameIntroButton.setBackground(new Color(0, 0, 0));
		gameIntroButton.setForeground(new Color(255, 255, 255));
		gameIntroButton.setBounds(127, 326, 135, 172);
		frame.getContentPane().add(gameIntroButton);
		gameIntroButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 處理遊戲介紹按鈕的動作，跳轉到遊戲介紹畫面
				JOptionPane.showMessageDialog(frame, "跳轉到遊戲介紹畫面");
				showGameIntroduction();
			}
		});
		allianceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 處理聯盟介紹按鈕的動作，跳轉到聯盟介紹頁面
				JOptionPane.showMessageDialog(frame, "跳轉到聯盟介紹頁面");
				showLeagueIntroduction();
			}
		});
		viewRecordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 處理觀賽紀錄按鈕的動作，跳轉到觀賽畫面
				JOptionPane.showMessageDialog(frame, "跳轉到觀賽畫面，歷屆競賽資料查詢");
			}
		});
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 處理登出按鈕的動作
				showCharacterGui(); // 呼叫登出後的處理方法
			}
		});
	}
	
	public void showGameIntroduction() {
	    frame.dispose(); // 關閉 PlayerPage 畫面
	    EventQueue.invokeLater(() -> {
	        new GameIntroduction(playerAccount);
	    });
	}
	public void showLeagueIntroduction() {
	    frame.dispose(); // 關閉 PlayerPage 畫面
	    EventQueue.invokeLater(() -> {
	        new LeagueIntroduction(playerAccount);
	    });
	}

	private void createAccountLabel() {

		accountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		accountLabel.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		accountLabel.setForeground(new Color(0, 0, 0));
		accountLabel.setBounds(657, 9, 117, 25);
		frame.getContentPane().add(accountLabel);
		accountLabel.setVisible(true);
	}

	// 新增設定玩家帳號的方法
	public void setPlayerAccount(String userid) {
		playerAccount = userid;
		accountLabel.setText("welcome , " + playerAccount);
	}

	public void showPlayerPage() {
		if (frame == null) { // 如果 frame 不存在，則創建一個新的實例
			frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setSize(800, 600); // 設置合適的寬度和高度
			frame.setLocationRelativeTo(null); // 置中顯示
			frame.setVisible(true);
		} else {
			frame.setVisible(true); // 如果 frame 已經存在，則只是顯示它
		}
	}

	public void showCharacterGui() { // 呼叫 CharacterGui 的建構子重新顯示身分選擇畫面 new
		frame.dispose(); // 關閉目前的 PublicPage 畫面
		CharacterGui chaframe = new CharacterGui();

	}

	private void createAdvertisementArea() {
		frame.getContentPane().setLayout(null);
		JPanel adPanel = new JPanel();
		adPanel.setForeground(new Color(255, 255, 255));
		adPanel.setBounds(0, 43, 786, 227);
		adPanel.setBackground(new Color(0, 0, 0));
		JLabel adLabel = new JLabel("廣告投放區域");
		adLabel.setForeground(new Color(255, 255, 255));
		adPanel.add(adLabel);
		frame.getContentPane().add(adPanel);
	}

}
