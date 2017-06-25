
package com.mycompany.OnlineLibraryForStudent.service;

import com.mycompany.OnlineLibraryForStudent.model.Subject;
import java.util.List;

/**
 *
 * @author YARUS
 */
public interface SubjectService {
    Subject addSubject(Subject subject);
    void delete(long id);
    Subject editArticle(Subject subject);
    List<Subject> getAll();
    
    Subject findById(long id);
    List<Subject> findByName(String name);
}
