package com.wzx.service.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by arthurwang on 17/3/28.
 */
@Service
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        if (exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
            redirectStrategy.sendRedirect(request, response, "/login?error");
        } else if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
            redirectStrategy.sendRedirect(request, response, "/login?error");
        }
    }
}

