import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AdvertiserPage extends JFrame {
	 private JFrame frame;
	 private JPanel adsPanel;
	 private JComboBox<String> adTypeComboBox;
	 private JTextField remainingBalanceField;

	 private static final String[] AD_TYPES = { "聯盟贊助商", "遊戲廣告商" };
	 private static final double LEAGUE_SPONSOR_COST = 1000.0;
	 private static final double GAME_ADVERTISER_COST = 800.0;

	 private double remainingBalance = 5000.0;
	 

	 public static void main(String[] args) {
	  EventQueue.invokeLater(() -> {
	   try {
	    AdvertiserPage window = new AdvertiserPage();
	    window.initialize();
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	  });
	 }

	 /**
	  * @wbp.parser.entryPoint
	  */
	 public void initialize() {
		 
	  frame = new JFrame();
	  frame.setBounds(300, 300, 600, 400);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	  frame.getContentPane().setBackground(new Color(173, 216, 230));

	  frame.getContentPane().setLayout(new BorderLayout());

	  adsPanel = new JPanel();
	  adsPanel.setLayout(new BoxLayout(adsPanel, BoxLayout.Y_AXIS));
	  JScrollPane scrollPane = new JScrollPane(adsPanel);
	  frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

	  JButton btnNewAd = new JButton("新增廣告");
	  btnNewAd.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	    createNewAd();
	   }
	  });
	  frame.getContentPane().add(btnNewAd, BorderLayout.NORTH);

	  JPanel controlPanel = new JPanel();
	  frame.getContentPane().add(controlPanel, BorderLayout.SOUTH);

	  JLabel lblAdType = new JLabel("廣告方案:");
	  controlPanel.add(lblAdType);

	  adTypeComboBox = new JComboBox<>(AD_TYPES);
	  controlPanel.add(adTypeComboBox);

	  JButton btnCalculateCost = new JButton("計算廣告費用");
	  btnCalculateCost.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	    calculateCost();
	   }
	  });
	  controlPanel.add(btnCalculateCost);

	  JLabel lblRemainingBalance = new JLabel("剩餘餘額:");
	  controlPanel.add(lblRemainingBalance);

	  JPanel topPanel = new JPanel();
	  topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	  String multiLineText = "<html>請注意：<br>聯盟贊助商的廣告花費為1000元<br>遊戲廣告商為800元<br>新增廣告後需點擊下方<br>'計算廣告費用'以進行扣款，謝謝</html>";
	  JLabel textLabel = new JLabel(multiLineText);
	  topPanel.add(textLabel);

	  frame.getContentPane().add(topPanel, BorderLayout.WEST);

	  remainingBalanceField = new JTextField();
	  remainingBalanceField.setEditable(false);
	  controlPanel.add(remainingBalanceField);

	  updateRemainingBalance();
	

	  frame.setVisible(true);
	 }
	 

	 /**
	  * @wbp.parser.entryPoint
	  */
	 private void createNewAd() {

	  String[] options = { "聯盟贊助商", "遊戲廣告商" };
	  int adType = JOptionPane.showOptionDialog(frame, "選擇廣告類型", "廣告類型", JOptionPane.DEFAULT_OPTION,
	    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

	  String input = JOptionPane.showInputDialog(frame, "請輸入廣告名稱");
	  if (input != null && !input.isEmpty()) {
	   JPanel adPanel = new JPanel();
	   adPanel.setLayout(new FlowLayout());

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
	  String selectedAdType = (String) adTypeComboBox.getSelectedItem();
	  double cost = 0.0;

	  if (selectedAdType.equals(AD_TYPES[0])) {
	   cost = LEAGUE_SPONSOR_COST;
	  } else if (selectedAdType.equals(AD_TYPES[1])) {
	   cost = GAME_ADVERTISER_COST;
	  }

	  if (remainingBalance >= cost) {
	   remainingBalance -= cost;
	   updateRemainingBalance();
	   JOptionPane.showMessageDialog(frame, "廣告費用已計算完成。");
	  } else {
	   JOptionPane.showMessageDialog(frame, "餘額不足以支付廣告費用！", "錯誤", JOptionPane.ERROR_MESSAGE);
	  }
	 }

	 private void updateRemainingBalance() {
	  remainingBalanceField.setText(String.valueOf(remainingBalance));
	 }

}
