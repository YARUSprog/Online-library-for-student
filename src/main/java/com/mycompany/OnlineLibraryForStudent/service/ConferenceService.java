
package com.mycompany.OnlineLibraryForStudent.service;

import com.mycompany.OnlineLibraryForStudent.model.Conference;
import java.util.List;

/**
 *
 * @author YARUS
 */
public interface ConferenceService {
    Conference addConference(Conference conference);
    void delete(long id);
    Conference editConference(Conference conference);
    List<Conference> getAll();
    
    Conference findById(long id);
    
    List<Conference> findByName(String name);
}
