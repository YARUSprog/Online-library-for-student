
package com.mycompany.OnlineLibraryForStudent.service.impl;

import com.mycompany.OnlineLibraryForStudent.model.Users;
import com.mycompany.OnlineLibraryForStudent.model.dao.UsersRepository;
import com.mycompany.OnlineLibraryForStudent.service.UsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YARUS
 */
@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    UsersRepository usersRepository;
    
    @Override
    public Users addUsers(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public void delete(long id) {
        usersRepository.delete(id);
    }

    @Override
    public Users editBank(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public List<Users> getAll() {
        return (List<Users>)usersRepository.findAll();
    }

    @Override
    public List<Users> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public List<Users> findByLogin(String login) {
        return usersRepository.findByLogin(login);
    }

    @Override
    public List<Users> findByFirstName(String firstName) {
        return usersRepository.findByFirstName(firstName);
    }

    @Override
    public List<Users> findByMiddleName(String middleName) {
        return usersRepository.findByMiddleName(middleName);
    }

    @Override
    public List<Users> findByLastName(String lastName) {
        return usersRepository.findByLastName(lastName);
    }

    @Override
    public List<Users> findByPhone(String phone) {
        return usersRepository.findByPhone(phone);
    }

    @Override
    public List<Users> findInFullName(String name) {
        return usersRepository.findInFullName(name);
    }
    
}
