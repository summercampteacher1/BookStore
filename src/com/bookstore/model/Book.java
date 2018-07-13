package com.bookstore.model;

public class Book {
	
	public Book(int id,String title,String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}
	
	public Book(String title,String author) {
		this(-1,title,author);
	}

	private int id;
	private String title;
	private String author;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("\n**********************\n");
		sb.append("Book id:").append(id).append("\n");
		sb.append("Title:").append(title).append("\n");
		sb.append("Author:").append(author).append("\n");
		sb.append("\n**********************\n");
		return sb.toString();
	}
	
}
