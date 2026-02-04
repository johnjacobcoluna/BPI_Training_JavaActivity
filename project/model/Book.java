package m5.project.model;

public class Book {
    private Integer bookid;
    private String booktitle;
    private String bookauthor;
    private boolean is_available;

    public Integer getBookId() {
    	return bookid; 
    	}
    public void setBookId(Integer bookid) {
    	this.bookid = bookid; 
    	}
    public String getBookTitle() {
    	return booktitle; 
    	}
    public void setBookTitle(String booktitle) {
    	this.booktitle = booktitle;
    	}
    public String getBookAuthor() {
    	return bookauthor; 
    	}
    public void setBookAuthor(String bookauthor) {
    	this.bookauthor = bookauthor; 
    	}
    public boolean getIsAvailable() {
    	return is_available; 
    	}
    public void setIsAvailable(boolean is_available) {
    	this.is_available = is_available; 
    	}
}
