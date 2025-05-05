package com.raid.tasks.auth.services;

import com.raid.tasks.auth.AuthenticationRequest;
import com.raid.tasks.auth.AuthenticationResponse;
import com.raid.tasks.auth.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request) throws Exception;
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
