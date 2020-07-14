package entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String firstName;
    @Column
    private String lastName;

    private Address address;

    @OneToMany(mappedBy = "person",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<PersonTask> tasks;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdTimestamp;
    @UpdateTimestamp
    private LocalDateTime updateTimestamp;

    public Person() {
        tasks = new HashSet<>();
    }

    public Person(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean addTask(PersonTask task) {
        if (isPersonContainTask(task))
            return true;
        return tasks.add(task);
    }

    private boolean isPersonContainTask(PersonTask task) {
        return tasks.contains(task);
    }

    public PersonTask addTask(Task task) {
        Optional<PersonTask> first = tasks.stream().filter(e -> e.getTask().equals(task)).findFirst();
        return first.orElseGet(() -> new PersonTask(this, task));
    }

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public LocalDateTime getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(LocalDateTime updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<PersonTask> getTasks() {
        return tasks;
    }

    public void setTasks(Set<PersonTask> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
