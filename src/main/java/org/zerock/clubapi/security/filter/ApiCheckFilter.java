package org.zerock.clubapi.security.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.zerock.clubapi.security.util.JWTUtil;

import com.nimbusds.jose.shaded.json.JSONObject;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter{

    private AntPathMatcher antAntPathMatcher;
    private String pattern;
    private JWTUtil jwtUtil;

    public ApiCheckFilter(String pattern, JWTUtil jwtUtil) {
        this.antAntPathMatcher = new AntPathMatcher();
        this.pattern = pattern;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
            
        log.info("REQUESTURI: " + request.getRequestURI());
        
        log.info(antAntPathMatcher.match(pattern, request.getRequestURI()));

        if(antAntPathMatcher.match(pattern, request.getRequestURI())) {
            
            log.info("ApiCheckFilter........................");
            log.info("ApiCheckFilter........................");
            log.info("ApiCheckFilter........................");

            boolean checkHeader = checkAuthHeader(request);
            
            if(checkHeader) {
                filterChain.doFilter(request, response);
                return;
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                // json 리턴 및 한글깨짐 수정
                response.setContentType("application/json;charset=utf-8");
                JSONObject json = new JSONObject();
                String message = "FAIL CHECK API TOKEN";
                json.put("code", "403");
                json.put("message", message);

                PrintWriter out = response.getWriter();
                out.print(json);
                return;
            }

        }

        
        filterChain.doFilter(request, response);
    }

    private boolean checkAuthHeader(HttpServletRequest request) {
        
        boolean checkResult = false;

        String authHeader = request.getHeader("Authorization");

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")) {
            log.info("Authorization exist: " + authHeader);

            // if(authHeader.equals("12345678")) {
            //     checkResult = true;
            // }
            try {
                
                String email = jwtUtil.validateAndExtract(authHeader.substring(7));
                log.info("validate result: " + email);
                checkResult = email.length() > 0;

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return checkResult;
    }
    
}
