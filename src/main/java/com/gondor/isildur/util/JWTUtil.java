package com.gondor.isildur.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// We need a signing key, so we'll create one just for this example. Usually
// the key would be read from your application configuration instead.
public class JWTUtil {
  private static final long TOKEN_EXPIRED_TIME = 30 * 24 * 60 * 600;
  /**
   * jwt 加密解密密钥
   */
  private static final String JWT_SECRET = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";
  static final Base64.Encoder encoder = Base64.getEncoder();
  static final byte[] textByte = JWT_SECRET.getBytes(StandardCharsets.UTF_8);
  // 编码
  private static final byte[] apiKeySecretBytes = encoder.encode(textByte);
  // private static final byte[] apiKeySecretBytes =
  // DatatypeConverter.parseBase64Binary(JWT_SECRET);
  private static final Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());

  public static String createToken(Map<String, String> claims) {

    // Map<String, String> claims = Map.of("id", id, "role", role, "name", name);

    long nowMillis = System.currentTimeMillis();
    // Date now = new Date(nowMillis);
    long expMillis = nowMillis + TOKEN_EXPIRED_TIME;
    Date exp = new Date(expMillis);

    String jws = Jwts.builder().setClaims(claims).signWith(signingKey).setExpiration(exp).compact();
    return jws;
  }

  public static Claims verifyToken(String jws) throws Exception {
    Claims claims = Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(jws).getBody();
    if (claims != null) {
      return claims;
    } else {
      throw new Exception("Token not valid");
    }
  }

  public static void main(String[] args) throws Exception {
    Map<String, String> claims = Map.of("id", "123");

    System.out.print(verifyToken(createToken(claims)));

  }
}
