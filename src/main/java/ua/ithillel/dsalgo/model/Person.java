package ua.ithillel.dsalgo.model;

import java.util.Objects;

public class Person implements Comparable<Person> {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        // reflective x.equals(x)
        // non-null x.equlas(null) -> false
        // transitive x.equals(y) y.equals(z) <-> x.equlas(z)
        // consistency x.equals(y) -. ... - x.equals(y)
        // symmetry x.equals(y) <-> y.equals(x)

        if (this == object) return true;
        if (object == null || !(object instanceof Person)) return false;
        Person person = (Person) object;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    @Override
    public int compareTo(Person o) {
        return age - o.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
