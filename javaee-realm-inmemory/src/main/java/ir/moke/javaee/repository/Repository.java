package ir.moke.javaee.repository;

import ir.moke.javaee.model.UserDetail;

import jakarta.ejb.Local;
import java.util.List;

@Local
public interface Repository {

    void insert(UserDetail userDetail);

    void update(int index,UserDetail userDetail);

    void delete(UserDetail userDetail);

    UserDetail select(int id);

    UserDetail select(String username);

    List<UserDetail> select();
}
