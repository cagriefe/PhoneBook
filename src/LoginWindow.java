import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow extends JFrame implements ActionListener {

	JLabel lblUser = new JLabel("Username:");
	JLabel lblPass = new JLabel("Password:");
	JTextField txtUser = new JTextField();
	JPasswordField txtPass = new JPasswordField();
	JButton btnCancel = new JButton("CANCEL");
	JButton btnLogin = new JButton("SUBMIT");

	public LoginWindow() {
		setTitle("Login Window");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 2));
		add(lblUser);
		add(txtUser);
		add(lblPass);
		add(txtPass);
		add(btnCancel);
		add(btnLogin);

		btnCancel.addActionListener(this);
		btnLogin.addActionListener(this);
	}

	public static void main(String[] args) {
		new LoginWindow().setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			System.exit(0);
		}

		else if (e.getSource() == btnLogin) {
			String username = "admin";
			String pass = "1234";
			if (txtUser.getText().equals(username) && txtPass.getText().equals(pass)) {
				// messageBox:joptionpane
				JOptionPane.showMessageDialog(null, "Correct!");
				// System.out.println("correct credentials!");

				new ContactBook().setVisible(true);
				setVisible(false);
			} else

				JOptionPane.showMessageDialog(null, "InCorrect!");
			// System.out.println("incorrect credentials!");}

		}

	}
}
