import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class LeagueOwnerPage extends JFrame {
    private JPanel contentPane;
    private JScrollPane inputScrollPane;
    private JPanel inputPanel;
    private String name = "";
    private String exp = "";
    private boolean isClosed = false;//供登出後再進來時判斷用的

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
            	LeagueOwnerPage frame = new LeagueOwnerPage();
                frame.setVisible(true);
                System.out.println("進到聯盟者介面");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LeagueOwnerPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 520);
        contentPane = new JPanel();
        setContentPane(contentPane);
        
        JButton logoutButton = new JButton("登出");
		logoutButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		logoutButton.setBackground(new Color(0, 0, 0));
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.setBounds(12, 10, 80, 25);
		getContentPane().add(logoutButton);
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 處理登出按鈕的動作
				System.out.println("Logout Button Clicked"); // Add this line for debugging
		        showCharacterGui();// 呼叫登出後的處理方法
			}
		});

        JButton btnNewButton = new JButton("新增聯盟");
        btnNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnNewButton.setBounds(299, 10, 155, 25);
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(0, 0, 0));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showNewWindow();
            }
        });
        contentPane.setLayout(null);
        contentPane.add(btnNewButton);

        inputScrollPane = new JScrollPane();
        inputScrollPane.setBounds(0, 40, 780, 440);
        contentPane.add(inputScrollPane);

        inputPanel = new JPanel();
        inputScrollPane.setViewportView(inputPanel);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
    }
	public void showCharacterGui() {
		// 隱藏當前的 LeagueOwnerPage，而不是關閉
	    setVisible(false);
	    
	    // 呼叫 CharacterGui 的建構子重新顯示身分選擇畫面
	    CharacterGui cha = new CharacterGui();
	}
    
    private void showNewWindow() {
        JFrame newFrame = new JFrame("新增聯盟");
        newFrame.setSize(400, 200);
        newFrame.setLocationRelativeTo(null);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton browseButton = new JButton("選擇圖片");
        JTextField newTextFieldForImage = new JTextField(20);
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("選擇圖片");
                int userSelection = fileChooser.showOpenDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    newTextFieldForImage.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        panel.add(newTextFieldForImage);
        panel.add(browseButton);

        JLabel nameLabel = new JLabel("聯盟名稱");
        JTextField newTextFieldForName = new JTextField(20);
        panel.add(newTextFieldForName);
        panel.add(nameLabel);

        JLabel textLabel = new JLabel("聯盟描述");
        JTextField newTextFieldForEXP = new JTextField(20);
        panel.add(newTextFieldForEXP);
        panel.add(textLabel);

        JButton addButton = new JButton("新增至主頁面");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imagePath = newTextFieldForImage.getText();
                String name = newTextFieldForName.getText();
                String exp = newTextFieldForEXP.getText();

                if (!imagePath.isEmpty() && !name.isEmpty() && !exp.isEmpty()) {
                    addNewGame(imagePath, name, exp);
                    newFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "請填寫所有必要的資訊");
                }
            }
        });

        // 使用BorderLayout，將按鈕置於SOUTH位置
        newFrame.getContentPane().setLayout(new BorderLayout());
        newFrame.getContentPane().add(panel, BorderLayout.CENTER);
        newFrame.getContentPane().add(addButton, BorderLayout.SOUTH);

        newFrame.setVisible(true);
    }

    private void addNewGame(String imagePath, String name, String exp) {
        JButton newButton = createGameButton(imagePath, name, exp);
        inputPanel.add(newButton);
        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private JButton createGameButton(String imagePath, String name, String exp) {
        JButton newButton = new JButton();

        try {
            BufferedImage originalImage = ImageIO.read(new java.io.File(imagePath));
            Image scaledImage = originalImage.getScaledInstance(100, 80, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            newButton.setIcon(scaledIcon);

            // 設置按鈕的文字內容和提示信息
            newButton.setText("<html><center>" + name + "<br>" + exp + "</center></html>");
            newButton.setToolTipText("聯盟名稱: " + name + "\n聯盟描述: " + exp);
        } catch (Exception ex) {
            // 錯誤處理，顯示錯誤訊息
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "圖片讀取錯誤：" + ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
        }

        newButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    showEditDeleteWindow(newButton);
                }
            }
        });

        return newButton;
    }

    private void showEditDeleteWindow(JButton targetButton) {
        final String[] targetName = {""};
        final String[] targetExp = {""};

        // 獲取 name 和 exp
        String[] tooltipLines = targetButton.getToolTipText().split("\n");
        for (String line : tooltipLines) {
            if (line.startsWith("聯盟名稱: ")) {
                targetName[0] = line.substring("聯盟名稱: ".length());
            } else if (line.startsWith("聯盟描述: ")) {
                targetExp[0] = line.substring("聯盟描述: ".length());
            }
        }

     // 創建編輯/刪除視窗
        JFrame editDeleteFrame = new JFrame("編輯/刪除視窗");
        editDeleteFrame.setSize(400, 200);
        editDeleteFrame.setLocationRelativeTo(null);
        editDeleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        editDeleteFrame.getContentPane().add(panel);

        // 創建 GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 加入空隙

        // 遊戲名稱
        JLabel nameLabel = new JLabel("聯盟名稱: " + targetName[0]);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        // 遊戲描述
        JLabel expLabel = new JLabel("聯盟描述: " + targetExp[0]);
        gbc.gridy = 1;
        panel.add(expLabel, gbc);

        // 編輯按鈕
        JButton editButton = new JButton("編輯內容");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editGame(targetName[0], targetExp[0], targetButton);
            }
        });
        gbc.gridy = 2;
        panel.add(editButton, gbc);

        // 原本圖片標籤
        JLabel originalImageLabel = new JLabel("原本圖片:");
        gbc.gridy = 3;
        panel.add(originalImageLabel, gbc);

        // 顯示原本的圖片
        JLabel originalImageDisplay = new JLabel();
        originalImageDisplay.setIcon(targetButton.getIcon());
        gbc.gridy = 4;
        panel.add(originalImageDisplay, gbc);

        // 選擇新圖片按鈕
        JButton chooseFileButton = new JButton("選擇新圖片");
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseNewImage(targetButton, originalImageDisplay);
            }
        });
        gbc.gridy = 5;
        panel.add(chooseFileButton, gbc);

        // 刪除按鈕
        JButton deleteButton = new JButton("刪除聯盟");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteGame(targetButton);
                editDeleteFrame.dispose();
            }
        });
        gbc.gridy = 6;
        panel.add(deleteButton, gbc);
        editDeleteFrame.setVisible(true);

    }
    private void chooseNewImage(JButton targetButton, JLabel imageDisplay) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("選擇新圖片");
        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            String newImagePath = selectedFile.getAbsolutePath();

            try {
                BufferedImage originalImage = ImageIO.read(new java.io.File(newImagePath));
                Image scaledImage = originalImage.getScaledInstance(100, 80, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                targetButton.setIcon(scaledIcon);
                imageDisplay.setIcon(scaledIcon);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void editGame(String targetName, String targetExp, JButton targetButton) {
        JFrame editFrame = new JFrame("編輯聯盟視窗");
        editFrame.setSize(400, 200);
        editFrame.setLocationRelativeTo(null);
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        editFrame.getContentPane().add(panel);

        JLabel nameLabel = new JLabel("聯盟名稱");
        JTextField newTextFieldForName = new JTextField(20);
        newTextFieldForName.setText(targetName); // 使用目標按鈕的名稱
        panel.add(newTextFieldForName);
        panel.add(nameLabel);

        JLabel textLabel = new JLabel("聯盟描述");
        JTextField newTextFieldForEXP = new JTextField(20);
        newTextFieldForEXP.setText(targetExp); // 使用目標按鈕的描述
        panel.add(newTextFieldForEXP);
        panel.add(textLabel);

        JButton confirmEditButton = new JButton("確認編輯");
        confirmEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = newTextFieldForName.getText(); // Update name variable
                exp = newTextFieldForEXP.getText();   // Update exp variable

                System.out.println("Debug - Name: " + name + ", Exp: " + exp); // Add this line for debug

                if (!name.isEmpty() && !exp.isEmpty()) {
                    updateGame(targetButton, getImagePathFromButton(targetButton), name, exp);
                    editFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "請填寫所有必要的資訊");
                }
            }
        });


        panel.add(confirmEditButton);

        // Wrap the code in invokeLater to ensure it runs on the EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                editFrame.setVisible(true);
            }
        });
    }


    private String getImagePathFromButton(JButton button) {
        String imagePath = "";

        // 解析按鈕的 tooltip，獲取 image path
        String[] tooltipLines = button.getToolTipText().split("\n");
        for (String line : tooltipLines) {
            if (line.startsWith("聯盟名稱: ")) {
                // 不處理遊戲名稱
            } else if (line.startsWith("聯盟描述: ")) {
                // 不處理遊戲描述
            } else {
                // 更新為處理 image path
                imagePath = line;
            }
        }

        return imagePath;
        
    }

    private void updateGame(JButton targetButton, String newImagePath, String newName, String newExp) {
        System.out.println("Update League - Name: " + newName + ", Exp: " + newExp + ", ImagePath: " + newImagePath);
        System.out.println("New Name: " + newName);
        System.out.println("New Exp: " + newExp);
        try {
            BufferedImage originalImage = ImageIO.read(new java.io.File(newImagePath));
            Image scaledImage = originalImage.getScaledInstance(100, 80, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            System.out.println("Update League - Setting Icon and Tooltip");

            targetButton.setIcon(scaledIcon);

            // 更新按鈕的文字內容和提示信息
            targetButton.setToolTipText("聯盟名稱: " + newName + "\n聯盟描述: " + newExp);
            targetButton.setText("<html><center>" + newName + "<br>" + newExp + "</center></html>");

            // 顯式地告訴容器重新繪製
            targetButton.revalidate();
            targetButton.repaint();

            System.out.println("Update League - Completed");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void deleteGame(JButton targetButton) {
        inputPanel.remove(targetButton);
        inputPanel.revalidate();
        inputPanel.repaint();
    }
}
