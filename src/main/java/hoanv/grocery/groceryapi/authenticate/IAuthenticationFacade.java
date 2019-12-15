package hoanv.grocery.groceryapi.authenticate;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
