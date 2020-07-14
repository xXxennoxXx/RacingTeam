package entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PersonTask {

    @EmbeddedId
    private PersonTaskId personTaskId;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    private Person person;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("taskId")
    private Task task;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdTimestamp;
    @UpdateTimestamp
    private LocalDateTime updateTimestamp;

    public PersonTask() {
    }

    public PersonTask(Person person, Task task) {
        this.person = person;
        this.task = task;
        person.addTask(this);
        task.addPerson(this);
        personTaskId = new PersonTaskId(person.getId(), task.getId());
    }

    public PersonTaskId getPersonTaskId() {
        return personTaskId;
    }

    public void setPersonTaskId(PersonTaskId personTaskId) {
        this.personTaskId = personTaskId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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
}
