package lewiszlw.jjwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.security.KeyPair;
import java.util.Date;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-31
 */
public class JJWTDemo {

    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder()
                .setSubject("Joe")
                .setExpiration(new Date())
                .signWith(key)
                .compact();
        System.out.println("jws: " + jws);
        try {
            System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject());
        } catch (ExpiredJwtException e) {
            System.out.println("过期");
        }

        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
    }
}
