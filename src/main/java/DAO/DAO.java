package DAO;

import java.util.ArrayList;
import java.util.Optional;

public interface DAO<T> {
    ArrayList<T> getAll();

   T get(String id);

    void add(T t);

    void update(T t);

    void delete(T t);

    ArrayList<String> getAllName();
}
