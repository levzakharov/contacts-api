package ru.kpfu.itis.lzakharov.contacts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.lzakharov.contacts.model.Client;
import ru.kpfu.itis.lzakharov.contacts.repository.ClientRepository;
import ru.kpfu.itis.lzakharov.contacts.security.util.JwtUtil;
import ru.kpfu.itis.lzakharov.contacts.service.ClientService;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class MainController {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String sayHello() {
        Client client = new Client();
        client.setId(1L);
        client.setUsername("Lev");
        client.setPassword("password");
        clientRepository.save(client);

        return "Hello!";
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public ResponseEntity<?> user(Principal principal) {
        return ResponseEntity.ok(principal.getName());
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<String> getToken(@RequestParam(name = "username") String username,
                                           @RequestParam(name = "password") String password) {

        Client client = clientService.find(username, password);

        if (client == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong username or password");
        }

        String token = JwtUtil.generateToken(client);

        return ResponseEntity.ok(token);
    }
}
