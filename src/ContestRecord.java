import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

public class ContestRecord extends JFrame {

	private JPanel leaguesPanel;
	private JFrame frame = new JFrame("League Introduction");
	private static String playerAccount;
	private static JLabel accountLabel = new JLabel("遊戲玩家"); // 初始化 JLabel

	public static void main(String[] args) {
		// 在事件分發線程中運行Swing應用程序
		EventQueue.invokeLater(() -> {
			new ContestRecord(playerAccount);
		});
	}

	public ContestRecord(String userid) {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 520);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); // 置中顯示
		

		// 初始化 gamesPanel
		leaguesPanel = new JPanel();	
		leaguesPanel.setBackground(new Color(255, 255, 255));
		leaguesPanel.setPreferredSize(new Dimension(700, 480));
		JScrollPane scrollPane = new JScrollPane(leaguesPanel);
		leaguesPanel.setLayout(null);		
		scrollPane.setBounds(0, 61, 786, 422);
		frame.getContentPane().add(scrollPane);

		JLabel lblNewLabel = new JLabel("聯盟介紹");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		lblNewLabel.setBounds(320, 10, 137, 41);
		frame.getContentPane().add(lblNewLabel);

		JButton turnbackButton = new JButton("返回");
		turnbackButton.setBackground(new Color(0, 0, 0));
		turnbackButton.setForeground(new Color(255, 255, 255));
		turnbackButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		turnbackButton.setBounds(12, 10, 80, 25);
		frame.getContentPane().add(turnbackButton);
		turnbackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 處理返回按鈕的動作
				frame.dispose(); // 關閉當前的 GameIntroduction 畫面
				PlayerPage playerPage = new PlayerPage(userid); // 創建 PlayerPage 實例
				playerPage.showPlayerPage(); // 顯示 PlayerPage
			}
		});

		// 連接到資料庫並從中擷取遊戲資訊
		List<LeagueInfo> leagueList = getLeagueInfoFromDatabase();

		// 使用 GridBagLayout 替換原先的 GridLayout
		leaguesPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10); // 設定元件的外部間距

		// 使用遊戲資訊填充 leaguesPanel
		for (LeagueInfo leagueInfo : leagueList) {
		    JPanel league = createLeagueButton(leagueInfo);
		    leaguesPanel.add(league, gbc);
		    
		    gbc.gridx++;
		    if (gbc.gridx > 2) {
		        gbc.gridx = 0;
		        gbc.gridy++;
		    }
		}

		setPlayerAccount(userid); // 在建構子中設定玩家帳號
		createAccountLabel(); // 新增呼叫建立帳號標籤的方法

		frame.setVisible(true);
	}

	// 新增設定玩家帳號的方法
	public void setPlayerAccount(String userid) {
		playerAccount = userid;
		accountLabel.setText("welcome , " + playerAccount);
	}

	private void createAccountLabel() {

		accountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		accountLabel.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		accountLabel.setForeground(new Color(0, 0, 0));
		accountLabel.setBounds(657, 9, 117, 25);
		frame.getContentPane().add(accountLabel);
		accountLabel.setVisible(true);
	}

	private List<LeagueInfo> getLeagueInfoFromDatabase() {
		List<LeagueInfo> LeagueList = new ArrayList<>();

		try {
			// 連接到資料庫
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java", "postgres",
					"mars19363");
			Statement statement = connection.createStatement();
			System.out.println("Opened database successfully");

			// 執行SQL查詢
			String sql = "SELECT leagueowner_id, leagueowner, leaguename, leagueintro,imagepath FROM league";
			ResultSet resultSet = statement.executeQuery(sql);

			// 處理結果集
			while (resultSet.next()) {
				String leaguename = resultSet.getString("leaguename");
				String leagueintro = resultSet.getString("leagueintro");
				String leagueowner = resultSet.getString("leagueowner") + resultSet.getString("leagueowner_id");
				String imagepath = resultSet.getString("imagepath");

				System.out.println("Name: " + leaguename);
				System.out.println("Introduction: " + leagueintro);
				System.out.println("leagueowner: " + leagueowner);
				System.out.println("Image Path: " + imagepath);

				// 創建GameInfo物件並將其添加到列表中
				LeagueList.add(new LeagueInfo(leaguename, leagueintro, leagueowner, imagepath));
			}

			// 關閉資源
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// 印出錯誤訊息
			System.err.println("Database connection error: " + e.getMessage());
			e.printStackTrace();
		}

		return LeagueList;
	}

	private JPanel createLeagueButton(LeagueInfo leagueInfo) {
		JPanel leaguePanel = new JPanel(new FlowLayout()); // 使用FlowLayout
		leaguePanel.setPreferredSize(new Dimension(200, 200));

		// 載入並顯示圖片
		ImageIcon icon = new ImageIcon(leagueInfo.getImagepath());
		Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(image));
		leaguePanel.add(imageLabel);

		JLabel nameLabel = new JLabel("聯盟名稱: " + leagueInfo.getLeaguename());
		leaguePanel.add(nameLabel);

		// 將 gamePanel 設置為可點擊
		leaguePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 顯示詳細介紹的彈出視窗
				showDetailsDialog(leagueInfo);
			}
		});

		return leaguePanel;
	}

	private void showDetailsDialog(LeagueInfo leagueInfo) {
		// 創建一個面板來容納詳細信息
		JPanel detailsPanel = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.WEST; // 對齊到左邊
	    gbc.fill = GridBagConstraints.HORIZONTAL; // 水平擴展
	    
	    
		JLabel leaguenameLabel = new JLabel("聯盟名稱: " + leagueInfo.getLeaguename());
		detailsPanel.add(leaguenameLabel, gbc);
		gbc.gridy++;
		
		
	    // 添加空行標籤
	    JLabel blankLabel = new JLabel(" ");
	    detailsPanel.add(blankLabel, gbc);
	    gbc.gridy++;

		// 添加介紹標籤
		
		JLabel introductionLabel = new JLabel("聯盟自介: " );
		detailsPanel.add(introductionLabel, gbc);
		gbc.gridy++;
		
		JLabel introLabel = new JLabel("<html>" + leagueInfo.getLeagueintro() + "</html>");
		detailsPanel.add(introLabel, gbc);
		gbc.gridy++;
		
		// 添加空行標籤
	    JLabel blankLabel1 = new JLabel(" ");
	    detailsPanel.add(blankLabel1, gbc);
	    gbc.gridy++;


		// 添加開發者標籤
		
		JLabel leagueownerLabel = new JLabel("聯盟者: " + leagueInfo.getLeagueowner());
		detailsPanel.add(leagueownerLabel,gbc);
		gbc.gridy++;

		// 創建一個新的 JOptionPane，包含 detailsPanel 和 startGameButton
		JOptionPane optionPane = new JOptionPane(detailsPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION,
				null);

		// 創建一個 JDialog 並設置其屬性
		JDialog dialog = optionPane.createDialog(null, "詳細介紹");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setSize(600, 350); // 設置你想要的大小
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	private static class LeagueInfo {
		private String leaguename;
		private String leagueintro;
		private String leagueowner;
		private String imagepath;

		public LeagueInfo(String leaguename, String leagueintro, String leagueowner, String imagepath) {
			this.leaguename = leaguename;
			this.leagueintro = leagueintro;
			this.leagueowner = leagueowner;
			this.imagepath = imagepath;

		}

		public String getLeaguename() {
			return leaguename;
		}

		public String getLeagueintro() {
			return leagueintro;
		}

		public String getLeagueowner() {
			return leagueowner;
		}

		public String getImagepath() {
			return imagepath;
		}

	}

}
