
package com.mycompany.OnlineLibraryForStudent.service.impl;

import com.mycompany.OnlineLibraryForStudent.model.UserRoles;
import com.mycompany.OnlineLibraryForStudent.model.dao.UserRolesRepository;
import com.mycompany.OnlineLibraryForStudent.service.UserRolesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YARUS
 */
@Service
public class UserRolesServiceImpl implements UserRolesService{

    @Autowired
    UserRolesRepository userRolesRepository;
    
    @Override
    public UserRoles addSubject(UserRoles userRoles) {
        return userRolesRepository.save(userRoles);
    }

    @Override
    public void delete(long id) {
        userRolesRepository.delete(id);
    }

    @Override
    public UserRoles editArticle(UserRoles userRoles) {
        return userRolesRepository.save(userRoles);
    }

    @Override
    public List<UserRoles> getAll() {
        return (List<UserRoles>) userRolesRepository.findAll();
    }

    @Override
    public List<UserRoles> findByName(String name) {
        return userRolesRepository.findByName(name);
    }
    
}
