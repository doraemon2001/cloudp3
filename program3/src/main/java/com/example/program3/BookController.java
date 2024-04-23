package com.example.program3;

import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController 
{
	List<Book> l=new ArrayList<Book>();
	
	@GetMapping
	public List<Book> getBooks()
	{
		return l;
	}
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable Long id,@RequestBody Book b)
	{
		return findBook(id);
		
		
	}
//	@PostMapping
//	public List<Book> postBooks(@RequestBody Book b)
//	{
//		l.add(b);
//		return l;
//	}
	
	@PostMapping
	public ResponseEntity<?> postBooks(@Valid @RequestBody Book b, BindingResult result)
	{
		List<String> errors=new ArrayList<String>();
		if(result.hasErrors())
		{
			List<FieldError> er=result.getFieldErrors();
			for(FieldError e:er)
			{
				errors.add(e.getField()+" "+e.getDefaultMessage());

			}
			return ResponseEntity.badRequest().body(errors);
		}
		
		l.add(b);
		return ResponseEntity.status(HttpStatus.CREATED).body(b);
	}
	@PutMapping("/{id}")
	public List<Book> putBooks(@PathVariable Long id,@RequestBody Book bo)
	{
		Book existingbook=findBook(id);
		if(existingbook!=null)
		{
			existingbook.setAuthor(bo.getAuthor());
			existingbook.setTitle(bo.getTitle());
			existingbook.setYear(bo.getYear());
			
		}
		return l;
	}
	@DeleteMapping("/{id}")
	public List<Book> deleteBooks(@PathVariable Long id)
	{
		for(int i=0;i<l.size();i++)
		{
			Book now=findBook(id);
			if(now!=null)
			{
				l.remove(now);
			}
		}
		return l;
		
	}
	
	public Book findBook(Long id)
	{
		for(int i=0;i<l.size();i++)
		{
			Book existingbook=l.get(i);
			if(existingbook.getId().equals(id))
			{
				return existingbook;
			}
		}
		return null;
	}
	
	
}
