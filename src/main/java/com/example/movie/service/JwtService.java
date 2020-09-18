package com.example.movie.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface JwtService {
    String createToken(String subject, long ttlMillis);

    String getSubject(String token);
}
