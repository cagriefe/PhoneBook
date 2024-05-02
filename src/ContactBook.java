import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

public class ContactBook extends JFrame implements ActionListener {
	JButton btnAdd = new JButton("ADD");
	JButton btnEdit = new JButton("EDIT");
	JButton btnDelete = new JButton("DELETE");
	JButton btnClear = new JButton("CLEAR");
	JButton btnSearch = new JButton("SEARCH");
	JButton btnSort = new JButton("SORT");
	JButton btnLoad = new JButton("LOAD");
	JButton btnExport = new JButton("EXPORT");
	JButton btnExit = new JButton("EXIT");

	DefaultListModel<Contact> dlm = new DefaultListModel<Contact>();
	JList<Contact> list = new JList<>(dlm);
	JScrollPane sp = new JScrollPane(list);

	AddNewContact anc = new AddNewContact(dlm);

	public ContactBook() {
		super("All Contacts");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setSize(300, 500);

		JPanel pnlButtons = new JPanel(new GridLayout(3, 3));
		pnlButtons.add(btnAdd);
		pnlButtons.add(btnEdit);
		pnlButtons.add(btnDelete);
		pnlButtons.add(btnClear);
		pnlButtons.add(btnSearch);
		pnlButtons.add(btnSort);
		pnlButtons.add(btnLoad);
		pnlButtons.add(btnExport);
		pnlButtons.add(btnExit);

		add(pnlButtons, BorderLayout.SOUTH);
		add(sp, BorderLayout.CENTER);

		btnAdd.addActionListener(this);
		btnEdit.addActionListener(this);
		btnDelete.addActionListener(this);
		btnClear.addActionListener(this);
		btnSearch.addActionListener(this);
		btnSort.addActionListener(this);
		btnLoad.addActionListener(this);
		btnExport.addActionListener(this);
		btnExit.addActionListener(this);

		for (int i = 0; i < 50; i++) {
			dlm.addElement(new Contact());
		}
	}

	public static void main(String[] args) {
		new ContactBook().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDelete) {
			int index = list.getSelectedIndex();
			if (index != -1)
				dlm.remove(index);

			
		} else if (e.getSource() == btnClear) {
			dlm.clear();
		} else if (e.getSource() == btnAdd) {
			anc.btnSubmit.setText("ADD");

			anc.setVisible(true);
		} else if (e.getSource() == btnEdit) {
            Contact selected = list.getSelectedValue();
            if (selected != null) { // Check if a contact is selected
                EditContactWindow editWindow = new EditContactWindow(selected);
                editWindow.setVisible(true);
            }
		} else if (e.getSource() == btnExit) {
			System.exit(0);
		} else if (e.getSource() == btnLoad) {
			try {
				FileInputStream fis = new FileInputStream("AllContacts.txt");
				ObjectInputStream ois = new ObjectInputStream(fis);

				while (true) {
					try {
						Contact c = (Contact) ois.readObject(); // downcasting
						dlm.addElement(c);
					} catch (EOFException e1) {
						break;
					}
				}

				ois.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			}


		} else if (e.getSource() == btnExport) {
			try {
				FileOutputStream fos = new FileOutputStream("AllContacts.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				for (int i = 0; i < dlm.size(); i++) {
					oos.writeObject(dlm.get(i)); // upcasting
				}

				oos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}
}
