
package com.mycompany.OnlineLibraryForStudent.model.dao;

import com.mycompany.OnlineLibraryForStudent.model.UserRoles;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author YARUS
 */
public interface UserRolesRepository extends CrudRepository<UserRoles, Long>{
    List<UserRoles> findByName(String name);
}
