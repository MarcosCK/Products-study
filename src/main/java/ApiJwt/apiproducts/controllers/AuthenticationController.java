package ApiJwt.apiproducts.controllers;

import ApiJwt.apiproducts.model.entity.User.AuthenticationDTO;
import ApiJwt.apiproducts.model.entity.User.RegisterDTO;
import ApiJwt.apiproducts.model.entity.User.User;
import ApiJwt.apiproducts.model.repository.User.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO dto){
        if(this.repository.findByLogin(dto.login()) != null)return ResponseEntity.badRequest().build();

        String encryptPassword = new BCryptPasswordEncoder().encode(dto.password());
        User newUser = new User(dto.login(), encryptPassword, dto.role());

        repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
