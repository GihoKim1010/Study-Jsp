package com.giho.PracticeEL;

public class Cat {
	public String name;
	public int age;

	public Cat(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	Cat myCat=new Cat("키티", 7);

	// 우클릭 - source - generate getter and setter 로 만듦.
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

}
