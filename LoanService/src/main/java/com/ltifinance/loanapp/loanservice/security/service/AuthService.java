package com.ltifinance.loanapp.loanservice.security.service;

import com.ltifinance.loanapp.loanservice.security.entity.AppUser;
import com.ltifinance.loanapp.loanservice.security.repository.AppUserRepository;
import org.apache.catalina.Authenticator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public AuthService(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder, JwtService jwtService,AuthenticationManager authenticationManager) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager=authenticationManager;

    }

    public void register(String username, String password) {
        if (appUserRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setRole("CUSTOMER");

        appUserRepository.save(appUser);
    }




        public String login(String username, String password) {
            System.out.println("LOGIN: entered AuthService");

            System.out.println("LOGIN: AuthenticationManager = "
                    + authenticationManager);

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    username, password));

            System.out.println("LOGIN: authentication successful = "
                    + authentication.isAuthenticated());

            return jwtService.generateToken(username);
        }
}
