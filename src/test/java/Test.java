import entities.Assembly;
import entities.Part;
import entities.PartState;
import org.hibernate.Session;
import org.junit.Before;
import repo.Repo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

public class Test {

    private Logger logger;

    @Before
    public void init() {
        this.logger = Logger.getLogger(Test.class.toString());
    }


    @org.junit.Test
    public void test() {
        new Repo() {
            public void init() {
                try (Session session = getSession()) {
                    session.beginTransaction();

                    Part part = session.createQuery("from Part ", Part.class).getResultList().get(0);
                    Part part1 = session.find(Part.class, 3l);

                    System.out.println(part1.toString());

//                    part.setSerialNumber(122l);

                    session.getTransaction().commit();
                }
            }
        }.init();
    }

    @org.junit.Test
    public void testInsert() {
        new Repo() {
            public void init() {
                try (Session session = getSession()) {
                    session.beginTransaction();


                    Part part = new Part();
                    part.setSerialNumber(1131123l);
                    part.setState(PartState.UNDER_CONSTRUCTION);
                    session.persist(part);
                    session.getTransaction().commit();
                }
            }
        }.init();
    }

    @org.junit.Test
    public void testInsert2() {

        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localTime);
        System.out.println(localDate);
        System.out.println(localDateTime);
    }

    @org.junit.Test
    public void testInsertAssembly() {
        try {
            new Repo() {
                public void init() throws InterruptedException {
                    try (Session session = getSession()) {
                        session.beginTransaction();


                        Assembly assembly = new Assembly();
                        assembly.setSerialNumber(1134524656345354l);

                        session.persist(assembly);

                        Thread.sleep(1000);

                        assembly.setSerialNumber(121234234132431543l);

                        session.getTransaction().commit();
                    }
                }
            }.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
