package example.security;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.BadPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import org.springframework.security.crypto.password.PasswordEncoder;

public class DESPasswordEncoder
  extends org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
  implements PasswordEncoder {

    SecretKey key;

    public DESPasswordEncoder()
    throws Exception {
      super();
      key = DESUtils.generateKey();
    }

    @Override
    public java.lang.String encode(java.lang.CharSequence rawPassword)
    {
      try {
        String res = DESUtils.encrypt(rawPassword.toString(), key);
        return super.encode(res);//BCrypt.hashpw(res, BCrypt.gensalt());
      } catch(Exception e) {}
      return super.encode(rawPassword);
    }

    @Override
    public boolean matches(java.lang.CharSequence rawPassword, java.lang.String encodedPassword)
     {
         try {
           String res = DESUtils.encrypt(rawPassword.toString(), key);
           return super.matches(res, encodedPassword);
         } catch(Exception e) {}
         return false;
     }
}
