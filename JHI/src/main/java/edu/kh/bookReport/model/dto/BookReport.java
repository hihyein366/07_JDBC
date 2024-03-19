package edu.kh.bookReport.model.dto;

public class BookReport {
	
	private int bookNo;
	private String bookTitle;
	private String bookContent;
	private String bookAuthor;
	private String regDate;
	
public BookReport() {}

	public BookReport(int bookNo, String bookTitle, String bookContent, String bookAuthor, String regDate) {
		super();
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.bookContent = bookContent;
		this.bookAuthor = bookAuthor;
		this.regDate = regDate;
	}
	
	public BookReport(int bookNo, String bookTitle, String bookAuthor, String regDate) {
		super();
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.regDate = regDate;
	}
	

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookContent() {
		return bookContent;
	}

	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "BookReport [bookNo=" + bookNo + ", bookTitle=" + bookTitle + ", bookContent=" + bookContent + ", bookAuthor="
				+ bookAuthor + ", regDate=" + regDate + "]";
	}
	
	

}
