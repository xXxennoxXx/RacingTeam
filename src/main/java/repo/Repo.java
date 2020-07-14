package repo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Repo {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            ourSessionFactory = new Configuration().
                    configure("hibernate.cfg.xml").
                    buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void makeRequest(Consumer<Session> f) {
        new Repo() {
            public void init(Consumer<Session> f) {
                try (Session session = getSession()) {
                    session.beginTransaction();
                    f.accept(session);
                    session.getTransaction().commit();
                }
            }
        }.init(f);
    }
}
