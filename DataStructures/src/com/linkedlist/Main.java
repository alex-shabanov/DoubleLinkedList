package com.linkedlist;

public class Main {

	public static void main(String[] args) {
		
		LinkedList<String> list = new LinkedList<String>();
	      
		list.addLast("1 2 3");
		list.add("a b c", 0);
		list.add("Inserting element one.", 2);
		list.add("Inserting element two.", 3);
		list.printList();
		list.remove(0);
		list.printList();
		
		list.removeLast();
		list.remove(list.size() - 1);
		list.printList();
		list.addFirst("Hi there.");
		list.printList();
		list.addLast("Hello World!");
		list.printList();
		
		System.out.println("Is list empty? " + String.valueOf(list.isEmpty()));
		
		list.remove(list.size() - 1);
		list.remove(list.size() - 1);
		list.remove(list.size() - 1);
		list.remove(list.size() - 1);
		
		list.printList();
		System.out.println("Is list empty? " + String.valueOf(list.isEmpty()));
		
		list.addFirst("1 2 3");
		list.add("a b c", list.size());
		list.add("Inserting element one.", 2);
		list.add("Inserting element two.", list.size());
		list.addLast("Inserting element three.");
		
		LinkedList<String> anotherList = list.deepCopy();
		anotherList.printList();
		
		System.out.println("List contains? " + String.valueOf(list.contains("Hello World!")));
		System.out.println("List contains? " + String.valueOf(list.contains("1 2 3")));
		
		list.reverse();
		list.printList();
		list.printListReverse();
	}

}
