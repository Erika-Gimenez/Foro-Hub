package com.desafio.forohub.infra.segurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.desafio.forohub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

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

           String token= JWT.create()
                    .withIssuer("foro hub") // Identificador del emisor del token
                    .withSubject(usuario.getCorreoElectronico()) // Usuario que se está autenticando
                    .withClaim("id", usuario.getId()) // Agregar un campo personalizado (ID del usuario)
                    .withIssuedAt(Date.from(Instant.now())) // Campo `issuedAt` en UTC .withExpiresAt(Date.from(generarFechaExpiracion())) // Fecha de expiración del token
                   .withExpiresAt(Date.from(generarFechaExpiracion()))
                   .sign(algorithm);
           // logTokenDetails(token);
            return token;
            // Firmar el token con el algoritmo definido
        } catch (JWTCreationException e) {
            // Manejo de excepción si ocurre un error durante la creación del token
            throw new RuntimeException("Error al crear el token JWT", e);
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
                    .acceptLeeway(10)
                    .build()
                    .verify(token); // Verificar el token

            // Retornar el subject email contenido en el token
            return verifier.getSubject();
        } catch (TokenExpiredException e) {
            throw new RuntimeException("El token JWT ha expirado", e);
        } catch (JWTVerificationException e) {
            // Manejo de excepción si ocurre un error durante la verificación del token
            throw new RuntimeException("Error al verificar el token JWT", e);
        }
    }

    // Mtdo para generar la fecha de expiración del token
    private Instant generarFechaExpiracion() {
        Instant expiration = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
        //System.out.println("Fecha de expiración generada: " + expiration);
        // El token expira 2 horas después de la generación
        return expiration;
    }

   /* private void logTokenDetails(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            Date issuedAt = decodedJWT.getIssuedAt();
            Date expiresAt = decodedJWT.getExpiresAt();
            System.out.println("Token emitido en: " + issuedAt);
            System.out.println("Token expira en: " + expiresAt);
        } catch (JWTDecodeException e) {
            System.err.println("Error al decodificar el token JWT: " + e.getMessage());
        }
    }*/



}
