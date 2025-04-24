package com.linkedin.Connections_Service.Controller;

import com.linkedin.Connections_Service.Entity.Person;
import com.linkedin.Connections_Service.Service.ConnectionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class ConnectionsController {

    private final ConnectionsService connectionsService;

    public ConnectionsController(ConnectionsService connectionsService) {
        this.connectionsService = connectionsService;
    }

    @GetMapping("/{userId}/first-degree")
    public ResponseEntity<List<Person>> getFirstConnections(@PathVariable Long userId){
        return new ResponseEntity<>(this.connectionsService.getAllFirstDegreeConnections(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/second-degree")
    public ResponseEntity<List<Person>> getSecondConnections(@PathVariable Long userId){
        return new ResponseEntity<>(this.connectionsService.getAllSecondDegreeConnections(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/third-degree")
    public ResponseEntity<List<Person>> getThirdConnection(@PathVariable Long userId){
        return new ResponseEntity<>(this.connectionsService.getAllThirdDegreeConnections(userId), HttpStatus.OK);
    }
}
