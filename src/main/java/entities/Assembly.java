package entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Assembly implements Machinery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long serialNumber;

    @CreationTimestamp
    private LocalDateTime createdTimestamp;

    @UpdateTimestamp
    private LocalDateTime updatedTimestamp;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Assembly_Part",
            joinColumns = {@JoinColumn(name = "assembly_id")},
            inverseJoinColumns = {@JoinColumn(name = "part_id")}
    )
    private Set<Part> parts;

    public boolean addParts(Part... parts) {
        for (Part p : parts)
            addPart(p);
        return true;
    }

    public boolean addPart(Part part) {
        if (isPartsContains(part))
            return true;
        parts.add(part);
        part.addAssembly(this);
        return true;
    }

    private boolean isPartsContains(Part part) {
        return parts.contains(part);
    }

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public LocalDateTime getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(LocalDateTime updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    public Assembly() {
        this.parts = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assembly assembly = (Assembly) o;
        return Objects.equals(id, assembly.id) &&
                Objects.equals(serialNumber, assembly.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serialNumber, parts);
    }

    @Override
    public String toString() {
        return "Assembly{" +
                "id=" + id +
                ", serialNumber=" + serialNumber +
                ", parts=" + parts.size() +
                '}';
    }
}
