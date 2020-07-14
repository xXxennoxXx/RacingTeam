package entities;

import generators.CurrentTimeGenerator;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Part implements Machinery, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;

    @Column(unique = true)
    private Long serialNumber;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = true)
    private PartState state;

    @GeneratorType(type = CurrentTimeGenerator.class, when = GenerationTime.INSERT)
    @Column
    private LocalDate insertDate;

    @GeneratorType(type = CurrentTimeGenerator.class, when = GenerationTime.ALWAYS)
    @Column
    private LocalDate updateDate;


    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Assembly_Part",
            inverseJoinColumns = {@JoinColumn(name = "assembly_id")},
            joinColumns = {@JoinColumn(name = "part_id")}
    )
    private Set<Assembly> assemblies;

    public Part() {
        assemblies = new HashSet<>();
    }

    public boolean addAssembly(Assembly assembly) {
        if (isAssembliesContain(assembly))
            return true;
        assemblies.add(assembly);
        assembly.addPart(this);
        return true;
    }

    private boolean isAssembliesContain(Assembly assembly) {
        return assemblies.contains(assembly);
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDate getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDate insertDate) {
        this.insertDate = insertDate;
    }

    public PartState getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(PartState state) {
        this.state = state;
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

    public Set<Assembly> getAssemblies() {
        return assemblies;
    }

    public void setAssemblies(Set<Assembly> assemblies) {
        this.assemblies = assemblies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(id, part.id) &&
                Objects.equals(serialNumber, part.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serialNumber);
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serialNumber=" + serialNumber +
                ", state=" + state +
                ", insertDate=" + insertDate +
                ", assemblies=" + assemblies.size() +
                '}';
    }
}
