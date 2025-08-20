# Brunel Hospital Management System

## Overview
The Brunel Hospital Management System (BHMS) is a Java-based desktop application designed to streamline healthcare review management. It provides an intuitive and user-friendly interface for patients to:
- Search for doctors
- Submit reviews and ratings
- View aggregated ratings and feedback

The system ensures data consistency, error handling, and efficient navigation across multiple windows, making it accessible even to users with minimal technical expertise.

---

## Features
- **Doctor Search** – Patients can search for doctors by name or specialization.
- **Add Reviews** – Patients can rate doctors (1–5) and leave comments.
- **View Ratings** – Displays average ratings, feedback history, and highlights top-rated doctors.
- **Dynamic Updates** – Reviews update in real time without inconsistent UI behavior.
- **Error Handling** – Validates input fields and prevents incorrect submissions.

---

## Screenshots
*(Upload your screenshots in the `images/` folder and reference them here)*

### 1. Home Screen
![Home Screen](images/home.png)

### 2. Doctor Search
![Doctor Search](images/search.png)

### 3. Add Review
![Add Review](images/add_review.png)

---

## Technology Stack
- **Language:** Java  
- **GUI:** Swing  
- **Data Storage:** CSV (doctor records and reviews)  
- **Testing:** JUnit and manual interface testing  

---

## Project Structure
BrunelHospitalManagementSystem/
│
├── src/
│ ├── GUI.java # Main entry point, handles application UI
│ ├── Search.java # Search doctors by name or specialization
│ ├── AddReview.java # Add reviews and ratings for doctors
│ ├── Doctor.java # Doctor entity with attributes and methods
│ ├── Review.java # Review entity for storing rating and comment
│ └── doctors.csv # Data file containing doctor information and reviews
│
├── images/ # Screenshots for README (home.png, search.png, add_review.png)
│
├── README.md # Project documentation


## Getting Started

### Prerequisites
- Java JDK 8 or later
- Any IDE supporting Java (e.g., IntelliJ IDEA, Eclipse, NetBeans)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/BrunelHospitalManagementSystem.git
   
2. Open the project in your IDE.

3. Compile and run the GUI.java file.

## Usage

###Home Tab: View list of doctors with ratings and reviews.

###Search Doctor Tab: Look up doctors by name or specialization.

###Add Review Tab: Submit new ratings and comments.

##Testing

The system was tested using:

JUnit Tests: For validating backend logic.

Manual Testing: Ensured smooth GUI navigation, proper sorting, and correct rating updates.

##Future Enhancements

Enhanced input validation (stricter checks for ratings and comments).

Advanced filtering and sorting (by date, keyword, or rating).

Role-based access control for patients and administrators.

Improved UI/UX with responsive layouts and modern design.

##Author

Developed by Nafis Alam Tousim
Department of Computer Science
Brunel University London



