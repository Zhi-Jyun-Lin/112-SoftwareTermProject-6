import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AdminPage extends JFrame implements ActionListener {
	private JFrame frame;
	private JPanel gamesPanel;
		

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				AdminPage window = new AdminPage();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public AdminPage() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 300, 800, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		frame.getContentPane().setBackground(SystemColor.control);
		frame.getContentPane().setLayout(null);

		JButton btnNewAd = new JButton("新增遊戲");
		btnNewAd.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnNewAd.setBounds(651, 79, 112, 25);
		btnNewAd.setForeground(new Color(255, 255, 255));
		btnNewAd.setBackground(new Color(0, 0, 0));
		btnNewAd.addActionListener(this);
		frame.getContentPane().add(btnNewAd);

		JButton logoutButton = new JButton("登出");
		logoutButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		logoutButton.setBackground(new Color(0, 0, 0));
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.setBounds(12, 10, 80, 25);
		frame.getContentPane().add(logoutButton);

		
		gamesPanel = new JPanel();
		gamesPanel.setBackground(new Color(255, 255, 255));
		gamesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		gamesPanel.setPreferredSize(new Dimension(700, 1000));
		JScrollPane scrollPane = new JScrollPane(gamesPanel);
		scrollPane.setBounds(12, 114, 751, 359);
		frame.getContentPane().add(scrollPane);


		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 處理登出按鈕的動作
				showCharacterGui(); // 呼叫登出後的處理方法
			}
		});
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("新增遊戲")) {
			createNewGame();
		} 
	}

	private void createNewGame() {

		
		String input = JOptionPane.showInputDialog(frame, "請輸入遊戲名稱");
		if (input != null && !input.isEmpty()) {
			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new FlowLayout());
			gamePanel.setPreferredSize(new Dimension(720, 150));

			JLabel gameNameLabel = new JLabel("遊戲名稱: " + input);

			JTextField textField = new JTextField(20);
			textField.setText(input);

			Color backgroundColor = new Color(173, 216, 230);
			
			gamePanel.setBackground(backgroundColor);

			JButton btnEdit = new JButton("編輯");
			JButton btnDelete = new JButton("刪除");

			JButton btnUploadImage = new JButton("上傳圖片");
			btnUploadImage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					uploadImage(gamePanel);
				}
			});

			gamePanel.add(btnEdit);
			gamePanel.add(btnDelete);
			gamePanel.add(textField);
			gamePanel.add(btnUploadImage);

			gamesPanel.add(gamePanel);
			frame.validate();
			frame.repaint();

			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editGame(textField);
				}
			});

			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteGame(gamePanel);
				}
			});
		} else {
			JOptionPane.showMessageDialog(frame, "遊戲名稱不能為空！");
		}
	}
	 
 
	private void uploadImage(JPanel gamePanel) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("選擇圖片");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int result = fileChooser.showOpenDialog(frame);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());

			int originalWidth = originalIcon.getIconWidth();
			int originalHeight = originalIcon.getIconHeight();

			int gamePanelWidth = gamePanel.getWidth() - 20;
			int gamePanelHeight = gamePanel.getHeight() - 20;

			int scaledWidth, scaledHeight;
			double widthRatio = (double) gamePanelWidth / originalWidth;
			double heightRatio = (double)gamePanelHeight / originalHeight;

			if (widthRatio < heightRatio) {
				scaledWidth = gamePanelWidth;
				scaledHeight = (int) (originalHeight * widthRatio);
			} else {
				scaledWidth = (int) (originalWidth * heightRatio);
				scaledHeight = gamePanelHeight;
			}

			Image img = originalIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
			ImageIcon resizedIcon = new ImageIcon(img);

			JOptionPane.showMessageDialog(frame, "遊戲已上傳完成!");

			Component[] components = gamePanel.getComponents();
			for (Component component : components) {
				if (component instanceof JButton && (((JButton) component).getText().equals("編輯")
						|| ((JButton) component).getText().equals("刪除"))) {
					gamePanel.remove(component);
					gamePanel.add(component);
				}
			}

			JLabel imageLabel = new JLabel(resizedIcon);
			gamePanel.add(imageLabel);
			frame.validate();
			frame.repaint();

		}
	}

	private void editGame(JTextField textField) {
		String editedText = JOptionPane.showInputDialog(frame, "請輸入編輯後的文字", textField.getText());
		if (editedText != null) {
			textField.setText(editedText);
		}
	}

	private void deleteGame(JPanel adPanel) {
		int result = JOptionPane.showConfirmDialog(frame, "確定要刪除遊戲嗎？", "刪除遊戲", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			gamesPanel.remove(adPanel);
			frame.validate();
			frame.repaint();
		}
	}

	public void showCharacterGui() { // 呼叫 CharacterGui 的建構子重新顯示身分選擇畫面 new
		frame.dispose(); // 關閉目前的 PublicPage 畫面
		CharacterGui chaframe = new CharacterGui();

	}

}