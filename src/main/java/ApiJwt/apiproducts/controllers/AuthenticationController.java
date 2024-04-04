package ApiJwt.apiproducts.controllers;

import ApiJwt.apiproducts.infra.security.TokenService;
import ApiJwt.apiproducts.model.entity.user.AuthenticationDTO;
import ApiJwt.apiproducts.model.entity.user.LoginResponseDTO;
import ApiJwt.apiproducts.model.entity.user.RegisterDTO;
import ApiJwt.apiproducts.model.entity.user.User;
import ApiJwt.apiproducts.model.repository.User.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
   private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
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
