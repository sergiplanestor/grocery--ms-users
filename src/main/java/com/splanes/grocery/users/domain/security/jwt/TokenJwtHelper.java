package com.splanes.grocery.users.domain.security.jwt;

import com.splanes.grocery.users.commons.Scope;
import com.splanes.grocery.users.domain.model.AuthToken;
import io.jsonwebtoken.*;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.splanes.grocery.users.commons.Scope.apply;

@Component
@NoArgsConstructor
@Slf4j
public class TokenJwtHelper {

    public static final String BEARER_TYPE = "Bearer";
    private static final String AUTH_HEADER = "Authorization";
    private static final String ISSUER = "GroceryBE$ms-users";
    private static final String USR_UUID = "UUID";
    private static final String ROLES = "Roles";

    @Value("${jwt.secret}")
    private static String secret;

    @Value("${jwt.lifetimeMs}")
    private int lifetime;

    private Map<String, Object> claims(String uuid, List<String> roles) {
        return apply(new HashMap<>(), claims -> {
            claims.put(USR_UUID, uuid);
            claims.put(ROLES, roles);
        });
    }

    private Calendar expirationOf(Calendar now) {
        return apply(now, (o) -> o.add(Calendar.MILLISECOND, lifetime));
    }

    public String newJwt(String uuid, String email, List<String> roles) {
        Calendar now = Calendar.getInstance();

        return Jwts.builder()
                .setIssuer(ISSUER)
                .setIssuedAt(now.getTime())
                .setExpiration(expirationOf(now).getTime())
                .setAudience(email)
                .setClaims(claims(uuid, roles))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String findByRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTH_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)) {
            return bearerToken.replace(BEARER_TYPE, "").trim();
        }
        return null;
    }

    public static Optional<AuthToken> parse(String token) {
        try {
            Jws<Claims> jws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return Optional.of(AuthToken.builder()
                    .email(jws.getBody().getSubject())
                    .jws(jws)
                    .build());
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return Optional.empty();
    }
}
