package generators;

import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

import java.time.LocalDate;

public class CurrentTimeGenerator implements ValueGenerator<LocalDate> {
    @Override
    public LocalDate generateValue(Session session, Object o) {
        return LocalDate.now();
    }
}
