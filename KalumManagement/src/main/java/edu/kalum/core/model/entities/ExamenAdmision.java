package edu.kalum.core.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "examen_admision")
public class ExamenAdmision implements Serializable {
    @Id
    @Column(name = "examen_id")
    public String examenId;

    @NotEmpty(message = "El campo fecha de examen no puede estar vacio")
    @NotNull(message = "El valor en fecha de examen no es valido")
    @Column(name = "fecha_examen")
    public String fechaExamen;

    @OneToMany(mappedBy = "examenAdmision", fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    public List<Aspirante> aspirantes;
}
