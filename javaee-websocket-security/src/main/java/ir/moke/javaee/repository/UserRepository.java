package ir.moke.javaee.repository;

import ir.moke.javaee.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserRepository {

    void add(User user);
    User find(String username);

    List<User> find();
}
