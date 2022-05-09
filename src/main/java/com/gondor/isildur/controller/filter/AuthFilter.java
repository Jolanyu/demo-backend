package com.gondor.isildur.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gondor.isildur.util.JWTUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@WebFilter(urlPatterns = { "/*" })
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();
        if (requestURI.contains("/login") || requestURI.contains("/register")
                || requestURI.contains("/kaptcha")) {
            chain.doFilter(request, response);
            return;
        }
        if (req.getHeader("authorization") == null) {
            rep.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }
        String auth = req.getHeader("authorization");
        try {
            Claims claims = JWTUtil.verifyToken(auth);
            req.setAttribute("id", claims.get("id"));
            req.setAttribute("role", claims.get("role"));
            chain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            rep.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
        } catch (SignatureException e) {
            rep.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token signature error");
        } catch (Exception e) {
            rep.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token not valid");
            e.printStackTrace();
        }
    }
}
