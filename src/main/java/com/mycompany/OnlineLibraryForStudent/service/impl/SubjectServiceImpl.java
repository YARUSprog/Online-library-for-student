
package com.mycompany.OnlineLibraryForStudent.service.impl;

import com.mycompany.OnlineLibraryForStudent.model.Subject;
import com.mycompany.OnlineLibraryForStudent.model.dao.SubjectRepository;
import com.mycompany.OnlineLibraryForStudent.service.SubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YARUS
 */
@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    SubjectRepository subjectRepository;
    
    @Override
    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);         
    }

    @Override
    public void delete(long id) {
        subjectRepository.delete(id);
    }

    @Override
    public Subject editArticle(Subject subject) {
        return subjectRepository.save(subject);         
    }

    @Override
    public List<Subject> getAll() {
        return (List<Subject>) subjectRepository.findAll();
    }

    @Override
    public List<Subject> findByName(String name) {
        return subjectRepository.findByName(name);
    }

    @Override
    public Subject findById(long id) {
        return subjectRepository.findOne(id);
    }
    
}
