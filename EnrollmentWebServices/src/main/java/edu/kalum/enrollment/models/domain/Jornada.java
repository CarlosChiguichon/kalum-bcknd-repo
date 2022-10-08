package edu.kalum.enrollment.models.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Table(name = "jornada")
@NamedQueries({ @NamedQuery(name = "jornada.findAll",query = "select j from Jornada j order by j.jornada")})
public class Jornada implements Serializable {
    @Id
    @Column(name = "jornada_id")
    private String jornadaId;
    @Column(name = "jornada")
    private String jornada;
    @XmlTransient
    @OneToMany(mappedBy = "jornada", fetch = FetchType.EAGER)
    private List<Aspirante> aspirantes;
    @Column(name = "descripcion")
    private String description;
    public String toString() { return "jornada {" + this.jornadaId + ", " + this.jornada + "}";}
}
