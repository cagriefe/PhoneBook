import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditContactWindow extends JFrame implements ActionListener {
    JTextField txtName = new JTextField();
    JTextField txtPhone = new JTextField();
    JButton btnSave = new JButton("Save");
    JButton btnCancel = new JButton("Cancel");

    Contact contact;

    public EditContactWindow(Contact contact) {
        super("Edit Contact");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Close only this window
        setLocationRelativeTo(null);
        setSize(300, 200);

        this.contact = contact;

        JPanel pnlFields = new JPanel(new GridLayout(2, 2));
        pnlFields.add(new JLabel("Name:"));
        pnlFields.add(txtName);
        pnlFields.add(new JLabel("Phone:"));
        pnlFields.add(txtPhone);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlButtons.add(btnSave);
        pnlButtons.add(btnCancel);

        add(pnlFields, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        // Populate fields with contact details
        txtName.setText(contact.name);
        txtPhone.setText(contact.phone);

        btnSave.addActionListener(this);
        btnCancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            // Update contact details with the edited values
            contact.name = txtName.getText();
            contact.phone = txtPhone.getText();

            // Close the window
            dispose();
        } else if (e.getSource() == btnCancel) {
            // Close the window without saving changes
            dispose();
        }
    }
}
