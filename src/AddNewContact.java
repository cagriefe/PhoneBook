import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddNewContact extends JFrame implements MouseListener, ActionListener, FocusListener {
	JTextField txtName = new JTextField();
	JTextField txtPhone = new JTextField();
	JTextField txtEmail = new JTextField();
	JRadioButton rbF = new JRadioButton("F");
	JRadioButton rbM = new JRadioButton("M");
	JRadioButton rbNotSpecified = new JRadioButton("Not specified", true);
	JCheckBox chkDancing = new JCheckBox("Dancing");
	JCheckBox chkSwimming = new JCheckBox("Swimming");
	JCheckBox chkReading = new JCheckBox("Reading");
	JButton btnClear = new JButton("Clear");
	JButton btnExit = new JButton("Exit");
	JButton btnSubmit = new JButton("Submit");

	String[] cities = { "Other", "Istanbul", "Giresun", "Nigde", "Sinop" };
	JComboBox cmbHomeTown = new JComboBox<>(cities);
	String[] occ = { "Other", "Health", "Education", "Community" };
	JComboBox cmbOcc = new JComboBox<>(occ);

	JLabel lblName = new JLabel();
	JLabel lblPhone = new JLabel();
	JLabel lblEmail = new JLabel();
	JLabel lblOccupation = new JLabel(new ImageIcon("icons/Other.jpg"));

	DefaultListModel<Contact> dlm;
	int selectedIndex;

	AddNewContact(DefaultListModel<Contact> dlm) {
		this.dlm = dlm;
		setSize(400, 600);
		setLayout(new GridLayout(8, 3));
		setTitle("Add New Contact");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rbF);
		bg.add(rbM);
		bg.add(rbNotSpecified);

		JPanel pnlGender = new JPanel();
		pnlGender.setLayout(new FlowLayout());
		pnlGender.add(rbF);
		pnlGender.add(rbM);
		pnlGender.add(rbNotSpecified);

		JPanel pnlHobbies = new JPanel(new GridLayout(3, 1));
		pnlHobbies.add(chkDancing);
		pnlHobbies.add(chkReading);
		pnlHobbies.add(chkSwimming);

		add(new JLabel("NAME: "));
		add(txtName);
		add(lblName);

		add(new JLabel("PHONE: "));
		add(txtPhone);
		add(lblPhone);

		add(new JLabel("EMAIL: "));
		add(txtEmail);
		add(lblEmail);

		add(new JLabel("GENDER:"));
		add(pnlGender);
		add(new JLabel());

		add(new JLabel("HOBBIES:"));
		add(pnlHobbies);
		add(new JLabel());

		add(new JLabel("HOMETOWN:"));
		add(cmbHomeTown);
		add(new JLabel());

		add(new JLabel("OCCUPATION:"));
		add(cmbOcc);
		add(lblOccupation);

		add(btnExit);
		add(btnClear);
		add(btnSubmit);

		pack();

		btnSubmit.addMouseListener(this);
		btnClear.addMouseListener(this);
		btnExit.addMouseListener(this);
		cmbOcc.addActionListener(this);
//		lblOccupation.setIcon(new ImageIcon("icons/Other.jpg"));

		txtName.addFocusListener(this);
		txtPhone.addFocusListener(this);
		txtEmail.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == txtName) {
			lblName.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {

//		if(e.getSource() == txtName)
//		{
//			if(txtName.getText().equals(""))
//			{
//				lblName.setForeground(Color.red);
//				lblName.setText("Name cannot be empty");
//			}
//			else
//			{
//				lblName.setText("");
//			}
//		}
//		if(e.getSource() == txtPhone)
//		{
//			if(txtPhone.getText().equals(""))
//			{
//				lblPhone.setForeground(Color.red);
//				lblPhone.setText("Name cannot be empty");
//			}
//			else
//			{
//				lblPhone.setText("");
//			}
//		}
		if (e.getSource() == txtName)
			Check(txtName, lblName, "[A-Z][a-z]+[\\s][A-Z][a-z]+");
		if (e.getSource() == txtPhone)
			Check(txtPhone, lblPhone, "");
		if (e.getSource() == txtEmail)
			Check(txtEmail, lblEmail, "");
	}

	void Check(JTextField txt, JLabel lbl, String regex) {
		if (txt.getText().matches(regex)) {
			lbl.setText("INCORRECT");
			lbl.setForeground(Color.red);
		} else {
			lbl.setText("CORRECT");
			lbl.setForeground(Color.green);
		}
	}

	public void Clear() {
		txtName.setText("");
		txtEmail.setText("");
		txtPhone.setText("");
		rbF.setSelected(false);
		rbM.setSelected(false);
		rbNotSpecified.setSelected(true);
		chkDancing.setSelected(false);
		chkReading.setSelected(false);
		chkSwimming.setSelected(false);
		cmbHomeTown.setSelectedIndex(0);
		cmbOcc.setSelectedIndex(0);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnClear) {
			Clear();
		} else if (e.getSource() == btnSubmit) {
			Contact c = new Contact(txtName.getText(), txtPhone.getText());
//			dlm.addElement(c);

			if (btnSubmit.getText().equals("ADD"))
				dlm.add(0, c);
			else {
				dlm.remove(selectedIndex);
				dlm.add(selectedIndex, c);
			}

			Clear();
			setVisible(false);
		} else if (e.getSource() == btnExit) {
//			JOptionPane.showMessageDialog(null, "are you sure");
//			JOptionPane.showInputDialog("xxxx");
//			JOptionPane.showConfirmDialog(btnExit, "Are you sure?");
			int answer = JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "Sure?",
					JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// String[] occ = {"Other", "Health", "Education", "Community"};
		if (e.getSource() == cmbOcc) {
//			if(cmbOcc.getSelectedIndex() == 0)
//				lblOccupation.setIcon(new ImageIcon("icons/Other.jpg"));
//			if(cmbOcc.getSelectedIndex() == 1)
//				lblOccupation.setIcon(new ImageIcon("icons/Health.jpg"));

//			if(cmbOcc.getSelectedItem() == "Other")
//				lblOccupation.setIcon(new ImageIcon("icons/Other.jpg"));
//			if(cmbOcc.getSelectedItem() == "Health")
//				lblOccupation.setIcon(new ImageIcon("icons/Health.jpg"));
//			if(cmbOcc.getSelectedItem() == "Education")
//				lblOccupation.setIcon(new ImageIcon("icons/Education.jpg"));
//			if(cmbOcc.getSelectedItem() == "Community")
//				lblOccupation.setIcon(new ImageIcon("icons/Community.jpg"));

			lblOccupation.setIcon(new ImageIcon("icons/" + cmbOcc.getSelectedItem() + ".jpg"));
		}
	}

}
