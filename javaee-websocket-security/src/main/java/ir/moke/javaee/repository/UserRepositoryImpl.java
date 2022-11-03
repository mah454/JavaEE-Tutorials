package ir.moke.javaee.repository;

import ir.moke.javaee.model.User;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserRepositoryImpl implements UserRepository {

    private static final List<User> authStore = new ArrayList<>();

    @Override
    public void add(User user) {
        authStore.add(user);
    }

    @Override
    public User find(String username) {
        return authStore.stream().filter(e -> e.getUsername().equals(username)).findFirst().orElseThrow(NotFoundException::new);
    }

    @Override
    public List<User> find() {
        return authStore;
    }
}
