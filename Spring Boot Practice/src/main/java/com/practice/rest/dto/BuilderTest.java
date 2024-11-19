package com.practice.rest.dto;

class Person{
    private String name;
    private int age;

    public static PersonBuilder builder(){
        return new PersonBuilder();
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    static class PersonBuilder{
        private String name;
        private int age;

        public PersonBuilder name(String name){
            this.name = name;
            return this;
        }

        public PersonBuilder age(int age){
            this.age = age;
            return this;
        }

        public Person build(){
            return new Person(name, age);
        }
    }


}



public class BuilderTest {
    public static void main(String[] args) {
        Person person = Person.builder().build();
    }
}
