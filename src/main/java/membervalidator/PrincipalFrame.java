package membervalidator;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.SQLException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PrincipalFrame extends JFrame{
	
	Checker checker = new Checker();
	AudioPlayer audioPlayer = new AudioPlayer();
	public PrincipalFrame() throws HeadlessException {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension dim = new Dimension();
		dim.setSize(700, 500);
		this.setSize(dim);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		Container contentPane = this.getContentPane();
				
		contentPane.setLayout(null);
		
		JTextField customerField = new JTextField();
		
		customerField.setSize(new Dimension(100,20));
		customerField.setLocation(20,20);
		
		JLabel customerLbl = new JLabel();
		customerLbl.setSize(200,20);
		customerLbl.setVisible(true);
		customerLbl.setLocation(20,40);
		
		JPanel vlidPanel = new JPanel();
		vlidPanel.setVisible(true);
		vlidPanel.setSize(200,100);
		contentPane.getSize();
		
		vlidPanel.setLocation(20,60);
		
		
		contentPane.add(customerField);
		
		contentPane.add(customerLbl);
				
		contentPane.add(vlidPanel);
		
		
		customerField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				check();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				check();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				check();
				
			}
			
						
			private void check() {
                Runnable doAssist = new Runnable() {
                    @Override
                    public void run() {
                    	String customerNumber = customerField.getText();
        				if(customerNumber.length()==12) {
        					System.out.println("Customer number : " + customerNumber);
        					
        					try {
								Customer member = checker.checkMemberValidity(customerNumber);
								if(member!=null) {
									
									setValidationPanelSize(vlidPanel, contentPane);
									customerLbl.setText(member.firstName + " " + member.lastName + " - " + customerNumber);
									vlidPanel.setBackground(Color.GREEN);
									vlidPanel.setBorder(BorderFactory.createBevelBorder(ABORT, Color.DARK_GRAY, Color.LIGHT_GRAY));
									audioPlayer.play("./ok.wav");
								}else {
									setValidationPanelSize(vlidPanel, contentPane);
									customerLbl.setText("");
									vlidPanel.setBackground(Color.RED);
									vlidPanel.setBorder(BorderFactory.createBevelBorder(ABORT, Color.DARK_GRAY, Color.LIGHT_GRAY));
									audioPlayer.play("./not_ok.wav");
								}
							} catch (SQLException e) {
								
								e.printStackTrace();
							} catch (UnsupportedAudioFileException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (LineUnavailableException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
        					
        					customerField.setText("");
        					
        					customerField.requestFocus();
        				}
                    }
                };
                SwingUtilities.invokeLater(doAssist);
            }

		});
		
		
		this.setVisible(true);
		
	}
	
	public void setValidationPanelSize(JPanel vlidPanel, Container contentPane) {
		Double frameWidth = contentPane.getSize().getWidth();
		Double frameHeight =  contentPane.getSize().getHeight();
		
		vlidPanel.setSize(frameWidth.intValue() - 40, frameHeight.intValue() - 80);
	}
	

}
