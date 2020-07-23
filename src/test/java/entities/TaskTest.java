package entities;

import org.hibernate.Session;
import org.junit.Test;
import repo.Repo;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static utils.StringUtils.show;

public class TaskTest {


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



}