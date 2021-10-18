package com.genereux.database.DAO;

import com.genereux.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    // add a method to sort by last name
    public List<User> findAllByOrderByLastNameAsc();

}
