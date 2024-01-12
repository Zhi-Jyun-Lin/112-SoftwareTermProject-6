import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeagueOwnerPage extends JFrame {
    // 用於各天的JFrame
	
    private JFrame MondayFrame, TuesdayFrame, WednesdayFrame, ThursdayFrame, FridayFrame, SaturdayFrame, SundayFrame;
    private JFrame TheWeekFrame;
    private String a =new String ("星期一：");private String b =new String ("星期二：");private String c =new String ("星期三：");private String d =new String ("星期四：");private String e =new String ("星期五：");private String f =new String ("星期六：");private String g =new String ("星期天：");
   
    private JPanel contentPane;
    private JScrollPane inputScrollPane;
    private JPanel inputPanel;
    private JComboBox<String> comboBox;
    private int selectedIndex = -1;
    


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
            	LeagueOwnerPage frame = new LeagueOwnerPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LeagueOwnerPage() {    	   	
		    	
    	TheWeekFrame = new JFrame("週賽程");
    	TheWeekFrame.setSize(300, 200);
    	TheWeekFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	TheWeekFrame.setLocationRelativeTo(null);
    	TheWeekFrame.getContentPane().setLayout(new BoxLayout(TheWeekFrame.getContentPane(), BoxLayout.Y_AXIS));
    	TheWeekFrame.setVisible(false);  // 設置為不可見
    	
    	MondayFrame = new JFrame("星期一當日賽程");
    	MondayFrame.setSize(300, 200);
    	MondayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	MondayFrame.setLocationRelativeTo(null);
    	MondayFrame.setVisible(false);  // 設置為不可見

    	TuesdayFrame = new JFrame("星期二當日賽程");
    	TuesdayFrame.setSize(300, 200);
    	TuesdayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	TuesdayFrame.setLocationRelativeTo(null);
    	TuesdayFrame.setVisible(false);  // 設置為不可見
    	
    	WednesdayFrame = new JFrame("星期三當日賽程");
    	WednesdayFrame.setSize(300, 200);
    	WednesdayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	WednesdayFrame.setLocationRelativeTo(null);
    	WednesdayFrame.setVisible(false);  // 設置為不可見
    	
    	ThursdayFrame = new JFrame("星期四當日賽程");
    	ThursdayFrame.setSize(300, 200);
    	ThursdayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	ThursdayFrame.setLocationRelativeTo(null);
    	ThursdayFrame.setVisible(false);  // 設置為不可見
    	
    	FridayFrame = new JFrame("星期五當日賽程");
    	FridayFrame.setSize(300, 200);
    	FridayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	FridayFrame.setLocationRelativeTo(null);
    	FridayFrame.setVisible(false);  // 設置為不可見
    	
    	SaturdayFrame = new JFrame("星期六當日賽程");
    	SaturdayFrame.setSize(300, 200);
    	SaturdayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	SaturdayFrame.setLocationRelativeTo(null);
    	SaturdayFrame.setVisible(false);  // 設置為不可見
    	
    	SundayFrame = new JFrame("星期天當日賽程");
    	SundayFrame.setSize(300, 200);
    	SundayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	SundayFrame.setLocationRelativeTo(null);
    	SundayFrame.setVisible(false);  // 設置為不可見
    	
    	
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 520);
        contentPane = new JPanel();
        contentPane.setToolTipText("");
        setContentPane(contentPane);
        contentPane.setLayout(null);

        comboBox = new JComboBox<>();
        comboBox.setForeground(new Color(0, 0, 0));
        comboBox.setBackground(new Color(255, 255, 255));
        comboBox.addItem("殊死戰");
        comboBox.addItem("團隊賽");
        comboBox.addItem("死鬥");
        comboBox.addItem("積分對戰");
        comboBox.setBounds(512, 83, 140, 30);
        contentPane.add(comboBox);
        comboBox.setVisible(false);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIndex = comboBox.getSelectedIndex();
            }
        });
        comboBox.setSelectedIndex(-1);

        
        
        
        JButton btnRemoveInput = new JButton("刪除競賽種類");
        btnRemoveInput.setForeground(new Color(255, 255, 255));
        btnRemoveInput.setBackground(new Color(0, 0, 0));
        btnRemoveInput.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        btnRemoveInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	comboBox.setVisible(true);
                if (selectedIndex != -1) {  // 確認有選擇項目
                    removeSelectedItem();
                    comboBox.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "請先從選單選擇要刪除的遊戲！");
                }
            }
        });
        btnRemoveInput.setBounds(659, 282, 115, 30);
        contentPane.add(btnRemoveInput);

        JButton AddGame = new JButton("新增競賽種類");
        AddGame.setForeground(new Color(255, 255, 255));
        AddGame.setBackground(new Color(0, 0, 0));
        AddGame.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        AddGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openInputDialog();
            }
        });
        AddGame.setBounds(659, 242, 115, 30);
        contentPane.add(AddGame);

        inputScrollPane = new JScrollPane();
        inputScrollPane.setBounds(12, 123, 640, 350);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        inputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        inputScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        inputScrollPane.getVerticalScrollBar().setBlockIncrement(64);
        contentPane.add(inputScrollPane);

        inputPanel = new JPanel();
        inputScrollPane.setRowHeaderView(inputPanel);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        
        
        JButton ButtonInInput = new JButton("星期一");
        ButtonInInput.setBackground(SystemColor.inactiveCaptionBorder);
        ButtonInInput.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
        ButtonInInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedGame = (String) comboBox.getSelectedItem();  // 取得comboBox的選中項目
                displaySelectedGameInMondayFrame(selectedGame);  // 顯示選中的遊戲在MondayFrame上
                MondayFrame.setVisible(true);
                comboBox.setVisible(false);
                comboBox.setSelectedIndex(-1);
            }
        });

        configureButton(ButtonInInput);
        inputPanel.add(ButtonInInput);

        JButton ButtonInInput_2 = new JButton("星期二");
        ButtonInInput_2.setBackground(SystemColor.inactiveCaptionBorder);
        ButtonInInput_2.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
        ButtonInInput_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String selectedGame = (String) comboBox.getSelectedItem();  // 取得comboBox的選中項目
                displaySelectedGameInTuesdayFrame(selectedGame);  // 顯示選中的遊戲在MondayFrame上
        		TuesdayFrame.setVisible(true);
        		comboBox.setVisible(false);
        		comboBox.setSelectedIndex(-1);
        	}
        });
        configureButton(ButtonInInput_2);
        inputPanel.add(ButtonInInput_2);

        JButton ButtonInInput_3 = new JButton("星期三");
        ButtonInInput_3.setBackground(SystemColor.inactiveCaptionBorder);
        ButtonInInput_3.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
        ButtonInInput_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String selectedGame = (String) comboBox.getSelectedItem();  // 取得comboBox的選中項目
                displaySelectedGameInWednesdayFrame(selectedGame);  // 顯示選中的遊戲在MondayFrame上
        		WednesdayFrame.setVisible(true);
        		comboBox.setVisible(false);
        		comboBox.setSelectedIndex(-1);
        	}
        });
        configureButton(ButtonInInput_3);
        inputPanel.add(ButtonInInput_3);

        JButton ButtonInInput_4 = new JButton("星期四");
        ButtonInInput_4.setBackground(SystemColor.inactiveCaptionBorder);
        ButtonInInput_4.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
        ButtonInInput_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String selectedGame = (String) comboBox.getSelectedItem();  // 取得comboBox的選中項目
                displaySelectedGameInThursdayFrame(selectedGame);  // 顯示選中的遊戲在MondayFrame上
        		ThursdayFrame.setVisible(true);
        		comboBox.setVisible(false);
        		comboBox.setSelectedIndex(-1);
        	}
        });
        configureButton(ButtonInInput_4);
        inputPanel.add(ButtonInInput_4);

        JButton ButtonInInput_5 = new JButton("星期五");
        ButtonInInput_5.setBackground(SystemColor.inactiveCaptionBorder);
        ButtonInInput_5.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
        ButtonInInput_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String selectedGame = (String) comboBox.getSelectedItem();  // 取得comboBox的選中項目
                displaySelectedGameInFridayFrame(selectedGame);  // 顯示選中的遊戲在MondayFrame上
        		FridayFrame.setVisible(true);
        		comboBox.setVisible(false);
        		comboBox.setSelectedIndex(-1);
        	}
        });
        configureButton(ButtonInInput_5);
        inputPanel.add(ButtonInInput_5);

        JButton ButtonInInput_6 = new JButton("星期六");
        ButtonInInput_6.setBackground(SystemColor.inactiveCaptionBorder);
        ButtonInInput_6.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
        ButtonInInput_6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String selectedGame = (String) comboBox.getSelectedItem();  // 取得comboBox的選中項目
                displaySelectedGameInSatudayFrame(selectedGame);  // 顯示選中的遊戲在MondayFrame上
        		SaturdayFrame.setVisible(true);
        		comboBox.setVisible(false);
        		comboBox.setSelectedIndex(-1);
        	}
        });
        configureButton(ButtonInInput_6);
        inputPanel.add(ButtonInInput_6);

        JButton ButtonInInput_7 = new JButton("星期日");
        ButtonInInput_7.setBackground(SystemColor.inactiveCaptionBorder);
        ButtonInInput_7.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
        ButtonInInput_7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String selectedGame = (String) comboBox.getSelectedItem();  // 取得comboBox的選中項目
                displaySelectedGameInSundayFrame(selectedGame);  // 顯示選中的遊戲在MondayFrame上
        		SundayFrame.setVisible(true);
        		comboBox.setVisible(false);
        		comboBox.setSelectedIndex(-1);
        	}
        });
        configureButton(ButtonInInput_7);
        inputPanel.add(ButtonInInput_7);

        JButton btnNewButton_1 = new JButton("儲存變更");
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setBackground(new Color(0, 0, 0));
        btnNewButton_1.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	consolidateContentToTheWeekFrame();
            	TheWeekFrame.setVisible(true);
            }
        });

        btnNewButton_1.setBounds(659, 202, 115, 30);
        contentPane.add(btnNewButton_1);
        
        JButton AddNewRule = new JButton("編輯排程");
        AddNewRule.setForeground(new Color(255, 255, 255));
        AddNewRule.setBackground(new Color(0, 0, 0));
        AddNewRule.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        AddNewRule.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		comboBox.setVisible(true);
        	}
        });
        AddNewRule.setBounds(659, 123, 115, 30);
        contentPane.add(AddNewRule);
        
        JButton btnNewButton = new JButton("查看目前規劃");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(0, 0, 0));
        btnNewButton.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TheWeekFrame.setVisible(true);
        	}
        });
        btnNewButton.setBounds(659, 163, 115, 30);
        contentPane.add(btnNewButton);
        
        JButton logoutButton = new JButton("登出");
        logoutButton.setBackground(new Color(0, 0, 0));
        logoutButton.setForeground(new Color(255, 255, 255));
        logoutButton.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        logoutButton.setBounds(12, 10, 80, 25);
        contentPane.add(logoutButton);
        
        JLabel leagueName = new JLabel("尚未命名");
        leagueName.setBounds(274, 10, 308, 16);
        contentPane.add(leagueName);
        
        JLabel leagueIntro = new JLabel("未編輯介紹");
        leagueIntro.setVerticalAlignment(SwingConstants.TOP);
        leagueIntro.setBounds(274, 39, 378, 34);
        contentPane.add(leagueIntro);
        
        JButton setButton = new JButton("設定");
        setButton.setBackground(new Color(0, 0, 0));
        setButton.setForeground(new Color(255, 255, 255));
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 創建一個新的 JFrame 作為主要窗口
                JFrame editFrame = new JFrame("編輯資訊");
                editFrame.setSize(300, 150);
                editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                editFrame.setLocationRelativeTo(null);

                // 創建一個 JPanel 作為容器
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(2, 1));

                // 創建兩個按鈕，一個用於編輯 leagueName，另一個用於編輯 leagueIntro
                JButton editNameButton = new JButton("編輯 聯盟名稱");
                JButton editIntroButton = new JButton("編輯 聯盟介紹");

                // 添加 ActionListener 到按鈕
                editNameButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String newName = JOptionPane.showInputDialog(editFrame, "請輸入新的 聯盟名稱:", leagueName.getText());
                        if (newName != null && !newName.isEmpty()) {
                        	leagueName.setText(newName);
                        }
                    }
                });

                editIntroButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String newIntro = JOptionPane.showInputDialog(editFrame, "請輸入新的 聯盟介紹:", leagueIntro.getText());
                        if (newIntro != null && !newIntro.isEmpty()) {
                            leagueIntro.setText(newIntro);
                        }
                    }
                });

                // 將按鈕添加到面板
                panel.add(editNameButton);
                panel.add(editIntroButton);

                // 將面板添加到 JFrame 並設置可見
                editFrame.getContentPane().add(panel);
                editFrame.setVisible(true);
            }
        });
        setButton.setBounds(684, 8, 90, 29);
        contentPane.add(setButton);
        
        JLabel lblNewLabel = new JLabel("聯盟名稱：");
        lblNewLabel.setBounds(210, 10, 84, 16);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("聯盟介紹：");
        lblNewLabel_1.setBounds(210, 39, 84, 16);
        contentPane.add(lblNewLabel_1);
        
        JButton expButton = new JButton("說明");
        expButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 彈出第一個彈跳視窗，包含五個按鈕
                String[] options = {"儲存變更", "查看目前規劃", "新增競賽種類", "刪除競賽種類", "編輯排程"};
                int choice = JOptionPane.showOptionDialog(null, "請選擇一個按鈕", "選擇按鈕", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                
                // 根據用戶的選擇，彈出相應的彈跳視窗
                switch (choice) {
                    case 0:
                        JOptionPane.showMessageDialog(null, "將此星期排定的賽程存入表單");
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "開啟已儲存的表單，查看目前規劃");
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "開創新的遊戲種類\n用於“編輯排程“時選擇");
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "刪除“編輯排程“中 遊戲種類表單 之選項");
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "開啟 遊戲種類表單 ，選定遊戲種類後，點選欲排定之日期");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "請選擇一個說明");
                        break;
                }
            }
        });

        expButton.setForeground(Color.WHITE);
        expButton.setFont(new Font("Dialog", Font.PLAIN, 16));
        expButton.setBackground(Color.BLACK);
        expButton.setBounds(684, 44, 90, 25);
        contentPane.add(expButton);
        
        JLabel lblNewLabel_1_1 = new JLabel("！排訂完一星期之賽事後記得按下”儲存變更“儲存表單！");
        lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        lblNewLabel_1_1.setBounds(12, 97, 352, 16);
        contentPane.add(lblNewLabel_1_1);
        
        
        logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 處理登出按鈕的動作
				System.out.println("Logout Button Clicked"); // Add this line for debugging
		        showCharacterGui();// 呼叫登出後的處理方法
			}
		});
        
        
    }

    public void showCharacterGui() {
		// 隱藏當前的 LeagueOwnerPage，而不是關閉
	    setVisible(false);
	    
	    // 呼叫 CharacterGui 的建構子重新顯示身分選擇畫面
	    CharacterGui cha = new CharacterGui();
	}
    
    private void configureButton(JButton button) {
        Dimension size = new Dimension(651, 50);
        button.setMinimumSize(size);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        inputPanel.add(Box.createVerticalGlue());
        inputPanel.add(button);
        inputPanel.add(Box.createVerticalGlue());
    }

    private void openInputDialog() {
        JFrame inputFrame = new JFrame("輸入競賽名稱");
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
    
    private void removeSelectedItem() {
        int result = JOptionPane.showConfirmDialog(null, "確定要刪除選則中的遊戲嗎？", "確認刪除", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            comboBox.removeItemAt(selectedIndex);
            JOptionPane.showMessageDialog(null, "選中的遊戲已成功刪除！");
            selectedIndex = -1;  // 重置選擇索引
        }
        if (result == JOptionPane.NO_OPTION) {
        	comboBox.setSelectedIndex(-1);
        }
    }
    private void displaySelectedGameInMondayFrame(String gameName) {
        if (gameName != null && !gameName.isEmpty()) {
            JLabel gameLabel = new JLabel(gameName);
            MondayFrame.getContentPane().removeAll();  // 清除之前的內容
            MondayFrame.getContentPane().add(gameLabel);  // 添加新的內容
            MondayFrame.revalidate();  // 重新布局
            MondayFrame.repaint();  // 重繪視窗以顯示新的內容
            JOptionPane.showMessageDialog(null, "賽程已新增！");
            MondayFrame.setVisible(true);  // 顯示MondayFrame
            
        }
    }
    
    private void displaySelectedGameInTuesdayFrame(String gameName) {
        if (gameName != null && !gameName.isEmpty()) {
            JLabel gameLabel = new JLabel(gameName);
            TuesdayFrame.getContentPane().removeAll();  // 清除之前的內容
            TuesdayFrame.getContentPane().add(gameLabel);  // 添加新的內容
            TuesdayFrame.revalidate();  // 重新布局
            TuesdayFrame.repaint();  // 重繪視窗以顯示新的內容
            JOptionPane.showMessageDialog(null, "賽程已新增！");
            TuesdayFrame.setVisible(true);  // 顯示MondayFrame
            
        }
    }
    
    private void displaySelectedGameInWednesdayFrame(String gameName) {
        if (gameName != null && !gameName.isEmpty()) {
            JLabel gameLabel = new JLabel(gameName);
            WednesdayFrame.getContentPane().removeAll();  // 清除之前的內容
            WednesdayFrame.getContentPane().add(gameLabel);  // 添加新的內容
            WednesdayFrame.revalidate();  // 重新布局
            WednesdayFrame.repaint();  // 重繪視窗以顯示新的內容
            JOptionPane.showMessageDialog(null, "賽程已新增！");
            WednesdayFrame.setVisible(true);  // 顯示MondayFrame
            
        }
    }
    
    private void displaySelectedGameInThursdayFrame(String gameName) {
        if (gameName != null && !gameName.isEmpty()) {
            JLabel gameLabel = new JLabel(gameName);
            ThursdayFrame.getContentPane().removeAll();  // 清除之前的內容
            ThursdayFrame.getContentPane().add(gameLabel);  // 添加新的內容
            ThursdayFrame.revalidate();  // 重新布局
            ThursdayFrame.repaint();  // 重繪視窗以顯示新的內容
            JOptionPane.showMessageDialog(null, "賽程已新增！");
            ThursdayFrame.setVisible(true);  // 顯示MondayFrame
            
        }
    }
    
    private void displaySelectedGameInFridayFrame(String gameName) {
        if (gameName != null && !gameName.isEmpty()) {
            JLabel gameLabel = new JLabel(gameName);
            FridayFrame.getContentPane().removeAll();  // 清除之前的內容
            FridayFrame.getContentPane().add(gameLabel);  // 添加新的內容
            FridayFrame.revalidate();  // 重新布局
            FridayFrame.repaint();  // 重繪視窗以顯示新的內容
            JOptionPane.showMessageDialog(null, "賽程已新增！");
            FridayFrame.setVisible(true);  // 顯示MondayFrame
            
        }
    }
    
    private void displaySelectedGameInSatudayFrame(String gameName) {
        if (gameName != null && !gameName.isEmpty()) {
            JLabel gameLabel = new JLabel(gameName);
            SaturdayFrame.getContentPane().removeAll();  // 清除之前的內容
            SaturdayFrame.getContentPane().add(gameLabel);  // 添加新的內容
            SaturdayFrame.revalidate();  // 重新布局
            SaturdayFrame.repaint();  // 重繪視窗以顯示新的內容
            JOptionPane.showMessageDialog(null, "賽程已新增！");
            SaturdayFrame.setVisible(true);  // 顯示MondayFrame
            
        }
    }
    
    private void displaySelectedGameInSundayFrame(String gameName) {
        if (gameName != null && !gameName.isEmpty()) {
            JLabel gameLabel = new JLabel(gameName);
            SundayFrame.getContentPane().removeAll();  // 清除之前的內容
            SundayFrame.getContentPane().add(gameLabel);  // 添加新的內容
            SundayFrame.revalidate();  // 重新布局
            SundayFrame.repaint();  // 重繪視窗以顯示新的內容
            JOptionPane.showMessageDialog(null, "賽程已新增！");
            SundayFrame.setVisible(true);  // 顯示MondayFrame
            
        }
    }
    
    public void consolidateContentToTheWeekFrame() {
    	consolidateFrameContent(MondayFrame, a);
        consolidateFrameContent(TuesdayFrame, b);
        consolidateFrameContent(WednesdayFrame, c);
        consolidateFrameContent(ThursdayFrame, d);
        consolidateFrameContent(FridayFrame, e);
        consolidateFrameContent(SaturdayFrame, f);
        consolidateFrameContent(SundayFrame, g);
    }

    private void consolidateFrameContent(JFrame frame, String day) {
        Component[] components = frame.getContentPane().getComponents();
        JLabel today = new JLabel(day);
        if (components.length == 0) {
            JLabel noEventLabel = new JLabel("今日無賽事");
            frame.getContentPane().add(today);
            TheWeekFrame.getContentPane().add(today);
            frame.getContentPane().add(noEventLabel);
            TheWeekFrame.getContentPane().add(noEventLabel);
        } 
        
        else {
            for (int i = 0; i < components.length; i++) {
                Component component = components[i];
                frame.getContentPane().add(today);
                TheWeekFrame.getContentPane().add(today);
                TheWeekFrame.getContentPane().add(component);
            }
        }
    }
}