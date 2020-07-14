package repo;

import entities.Part;
import org.hibernate.Session;

import java.util.List;

public class PartRepo extends Repo {

    public List<Part> getParts() {
        try (Session session = getSession()) {
            return session.createQuery("from Part ", Part.class).getResultList();
        }
    }
}
