package com.pack.gatewayservice.filter;


import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.pack.gatewayservice.config.RouteValidators;
import com.pack.gatewayservice.util.JwtUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	public static Logger log = org.slf4j.LoggerFactory.getLogger(AuthenticationFilter.class);
    @Autowired
    private RouteValidators validator;

    //    @Autowired
//    private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                  log.info(authHeader + "****");
                   jwtUtil.validateToken(authHeader);

                } catch (Exception e) {
                    log.info("invalid acceesss" + "    *******");
                    throw new RuntimeException("unauthorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}

