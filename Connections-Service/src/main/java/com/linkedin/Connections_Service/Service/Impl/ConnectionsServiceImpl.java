package com.linkedin.Connections_Service.Service.Impl;

import com.linkedin.Connections_Service.Auth.UserContextHolder;
import com.linkedin.Connections_Service.Entity.Person;
import com.linkedin.Connections_Service.Repository.PersonRepository;
import com.linkedin.Connections_Service.Service.ConnectionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionsServiceImpl implements ConnectionsService {

    private final Logger log = LoggerFactory.getLogger(ConnectionsServiceImpl.class);

    private final PersonRepository personRepository;

    public ConnectionsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // This is how we can get the UserId from the UserContextHolder and we don't need to pass in URL
    // To apply this we need to just use the Auth package
    @Override
    public List<Person> getAllFirstDegreeConnections() {
        Long userId = UserContextHolder.getCurrentUserId();
        log.debug("Getting 1st degree connections for user with Id: {}", userId);
        return this.personRepository.getFirstDegreeConnections(userId);
    }

    @Override
    public List<Person> getAllSecondDegreeConnections(Long userId) {
        log.debug("Getting 2st degree connections for user with Id: {}", userId);
        return this.personRepository.getSecondDegreeConnections(userId);
    }

    @Override
    public List<Person> getAllThirdDegreeConnections(Long userId) {
        log.debug("Getting 3st degree connections for user with Id: {}", userId);
        return this.personRepository.getThirdDegreeConnections(userId);
    }
}
