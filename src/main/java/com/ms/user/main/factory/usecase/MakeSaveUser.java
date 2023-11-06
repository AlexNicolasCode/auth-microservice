package com.ms.user.main.factory.usecase;

import org.springframework.stereotype.Service;

import com.ms.user.data.usecase.DbSaveUser;
import com.ms.user.domain.usecase.SaveUser;
import com.ms.user.infra.database.UserPostgresRepository;

@Service
public class MakeSaveUser {
    
    public MakeSaveUser (UserPostgresRepository userPostgresRepository) {
        this.userPostgresRepository = userPostgresRepository;
    }
    
    private UserPostgresRepository userPostgresRepository;
    
    public SaveUser build () {
        return new DbSaveUser(this.userPostgresRepository);
    }
}
