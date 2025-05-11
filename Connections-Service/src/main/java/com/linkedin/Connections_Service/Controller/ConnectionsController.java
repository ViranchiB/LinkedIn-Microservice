package com.linkedin.Connections_Service.Controller;

import com.linkedin.Connections_Service.Entity.Person;
import com.linkedin.Connections_Service.Service.ConnectionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class ConnectionsController {

    private final ConnectionsService connectionsService;

    public ConnectionsController(ConnectionsService connectionsService) {
        this.connectionsService = connectionsService;
    }

    // Here we are fetching the user ID from the token or Context holder and pass it to find the 1st degree connections
    @GetMapping("/first-degree")
    public ResponseEntity<List<Person>> getFirstConnections(){
        return new ResponseEntity<>(this.connectionsService.getAllFirstDegreeConnections(), HttpStatus.OK);
    }

    @GetMapping("/second-degree")
    public ResponseEntity<List<Person>> getSecondConnections(){
        return new ResponseEntity<>(this.connectionsService.getAllSecondDegreeConnections(), HttpStatus.OK);
    }

    @GetMapping("/third-degree")
    public ResponseEntity<List<Person>> getThirdConnection(){
        return new ResponseEntity<>(this.connectionsService.getAllThirdDegreeConnections(), HttpStatus.OK);
    }
}
