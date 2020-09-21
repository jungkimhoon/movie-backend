package com.example.movie.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface JwtService {
    public String getSubject(String token);
    public String createToken(String subject, long ttlMillis);
    public boolean isUsable(String jwt);
}
