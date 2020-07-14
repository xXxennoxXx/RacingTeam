package entities;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.junit.Test;
import repo.Repo;

public class AssemblyTest {

    @Test
    public void init() {
        new Repo() {
            public void init() {
                try (Session session = getSession()) {
                    session.beginTransaction();

                    Part part = session.find(Part.class, 19l);
                    Assembly assembly = session.find(Assembly.class, 11l);

                    part.getAssemblies().add(assembly);
                    session.flush();
//                    session.detach(assembly);
                    session.refresh(assembly);
                    System.out.println(assembly.getParts().contains(part));

                    session.getTransaction().commit();
                }
            }
        }.init();

    }

}