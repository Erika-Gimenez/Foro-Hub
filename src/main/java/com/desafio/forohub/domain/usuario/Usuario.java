package com.desafio.forohub.domain.usuario;

import com.desafio.forohub.domain.respuesta.Respuesta;
import com.desafio.forohub.domain.topico.entity.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nombre;
    @Column(unique = true, nullable = false)//Determina si una columna en la base de datos permite valores nulos (NULL) o no.
    private String correoElectronico;
    private String contrasenia;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Topico> topicos;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas;

    /*
    cascade = CascadeType.ALL, orphanRemoval = true
     CascadeType.ALL Propaga las operaciones realizadas sobre la entidad padre hacia las entidades relacionadas como persistir, actualizar o eliminar.
     orphanRemoval = true elimina solo los registros que quedan "huérfanos" al ser removidos de la relación.
    ejemplo:Tienes un Usuario con 3 tópicos en su lista. Si eliminas uno de esos tópicos de la lista,
     JPA elimina ese Topico de la base de datos si orphanRemoval = true.
     Si no lo tienes configurado, el registro del tópico quedará huérfano en la base de datos.
     */
}
