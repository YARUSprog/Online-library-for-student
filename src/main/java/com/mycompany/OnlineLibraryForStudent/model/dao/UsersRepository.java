
package com.mycompany.OnlineLibraryForStudent.model.dao;

import com.mycompany.OnlineLibraryForStudent.model.Users;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author YARUS
 */
public interface UsersRepository extends CrudRepository<Users, Long>{
    List<Users> findByEmail(String email);
    List<Users> findByLogin(String login);
    List<Users> findByFirstName(String firstName);
    List<Users> findByMiddleName(String middleName);
    List<Users> findByLastName(String lastName);
    List<Users> findByPhone(String phone);
    
    @Query("SELECT u FROM Users u where u.firstName = :name OR u.middleName = :name OR u.lastName = :name")
    List<Users> findInFullName(@Param("name") String name);
}
