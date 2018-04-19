package me.roman.lightshotdumper;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import me.roman.lightshotdumper.utils.Utils;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JCheckBox;

public class Gui extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int valid = 0;
	private int unvalid = 0;


	public Gui() {
		setTitle("Lightshot Dumper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 279, 399);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox removeAfterOpen = new JCheckBox("Remove link after opening");
		removeAfterOpen.setSelected(true);
		removeAfterOpen.setBounds(113, 272, 151, 23);
		contentPane.add(removeAfterOpen);
		
		DefaultListModel<String> dlm = new DefaultListModel<String>();
	    JList<String> list = new JList<>(dlm);
	    list.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	            	String value = (String)list.getModel().getElementAt(list.locationToIndex(e.getPoint()));
	                Utils.openWebpage(value);
	                
	                if(removeAfterOpen.isSelected())
	                dlm.remove(list.locationToIndex(e.getPoint()));
	            }
	        }
	    });
	    list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list.setBounds(322, 11, 258, 239);
		
		JScrollPane scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(10, 14, 254, 251);
		
		contentPane.add(scroll);
		
		JLabel validLabel = new JLabel("Valid: 0");
		validLabel.setForeground(new Color(0, 128, 0));
		validLabel.setBounds(10, 269, 104, 14);
		contentPane.add(validLabel);
		
		JLabel unvalidLabel = new JLabel("Not valid: 0");
		unvalidLabel.setForeground(new Color(255, 0, 0));
		unvalidLabel.setBounds(11, 287, 104, 14);
		contentPane.add(unvalidLabel);
		
		JSpinner countBox = new JSpinner();
		countBox.setBounds(52, 312, 73, 20);
		contentPane.add(countBox);
		
		JButton scrapButton = new JButton("SCRAP LINKS");
		scrapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Starting...");
				new Thread() {
					@Override
					public void run() {
						Integer count = (Integer) countBox.getValue();
						for (int i = 0; i < count; i++) {
							String link = Utils.checkLink(Utils.generateRandomLink(6));
								if(link.endsWith("| VALID IMAGE!")) {
									dlm.addElement(link.replace("| VALID IMAGE!", "") + "\n");
									valid++;
									
								} else if(link.endsWith("| NOT VALID!")) {
									unvalid++;
								}
								validLabel.setText("Valid: " + valid);
								unvalidLabel.setText("Not valid: " + unvalid);
						}
						System.out.println("Done!");
					}
				}.start();
			}
		});
		scrapButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrapButton.setBounds(10, 343, 254, 23);
		contentPane.add(scrapButton);
		
		JLabel lblNewLabel = new JLabel("by Roman");
		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		lblNewLabel.setBounds(217, 327, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Count:");
		lblNewLabel_1.setBounds(10, 314, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		countBox.setValue(25);
		
		JLabel lblValidImages = new JLabel("Valid images:");
		lblValidImages.setBounds(10, 0, 126, 14);
		contentPane.add(lblValidImages);
	}
}
