
package com.mycompany.OnlineLibraryForStudent.service.impl;

import com.mycompany.OnlineLibraryForStudent.model.Conference;
import com.mycompany.OnlineLibraryForStudent.model.dao.ConferenceRepository;
import com.mycompany.OnlineLibraryForStudent.service.ConferenceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YARUS
 */
@Service
public class ConferenceServiceImpl implements ConferenceService{

    @Autowired
    ConferenceRepository conferenceRepository;
    
    @Override
    public Conference addConference(Conference conference) {
        return conferenceRepository.save(conference);
    }

    @Override
    public void delete(long id) {
        conferenceRepository.delete(id);
    }

    @Override
    public Conference editConference(Conference conference) {
        return conferenceRepository.save(conference);
    }

    @Override
    public List<Conference> getAll() {
        return (List<Conference>) conferenceRepository.findAll();
    }

    @Override
    public List<Conference> findByName(String name) {
        return conferenceRepository.findByName(name);
    }

    @Override
    public Conference findById(long id) {
        return conferenceRepository.findOne(id);
    }
    
}
