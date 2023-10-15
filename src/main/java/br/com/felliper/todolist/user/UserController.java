package br.com.felliper.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserRepository repository;
    @PostMapping
    public ResponseEntity create(@RequestBody User user) {

        User verif = this.repository.findByUsername(user.getUsername());
        if (verif != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe.");
        }
        var passwordHashed = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(passwordHashed);

        User userCreated = this.repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
