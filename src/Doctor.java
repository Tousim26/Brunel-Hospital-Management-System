import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String name;
    private String specialization;
    private double totalRating;
    private int numberOfReviews;
    private List<Review> reviews;

    public Doctor(String name, String specialization, double totalRating, int numberOfReviews) {
        this.name = name;
        this.specialization = specialization;
        this.totalRating = totalRating;
        this.numberOfReviews = numberOfReviews;
        this.reviews = new ArrayList<>();
    } // Constructor to initialize a doctor with basic information and empty reviews
      // list

    public void addReview(Review review) {
        reviews.add(review); // Add the new review to the list
        totalRating += review.getRating(); // Add the rating from the new review to the total rating
        numberOfReviews++; // Increment the number of reviews
    } // Method to add a review for the doctor

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public double getAverageRating() {
        return numberOfReviews > 0 ? totalRating / numberOfReviews : 0;
    }

    public int getNumberOfReviews() {
        return numberOfReviews -1;
    }

    public Review getLatestReview() {
        // Return the last review if there are any, otherwise return null
        return reviews.size() > 0 ? reviews.get(reviews.size() - 1) : null;
    }
    // Method to get the latest review for the doctor
}
