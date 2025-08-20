project: "Brunel Hospital Management System"

overview: |
  The Brunel Hospital Management System (BHMS) is a Java-based desktop application 
  designed to streamline healthcare review management. It provides an intuitive and 
  user-friendly interface for patients to:
    - Search for doctors
    - Submit reviews and ratings
    - View aggregated ratings and feedback

  The system ensures data consistency, error handling, and efficient navigation across 
  multiple windows, making it accessible even to users with minimal technical expertise.

features:
  - Doctor Search: "Patients can search for doctors by name or specialization."
  - Add Reviews: "Patients can rate doctors (1–5) and leave comments."
  - View Ratings: "Displays average ratings, feedback history, and highlights top-rated doctors."
  - Dynamic Updates: "Reviews update in real time without inconsistent UI behavior."
  - Error Handling: "Validates input fields and prevents incorrect submissions."

screenshots:
  - title: "Home Screen"
    path: "images/home.png"
  - title: "Doctor Search"
    path: "images/search.png"
  - title: "Add Review"
    path: "images/add_review.png"

technology_stack:
  language: "Java"
  gui: "Swing"
  data_storage: "CSV (doctor records and reviews)"
  testing: "JUnit and manual interface testing"

project_structure: |
  BrunelHospitalManagementSystem/
  │
  ├── src/
  │   ├── GUI.java           # Main entry point, handles application UI
  │   ├── Search.java        # Search doctors by name or specialization
  │   ├── AddReview.java     # Add reviews and ratings for doctors
  │   ├── Doctor.java        # Doctor entity with attributes and methods
  │   ├── Review.java        # Review entity for storing rating and comment
  │   └── doctors.csv        # Data file containing doctor information and reviews
  │
  ├── images/                # Screenshots for README (home.png, search.png, add_review.png)
  │
  ├── README.md              # Project documentation
  └── LICENSE                # License file (if applicable)

getting_started:
  prerequisites:
    - "Java JDK 8 or later"
    - "Any IDE supporting Java (e.g., IntelliJ IDEA, Eclipse, NetBeans)"
  installation:
    - step: "Clone the repository"
      command: "git clone https://github.com/yourusername/BrunelHospitalManagementSystem.git"
    - step: "Open the project in your IDE"
    - step: "Compile and run the GUI.java file"

usage:
  home_tab: "View list of doctors with ratings and reviews."
  search_doctor_tab: "Look up doctors by name or specialization."
  add_review_tab: "Submit new ratings and comments."

testing:
  - "JUnit Tests: For validating backend logic."
  - "Manual Testing: Ensured smooth GUI navigation, proper sorting, and correct rating updates."

future_enhancements:
  - "Enhanced input validation (stricter checks for ratings and comments)."
  - "Advanced filtering and sorting (by date, keyword, or rating)."
  - "Role-based access control for patients and administrators."
  - "Improved UI/UX with responsive layouts and modern design."

author:
  name: "Nafis Alam Tousim"
  department: "Department of Computer Science"
  university: "Brunel University London"
