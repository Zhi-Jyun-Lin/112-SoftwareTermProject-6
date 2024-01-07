import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PublicPage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PublicPage window = new PublicPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PublicPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createAdvertisementArea();
		createButtons();

		// Add other components as needed...

		frame.setVisible(true);
	}

	private void createButtons() {

		JButton loginButton = new JButton("登入");
		loginButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		loginButton.setBackground(new Color(0, 0, 0));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBounds(699, 10, 75, 23);
		frame.getContentPane().add(loginButton);

		JButton viewRecordButton = new JButton("觀賽紀錄");
		viewRecordButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		viewRecordButton.setBackground(new Color(0, 0, 0));
		viewRecordButton.setForeground(new Color(255, 255, 255));
		viewRecordButton.setBounds(510, 10, 135, 23);
		frame.getContentPane().add(viewRecordButton);

		JButton allianceButton = new JButton("聯盟介紹");
		allianceButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		allianceButton.setBackground(new Color(0, 0, 0));
		allianceButton.setForeground(new Color(255, 255, 255));
		allianceButton.setBounds(332, 10, 135, 23);
		frame.getContentPane().add(allianceButton);

		JButton gameIntroButton = new JButton("遊戲介紹");
		gameIntroButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		gameIntroButton.setBackground(new Color(0, 0, 0));
		gameIntroButton.setForeground(new Color(255, 255, 255));
		gameIntroButton.setBounds(154, 10, 135, 23);
		frame.getContentPane().add(gameIntroButton);
		gameIntroButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 處理遊戲介紹按鈕的動作，跳轉到遊戲介紹畫面
				JOptionPane.showMessageDialog(frame, "跳轉到遊戲介紹畫面");
			}
		});
		allianceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 處理聯盟介紹按鈕的動作，跳轉到聯盟介紹頁面
				JOptionPane.showMessageDialog(frame, "跳轉到聯盟介紹頁面");
			}
		});
		viewRecordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 處理觀賽紀錄按鈕的動作，跳轉到觀賽畫面
				JOptionPane.showMessageDialog(frame, "跳轉到觀賽畫面，歷屆競賽資料查詢");
			}
		});
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 處理登入按鈕的動作
				showCharacterGui(); // 呼叫登入後的處理方法
			}
		});
	}

	public void showPublicPage() {
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
