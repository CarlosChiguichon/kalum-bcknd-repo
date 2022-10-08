package edu.kalum.enrollment.models.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aspirante")
@NamedQueries({ @NamedQuery(name = "Aspirante.findAll",query = "select a from Aspirante a order by a.apellidos")})
public class Aspirante  implements Serializable {
    @Id
    @Column(name = "no_expediente")
    private String noExpediente;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email")
    private String email;
    @Column(name = "examen_id")
    private String examenId;
    @Column(name = "estatus")
    private String estatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carrera_id", referencedColumnName = "carrera_id")
    private CarreraTecnica carreraTecnica;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jornada_id")
    private Jornada jornada;
}
