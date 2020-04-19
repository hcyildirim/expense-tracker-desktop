package repositories;

import java.io.IOException;
import java.util.List;

public interface Repository<T, V> {

    List<T> all() throws IOException;

    T getById(V id) throws IOException;

    void create(T t) throws IOException;

    void update(T t) throws IOException;

    void delete(T t) throws IOException;

}