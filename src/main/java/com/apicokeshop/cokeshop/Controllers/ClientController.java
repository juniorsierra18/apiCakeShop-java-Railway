package com.apicokeshop.cokeshop.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apicokeshop.cokeshop.Entities.Client;
import com.apicokeshop.cokeshop.Repositories.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id){
        return clientRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No Client found with ID: " + id));
    }

    @PostMapping
    public Client createcClient(@RequestBody Client client){
        return clientRepository.save(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client detailClient){
        Client client = clientRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No Client found with ID: " + id));

        client.setFullname(detailClient.getFullname());
        client.setEmail(detailClient.getEmail());
        client.setNumber(detailClient.getNumber());
        client.setAddress(detailClient.getAddress());
        client.setPassword(detailClient.getPassword());

        return clientRepository.save(client);
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Long id){
        Client client = clientRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No Client found with ID: " + id));

        clientRepository.delete(client);
        return "The Client with ID " + id + " was removed.";
    }
}
