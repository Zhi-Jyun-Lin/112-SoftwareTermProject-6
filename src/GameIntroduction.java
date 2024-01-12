import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
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

public class GameIntroduction extends JFrame {

	private JPanel gamesPanel;
	private JFrame frame = new JFrame("Game Introduction");
	private static String playerAccount;
	private static JLabel accountLabel = new JLabel("遊戲玩家"); // 初始化 JLabel

	public static void main(String[] args) {
		// 在事件分發線程中運行Swing應用程序
		EventQueue.invokeLater(() -> {
			new GameIntroduction(playerAccount);
		});
	}

	public GameIntroduction(String userid) {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 520);
		frame.setLocationRelativeTo(null); // 置中顯示
		frame.getContentPane().setLayout(null);

		// 初始化 gamesPanel
		gamesPanel = new JPanel();
		gamesPanel.setBackground(new Color(255, 255, 255));
		gamesPanel.setPreferredSize(new Dimension(700, 480));
		JScrollPane scrollPane = new JScrollPane(gamesPanel);
		gamesPanel.setLayout(null);
		scrollPane.setBounds(0, 61, 786, 422);
		frame.getContentPane().add(scrollPane);

		JLabel lblNewLabel = new JLabel("遊戲介紹");
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
		List<GameInfo> gameList = getGameInfoFromDatabase();
		// 使用 GridBagLayout 替換原先的 GridLayout
		gamesPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10); // 設定元件的外部間距

		// 使用遊戲資訊填充 leaguesPanel
		for (GameInfo gameInfo : gameList) {
			JPanel game = createGameButton(gameInfo);
			gamesPanel.add(game, gbc);

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

	private List<GameInfo> getGameInfoFromDatabase() {
		List<GameInfo> gameList = new ArrayList<>();

		try {
			// 連接到資料庫
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java", "postgres",
					"mars19363");
			Statement statement = connection.createStatement();
			System.out.println("Opened database successfully");

			// 執行SQL查詢
			String sql = "SELECT name, introduction, developer, imagepath, type, link FROM game";
			ResultSet resultSet = statement.executeQuery(sql);

			// 處理結果集
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				String introduction = resultSet.getString("introduction");
				String developer = resultSet.getString("developer");
				String imagepath = resultSet.getString("imagepath");
				String link = resultSet.getString("link");
				String type = resultSet.getString("type");

				System.out.println("Name: " + name);
				System.out.println("Type: " + type);
				System.out.println("Introduction: " + introduction);
				System.out.println("Developer: " + developer);
				System.out.println("Image Path: " + imagepath);
				System.out.println("Link: " + link);

				// 創建GameInfo物件並將其添加到列表中
				gameList.add(new GameInfo(name,type, introduction, developer, imagepath, link));
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

		return gameList;
	}

	private JPanel createGameButton(GameInfo gameInfo) {
		JPanel gamePanel = new JPanel(new FlowLayout()); // 使用FlowLayout
		gamePanel.setPreferredSize(new Dimension(200, 200));

		// 載入並顯示圖片
		ImageIcon icon = new ImageIcon(gameInfo.getImagepath());
		Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(image));
		gamePanel.add(imageLabel);

		JLabel nameLabel = new JLabel("遊戲名稱: " + gameInfo.getName());
		gamePanel.add(nameLabel);
		JLabel typeLabel = new JLabel("遊戲類別: " + gameInfo.getType());
		gamePanel.add(typeLabel);
		JLabel developerLabel = new JLabel("開發者: " + gameInfo.getDeveloper());
		gamePanel.add(developerLabel);

		// 將 gamePanel 設置為可點擊
		gamePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 顯示詳細介紹的彈出視窗
				showDetailsDialog(gameInfo);
			}
		});

		return gamePanel;
	}

	private void showDetailsDialog(GameInfo gameInfo) {
	    // 創建一個面板來容納詳細信息
	    JPanel detailsPanel = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.WEST; // 對齊到左邊
	    gbc.fill = GridBagConstraints.HORIZONTAL; // 水平擴展

	    JLabel nameLabel = new JLabel("遊戲名稱: " + gameInfo.getName());
	    detailsPanel.add(nameLabel, gbc);
	    gbc.gridy++;
	    
	 // 添加空行標籤
	    JLabel blankLabel = new JLabel(" ");
	    detailsPanel.add(blankLabel, gbc);
	    gbc.gridy++;
	    
	    JLabel typeLabel = new JLabel("遊戲類別: " + gameInfo.getType());
	    detailsPanel.add(typeLabel, gbc);
	    gbc.gridy++;
	    
	 // 添加空行標籤
	    JLabel blankLabel2 = new JLabel(" ");
	    detailsPanel.add(blankLabel2, gbc);
	    gbc.gridy++;
	    
	    // 添加開發者標籤
	    JLabel developerLabel = new JLabel("開發者: " + gameInfo.getDeveloper());
	    detailsPanel.add(developerLabel, gbc);
	    gbc.gridy++;
	    
	 // 添加空行標籤
	    JLabel blankLabel3 = new JLabel(" ");
	    detailsPanel.add(blankLabel3, gbc);
	    gbc.gridy++;
	    
	    // 添加介紹標籤
	    JLabel introductionLabel = new JLabel("遊戲介紹: ");
	    detailsPanel.add(introductionLabel, gbc);
	    
	    gbc.gridy++;
	    // 添加介紹標籤
	    JLabel introLabel = new JLabel("<html>" + gameInfo.getIntroduction() + "<br></html>");
	    detailsPanel.add(introLabel, gbc);
	    
	    gbc.gridy++;
	    // 添加空白標籤
	    JLabel blankLabel4 = new JLabel(" ");
	    detailsPanel.add(blankLabel4, gbc);    

	    
	    gbc.gridy++;
	    // 創建一個按鈕來啟動遊戲
	    JButton startGameButton = new JButton("開始遊戲");
	    startGameButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // 處理開始遊戲，這裡你可以使用 gameInfo.getLink()
	            openLink(gameInfo.getLink());
	        }
	    });
	 // 調整按鈕的大小和對齊方式
	    startGameButton.setPreferredSize(new Dimension(150, 25));
	    startGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

	    // 添加按鈕
	    gbc.gridy++;
	    gbc.weightx = 1.0; // 水平擴展權重
	    detailsPanel.add(startGameButton, gbc);

	    // 創建一個新的 JOptionPane，包含 detailsPanel
	    JOptionPane optionPane = new JOptionPane(detailsPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null);

	    // 創建一個 JDialog 並設置其屬性
	    JDialog dialog = optionPane.createDialog(null, "詳細介紹");
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    dialog.setSize(600, 450); // 設置你想要的大小
	    dialog.setLocationRelativeTo(null);
	    dialog.setVisible(true);
	}

	// Helper method to open a link in the default browser
	private void openLink(String link) {
		try {
			Desktop.getDesktop().browse(new URI(link));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private static class GameInfo {
		private String name;
		private String type;
		private String introduction;
		private String developer;
		private String imagepath;
		private String link;

		public GameInfo(String name, String type, String introduction, String developer, String imagepath, String link) {
			this.name = name;
			this.type=type;
			this.introduction = introduction;
			this.developer = developer;
			this.imagepath = imagepath;
			this.link = link;
		}

		public String getName() {
			return name;
		}
		public String getType() {
			return type;
		}

		public String getIntroduction() {
			return introduction;
		}

		public String getDeveloper() {
			return developer;
		}

		public String getImagepath() {
			return imagepath;
		}

		public String getLink() {
			return link;
		}
	}

}
