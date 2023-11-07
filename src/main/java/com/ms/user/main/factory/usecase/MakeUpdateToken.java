package com.ms.user.main.factory.usecase;

import org.springframework.stereotype.Service;

import com.ms.user.data.protocol.GenerateToken;
import com.ms.user.data.usecase.DbUpdateToken;
import com.ms.user.domain.usecase.UpdateToken;
import com.ms.user.infra.cryptography.JwtAdapter;
import com.ms.user.infra.database.UserPostgresRepository;

@Service
public class MakeUpdateToken {
    
    public MakeUpdateToken(UserPostgresRepository userPostgresRepository) {    
        this.userPostgresRepository = userPostgresRepository;
    }
    
    private UserPostgresRepository userPostgresRepository;
    
    public UpdateToken build () {
        GenerateToken generateToken = new JwtAdapter();
        return new DbUpdateToken(generateToken, this.userPostgresRepository); 
    }
}
