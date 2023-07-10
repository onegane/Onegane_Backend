package onegane.onegane.global.jwt.exception;

import io.jsonwebtoken.JwtException;

public class InvalidRefreshTokenInfoException extends JwtException {

    public InvalidRefreshTokenInfoException(String message) {
        super(message);
    }

    public InvalidRefreshTokenInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
