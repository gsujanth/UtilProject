package com.sujanth.utils.sortutils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortUtil {
	
	public static void main(String[] args) {
		Person p1=new Person("ram",26);
		Person p2=new Person("bhim",24);
		Person p3=new Person("sam",25);
		Person p4=new Person("pam",26);
		
	
		ArrayList<Person> persons=new ArrayList<>();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		persons.add(p4);
		
		for (Person person : persons) {
			System.out.println(person.toString());
		}
		
		System.out.println("**************************");
		
		Collections.sort(persons, new Comparator<Person>(){

			@Override
			public int compare(Person arg0, Person arg1) {
				// TODO Auto-generated method stub
				//System.out.println(arg0.getAge()-arg1.getAge());
				int x=arg0.getAge()-arg1.getAge();
				if(x!=0) {
					return x;
				}
				else {
					return arg0.getName().compareTo(arg1.getName());
				}
				//return arg0.getAge()-arg1.getAge();
			}
			
		});
		
		for (Person person : persons) {
			System.out.println(person.toString());
		}
	}

}

class Person{
	String name;
	int age;
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	
	
}
