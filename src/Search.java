import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
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

public class Search extends JPanel {
    private JTable doctorTable;
    private JTextField ratingTextField;
    private JTextArea commentTextArea;
    private Font f; 
    private JButton searchButton, clearButton; 
    private GUI parentGUI;

    public Search(GUI parent) {
        this.parentGUI = parent;
        setLayout(null);
        setBackground(new Color(255, 242, 242)); 

        // Parent GUI is used for it is a convenient way and code reuseablity is achieved

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

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 50, 812, 425);
        add(scrollPane);

        doctorTable = new JTable();
        doctorTable.setBackground(new Color(255, 242, 242));
        scrollPane.setViewportView(doctorTable);

        // Similar to Parentclass 

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchTextField.getText();
                updateDoctorTable(searchQuery);
            }
        }); // Search button invokes in two way. One case can be using search button, the other case can be, using key pressed. In maintaining bullet proof coding, it has been assured. So, both of the listeners work properly. 

        // Basically, from searchTextField.getText(), a string can be invoked from user. Now with this parameterised method updateDoctorTable(seachQuery), the table is updated. 

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


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             searchTextField.setText("");
             updateDoctorTable("NULL");  
            }
        }); // Clear button to maintain UI experience cleen and smooth
    }

    private void updateDoctorTable(String searchQuery) {
        ArrayList<Doctor> doctorList = new ArrayList<>(parentGUI.getDoctors().values());
        // Retrieves all doctors from the parent GUI's map and convert to a list
        doctorList.removeIf(doctor -> !doctor.getName().toLowerCase().contains(searchQuery.toLowerCase()));
         // Filters out doctors whose names do not match the search query (case-insensitive)
        
        String[] columnNames = { "Doctor Name", "Specialization", "Average Rating", "Number of Reviews", "Latest Review" };
        Object[][] data = new Object[doctorList.size()][5];

        for (int i = 0; i < doctorList.size(); i++) {
            Doctor doctor = doctorList.get(i);      // Gets the current doctor

            data[i][0] = doctor.getName();
            data[i][1] = doctor.getSpecialization();
            data[i][2] = String.format("%.2f", doctor.getAverageRating());
            data[i][3] = doctor.getNumberOfReviews();
            data[i][4] = doctor.getLatestReview().toString();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        doctorTable.setModel(model);
    }

}
