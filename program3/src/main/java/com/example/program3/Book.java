package com.example.program3;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.*;

public class Book {
	@NotNull(message="Cant be empty")
	@Min(1)
	@Max(1000)
	Long id,year;
	@NotEmpty(message="cant be empty")
	@Length(min=3,max=20)
	String title,author;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", year=" + year + ", title=" + title + ", author=" + author + "]";
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
