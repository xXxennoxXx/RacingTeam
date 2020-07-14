package entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Version
    private int version;
    @Lob
    private String description;
    @OneToMany(mappedBy = "task",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<PersonTask> persons;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdTimestamp;
    @UpdateTimestamp
    private LocalDateTime updateTimestamp;


    public boolean addPerson(PersonTask person) {
        if (isTaskContainsPerson(person))
            return true;
        return persons.add(person);
    }

    public Task() {
        persons = new HashSet<>();
    }

    //todo jak zrobic contains na jednym zpol atrybutu powtarzalnego
    public PersonTask addPerson(Person person) {
        Optional<PersonTask> first = persons.stream().filter(e -> e.getPerson().equals(person)).findFirst();

        return first.orElseGet(() -> new PersonTask(person, this));

    }

    private boolean isTaskContainsPerson(PersonTask person) {
        return persons.contains(person);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Person> getPersons() {
        return persons
                .stream()
                .map(e -> e.getPerson())
                .collect(Collectors
                        .toSet());
    }

    public void setPersons(Set<PersonTask> tasks) {
        this.persons = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
