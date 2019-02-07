package hello;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import hello.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    //custom sql
    @Query(value = "select * from user where name = ?1", nativeQuery = true)
    @Modifying
    List<User> findByName(String nameString);

    //custom sql
    @Query(value = "select * from user where name = ?1 or name = ?2 ", nativeQuery = true)
    @Modifying
    List<User> findBy2Param(String nameString, String nameString2);

    //custom sql
    @Query(value = "update user set name = ?1 where id = ?2 ", nativeQuery = true)
    @Modifying
    @Transactional
    // void updateBySql(Integer id, String nameString);
    void updateBySql(String nameString, Integer id);
}