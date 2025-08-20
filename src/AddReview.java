import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AddReview extends JPanel {
    private JTable doctorTable;
    private JTextField ratingTextField;
    private JTextArea commentTextArea;
    private Font f; 
    private JButton searchButton, clearButton; 
    private GUI parentGUI;

    public AddReview(GUI parent) {
        this.parentGUI = parent;
        setLayout(null);
        setBackground(new Color(199, 219, 222));

        f = new Font("Franklin Gothic Demi", Font.BOLD, 14);

        JLabel searchLabel = new JLabel("Search Doctor:");
        searchLabel.setBounds(10, 10, 100, 25);
        searchLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
        add(searchLabel);

        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(115, 11, 495, 25);
        searchTextField.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
        add(searchTextField);

        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
        searchButton.setBounds(620, 11, 100, 25);
        add(searchButton);

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
        clearButton.setBounds(720, 11, 100, 25);
        add(clearButton);

        JLabel ratingLabel = new JLabel("Rating:");
        ratingLabel.setBounds(10, 260, 80, 25);
        ratingLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
        add(ratingLabel);

        ratingTextField = new JTextField();
        ratingTextField.setBounds(115, 260, 495, 25);
        ratingTextField.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
        add(ratingTextField);

        JLabel commentLabel = new JLabel("Comment:");
        commentLabel.setBounds(10, 295, 80, 25);
        commentLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
        add(commentLabel);

        commentTextArea = new JTextArea();
        commentTextArea.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));

        JScrollPane commentScrollPane = new JScrollPane(commentTextArea);
        commentScrollPane.setBounds(115, 295, 495, 50);
        add(commentScrollPane);

        JButton submitButton = new JButton("Submit Review");
        submitButton.setFont(f);
        submitButton.setBounds(620, 260,200, 25);
        add(submitButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 50, 812, 200);
        add(scrollPane);

        doctorTable = new JTable();
        doctorTable.setBackground(new Color(199, 219, 222));
        scrollPane.setViewportView(doctorTable);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchTextField.getText();
                updateDoctorTable(searchQuery);
            }
        });

        searchTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Not used
            }
        
            @Override
            public void keyPressed(KeyEvent  e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)  
         {
                    String searchQuery = searchTextField.getText();
                    updateDoctorTable(searchQuery);
                }
            }
        
            @Override
            public void keyReleased(KeyEvent e) {
                // Not used
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReview();
                updateDoctorTable("NULL");
                searchTextField.setText(""); 
                ratingTextField.setText("");
                commentTextArea.setText("");
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             searchTextField.setText("");
             ratingTextField.setText("");
             commentTextArea.setText("");
             updateDoctorTable("NULL");  
            }
        });
    }

    private void updateDoctorTable(String searchQuery) {
        ArrayList<Doctor> doctorList = new ArrayList<>(parentGUI.getDoctors().values());
        doctorList.removeIf(doctor -> !doctor.getName().toLowerCase().contains(searchQuery.toLowerCase()));
        
        String[] columnNames = { "Doctor Name", "Specialization", "Average Rating", "Number of Reviews", "Latest Review" };
        Object[][] data = new Object[doctorList.size()][5];

        for (int i = 0; i < doctorList.size(); i++) {
            Doctor doctor = doctorList.get(i);
            data[i][0] = doctor.getName();
            data[i][1] = doctor.getSpecialization();
            data[i][2] = String.format("%.2f", doctor.getAverageRating());
            data[i][3] = doctor.getNumberOfReviews();
            data[i][4] = doctor.getLatestReview().toString();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        doctorTable.setModel(model);
    }
    // Till this, the code is very much similar to Search.java
    

    private void addReview() {
        int selectedRow = doctorTable.getSelectedRow(); 

        // Here one thing is that, by searching, there is a possibility of having two doctors with the same name or nickname. In this case, specification determines their differences and the patient gets to select which of the doctor he/she wants to select and review. For, he must review by selecting the particular row. 

        if (selectedRow == -1) {
            System.err.println("No doctor selected");
            return;
        } 
    
        String selectedDoctor = (String) doctorTable.getValueAt(selectedRow, 0);
        String spec = (String) doctorTable.getValueAt(selectedRow, 1);
        String ratingText = ratingTextField.getText();
        String commentText = commentTextArea.getText();
        int rating;

        // System.out.println(selectedDoctor);


        try {
            rating = Integer.parseInt(ratingText);
            if (rating < 1 || rating > 5) {
                JOptionPane.showMessageDialog(null, "You must rate within 1-5 !","WARNING!!!", JOptionPane.WARNING_MESSAGE);
                // System.err.println("Invalid rating: " + rating);
                return;
            }
        } catch (NumberFormatException e) {
            // System.err.println("Invalid rating input: " + ratingText);
            JOptionPane.showMessageDialog(null, "You must rate within 1-5 !","WARNING!!!", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        // Retrieve the doctor object from the parent GUI
        Doctor doctor = parentGUI.getDoctor(selectedDoctor);
        if (doctor != null) {
            // Add the new review to the doctor
            doctor.addReview(new Review(rating, commentText));
            // Update the doctor table and refresh the view
            parentGUI.updateDoctorTable();
            parentGUI.showDoctorReviews();
            // Clear input fields
            ratingTextField.setText("");
            commentTextArea.setText("");
            // Show success message
            JOptionPane.showMessageDialog(null, "Review Added Successfully!","Success in Submission !", JOptionPane.INFORMATION_MESSAGE);
    
            // Append the review to the CSV file
            try (FileWriter writer = new FileWriter("C:\\\\Implementation\\\\src\\\\doctors.csv", true)) {
                writer.write(String.format("\n%s,%s,%.2f,%d,%s", doctor.getName(), doctor.getSpecialization(), doctor.getAverageRating(), doctor.getNumberOfReviews(), commentText));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
