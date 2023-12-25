package com.ms.user.data.protocol;

import com.ms.user.domain.dto.EmailDto;

public interface SendEmail {
    void send(EmailDto emailDto);
}
