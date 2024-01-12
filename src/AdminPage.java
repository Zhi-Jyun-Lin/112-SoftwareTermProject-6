import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AdminPage extends JFrame implements ActionListener {
	private JFrame frame;
	private JPanel gamesPanel;
    private JComboBox<String> comboBox;
    private int selectedIndex = -1;
    private JLabel nameLabel = new JLabel("未知開發者");		
    private JLabel EXPLabel = new JLabel("尚無介紹");
    private JLabel TypeLabel = new JLabel("");

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
		
		
		
        
		JButton AddGame = new JButton("新增遊戲種類");
		AddGame.setBackground(new Color(0, 0, 0));
		AddGame.setForeground(new Color(255, 255, 255));
        AddGame.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        AddGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openInputDialog();
            }
        });
        AddGame.setBounds(12, 76, 117, 30);
        frame.getContentPane().add(AddGame);


		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 處理登出按鈕的動作
				showCharacterGui();
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
			gamePanel.setPreferredSize(new Dimension(720, 300));
			
			
			JLabel gameNameLabel = new JLabel("遊戲名稱: " + input);
			
			JButton btnDelete = new JButton("刪除");

			JTextField textField = new JTextField(20);
			textField.setText(input);

			Color backgroundColor = new Color(173, 216, 230);		
			gamePanel.setBackground(backgroundColor);


			JButton btnUploadImage = new JButton("上傳圖片");
			btnUploadImage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					uploadImage(gamePanel);
				}
			});
			
			JButton btnCommon = new JButton("介紹");
			btnCommon.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showNewWindowForDis(gamePanel);
				}
			});

			gamePanel.add(btnDelete);
			gamePanel.add(textField);
			gamePanel.add(btnUploadImage);
			gamePanel.add(btnCommon);

			gamesPanel.add(gamePanel);
			frame.validate();
			frame.repaint();

			comboBox = new JComboBox<>();
	        comboBox.setForeground(new Color(0, 0, 0));
	        comboBox.setBackground(new Color(255, 255, 255));
	        comboBox.addItem("淘汰賽");
	        comboBox.addItem("冠軍賽");
	        comboBox.addItem("系列賽");
	        comboBox.addItem("積分對戰");
	        comboBox.setBounds(165, 77, 140, 30);
	        gamePanel.add(comboBox);
	        comboBox.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                selectedIndex = comboBox.getSelectedIndex();
	            }
	        });
	        comboBox.setSelectedIndex(-1);
	        nameLabel = new JLabel("未知開發者");		
			EXPLabel = new JLabel("尚無介紹");
			TypeLabel = new JLabel("");
			gamePanel.add(nameLabel);
			gamePanel.add(EXPLabel);
			gamePanel.add(TypeLabel);

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
	
    private void openInputDialog() {
        JFrame inputFrame = new JFrame("輸入競賽類型");
        inputFrame.setSize(300, 150);
        inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inputFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JTextField textField = new JTextField(20);
        JButton addButton = new JButton("新增");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                if (!inputText.isEmpty()) {
                    comboBox.addItem(inputText);
                    JOptionPane.showMessageDialog(null, "已新增至選單！");
                    inputFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "請輸入有效的文字！");
                }
            }
        });

        panel.add(textField);
        panel.add(addButton);

        inputFrame.getContentPane().add(panel);
        inputFrame.setVisible(true);
    }
    private void showNewWindowForDis(JPanel gamePanel) {
        JFrame newFrame = new JFrame("編輯視窗");
        newFrame.setSize(400, 200);
        newFrame.setLocationRelativeTo(null);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        newFrame.getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);  // 外部間距

        // 遊戲開發者
        JLabel nameLabel = new JLabel("遊戲開發者:");
        panel.add(nameLabel, gbc);

        gbc.gridx++;
        JTextField newTextFieldForName = new JTextField(20);
        panel.add(newTextFieldForName, gbc);

        // 遊戲描述
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel textLabel = new JLabel("遊戲描述:");
        panel.add(textLabel, gbc);

        gbc.gridx++;
        JTextField newTextFieldForEXP = new JTextField(20);
        panel.add(newTextFieldForEXP, gbc);
        
     // 遊戲類型
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel typeLabel = new JLabel("遊戲種類:");
        panel.add(typeLabel, gbc);
        
        gbc.gridx++;
        JTextField newTextFieldForType = new JTextField(20);
        panel.add(newTextFieldForType, gbc);

        /// 新增編輯按鈕
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;  // 改為 1
        JButton addButton = new JButton("新增編輯");
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name = newTextFieldForName.getText();
                String EXP = newTextFieldForEXP.getText();
                String Type = newTextFieldForType.getText();
                
                if (!Name.isEmpty() && !EXP.isEmpty()) {
                    addNewButtonDis(Name, Type, EXP,gamePanel);
                    newFrame.dispose(); // 關閉新視窗
                } else {
                    JOptionPane.showMessageDialog(null, "請輸入有效的編輯內容");
                }
            }
        });
        panel.add(addButton, gbc);

        newFrame.setVisible(true);
    }
    
    private void addNewButtonDis(String Name, String Type, String EXP, JPanel gamePanel) {
        nameLabel.setText("遊戲開發者: " + Name);
        TypeLabel.setText("遊戲種類: " + Type);
        EXPLabel.setText("<html>遊戲描述: " + EXP + "</html>");
        EXPLabel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(nameLabel);
        infoPanel.add(TypeLabel);
        infoPanel.add(EXPLabel);

        gamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        gamePanel.add(infoPanel);

        // 您的其他按鈕和元件（刪除、編輯介紹、上傳照片）可以添加在這裡

        frame.validate();  // 重新驗證和繪製框架以反映更改
        frame.repaint();
    }
       
    
}