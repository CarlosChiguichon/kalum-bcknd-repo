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
@Table(name = "carrera_tecnica")
@NamedQueries({ @NamedQuery(name = "carreraTecnica.findAll",query = "select ct from CarreraTecnica ct order by ct.carreraTecnica")})
public class CarreraTecnica implements Serializable {
    @Id
    @Column(name = "carrera_id")
    private String carreraId;
    @Column(name = "carrera_tecnica")
    private String carreraTecnica;
    @XmlTransient
    @OneToMany(mappedBy = "carreraTecnica", fetch = FetchType.EAGER)
    private List<Aspirante> aspirantes;
    @Override
    public String toString(){
        return "CarreraTecnica {" +this.carreraId + ", " + this.carreraTecnica + "}";
    }
}
