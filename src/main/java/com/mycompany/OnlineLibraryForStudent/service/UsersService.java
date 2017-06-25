
package com.mycompany.OnlineLibraryForStudent.service;

import com.mycompany.OnlineLibraryForStudent.model.Users;
import java.util.List;

/**
 *
 * @author YARUS
 */
public interface UsersService {
    Users addUsers(Users users);
    void delete(long id);    
    Users editBank(Users users);
    List<Users> getAll();
    
    List<Users> findByEmail(String email);
    List<Users> findByLogin(String login);
    List<Users> findByFirstName(String firstName);
    List<Users> findByMiddleName(String middleName);
    List<Users> findByLastName(String lastName);
    List<Users> findByPhone(String phone);
    
    List<Users> findInFullName(String name);
}
