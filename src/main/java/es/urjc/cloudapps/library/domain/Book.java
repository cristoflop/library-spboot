package es.urjc.cloudapps.library.domain;

public class Book {

    private final String id;
    private String title;
    private String summary;
    private String author;
    private String editorial;
    private double rating;
    private int commentsCount;
    private int ratingsSum;

    private int version;

    public Book(String id, String title, String summary, String author, String editorial) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.editorial = editorial;
    }

    public void newComment(Comment comment) {
        this.version++;

        this.commentsCount++;
        this.ratingsSum += comment.getRating();
        this.rating = this.ratingsSum / commentsCount;
    }

    public void removeComment(Comment comment) {
        this.version++;

        this.commentsCount--;
        this.ratingsSum -= comment.getRating();
        this.rating = this.ratingsSum / commentsCount;
    }

    public int getVersion() {
        return version;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getResume() {
        return this.summary;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getEditorial() {
        return this.editorial;
    }

    public double getRating() {
        return this.rating;
    }
}
