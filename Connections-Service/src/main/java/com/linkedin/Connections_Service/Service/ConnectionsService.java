package com.linkedin.Connections_Service.Service;

import com.linkedin.Connections_Service.Entity.Person;

import java.util.List;

public interface ConnectionsService {
    List<Person> getAllFirstDegreeConnections(Long userId);
    List<Person> getAllSecondDegreeConnections(Long userId);
    List<Person> getAllThirdDegreeConnections(Long userId);
}
