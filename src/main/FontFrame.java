/* Author: Gray Sullivan, 2015.
 * graysullivan.com
 * 
 * Basic Text Editor Source Code
 * 
 */

package main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FontFrame {

static String[] fonts = {"Font", "Comic Sans MS (Default)", "Arial", "Consolas", "Times New Roman" };
static String[] sizes = {"Size", "10", "12", "14", "16 (Default)", "18", "20", "22", "24", "26", "28", "30" };
public static String chosenFont = "Comic Sans MS";
public static int chosenSize = 16;
private static JPanel panel;
private static JComboBox fontList = new JComboBox(fonts);
private static JComboBox sizeList = new JComboBox(sizes);
private static JLabel fontText = new JLabel("AaBbCcDdEe", SwingConstants.CENTER);
private static JLabel choose = new JLabel("Choose a Font", SwingConstants.CENTER);
private static JLabel error = new JLabel("Error", SwingConstants.CENTER);
private static JButton done = new JButton("Done");
static GridBagConstraints gbc = new GridBagConstraints();
	
	
	static JFrame frame = new JFrame("Notebook - Font"); {
		createView();
	}
	
	public static void createView() {
		//Creates UI components.
		
		frame.setSize(412, 280);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.revalidate();
		frame.repaint();
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		Font cFont = new Font("Arial", Font.PLAIN, 18);
		
		fontList.setFont(cFont);
		sizeList.setFont(cFont);
		
		panel.setLayout(new GridBagLayout());
		done.setMargin(new Insets(15, 15, 15, 15));
		
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 3, 6, 3);
        
		panel.add(fontList, gbc);
		gbc.gridx++;
		panel.add(sizeList, gbc);
	
		frame.getContentPane().add(fontText, BorderLayout.CENTER);
		frame.getContentPane().add(done, BorderLayout.SOUTH);
        
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		addListeners();
	}
	
	public static void addListeners() {
		//Fonts
		final Font fcomicSans = new Font("Comic Sans MS", Font.PLAIN, chosenSize);
		final Font farial = new Font("Arial", Font.PLAIN, chosenSize);
		final Font fconsolas = new Font("Consolas", Font.PLAIN, chosenSize);
		final Font ftimesNewRoman = new Font("Times New Roman", Font.PLAIN, chosenSize);
		
		choose.setFont(fcomicSans);
		fontText.setFont(fcomicSans);
		
		done.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		
		});
		
		fontList.addActionListener(new ActionListener() {
			//Checks which font the user has selected, then sets font to it. 
			
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == fontList) {
					JComboBox cb = (JComboBox)e.getSource();
					String font = (String)cb.getSelectedItem();
					System.out.println("Source");
					switch(font) {
						case "Font": chosenFont = "Comic Sans MS"; fontText.setFont(fcomicSans); updateFont(); panel.updateUI();
							break;
						case "Comic Sans MS (Default)": chosenFont = "Comic Sans MS"; fontText.setFont(fcomicSans); updateFont(); panel.updateUI();
							break;
						case "Arial": chosenFont = "Arial"; fontText.setFont(farial); updateFont(); panel.updateUI();
							break;
						case "Consolas": chosenFont = "Consolas"; fontText.setFont(fconsolas); updateFont(); panel.updateUI();
							break; 
						case "Times New Roman": chosenFont = "Times New Roman"; fontText.setFont(ftimesNewRoman); updateFont(); panel.updateUI();
							break;
						default: panel.removeAll(); panel.add(fontList); panel.add(error);
					}
				}
			}
		});
		
		sizeList.addActionListener(new ActionListener() {
			//Checks which font size the user has selected, then sets font size to it.
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == sizeList) {
					JComboBox cb = (JComboBox)e.getSource();
					String size = (String)cb.getSelectedItem(); 
					switch(size) {
					case "Size": chosenSize = 16; updateSize();
						break;
					case "10": chosenSize = 10; updateSize();
						break;
					case "12": chosenSize = 12; updateSize();
						break;
					case "14": chosenSize = 14; updateSize();
						break;
					case "16 (Default)": chosenSize = 16; updateSize();
						break;
					case "18": chosenSize = 18; updateSize();
						break;
					case "20": chosenSize = 20; updateSize();
						break;
					case "22": chosenSize = 22; updateSize();
						break;
					case "24": chosenSize = 24; updateSize();
						break;
					case "26": chosenSize = 26; updateSize(); 
						break;
					case "28": chosenSize = 28; updateSize();
						break;
					case "30": chosenSize = 30; updateSize();
						break;
					}
				}
			}
			
		});
		
	}
	
	public static void updateSize() {
		//Updates text area font size in main (Editor) class. 
		fontText.setFont(new Font(chosenFont, Font.PLAIN, chosenSize)); panel.updateUI(); updateFont();
	}
	
	public static void updateFont() {
		//Updates text area font in main (Editor) class.
		switch(chosenFont) {
			case "Comic Sans MS": Editor.ta.setFont(new Font("Comic Sans MS", Font.PLAIN, chosenSize));
				break;
			case "Arial": Editor.ta.setFont(new Font("Arial", Font.PLAIN, chosenSize));
				break;
			case "Consolas": Editor.ta.setFont(new Font("Consolas", Font.PLAIN, chosenSize));
				break;
			case "Times New Roman": Editor.ta.setFont(new Font("Times New Roman", Font.PLAIN, chosenSize));
				break;
		}
	}
	
}

