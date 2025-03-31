package membervalidator;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.SQLException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
				
		JTextField customerField = new JTextField();
		
		customerField.setSize(new Dimension(20,20));
		customerField.setLocation(20,20);
		
		JLabel customerLbl = new JLabel();
		customerLbl.setSize(200,20);
		customerLbl.setVisible(true);
		customerLbl.setLocation(20,40);
		
		JPanel validPanel = new JPanel();
				
		validPanel.setVisible(true);
		validPanel.setLocation(20,60);
		
		JLabel messageLbl = new JLabel();
		
		messageLbl.setVisible(true);
		
		messageLbl.setText("Veuillez scanner votre carte de membre, SVP.");
		
		Font lblFont = new Font("Serif", Font.PLAIN, 40);
		messageLbl.setFont(lblFont);
		
		
		
		validPanel.add(messageLbl);
		
		this.add(customerField);
		
		this.add(customerLbl);
				
		this.add(validPanel);
		
		setValidationPanelSize(validPanel);
				
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
									
									setValidationPanelSize(validPanel);
									messageLbl.setText(member.firstName + " " + member.lastName + " - " + customerNumber);
									validPanel.setBackground(Color.GREEN);
									audioPlayer.play("./ok_16.wav");
								}else {
									setValidationPanelSize(validPanel);
									customerLbl.setText("");
									validPanel.setBackground(Color.RED);
									audioPlayer.play("./not_ok_16.wav");
									messageLbl.setText("Carte de membre non reconnue ou abonnemnt non valide");
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
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
							}
        					
        					customerField.setText("");
        					
        					customerField.requestFocus();
        				}
                    }
                };
                SwingUtilities.invokeLater(doAssist);
            }

		});
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.setVisible(true);
		
		Double frameWidth = this.getSize().getWidth();
		Double frameHeight =  this.getSize().getHeight();
		System.out.println("Container size : " + frameWidth + ", " + frameHeight);
		
	}
	
	public void setValidationPanelSize(JPanel validPanel) {
		Double frameWidth = this.getSize().getWidth();
		Double frameHeight =  this.getSize().getHeight();
		System.out.println("Container size : " + frameWidth + ", " + frameHeight);
		validPanel.setSize(frameWidth.intValue() - 4, frameHeight.intValue() - 80);
	}
	

}
