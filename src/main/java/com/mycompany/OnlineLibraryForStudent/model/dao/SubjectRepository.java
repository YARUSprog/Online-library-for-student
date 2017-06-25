
package com.mycompany.OnlineLibraryForStudent.model.dao;

import com.mycompany.OnlineLibraryForStudent.model.Subject;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author YARUS
 */
public interface SubjectRepository extends CrudRepository<Subject, Long>{
    List<Subject> findByName(String name);
}
