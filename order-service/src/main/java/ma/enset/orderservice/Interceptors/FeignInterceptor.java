package ma.enset.orderservice.Interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        SecurityContext context= SecurityContextHolder.getContext();
        Authentication authentication=context.getAuthentication();
        JwtAuthenticationToken token=(JwtAuthenticationToken) authentication;
        DefaultOidcUser oidcUser=(DefaultOidcUser) token.getPrincipal();
        String jwtAccessToken=token.getToken().getTokenValue();
        requestTemplate.header("Authorization","Bearer "+jwtAccessToken);


    }
}
