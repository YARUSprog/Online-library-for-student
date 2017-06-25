
package com.mycompany.OnlineLibraryForStudent.service;

import com.mycompany.OnlineLibraryForStudent.model.UserRoles;
import java.util.List;

/**
 *
 * @author YARUS
 */
public interface UserRolesService {
    UserRoles addSubject(UserRoles userRoles);
    void delete(long id);
    UserRoles editArticle(UserRoles userRoles);
    List<UserRoles> getAll();
    
    List<UserRoles> findByName(String name);
}
