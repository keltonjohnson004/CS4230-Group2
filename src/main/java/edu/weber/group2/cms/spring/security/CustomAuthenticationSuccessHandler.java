package edu.weber.group2.cms.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.naming.AuthenticationNotSupportedException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
    private final JwtTokenProvider jwtTokenProvider;

    public CustomAuthenticationSuccessHandler(JwtTokenProvider jwtTokenProvider)
    {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException
    {
        String cookieJwt = jwtTokenProvider.createCookieTokenString(authentication);
        if(null!= cookieJwt)
        {
            response.setHeader("Set-Cookie", cookieJwt);
        }
        super.onAuthenticationSuccess(request,response,authentication);

    }
}
