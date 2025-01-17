package com.desafio.forohub.infra.segurity;


import com.desafio.forohub.domain.usuario.IUsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private IUsuarioRepository usuarioRepository;

    @Autowired
    public SecurityFilter(IUsuarioRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


            var authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                var token = authHeader.replace("Bearer ", "");
                try {
                    var correoElectronico = tokenService.getSubject(token);
                    if (correoElectronico != null) {
                        var usuario = usuarioRepository.findByCorreoElectronico(correoElectronico);
                        if (usuario.isPresent()) {
                            var authentication = new UsernamePasswordAuthenticationToken(
                                    usuario.get(), null, usuario.get().getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());;
                }
            }
            filterChain.doFilter(request, response);
        }

}
