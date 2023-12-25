package com.ms.user.data.usecase;

import com.ms.user.data.protocol.GetUserCountByEmailRepository;
import com.ms.user.domain.model.DefaultReturn;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.usecase.CheckEmailAlreadyUsed;

public class DbCheckEmailAlreadyUsed implements CheckEmailAlreadyUsed {
    private final GetUserCountByEmailRepository getUserCountByEmailRepository;

    public DbCheckEmailAlreadyUsed(GetUserCountByEmailRepository getUserCountByEmailRepository) {
        this.getUserCountByEmailRepository = getUserCountByEmailRepository;
    }

    @Override
    public DefaultReturn<Boolean> checkEmailAlreadyUsed(Email email) {
        try {
            DefaultReturn<Integer> result = this.getUserCountByEmailRepository.getUserCountByEmail(email);
            if (result.getError() != null) {
    
            }
            int userCount = result.getContent();
            boolean emailAlreadyUsed = userCount >= 1;
            return new DefaultReturn<Boolean>(null, emailAlreadyUsed);
        } catch (Exception error) {
            return new DefaultReturn<Boolean>(error.getMessage(), null);
        }
    }
}
