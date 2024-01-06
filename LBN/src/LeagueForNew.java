import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeagueForNew extends JFrame {
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
            	LeagueForNew frame = new LeagueForNew();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LeagueForNew() {
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
        setBounds(100, 100, 737, 557);
        contentPane = new JPanel();
        contentPane.setToolTipText("");
        setContentPane(contentPane);
        contentPane.setLayout(null);

        comboBox = new JComboBox<>();
        comboBox.addItem("殊死戰");
        comboBox.addItem("團隊賽");
        comboBox.addItem("死鬥");
        comboBox.addItem("積分對戰");
        comboBox.setBounds(60, 60, 130, 40);
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
        btnRemoveInput.setBounds(139, 10, 120, 30);
        contentPane.add(btnRemoveInput);

        JButton AddGame = new JButton("新增競賽種類");
        AddGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openInputDialog();
            }
        });
        AddGame.setBounds(10, 11, 117, 29);
        contentPane.add(AddGame);

        inputScrollPane = new JScrollPane();
        inputScrollPane.setBounds(46, 147, 651, 362);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        inputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        inputScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        inputScrollPane.getVerticalScrollBar().setBlockIncrement(64);
        contentPane.add(inputScrollPane);

        inputPanel = new JPanel();
        inputScrollPane.setViewportView(inputPanel);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        
        
        JButton ButtonInInput = new JButton("星期一");
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

        JButton btnNewButton_1 = new JButton("列印一星期賽程");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	consolidateContentToTheWeekFrame();
            	TheWeekFrame.setVisible(true);
            }
        });

        btnNewButton_1.setBounds(581, 11, 117, 29);
        contentPane.add(btnNewButton_1);
        
        JButton AddNewRule = new JButton("編輯排程");
        AddNewRule.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		comboBox.setVisible(true);
        	}
        });
        AddNewRule.setBounds(580, 106, 117, 29);
        contentPane.add(AddNewRule);
        
        JButton btnNewButton = new JButton("查看目前規劃");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TheWeekFrame.setVisible(true);
        	}
        });
        btnNewButton.setBounds(439, 11, 117, 29);
        contentPane.add(btnNewButton);
        
        
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
