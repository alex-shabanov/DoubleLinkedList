package com.linkedlist;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListUnitTest {
	
	LinkedList<Book> list;
	Book book1, book2, book3, book4, book5, book6, book7;
	Book book8, book9, book10, book11, book12, book13;
	
	public class Book {
		
		private String author;
		private String title;
		private String year;
		private String isbn;
		
		public Book(String title, String author, String year, String isbn){
			this.author = author;
			this.title = title;
			this.year = year;
			this.isbn = isbn;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getIsbn() {
			return isbn;
		}
		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}
		public String toString(){
			String str = "Title: " + getTitle()+ ",  " + "Author: " + getAuthor() + ",  " + "Year: " + getYear();
			System.out.println(str);
			return str;
		}
	}

	@Before
	public void setUp(){
		list = new LinkedList<Book>();
		book1 = new Book("Empire of Storms", "Sarah J. Mass", "2016", "1619636077");
		book2 = new Book("Lady Midnight", "Cassandra Clare", "2016", "1442468351");
		book3 = new Book("The Raven King", "Maggie Stiefvater", "2016", "0545424984");
		book4 = new Book("Glass Sword", "Victoria Aveyard", "2016", "0062310666");
		book5 = new Book("Heartless", "Marissa Meyer ", "2016", "1250044650");
		book6 = new Book("The Crown", "Kiera Cass", "2016", "0062392174");
		book7 = new Book("The Book Thief", "Markus Zusak", "2006", "0375831002");
		book8 = new Book("Life of Pi", "Yann Martel", "2006", "0770430074");
		book9 = new Book(null, null, null, null);
		list.addFirst(book3);
		list.addFirst(book2);
		list.addFirst(book1);
	}
	
	@After
	public void tearDown(){
		list = null;
		book1 = book2 = book3 = book4 = book5 = book6 = book7 = book8 = book9 = null;
	}
	
	@Test
	public void addFirstTest() {
		Book firstBook = list.getFirst();
		Book lastBook = list.getLast();
		Book middleBook = list.get(1);
		assertEquals(firstBook, book1);
		assertEquals(middleBook, book2);
		assertEquals(lastBook, book3);
	}
	
	@Test
	public void removeFirstTest() {
		list.removeFirst();
		Book firstBook = list.getFirst();
	    Book lastBook = list.getLast();
		assertEquals(firstBook, book2);
		assertEquals(lastBook, book3);
		list.removeFirst();
		assertEquals(lastBook, book3);
		list.removeFirst();
		assertEquals(list.getFirst(), null);
		assertEquals(list.getLast(), null);
	}
	
	@Test
	public void addLastTest() {
		list.remove(list.size() - 2);
		assertEquals(list.getFirst(), book1);
		assertEquals(list.getLast(), book3);
		list.removeLast();
		assertEquals(list.getFirst(), book1);
		assertEquals(list.getLast(), book1);
		list.add(book4, list.size());
		assertEquals(list.getFirst(), book1);
		assertEquals(list.getLast(), book4);
		list.removeFirst();
		list.removeLast();
		assertEquals(list.getFirst(), null);
		assertEquals(list.getLast(), null);
		list.add(book5, list.size());
		assertEquals(list.getFirst(), book5);
		assertEquals(list.getLast(), book5);
		list.addLast(book6);
		list.addFirst(book7);
		assertEquals(list.getFirst(), book7);
		assertEquals(list.getLast(), book6);
		assertEquals(list.get(list.size() - 2), book5);
	}
	
	@Test
	public void removeLastTest() {
		list.addLast(book4);
		list.addLast(book5);
	    assertEquals(list.getFirst(), book1);
	    assertEquals(list.getLast(), book5);
	    list.removeLast();
	    list.removeLast();
	    assertEquals(list.getLast(), book3);
	    list.removeLast();
	    list.removeLast();
	    list.removeLast();
	    assertEquals(list.getFirst(), null);
		assertEquals(list.getLast(), null);
	}
	
	@Test
	public void addTest() {
		list.remove(list.size() - 1);
		list.remove(list.size() - 1);
		list.remove(list.size() - 1);
		assertEquals(list.getFirst(), null);
		assertEquals(list.getLast(), null);
		list.add(book5, list.size());
		list.add(book6, list.size());
		list.add(book7, list.size());
		list.add(book8, list.size());
		assertEquals(list.getFirst(), book5);
		assertEquals(list.get(list.size() - 2), book7);
		assertEquals(list.getLast(), book8);
	}
	
	@Test
	public void removeTest() {
		list.add(book4, 1);
		list.add(book5, list.size() - 1);
		list.removeFirst();
		assertEquals(list.getFirst(), book4);
		assertEquals(list.getLast(), book3);
		list.removeLast();
		assertEquals(list.getLast(), book5);
		list.removeLast();
		assertEquals(list.getLast(), book2);
		list.remove(0);
		assertEquals(list.getFirst(), book2);
		assertEquals(list.getLast(), book2);
		list.remove(list.size() - 1);
		assertEquals(list.getFirst(), null);
		assertEquals(list.getLast(), null);
	}
	
	@Test
	public void sizeTest() {
		assertEquals(list.size(), 3);
		list.add(book6, 3);
		assertEquals(list.size(), 4);
		list.remove(list.size() - 1);
		assertEquals(list.size(), 3);
		list.remove(list.size() - 1);
		assertEquals(list.size(), 2);
		list.remove(list.size() - 1);
		assertEquals(list.size(), 1);
		list.remove(list.size() - 1);
		assertEquals(list.size(), 0);
	}
	
	@Test
	public void containsTest() {
		assertEquals(list.contains(book1), true);
		assertEquals(list.contains(book2), true);
		assertEquals(list.contains(book3), true);
		assertEquals(list.contains(book4), false);
		assertEquals(list.contains(book5), false);
		list.removeFirst();
		assertEquals(list.contains(book1), false);
		list.add(null, list.size() - 2);
		assertEquals(list.contains(null), true);
		assertEquals(list.size(), 3);
		list.remove(1);
		assertEquals(list.getFirst(), null);
		assertEquals(list.getLast(), book3);
		list.remove(list.size() - 2);
		assertEquals(list.getFirst(), book3);
		assertEquals(list.getLast(), book3);
		list.removeLast();
		assertEquals(list.getFirst(), null);
		assertEquals(list.getLast(), null);
	}
}
