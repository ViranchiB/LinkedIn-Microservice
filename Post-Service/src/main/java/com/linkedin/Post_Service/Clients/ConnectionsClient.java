package com.linkedin.Post_Service.Clients;

import com.linkedin.Post_Service.DTO.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CONNECTIONS-SERVICE")  // This should match the Connections service name.
public interface ConnectionsClient {

    @GetMapping("/person/first-degree")
    List<Person> getFirstConnections();
}
