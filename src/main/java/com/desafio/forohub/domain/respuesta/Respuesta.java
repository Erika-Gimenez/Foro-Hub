package com.desafio.forohub.domain.respuesta;


import com.desafio.forohub.domain.respuesta.dto.DatosActualizarRespuesta;
import com.desafio.forohub.domain.respuesta.dto.DatosRegistroRespuesta;
import com.desafio.forohub.domain.topico.dto.DatosTopico;
import com.desafio.forohub.domain.topico.entity.Topico;
import com.desafio.forohub.domain.usuario.dto.DatosUsuario;
import com.desafio.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)//nullable indica si el mensaje puede ser nula o no en este caso esta en false lo que significa que el msj no puede ser nulo e
    private String mensaje;

    private LocalDateTime fechaDeCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)//nullable indica si la clave foranea puede ser nula o no obviamente no queremos que las llaves foraneas sean nulas
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;

    private boolean solucion;

    public Respuesta(){
    }

    public Respuesta(Long id, String mensaje, LocalDateTime fechaCreacion, Usuario autor, Topico topico, boolean solucion) {
        this.id = id;
        this.mensaje = mensaje;
        this.fechaDeCreacion = fechaCreacion;
        this.autor = autor;
        this.topico = topico;
        this.solucion = solucion;
    }

    public Respuesta (DatosRegistroRespuesta datos, DatosTopico dtoTopico, DatosUsuario dtoUsuario){
        this.mensaje = datos.mensaje();
        this.fechaDeCreacion = LocalDateTime.now();
        this.autor = dtoUsuario.usuario();
        this.topico = dtoTopico.topico();
        this.solucion = true;
    }

    public void actualizarInformacion(DatosActualizarRespuesta datos) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public void setSolucion(boolean solucion) {
        this.solucion = solucion;
    }

    public Long getId() {
        return id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Topico getTopico() {
        return topico;
    }

    public boolean isSolucion() {
        return solucion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Respuesta respuesta = (Respuesta) o;
        return Objects.equals(id, respuesta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
