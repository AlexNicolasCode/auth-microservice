package data.mock.cryptography;

import com.ms.user.data.protocol.ComparePassword;
import com.ms.user.domain.model.Password;

public class ComparePasswordSpy implements ComparePassword {
    private Password password;
    private Password passwordEncoded;
    private Boolean result = true;
    private Integer count = 0;

    @Override
    public boolean compare(Password password, Password passwordEncoded) {
        this.count++;
        this.password = password;
        this.passwordEncoded = passwordEncoded;
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Password getPassword() {
        return password;
    }

    public Password getPasswordEncoded() {
        return passwordEncoded;
    }

    public Integer getCount() {
        return count;
    }
}
