package com.desafio.forohub.infra.segurity;

import com.desafio.forohub.domain.usuario.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionUserService implements UserDetailsService {

    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public AutenticacionUserService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException {
        return usuarioRepository.findByCorreoElectronico(correoElectronico)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
