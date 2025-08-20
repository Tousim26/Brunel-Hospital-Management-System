import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrollPane;
    private JLabel hospital_name;
    private ImageIcon img;
    private JPanel home, search;
    private Map<String, Doctor> doctors = new HashMap<>();

    /*
    @author Nafis 
     */
    
    public static void main(String[] args) throws FileNotFoundException {
        GUI frame = new GUI();
        frame.setVisible(true);
    }

    public GUI() {
        setTitle("Brunel Hospital Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 670);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 255, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        img = new ImageIcon(getClass().getResource("logo.png"));
        this.setIconImage(img.getImage());

        ImageIcon bannerIcon = new ImageIcon(getClass().getResource("Hospital.png"));
        Image image = bannerIcon.getImage();
        Image scaledImage = image.getScaledInstance(40, 35, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel bannerLabel = new JLabel(scaledIcon);
        bannerLabel.setBounds(0, 0, 70, 80);
        contentPane.add(bannerLabel);

        hospital_name = new JLabel("Brunel Hospital Management System");
        hospital_name.setBounds(65, 2, 500, 60);
        hospital_name.setFont(new Font("Bernard MT Condensed", Font.BOLD, 20));
        hospital_name.setForeground(Color.BLACK);
        contentPane.add(hospital_name);

        JLabel address = new JLabel("Kingston Ln, London, Uxbridge UB8 3PH, UK");
        address.setBounds(65, 22, 500, 60);
        address.setFont(new Font("Forte", Font.PLAIN, 13));
        address.setForeground(Color.BLACK);
        contentPane.add(address);

        home = new JPanel();
        home.setBounds(235, 100, 600, 340);
        home.setBackground(new Color(245, 255, 250));
        home.setLayout(null);
        contentPane.add(home);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(2, 2, 835, 488);
        home.add(scrollPane);

        table = new JTable();
        table.setBackground(new Color(245, 255, 250));
        scrollPane.setViewportView(table);

        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(20, 85, 840, 520);
        tp.addTab("Home", home);
        tp.addTab("Search Doctor", new Search(this));
        tp.addTab("Add Review", new AddReview(this));
        contentPane.add(tp);

        try {
            loadDoctors();
            updateDoctorTable();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(this::showDoctorReviews);
        // Used to maintain thread security. Also, since updating occurs, it is useful
        // when to update the GUI after the application has started.
    }

    private void loadDoctors() throws FileNotFoundException {
    	File file = new File("C:\\Implementation\\src\\doctors.csv");


        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
        }

        Scanner scanner = new Scanner(file);
        doctors.clear();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] details = line.split(",");

            if (details.length < 5) {
                System.err.println("Skipping invalid line: " + line);
                continue;
            }

            String name = details[0];
            String specialization = details[1];
            double rating;
            int numberOfReviews;
            String comment = details[4];

            try {
                rating = Double.parseDouble(details[2]);
                numberOfReviews = Integer.parseInt(details[3]);
            } catch (NumberFormatException e) {
                System.err.println("Skipping line due to parsing error: " + line);
                continue;
            }

            // Initialize Doctor object with totalRating and numberOfReviews from CSV
            Doctor doctor = new Doctor(name, specialization, rating * numberOfReviews, numberOfReviews);
            doctor.addReview(new Review((int) rating, comment));
            doctors.put(name, doctor);
        }

        scanner.close();
    }

    // Method to update the table with doctor data
    public void updateDoctorTable() {
        ArrayList<Doctor> doctorList = new ArrayList<>(doctors.values());
        doctorList.sort((d1, d2) -> Double.compare(d2.getAverageRating(), d1.getAverageRating()));

        String[] columnNames = { "Doctor Name", "Specialization", "Average Rating", "Number of Reviews",
                "Latest Review" };
        Object[][] data = new Object[doctorList.size()][5];
        // Attribute names for the table

        for (int i = 0; i < doctorList.size(); i++) {
            Doctor doctor = doctorList.get(i);
            data[i][0] = doctor.getName();
            data[i][1] = doctor.getSpecialization();
            data[i][2] = String.format("%.2f", doctor.getAverageRating());
            data[i][3] = doctor.getNumberOfReviews();
            Review latestReview = doctor.getLatestReview();
            data[i][4] = latestReview != null ? latestReview.toString() : "No reviews yet";
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }

    public void showDoctorReviews() {
        updateDoctorTable();
        scrollPane.revalidate();
        scrollPane.repaint();
    }
    // Method to display doctor reviews. Also, scrollpane has been refreshed and
    // repainted by inbuilt methods.

    public Doctor getDoctor(String name) {
        return doctors.get(name);
    } 
    // to get name of specific doctor

    public Map<String, Doctor> getDoctors() {
        return doctors;
    } 
    // Method to get all doctors
}
