package com.linkedin.Connections_Service.Repository;

import com.linkedin.Connections_Service.Entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Optional<Person> getByName(String name);



    // Find the 1st Degree Connections
    @Query("Match (personA:Person) -[:CONNECTED_TO]- (personB:Person) " +
            "Where personA.userId=$userId " +
            "return personB")
    List<Person> getFirstDegreeConnections(Long userId);


    // Give space when a new line is started
    // (otherwise the last word of previous line and new word of new will concatenate)


    // Find the 2nd Degree Connection
    @Query("MATCH (p:Person {userId:$userId})-[:CONNECTED_TO]->()-[:CONNECTED_TO]->(secondDegree) " +
            "RETURN DISTINCT secondDegree")
    List<Person> getSecondDegreeConnections(Long userId);





    // Find 3rd Degree Connection
    @Query("Match (p:Person{userId:$userId}) -[:CONNECTED_TO]->()-[:CONNECTED_TO]->()-[:CONNECTED_TO]->(thirdDegree) " +
            "Return DISTINCT thirdDegree")
    List<Person> getThirdDegreeConnections(Long userId);
}
