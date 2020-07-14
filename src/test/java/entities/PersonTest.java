package entities;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.junit.Test;
import repo.Repo;

import java.util.List;

import static utils.StringUtils.show;

public class PersonTest {

    @Test
    public void init() {
        new Repo() {
            public void init() {
                try (Session session = getSession()) {
                    session.beginTransaction();

                    List<Task> ft = session.createQuery("From Task ", Task.class).getResultList();

                    Person person = new Person();

                    for (Task f : ft) {
                        for (int i = 0; i < 4; i++) {


                            person = new Person("Jan", "Janowski");

                            Address address = new Address();
                            person.setAddress(address);

                            address.setCity("Warsaw");
                            address.setStreet("Marszalkowska");

                            session.persist(person);

                            person.addTask(f);
                        }
                    }

                    session.saveOrUpdate(person);

                    session.getTransaction().commit();
                }
            }
        }.init();
    }

    @Test
    public void fetechTest() {
        Repo.makeRequest(s -> {
            Integer task = s.createQuery("from Task ", Task.class).getResultList().size();
            List<Task> resultList = s.createQuery("from Task t join fetch t.persons", Task.class).getResultList();
            Integer taskPersons = resultList.size();
            resultList.forEach(e -> show(e.getId() + " " + e.getPersons().size()));
            show(task, taskPersons);


        });
    }


}