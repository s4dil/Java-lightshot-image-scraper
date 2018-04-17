package me.roman.lightshotdumper;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import me.roman.lightshotdumper.utils.Utils;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JLabel;

public class Gui extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea resultArea;


	public Gui() {
		setTitle("Lightshot link scraper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 337);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		resultArea = new JTextArea();
		resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

		JScrollPane scroll = new JScrollPane(resultArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(10, 11, 290, 239);
		contentPane.add(scroll);
		
		JSpinner countBox = new JSpinner();
		countBox.setBounds(118, 259, 73, 20);
		contentPane.add(countBox);
		
		JButton scrapButton = new JButton("SCRAP LINKS");
		scrapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread() {
					@Override
					public void run() {
						Integer count = (Integer) countBox.getValue();
						for (int i = 0; i < count; i++) {
							try {
								String link = Utils.checkLink(Utils.generateRandomLink(6));
								resultArea.append(link + "\n");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						System.out.println("Done!");
					}
				}.start();
			}
		});
		scrapButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrapButton.setBounds(10, 280, 291, 23);
		contentPane.add(scrapButton);
		
		JLabel lblNewLabel = new JLabel("by Roman");
		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		lblNewLabel.setBounds(201, 264, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Count:");
		lblNewLabel_1.setBounds(76, 261, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		countBox.setValue(5);
	}
}
