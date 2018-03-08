import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javafx.scene.control.TextField;

/** A program that makes code from a message that can be decrypted and encrypted
	@author Jeremy Hilliker @ Langara
	@author Alvin Ng @ Langara
	@version 2.05 May all your bacon burn!
	@see <a href="https://d2l.langara.bc.ca/d2l/lms/dropbox/user/folder_submit_files.d2l?db=51408&grpid=0&isprv=0&bp=0&ou=88736">a 09: Secrets</a>
*/

public class SecretComp extends JComponent {
	
	private final JTextField p1; // textbox for output
	private final JPanel ctrlPnl;// the controls
	
	// Controls
	private final JLabel key;//Label for key TextBox
	private final JLabel msg;//Label for Msg Textbox
	private final JButton encrypt;//Button to Encrypt 
	private final JButton decrypt;//Button to Decrypt
	private final JTextField keyText;//Key Textbox
	private final JTextField msgText;//Message TextBox
	private final JButton a;// Button A for the unlock code
	private final JButton b;// Button B for the unlock code
	private final JButton c;// Button C for the unlock code
   
   
	public SecretComp() {
		
		//Initialize Secrets object
		Secrets newSecret = SecretsFactory.makeSecrets();
	  
		//Set the initial layout
		setLayout(new BorderLayout(0,2));
	  
		//Add the output text box
		p1 = new JTextField();//changed to textField so it's copy and pastable
		p1.setEditable(false);
		add(p1);

		//Add the control panel
	 	ctrlPnl = new JPanel();
		ctrlPnl.setLayout(new GridLayout(0,3));
	  
		//Row 1
		// label Key
	  	key = new JLabel("key");
	  	ctrlPnl.add(key);
	  
		// label Msg
		msg = new JLabel("msg");
		ctrlPnl.add(msg);
		
		// Button Encrypt
		encrypt = new JButton("Encrypt");
		ctrlPnl.add(encrypt);

		//Row 2
		//TextBox Key
		keyText = new PTextField("Enter Key Here");
		ctrlPnl.add(keyText);
		
		//TextBox Msg
		msgText = new PTextField("Enter Msg here");
		ctrlPnl.add(msgText);
		
		//Decrypt Button
		decrypt = new JButton("Decrypt");
		ctrlPnl.add(decrypt);

		//Row 3
		//Button A
		a = new JButton("A");
		ctrlPnl.add(a);
		
		//Button B
		b = new JButton("B");
		ctrlPnl.add(b);
		
		//Button C
		c = new JButton("C");
		ctrlPnl.add(c);

		add(ctrlPnl, BorderLayout.SOUTH);

		//Action Listners
		//Set A to unlock(A)
		a.addActionListener(a -> {
			newSecret.unlock("A");
			p1.setText(newSecret.getMessage());
	  	});
	  
		//Set B to unlock(B)
		b.addActionListener(b -> {
			newSecret.unlock("B");
			p1.setText(newSecret.getMessage());
		});
	  
		//Set C to unlock(C)
		c.addActionListener(c -> {
			newSecret.unlock("C");
			p1.setText(newSecret.getMessage());
		});
	  	
		//If state is locked unlock with text in key and set output to getMessage
	 	 keyText.addActionListener(k -> {
			if (newSecret.getState() == Secrets.State.LOCKED){
				newSecret.unlock(keyText.getText());
				p1.setText(newSecret.getMessage());
			}
		});
		
		//Encrypts message and Sets to Output
		encrypt.addActionListener(e -> {
			p1.setText(
				newSecret.encrypt(
			 		keyText.getText(), msgText.getText()
			 	)
			);
		});
		
		//Decrypts message and Sets to Output
		decrypt.addActionListener(e -> {
			p1.setText(
				newSecret.decrypt(
			 		keyText.getText(), msgText.getText()
			 	)
			);
		});
		
		//Set Output
		p1.setText(newSecret.getMessage());
	}
/** A prompt class in order to set the prompt for a JTextField uses FocusListner and FocusEvent
	@author user3033626 @ stackoverflow.com
	@version 2017-11-25 18:50
	@see <a href="https://stackoverflow.com/questions/11200585/adding-a-prompt-text-property-to-jtextfield#_=_">PTextField</a>
*/
	public class PTextField extends JTextField {
		public PTextField(final String prompt) {
			super(prompt);
			addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					if(getText().isEmpty()) {
						setText(prompt);
					}
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					if(getText().equals(prompt)) {
						setText("");
					}
				}
			});

		}
	}
}
/*
 * STEP 09 * Message from assignment.
			key: The.Secret.Key
	cipher-text: cgmMbvbyDONppeM2paS+7A==
	 plain-text: You did it!

 * STEP 10 * Message sent to classmate. //clasmate was Mitchel Chow
			key: Alvin.Mitchel
	cipher-text: Y7ykMsSHckLXDwKyPT1DPw==
	 plain-text: El Psy Kongroo

//TODO
 * STEP 11 * Message received from classmate. 
			key: Mitchel.Alvin
	cipher-text: 3IDSCmbf/lzkLRJ9A0Wb3VBI9Q9zyAS5rq9rg7S+P6E=
	 plain-text: Join the fat-lyfe! -Mitchel

 * STEP 12 * Message for marker. 
			key: FMA
	cipher-text: 7dZ2lnBzqb2WBIudoHqJVxJ3aMgemGCa6RdW48YeBstoD+HIEkjE2umIjECuHiC5SJZr7xwWAP0Yvyu5/pWXMWemBUuCukktPP5LqzDPsGbCdm/T/Lg6d2P0CQXQpIIX
0Yvyu5/pWXMWemBUuCukktPP5LqzDPsGbCdm/T/Lg6d2P0CQXQpIIX
 */
