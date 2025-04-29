package com.linkedin.API_Gateway.Filters;

import com.linkedin.API_Gateway.Service.JWTServiceImpl;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

// Class helps to check if a request contains the header or not
// If header is present then it will extract the subject/claims(userId) from the header.
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    private final JWTServiceImpl jwtServiceImpl;

    public AuthenticationFilter(JWTServiceImpl jwtServiceImpl) {
        super(Config.class);
        this.jwtServiceImpl = jwtServiceImpl;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("Received a request for authentication filter");

            // It will extract the Authorization from the request
            final String tokenHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

            // this will if header is found or not found or Unauthorized
            if(tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                log.error("Authorization header is missing");

                return exchange.getResponse().setComplete();
            }

            // If token found
            final String token = tokenHeader.split("Bearer ")[1];

            try {
                String userId = this.jwtServiceImpl.getUserIdFromToken(token);
                ServerWebExchange modifiedExchange = exchange
                        .mutate()
                        .request(r -> r.header("X-User-Id", userId))
                        .build();

                return chain.filter(modifiedExchange);
            }catch (JwtException jwtException){
                log.error("JWT Exception: {}", jwtException.getLocalizedMessage());
                log.error(jwtException.getMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }


    public static class Config{

    }
}
