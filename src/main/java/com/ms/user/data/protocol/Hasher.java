package com.ms.user.data.protocol;

import com.ms.user.domain.model.Hash;

public interface Hasher {
    Hash hash(String plaintext);
}
