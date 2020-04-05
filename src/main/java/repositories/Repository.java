package repositories;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public interface Repository<T> {

    T create(T t);

    void delete(List<T> ts) throws IOException;

    List<T> all() throws IOException;

    List<T> filterBy(List<T> ts, Predicate<T> p) throws IOException;

    T findBy(List<T> ts, Predicate<T> p) throws IOException;

    boolean isExists(List<T> ts, Predicate<T> p) throws IOException;

}