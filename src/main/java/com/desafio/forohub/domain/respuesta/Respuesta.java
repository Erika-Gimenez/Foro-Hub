package com.desafio.forohub.domain.respuesta;

import com.desafio.forohub.domain.topico.dto.DatosActualizarTopico;
import com.desafio.forohub.domain.topico.dto.DatosTopico;
import com.desafio.forohub.domain.topico.entity.Topico;
import com.desafio.forohub.domain.usuario.DatosUsuario;
import com.desafio.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)//nullable indica si el mensaje puede ser nula o no en este caso esta en false lo que significa que el msj no puede ser nulo e
    private String mensaje;

    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)//nullable indica si la clave foranea puede ser nula o no obviamente no queremos que las llaves foraneas sean nulas
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;

    private boolean solucion;

    public Respuesta (DatosRegistroRespuesta datos, DatosTopico dtoTopico, DatosUsuario dtoUsuario){
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.autor = dtoUsuario.usuario();
        this.topico = dtoTopico.topico();
        this.solucion = datos.solucion();
    }

    public void actualizarInformacion(DatosActualizarRespuesta datos) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
    }

}
