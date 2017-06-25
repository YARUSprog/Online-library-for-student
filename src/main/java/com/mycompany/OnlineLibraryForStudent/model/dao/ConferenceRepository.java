
package com.mycompany.OnlineLibraryForStudent.model.dao;

import com.mycompany.OnlineLibraryForStudent.model.Conference;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author YARUS
 */
public interface ConferenceRepository extends CrudRepository<Conference, Long>{
    List<Conference> findByName(String name);
}
