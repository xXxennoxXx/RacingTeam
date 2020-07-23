package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "department", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<Person> persons;

    protected Department() {
        persons = new HashSet<>();
    }

    public Department(String name) {
        this();
        this.name = name;
    }

    public void addPersons(Person... persons) {
        for (Person p : persons)
            addPerson(p);
    }

    public boolean addPerson(Person person) {
        if (containPerson(person))
            return true;
        persons.add(person);
        person.assignToDepartment(this);
        return true;
    }

    public boolean containPerson(Person person) {
        return persons.contains(person);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
