package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PersonTaskId implements Serializable {
    private Long personId;
    private Long taskId;

    protected PersonTaskId() {
    }

    public PersonTaskId(Long personId, Long taskId) {
        this.personId = personId;
        this.taskId = taskId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonTaskId that = (PersonTaskId) o;
        return Objects.equals(personId, that.personId) &&
                Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, taskId);
    }
}
