import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PrincipalFrame extends JFrame{

	public PrincipalFrame() throws HeadlessException {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension dim = new Dimension();
		dim.setSize(700, 500);
		this.setSize(dim);
		
		Container contentPane = this.getContentPane();
				
		contentPane.setLayout(null);
		
		JTextField customerField = new JTextField();
		
		customerField.setSize(new Dimension(100,20));
		customerField.setLocation(20,20);
		
		this.add(customerField);
		
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
			
			public void check() {
				
				String customerNumber = customerField.getText();
				if(customerNumber.length()==12) {
					System.out.println("Customer number : " + customerNumber);
					
					customerField.setText("");
					
					customerField.requestFocus();
				}
				
				
			}
		});
		
		
		this.setVisible(true);
		
	}
	
	

}
