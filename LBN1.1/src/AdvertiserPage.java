import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AdvertiserPage extends JFrame implements ActionListener {
	private JFrame frame;
	private JPanel adsPanel;
	private JTextField remainingBalanceField;
	private static JComboBox<String>  comboBox ;

	private static String[] AD_TYPES = { "聯盟贊助商", "遊戲廣告商" };
	private static final double LEAGUE_SPONSOR_COST = 1000.0;
	private static final double GAME_ADVERTISER_COST = 800.0;

	private double remainingBalance = 5000.0;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				AdvertiserPage window = new AdvertiserPage();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public AdvertiserPage() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 300, 800, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		frame.getContentPane().setBackground(SystemColor.control);
		frame.getContentPane().setLayout(null);

		JButton btnNewAd = new JButton("新增廣告");
		btnNewAd.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnNewAd.setBounds(651, 67, 112, 25);
		btnNewAd.setForeground(new Color(255, 255, 255));
		btnNewAd.setBackground(new Color(0, 0, 0));
		btnNewAd.addActionListener(this);
		frame.getContentPane().add(btnNewAd);

		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(SystemColor.control);
		controlPanel.setBounds(12, 434, 751, 45);
		frame.getContentPane().add(controlPanel);
		controlPanel.setLayout(null);

		JLabel lblAdType = new JLabel("廣告方案:");
		lblAdType.setBounds(181, 12, 52, 22);
		lblAdType.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		controlPanel.add(lblAdType);

		comboBox = new JComboBox<>(AD_TYPES);
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(234, 10, 100, 25);
		controlPanel.add(comboBox);

		JButton btnCalculateCost = new JButton("計算廣告費用");
		btnCalculateCost.setBounds(346, 10, 117, 25);
		btnCalculateCost.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		btnCalculateCost.setBackground(new Color(0, 0, 0));
		btnCalculateCost.setForeground(new Color(255, 255, 255));
		btnCalculateCost.addActionListener(this);
		controlPanel.add(btnCalculateCost);

		JLabel lblRemainingBalance = new JLabel("剩餘餘額:");
		lblRemainingBalance.setBounds(468, 12, 52, 22);
		lblRemainingBalance.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		controlPanel.add(lblRemainingBalance);
		String multiLineText = "<html>請注意：<br>聯盟贊助商的廣告花費為1000元<br>遊戲廣告商為800元<br>新增廣告後需點擊下方<br>'計算廣告費用'以進行扣款，謝謝</html>";

		remainingBalanceField = new JTextField();
		remainingBalanceField.setBounds(525, 11, 45, 23);
		remainingBalanceField.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		remainingBalanceField.setEditable(false);
		controlPanel.add(remainingBalanceField);

		JButton logoutButton = new JButton("登出");
		logoutButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		logoutButton.setBackground(new Color(0, 0, 0));
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.setBounds(12, 10, 80, 25);
		frame.getContentPane().add(logoutButton);

		
		adsPanel = new JPanel();
		adsPanel.setBackground(new Color(255, 255, 255));
		adsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		adsPanel.setPreferredSize(new Dimension(700, 1000));
		JScrollPane scrollPane = new JScrollPane(adsPanel);
		scrollPane.setBounds(12, 102, 751, 328);
		frame.getContentPane().add(scrollPane);

		
		JLabel textLabel = new JLabel(
				"<html>請注意：<br>每新增一則聯盟贊助商的廣告花費為1000元/遊戲廣告為800元，新增廣告後需點擊下方\"計算廣告費用\"以進行扣款，謝謝!</html>");
		textLabel.setForeground(new Color(255, 0, 0));
		textLabel.setVerticalAlignment(SwingConstants.TOP);
		textLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		textLabel.setBounds(104, 6, 659, 33);
		frame.getContentPane().add(textLabel);

		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 處理登出按鈕的動作
				showCharacterGui(); // 呼叫登出後的處理方法
			}
		});

		updateRemainingBalance();

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("新增廣告")) {
			createNewAd();
		} else if (e.getActionCommand().equals("計算廣告費用")) {
			calculateCost();
		}
	}

	private void createNewAd() {

		String[] options = { "聯盟贊助商", "遊戲廣告商" };
		int adType = JOptionPane.showOptionDialog(frame, "選擇廣告類型", "廣告類型", JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		String input = JOptionPane.showInputDialog(frame, "請輸入廣告名稱");
		if (input != null && !input.isEmpty()) {
			JPanel adPanel = new JPanel();
			adPanel.setLayout(new FlowLayout());
			adPanel.setPreferredSize(new Dimension(720, 150));

			JLabel adNameLabel = new JLabel("廣告名稱: " + input);

			JTextField textField = new JTextField(20);
			textField.setText(input);

			Color backgroundColor;
			if (adType == 0) {
				backgroundColor = new Color(173, 216, 230);
			} else {
				backgroundColor = new Color(255, 204, 153);
			}
			adPanel.setBackground(backgroundColor);

			JButton btnEdit = new JButton("編輯");
			JButton btnDelete = new JButton("刪除");

			JButton btnUploadImage = new JButton("上傳圖片");
			btnUploadImage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					uploadImage(adPanel);
				}
			});

			adPanel.add(btnEdit);
			adPanel.add(btnDelete);
			adPanel.add(textField);
			adPanel.add(btnUploadImage);

			adsPanel.add(adPanel);
			frame.validate();
			frame.repaint();

			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editAd(textField);
				}
			});

			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteAd(adPanel);
				}
			});
		} else {
			JOptionPane.showMessageDialog(frame, "廣告名稱不能為空！");
		}
	}
	 
 
	private void uploadImage(JPanel adPanel) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("選擇圖片");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int result = fileChooser.showOpenDialog(frame);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());

			int originalWidth = originalIcon.getIconWidth();
			int originalHeight = originalIcon.getIconHeight();

			int adPanelWidth = adPanel.getWidth() - 20;
			int adPanelHeight = adPanel.getHeight() - 20;

			int scaledWidth, scaledHeight;
			double widthRatio = (double) adPanelWidth / originalWidth;
			double heightRatio = (double) adPanelHeight / originalHeight;

			if (widthRatio < heightRatio) {
				scaledWidth = adPanelWidth;
				scaledHeight = (int) (originalHeight * widthRatio);
			} else {
				scaledWidth = (int) (originalWidth * heightRatio);
				scaledHeight = adPanelHeight;
			}

			Image img = originalIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
			ImageIcon resizedIcon = new ImageIcon(img);

			JOptionPane.showMessageDialog(frame, "廣告已上傳完成!");

			Component[] components = adPanel.getComponents();
			for (Component component : components) {
				if (component instanceof JButton && (((JButton) component).getText().equals("編輯")
						|| ((JButton) component).getText().equals("刪除"))) {
					adPanel.remove(component);
					adPanel.add(component);
				}
			}

			JLabel imageLabel = new JLabel(resizedIcon);
			adPanel.add(imageLabel);
			frame.validate();
			frame.repaint();

		}
	}

	private void editAd(JTextField textField) {
		String editedText = JOptionPane.showInputDialog(frame, "請輸入編輯後的文字", textField.getText());
		if (editedText != null) {
			textField.setText(editedText);
		}
	}

	private void deleteAd(JPanel adPanel) {
		int result = JOptionPane.showConfirmDialog(frame, "確定要刪除廣告嗎？", "刪除廣告", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			adsPanel.remove(adPanel);
			frame.validate();
			frame.repaint();
		}
	}

	private void calculateCost() {
		
		String selectedAdType = (String) comboBox.getSelectedItem();
		double cost = 0.0;

		
		 if (selectedAdType.equals(AD_TYPES[0])) { cost = LEAGUE_SPONSOR_COST; } 
		 else if (selectedAdType.equals(AD_TYPES[1])) { cost = GAME_ADVERTISER_COST; }
		 

		if (remainingBalance >= cost) {
			remainingBalance -= cost;
			updateRemainingBalance();
			JOptionPane.showMessageDialog(frame, "廣告費用已計算完成。");
		} else {
			JOptionPane.showMessageDialog(frame, "餘額不足以支付廣告費用！", "錯誤", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void showCharacterGui() { // 呼叫 CharacterGui 的建構子重新顯示身分選擇畫面 new
		frame.dispose(); // 關閉目前的 PublicPage 畫面
		CharacterGui chaframe = new CharacterGui();

	}

	private void updateRemainingBalance() {
		remainingBalanceField.setText(String.valueOf(remainingBalance));
	}
}