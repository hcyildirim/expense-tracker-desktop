package repositories;

import java.io.IOException;
import java.util.List;

public interface Repository<T> {

    T create(T t);

    void delete(T t);

    List<T> all() throws IOException;

    T getById(Long id);

}