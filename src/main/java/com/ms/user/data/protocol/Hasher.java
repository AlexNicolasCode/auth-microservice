package com.ms.user.data.protocol;

import com.ms.user.domain.model.Hash;

public interface Hasher<T> {
    Hash hash(T plaintext);
}
