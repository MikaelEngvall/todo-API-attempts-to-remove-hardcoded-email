package se.lexicon.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.todoapi.domain.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Boolean existsByEmail(String email);

    User getUserByEmail(String email);

    @Query("select u from User u")
    List<User> getAll();

    @Modifying
    @Query("update User u set u.expired = :status where u.email = :email")
    void updateExpiredByEmail(@Param("email") String email, @Param("status") boolean status);

    @Modifying
    @Query("update User u set u.expired = :password where u.email = :email")
    void updatePasswordByEmail(@Param("email") String email, @Param("password") String newPassword);

}
