/* Author: Gray Sullivan, 2015.
 * graysullivan.com
 * 
 * Basic Text Editor Source Code
 * 
 */

package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Theme {

	private static JPanel panel;
	private static JButton bdefault = new JButton("Default");
	private static JButton dark = new JButton("Dark");
	private static JButton black = new JButton("Black");
	private static JButton blue = new JButton("Blue");
	private static JButton red = new JButton("Red");
	private static JButton orange = new JButton("Orange");
	private static JButton done = new JButton("Done");

	static JFrame frame = new JFrame("Text Editor - Theme Chooser"); {
		
		createView();
	}
	
	public void createView() {
		//Creates UI components.
		
		frame.setSize(412, 280);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.revalidate();
		frame.repaint();
		
		panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 8, 6, 8);
		panel.setFont(new Font("Dialog", Font.PLAIN, 20));
		gbc.gridx = 0;
		gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
		panel.add(bdefault, gbc);
		bdefault.setMargin(new Insets(30, 40, 30, 40));
		gbc.gridx++;
		panel.add(dark, gbc);
		dark.setMargin(new Insets(30, 40, 30, 40));
		gbc.gridx++;
		panel.add(black, gbc);
		black.setMargin(new Insets(30, 40, 30, 40));
		gbc.gridx = 0;
		gbc.gridy++;
		panel.add(blue, gbc);
		blue.setMargin(new Insets(30, 40, 30, 40));
		gbc.gridx++;
		panel.add(red, gbc);
		red.setMargin(new Insets(30, 40, 30, 40));
		gbc.gridx++;
		panel.add(orange, gbc);
		orange.setMargin(new Insets(30, 40, 30, 40));
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(done, gbc);
		done.setMargin(new Insets(20, 40, 20, 40));
		gbc.fill = GridBagConstraints.BOTH;
		
		addListeners();
		
	}
	
	public void addListeners() {
		//Adds listeners to change component colours.
		
		done.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		
		bdefault.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Editor.ta.setBackground(Color.WHITE);
				Editor.ta.setForeground(Color.BLACK);
				Editor.menu.setBackground(Color.LIGHT_GRAY);
				Editor.space.setBackground(Color.LIGHT_GRAY);
			}
		});
		
		black.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Editor.menu.setBackground(Color.LIGHT_GRAY);
				Editor.space.setBackground(Color.LIGHT_GRAY);
				Editor.ta.setBackground(Color.BLACK);
				Editor.ta.setForeground(Color.WHITE);
			}
		});
		
		dark.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Editor.ta.setBackground(Color.DARK_GRAY);
				Editor.ta.setForeground(Color.WHITE);
				Editor.menu.setBackground(Color.BLACK);
				Editor.space.setBackground(Color.BLACK);
			}
		});
		
		blue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Editor.menu.setBackground(Color.LIGHT_GRAY);
				Editor.space.setBackground(Color.LIGHT_GRAY);
				Editor.ta.setBackground(Color.BLUE);
				Editor.ta.setForeground(Color.WHITE);
			}
		});
		
		red.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Editor.menu.setBackground(Color.LIGHT_GRAY);
				Editor.space.setBackground(Color.LIGHT_GRAY);
				Editor.ta.setBackground(Color.RED);
				Editor.ta.setForeground(Color.WHITE);
			}
		});
		
		orange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Editor.menu.setBackground(Color.LIGHT_GRAY);
				Editor.space.setBackground(Color.LIGHT_GRAY);
				Editor.ta.setBackground(Color.ORANGE);
				Editor.ta.setForeground(Color.BLACK);
			}
		});
		
	}
	
}
