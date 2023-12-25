package com.ms.user.data.protocol;

import com.ms.user.domain.model.DefaultReturn;
import com.ms.user.domain.model.Email;

public interface GetUserCountByEmailRepository {
    DefaultReturn<Integer> getUserCountByEmail(Email email);
}
