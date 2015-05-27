* Author: Gray Sullivan, 2015.
 * graysullivan.com
 * 
 * Basic Text Editor Source Code
 * 
 */

package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Editor implements KeyListener {	
	
private JPanel text;
static JPanel menu;
static JPanel space;
public JButton saveAs = new JButton("Save As");
private JButton font = new JButton("Font");
private JButton open = new JButton("Open");
private JButton share = new JButton("Share");
private JButton save = new JButton("Save");
private JButton theme = new JButton("Theme");
private JButton buttonOne = new JButton("...");
private JButton buttonTwo = new JButton("...");
private JButton buttonThree = new JButton("...");
public JFrame exitFrame = new JFrame("Warning");
public JScrollPane sp;
private JPopupMenu pm;
private JMenuItem cut, copy, paste;
public static JTextArea ta;
private static JLabel sc;
String saveName;
String lastSave;
String lastSaveText = "";
String frameName = "Text Editor - Untitled";
int f = 0;
private boolean control, s;
boolean inFile = false;
final Clipboard clipboard = frame.getToolkit().getSystemClipboard();
	
	static JFrame frame = new JFrame("Text Editor - Untitled"); {
		frameName = "Text Editor - Untitled";
		frame.setSize(950,690);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		createView();
	}
	
	public void createView() {
		//Creates UI components.

		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 3, 6, 3);
		
		menu = new JPanel();
		menu.setLayout(new GridBagLayout());
		menu.setBackground(Color.LIGHT_GRAY);
		font.setMargin(new Insets(6, 6, 6, 6));
		saveAs.setMargin(new Insets(6, 6, 6, 6));
		open.setMargin(new Insets(6, 6, 6, 6));
		share.setMargin(new Insets(6, 6, 6, 6));
		save.setMargin(new Insets(6, 6, 6, 6));
		theme.setMargin(new Insets(6, 6, 6, 6));
		buttonOne.setMargin(new Insets(6, 6, 6, 6));
		buttonTwo.setMargin(new Insets(6, 6, 6, 6));
		buttonThree.setMargin(new Insets(6, 6, 6, 6));
		menu.add(saveAs, gbc);
		gbc.gridx++;
		menu.add(open, gbc);
		gbc.gridx++;
		menu.add(font, gbc);
		gbc.gridx++;
		menu.add(theme, gbc);
		gbc.gridy++;
		gbc.gridx = 0;
		menu.add(save, gbc);
		gbc.gridx++;
		menu.add(buttonOne, gbc);
		gbc.gridx++;
		menu.add(buttonTwo, gbc);
		gbc.gridx++;
		menu.add(buttonThree, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		addListeners();
		
		frame.setVisible(true);
	
		SwingUtilities.invokeLater(new Runnable() {
			   public void run() {
			     ta.requestFocusInWindow();
			     ta.grabFocus();
			   }
			 });
		
		nameLoop();
	}
		
	public void nameLoop() {
		//Checks every 50 milliseconds whether or not you have edited the document. 
		while (true) {
		    try
		    {
		        Thread.sleep(50);
		        if(!ta.getText().equals(lastSaveText)) {
					if(frameName.equals("Text Editor - Untitled") || frameName.equals("Text Editor - Untitled (Unsaved)")) {
						frameName = "Text Editor - Untitled (Unsaved)";
						frame.setTitle(frameName);
					} else {
						frame.setTitle("Text Editor - *" + saveName);
						frameName = "Text Editor - *" + saveName;
					}
				} else {
					if(frameName.equals("Text Editor - Untitled")) {
						frameName = "Text Editor - Untitled";
						frame.setTitle(frameName);
					} else {
						if(saveName!=null) {
						frame.setTitle("Text Editor - " + saveName);
						frameName = "Text Editor - " + saveName;
						} else {
							frameName = "Text Editor - Untitled";
							frame.setTitle(frameName);
						}
					}
				}
		    }
		    catch (Exception e)
		    {
		        e.printStackTrace();
		    }
		}
	}
	
	public void addListeners() {
		//Adds button & shortcut listeners.
		
		pm = new JPopupMenu();
		cut = new JMenuItem("Cut     ");
		copy = new JMenuItem("Copy     ");
		paste = new JMenuItem("Paste     ");
		
		pm.add(cut);
		pm.add(copy);
		pm.add(paste);
		
		text = new JPanel();
		text.setLayout(new BorderLayout());
		ta = new JTextArea();
		ta.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		ta.setLineWrap(true);
		ta.addKeyListener(this);
		text.add(ta, BorderLayout.WEST);
		JScrollPane jsp = new JScrollPane(ta);
		text.add(jsp);
	
		space = new JPanel();
		sc = new JLabel("       ");;
		space.add(sc);
		
		frame.getContentPane().add(menu, BorderLayout.NORTH);
		frame.getContentPane().add(text, BorderLayout.CENTER);
		frame.getContentPane().add(space, BorderLayout.WEST);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		space.setBackground(Color.LIGHT_GRAY);
		
		ta.addMouseListener(new MouseAdapter() {
		      public void mousePressed(MouseEvent evt) {
		        if (evt.isPopupTrigger()) {
		          pm.show(evt.getComponent(), evt.getX(), evt.getY());
		        }
		      }

		      public void mouseReleased(MouseEvent evt) {
		        if (evt.isPopupTrigger()) {
		          pm.show(evt.getComponent(), evt.getX(), evt.getY());
		        }
		      }
		    });
		
		copy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedText = ta.getSelectedText();
				StringSelection selection = new StringSelection(selectedText);
				clipboard.setContents(selection, selection); 
			}
			
		});
		
		cut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				String selectedText = ta.getSelectedText();
				StringSelection selection = new StringSelection(selectedText);
				clipboard.setContents(selection, selection); 
				int start = ta.getSelectionStart();
				int end = ta.getSelectionEnd();
				StringBuilder strBuilder = new StringBuilder(ta.getText());
				strBuilder.replace(start, end, "");
				ta.setText(strBuilder.toString());
				
			}
		});
		
		paste.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Transferable clipData = clipboard.getContents(clipboard);
	            if (clipData != null) {
	              try {
	                if 
	                  (clipData.isDataFlavorSupported
					    (DataFlavor.stringFlavor)) {
	                      String s = (String)(clipData.getTransferData(
	                        DataFlavor.stringFlavor));
	                  ta.replaceSelection(s);
	                }
	              } catch (UnsupportedFlavorException ufe) {
	                System.err.println("Flavor unsupported: " + ufe);
	              } catch (IOException ioe) {
	                System.err.println("Data not available: " + ioe);
	              }
	            }
				
			}
			
		});
		 
		theme.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Theme.frame.setVisible(true);
				System.out.println("Theme");
			}
			
		});
		
		font.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FontFrame.frame.setVisible(true);
			}
			
		});
		
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveMethod();
			}
		});
		
		saveAs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveAs();
				}
			});
		
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				JFileChooser fs = new JFileChooser();
				fs.setFileFilter(new FileNameExtensionFilter("Text File (*.txt)", "txt"));
				int result = fs.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					ta.setText("");
					File fi = fs.getSelectedFile();
					lastSave = fs.getSelectedFile().toString();
					lastSaveText = ta.getText();
					System.out.println(fi.getPath());
					
					try {
					String r = "";
	                BufferedReader in = new BufferedReader(
	                        new InputStreamReader(
	                                new FileInputStream(fi), "UTF8"));
	                while((r = in.readLine()) != null) {
	                    System.out.println(r);
	                    ta.setText(ta.getText() + r + "\n");
	                    frame.setTitle("Text Editor - " + fi.getPath());
	                    frameName = "Text Editor - " + fi.getPath();
	                    inFile = true;
	                    saveName = fi.toString();
	                    lastSaveText = ta.getText();
	                }
	                in.close();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
			
			} 
			
		});
		
		frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	if(!lastSaveText.equals(ta.getText())) {
            	f++;
            	if (f == 1) {
            		
                exitFrame.setSize(600, 140);
                exitFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                exitFrame.setLocationRelativeTo(null);
                
                JPanel textPanel = new JPanel();
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout());
                exitFrame.getContentPane().add(textPanel, BorderLayout.CENTER);
                exitFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
                JLabel exitLabel = new JLabel("You are about to exit. This will delete unsaved work.");
                JLabel askLabel = new JLabel("Would you like to save your work?");
                exitLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
                askLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
                JButton save = new JButton("Save");
                JButton close = new JButton("Don't Save");
                JButton cancel = new JButton("Cancel");
                save.setFont(new Font("Dialog", Font.PLAIN, 15));
                close.setFont(new Font("Dialog", Font.PLAIN, 15));
                cancel.setFont(new Font("Dialog", Font.PLAIN, 15));
                textPanel.add(exitLabel, BorderLayout.NORTH);
                textPanel.add(askLabel, BorderLayout.CENTER);
                buttonPanel.add(save);
                buttonPanel.add(close);
                buttonPanel.add(cancel);
                
                save.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						saveMethod();
						if(lastSaveText.equals(ta.getText())) {
							System.exit(0);
						}
					}
                	
                });
                
                close.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
                	
                });
                
                cancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						exitFrame.dispose();
						f = 0;
					}
                	
                });
                exitFrame.setVisible(true);
            } else {
            	exitFrame.toFront();
            }
          } else {
          	System.exit(0);
          }
        } 
            
        });
		
	}
	
	public static void main(String[] args) {
		loadUI();
		new Theme();
		new FontFrame();
		new Editor();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		//CTRL + S save shortcut
		if(key == KeyEvent.VK_CONTROL) {
			control = true;
		}
		
		if(key == KeyEvent.VK_S) {
			s = true;
		}
		
		if(control && s) {
			saveMethod();
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_CONTROL) {
			control = false;
		}
		
		if(key == KeyEvent.VK_S) {
			s = false;
		}
		
	}

    @Override
    public void keyTyped(KeyEvent e) {}

	public void saveAs() {
		//Save as function
		control = false;
		s = false;
		final JFileChooser fc = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Text File (*.txt)", "txt");
		fc.setFileFilter(filter);
		int response = fc.showSaveDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			String content = ta.getText();
			File fileName = fc.getSelectedFile();
			String fileNameString = fc.getSelectedFile().toString();
			lastSave = fc.getSelectedFile().toString();
			lastSaveText = ta.getText();
			try {
				if (fileNameString.indexOf(".txt")>=0) {
				FileWriter fw = new FileWriter(fileName.getPath());
				fw.write(content);
				fw.flush();
				fw.close();
				frame.setTitle("Text Editor - " + fileName.getPath());
				inFile = true;
				saveName = fileName.toString();
				} else {
					FileWriter fw = new FileWriter(fileName.getPath() + ".txt");
					fw.write(content);
					fw.flush();
					fw.close();
					frame.setTitle("Text Editor - " + fileName.getPath());
					inFile = true;
					saveName = fileName.toString();
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		} 
	}
	
	public void save() {
		//Save function
		try {
			FileWriter fw = new FileWriter(lastSave);
			lastSaveText = ta.getText();
			fw.write(ta.getText());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveMethod() {
		//Checks whether to save or save as
		if(inFile) {
			save();
		} else {
			saveAs();
		}
	}
	
	public static void loadUI() {
		//Loads UI Look and Feel.
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            System.out.println("UI Look and Feel Loaded");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
            System.out.println("UI Look and Feel Not Loaded");
        }
	}

}
