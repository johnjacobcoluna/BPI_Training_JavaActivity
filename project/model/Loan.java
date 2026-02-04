package m5.project.model;

public class Loan {
    private Integer loanid;
    private String loan_booktitle;
    private String borrower_name;
    private Integer loan_bookid;
    
    public Integer getLoanId() {
    	return loanid; 
    	}
    public void setLoanId(Integer loanid) {
    	this.loanid = loanid; 
    	}
    public String getLoanBookTitle() {
    	return loan_booktitle; 
    	}
    public void setLoanBookTitle(String loan_booktitle ) {
    	this.loan_booktitle = loan_booktitle; 
    	}
    public String getBorrowerName() {
    	return borrower_name; 
    	}
    public void setBorrowerName(String borrower_name) {
    	this.borrower_name = borrower_name; 
    	}
    public Integer getLoanBookId() {
    	return loan_bookid; 
    	}
    public void setLoanBookId(Integer loan_bookid) {
    	this.loan_bookid = loan_bookid; 
    	}
}
