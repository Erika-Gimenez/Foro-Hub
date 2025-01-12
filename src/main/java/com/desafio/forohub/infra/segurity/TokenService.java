package com.desafio.forohub.infra.segurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.desafio.forohub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    // Mtodo para generar un token JWT
    public String generarToken(Usuario usuario) {
        try {
            // Crear un algoritmo HMAC256 con el secreto proporcionado
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            // Construir y firmar el token JWT
            return JWT.create()
                    .withIssuer("foro hub") // Identificador del emisor del token
                    .withSubject(usuario.getCorreoElectronico()) // Usuario que se está autenticando
                    .withClaim("id", usuario.getId()) // Agregar un campo personalizado (ID del usuario)
                    .withExpiresAt(generarFechaExpiracion()) // Fecha de expiración del token
                    .sign(algorithm); // Firmar el token con el algoritmo definido
        } catch (JWTCreationException exception) {
            // Manejo de excepción si ocurre un error durante la creación del token
            throw new RuntimeException("Error al crear el token JWT", exception);
        }
    }

    // Mtodo para extraer el subject email del token
    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("El token no puede ser nulo");
        }
        try {
            // Crear un algoritmo HMAC256 con el secreto proporcionado
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            // Verificar y decodificar el token
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("foro hub") // Validar que el emisor sea el esperado
                    .build()
                    .verify(token); // Verificar el token

            // Retornar el subject email contenido en el token
            return verifier.getSubject();
        } catch (JWTVerificationException exception) {
            // Manejo de excepción si ocurre un error durante la verificación del token
            throw new RuntimeException("Error al verificar el token JWT", exception);
        }
    }

    // Mtdo para generar la fecha de expiración del token
    private Instant generarFechaExpiracion() {
        // El token expira 2 horas después de la generación
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);
    }


}
