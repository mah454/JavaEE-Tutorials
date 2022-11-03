package ir.moke.javaee.repository;

import ir.moke.javaee.exceptions.UserNotFoundException;
import ir.moke.javaee.model.UserDetail;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class RepositoryImpl implements Repository {

    private static final List<UserDetail> USER_DETAIL_LIST = new ArrayList<>();

    @PostConstruct
    public void init() {
        UserDetail userDetail = new UserDetail("1", "admin", "adminpass");
        userDetail.addRole("admin");
        userDetail.addRole("member");
        USER_DETAIL_LIST.add(userDetail);

        userDetail = new UserDetail("2", "john", "mypass");
        userDetail.addRole("member");
        USER_DETAIL_LIST.add(userDetail);
    }

    @Override
    public void insert(UserDetail userDetail) {
        USER_DETAIL_LIST.add(userDetail);
    }

    @Override
    public void update(int index, UserDetail userDetail) {
        USER_DETAIL_LIST.set(index, userDetail);
    }

    @Override
    public void delete(UserDetail userDetail) {
        USER_DETAIL_LIST.remove(userDetail);
    }

    @Override
    public UserDetail select(int id) {
        return USER_DETAIL_LIST.get(id);
    }

    @Override
    public UserDetail select(String username) {
        UserDetail userDetail = null;
        try {
            userDetail = USER_DETAIL_LIST.stream().filter(e -> e.getUsername().equals(username)).findFirst().orElseThrow(UserNotFoundException::new);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return userDetail;
    }

    @Override
    public List<UserDetail> select() {
        return USER_DETAIL_LIST;
    }
}
