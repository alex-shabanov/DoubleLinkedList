package com.linkedlist;

import java.util.NoSuchElementException;

import com.logfile.LogFile;

public class LinkedList<T> {
	
	private int size;
	private Node<T> head;
	private Node<T> tail;
	
	public static class Node<T> {
		
		private Node<T> next;
		private Node<T> prev;
		private T data = null;
		
		Node(T data){
			this.data = data;
			next = prev = null;
		}
		
		public T getData(){return data;}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(this.data == obj) return true;
			if(obj == null) return false;
			if(data.getClass() != obj.getClass()) return false;
			if(!this.data.equals(obj)) return false;
			return true;
		}
	}
	
	public LinkedList(){
		size = 0;
		head = tail = null;
	}
	
	public LinkedList<T> deepCopy(){
		if(this.head == null) return null;
		Node<T> currNode = this.head;
		LinkedList<T> newList = new LinkedList<T>();
		while(currNode != null){
			T data = currNode.data;
			newList.addLast(data);
			currNode = currNode.next;
		}
		newList.head = this.head;
		newList.tail = this.tail;
		newList.size = this.size;
		return newList;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public boolean contains(Object obj){
		if(obj == null){
			Node<T> currNode = this.head;
			while(currNode != null){
				T data = currNode.getData();
				if(data == null) return true;
				currNode = currNode.next;
			}
		}
		else {
			Node<T> currNode = this.head;
			while(currNode != null){
				if(currNode.equals(obj)) {
					return true;
				}
				currNode = currNode.next;
			}
		}
		return false;
	}
	public void addFirst(T data){
		try {
			if(head == null){
				head = new Node<T>(data);
				tail = head;
			}
			else {
				Node<T> newNode = new Node<T>(data);
				newNode.next = head;
				head.prev = newNode;
				head = newNode;
			}
			size++;
		}
		catch(Exception e) {
			LogFile.severe(this, "addFirst(): ", Constants.ADD_TO_FRONT_EXCEPTION, e);
		}
	}
	
	public void addLast(T data){
		try {
			if(head == null){
				head = new Node<T>(data);
				tail = head;
			}
			else {
				Node<T> newNode = new Node<T>(data);
				newNode.prev = tail;
				tail.next = newNode;
				tail = newNode;
			}
			size++;
		}
		catch(Exception e) {
			LogFile.severe(this, "addLast(): ", Constants.ADD_TO_BACK_EXCEPTION, e);
		}
	}
	
	public boolean add(T data, int index){
		try {
			if(index < 0 || index > size) throw new IndexOutOfBoundsException();
		}
		catch(IndexOutOfBoundsException e){
			LogFile.severe(this, "add(" + data + ", int " + index + ")", Constants.INDEX_OUT_OF_BOUNDS_EXCEPTION, e);
			return false;
		}
		if(index == 0){
			addFirst(data);
			return true;
		}
		if(index == size){
			addLast(data);
			return true;
		}
		int count = 0;
		Node<T> tempNode = head;
		while(tempNode != null){
			if(count == index - 1){
				try {
					Node<T> newNode = new Node<T>(data);
					newNode.prev = tempNode;
					newNode.next = tempNode.next;
					tempNode.next.prev = newNode;
					tempNode.next = newNode;
					size++;
				}
				catch(Exception e) {
					LogFile.severe(this, "addAtIndex(): ", Constants.ADD_TO_MIDDLE_EXCEPTION, e);
				}
				return true;
			}
			tempNode = tempNode.next;
			count++;
		}
		return false;
	}
	
	public T removeFirst(){
		Node<T> firstNode = head;
		if(firstNode == null) return null;
		T data = firstNode.getData();
		try {
			if(size == 1) head = tail = null;
			else {
				head = head.next;
				head.prev = null;
				firstNode.next = null;
			}
			size--;
		}
		catch(Exception e) {
			LogFile.severe(this, "removeFirst(): ", Constants.REMOVE_FIRST_ELEMENT, e);
		}
		return data;
	}
	
	public T removeLast(){
		Node<T> lastNode = tail;
		if(lastNode == null) return null;
		T data = lastNode.getData();
		try {
			if(size == 1) head = tail = null;
			else {
				tail = tail.prev;
				tail.next = null;
				lastNode.prev = null;
			}
			size--;
		}
		catch(Exception e) {
			LogFile.severe(this, "removeFirst(): ", Constants.REMOVE_LAST_ELEMENT, e);
		}
		return data;
	}
	
	public T remove(int index){
		T data = null;
		Node<T> tempNode = head;
		if(tempNode == null) return null;
		try {
			if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		}
		catch(IndexOutOfBoundsException e){
			LogFile.severe(this, "remove(int " + index + ")", Constants.INDEX_OUT_OF_BOUNDS_EXCEPTION, e);
			return data;
		}
		if(index == 0){
			data = removeFirst();
			return data;
		}
		if(index == size - 1){
			data = removeLast();
			return data;
		}
		int count = 0;
		while(tempNode != null){
			if(count == index - 1){
				Node<T> oldNode = tempNode.next;
				tempNode.next = oldNode.next;
				oldNode.next.prev = tempNode;
				oldNode.next = null;
				oldNode.prev = null;
				size--;
			}
			tempNode = tempNode.next;
			count++;
		}
		return data;
	}
	
	public T getFirst(){
		T data = null;
		try {
			if(size == 0) throw new NoSuchElementException();
			else {
				data = head.getData();
				return data;
			}
		}
		catch(NoSuchElementException e){
			LogFile.severe(this, "getFirst()", "NoSuchElementException", e);
		}
		return data;
	}
	
	public T getLast() {
		T data = null;
		try {
			if(size == 0) throw new NoSuchElementException();
			else {
				data = tail.getData();
				return data;
			}
		}
		catch(NoSuchElementException e){
			LogFile.severe(this, "getLast()", "NoSuchElementException", e);
		}
		return data;
	}
	
	public T get(int index){
		T data = null;
		Node<T> currNode = this.head;
		int count = 0;
		try {
			if(index < 0 || index > this.size) throw new IndexOutOfBoundsException();
		}
		catch(IndexOutOfBoundsException e){
			LogFile.severe(this, "get(int " + index + ")", "IndexOutOfBoundsException:  ", e);
			return data;
		}
		
		while(currNode != null){
			data = currNode.getData();
			if(count == index) return data;
			currNode = currNode.next;
			count++;
		}
		return data;
	}
	
	public void reverse() {
		if(size == 0) return;
		Node<T> tempNode = tail;
		tail = head;
		head= tempNode;
		Node<T> currNode = head;
		while(currNode != null){
			tempNode = currNode.next;
			currNode.next = currNode.prev;
			currNode.prev = tempNode;
			currNode = currNode.next;
		}
	}
	
	public void printList(){
		System.out.println("******************* Printing List **********************");
		Node<T> tempNode = head;
		while(tempNode != null){
			T data = tempNode.data;
			System.out.println(data);
			tempNode = tempNode.next;
		}
	}
	
	public void printListReverse(){
		System.out.println("******************* Printing List In Reverse Order **********************");
		Node<T> tempNode = tail;
		while(tempNode != null){
			T data = tempNode.data;
			System.out.println(data);
			tempNode = tempNode.prev;
		}
	}
}
