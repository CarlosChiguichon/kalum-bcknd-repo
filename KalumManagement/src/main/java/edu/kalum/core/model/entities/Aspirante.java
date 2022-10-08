package edu.kalum.core.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "aspirante")
public class Aspirante implements Serializable {
    @Id
    @NotEmpty(message = "El campo No Expediente no puede ir vacio")
    @Column(name = "no_expediente")
    private String noExpediente;
    @NotEmpty(message = "El campo apellidos no puede ir vacio")
    @Column(name = "apellidos")
    private String apellidos;
    @NotEmpty(message = "El campo nombres no puede ir vacio")
    @Column(name = "nombres")
    private String nombres;
    @NotEmpty(message = "El campo direccion no puede ir vacio")
    @Column(name = "direccion")
    private String direccion;
    @NotEmpty(message = "El campo telefono no puede ir vacio")
    @Column(name = "telefono")
    private String telefono;
    @Email
    @NotEmpty(message = "El campo email no puede ir vacio")
    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "examen_id", referencedColumnName = "examen_id")
    private ExamenAdmision examenAdmision;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carrera_id", referencedColumnName = "carrera_id")
    private CarreraTecnica carreraTecnica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jornada_id", referencedColumnName = "jornada_id")
    private Jornada jornada;
}
