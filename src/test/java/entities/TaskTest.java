package entities;

import org.hibernate.Session;
import org.junit.Test;
import repo.Repo;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static utils.StringUtils.show;

public class TaskTest {
    @Test
    public void testGetTasks() {
        new Repo() {
            public void init() {
                try (Session session = getSession()) {
                    session.beginTransaction();

                    Person person = new Person("Ben", "Ken obi");
                    person.setAddress(new Address("Warsaw", "Lesna"));

                    session.persist(person);

                    Task task1 = new Task(),
                            task2 = new Task();

                    session.persist(task1);
                    session.persist(task2);

                    person.addTask(task1);
                    person.addTask(task2);

                    session.saveOrUpdate(person);

                    session.getTransaction().commit();
                }
            }
        }.init();
    }

    @Test
    public void testVersion() {
        new Repo() {
            public void init() {
                try (Session session = getSession()) {
                    session.beginTransaction();

                    Person person1 = new Person("Donald", "Trump"),
                            person2 = new Person("Bill", "Cllinton");

                    Task task = session.find(Task.class, 45l);

                    session.persist(person1);
                    session.persist(person2);

                    task.addPerson(person1);
                    task.addPerson(person2);
                    task.addPerson(person2);

                    session.getTransaction().commit();
                }
            }
        }.init();
    }

    @Test
    public void testGetPersons() {
        new Repo() {
            public void init() {
                try (Session session = getSession()) {
                    session.beginTransaction();

                    Task task = session.find(Task.class, 45l);

                    Set<Person> persons = task.getPersons();

                    persons.forEach(e -> System.out.println(e.getFirstName() + " " + e.getLastName()));


                    session.getTransaction().commit();
                }
            }
        }.init();
    }

    //TODO
    @Test
    public void testUUID() {
        UUID uuid = UUID.randomUUID();

//        uuid.clockSequence(),
//        uuid.node(),
//        uuid.timestamp()
        show(uuid,
                uuid.getLeastSignificantBits(),
                uuid.getMostSignificantBits(),
                uuid.hashCode(),
                uuid.variant(),
                uuid.version()
        );
    }

    @Test
    public void delete() {
        new Repo() {
            public void init() {
                try (Session session = getSession()) {
                    session.beginTransaction();

                    List<Person> from_person_ = session.createQuery("from Person ", Person.class).getResultList();

                    for (Person p : from_person_)
                        session.delete(p);


                    session.getTransaction().commit();
                }
            }
        }.init();
    }

}