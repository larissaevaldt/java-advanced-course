package org.example.collections.searching;


public class Cat implements Comparable<Cat> {

    private String name;
    private Integer age;

    public Cat(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Cat{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cat) {
            Cat otherCat = (Cat) obj;
            if(name.equals(otherCat.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Cat otherCat) { // specifies the "natural ordering" for Dog
        // delegate to String which implements Comparable<String>
        return name.compareTo(otherCat.getName()); // sorts alphabetically by name
    }
}
