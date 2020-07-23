package entities;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repo.Repo;

import java.util.List;

import static utils.StringUtils.show;

public class DepartmentTest {


    @Before
    public void init() {
        Repo.makeRequest(s -> {

            Department suspensionDepartment = new Department("Suspension"), aerodynamicDepartment = new Department("Aerodynamic");

            Person person1 = new Person("Mariusz", "Pudzianowski"),
                    person2 = new Person("Paolo", "Navaci");

            suspensionDepartment.addPersons(person1, person2);
            show(
                    suspensionDepartment.getName(),
                    suspensionDepartment.getPersons().size(),
                    aerodynamicDepartment.getName(),
                    aerodynamicDepartment.getPersons().size());


//            s.persist(person1);
//            s.persist(person2);
            s.persist(suspensionDepartment);
            s.persist(aerodynamicDepartment);


        });
    }

    @Test
    public void testAddDepartment() {
        Repo.makeRequest(s -> {

            List<Department> departments = s.createQuery("from Department ", Department.class).getResultList();

            Assert.assertEquals(departments.get(0).getPersons().size(), 2);
            Assert.assertEquals(departments.size(), 2);
        });
    }

    @Test
    public void testFindDepartment() {
        Repo.makeRequest(s -> {

            Department department = s.createQuery("from Department d where d.name =:name ",
                    Department.class)
                    .setParameter("name", "Suspension")
                    .getSingleResult();
            Assert.assertNotNull(department);
        });
    }

    @Test
    public void testUpdateDepartment() {
        Repo.makeRequest(s -> {

            Department department = s.createQuery("from Department d where d.name =:name ",
                    Department.class)
                    .setParameter("name", "Suspension")
                    .getSingleResult();

            department.setName("Chassis");
        });
        Repo.makeRequest(s -> {
            Department department = s.createQuery("from Department d where d.name =:name ",
                    Department.class)
                    .setParameter("name", "Chassis")
                    .getSingleResult();
            Assert.assertEquals(department.getName(), "Chassis");
        });
    }

    @Test
    public void testDeletePerson() {
        Repo.makeRequest(s -> {
            Person person = s.createQuery("from Person p where p.id = 2 ", Person.class).getSingleResult();

            Department department = person.getDepartment();

            s.delete(person);
            s.flush();
//            s.refresh(department);
            Assert.assertFalse(department.getPersons().contains(person));
        });
    }


}