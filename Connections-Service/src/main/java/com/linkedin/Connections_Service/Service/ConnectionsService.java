package com.linkedin.Connections_Service.Service;

import com.linkedin.Connections_Service.Entity.Person;

import java.util.List;

public interface ConnectionsService {
    List<Person> getAllFirstDegreeConnections();
    List<Person> getAllSecondDegreeConnections();
    List<Person> getAllThirdDegreeConnections();
}
