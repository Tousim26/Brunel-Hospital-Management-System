public class Review {
    private int rating;
    private String comment;

    public Review(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    } //constractor

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    } //getters 

    @Override
    public String toString() {
        return "Rating: " + rating + "," + comment;
    }

    // Override the toString method to provide a custom string representation of the review
}
