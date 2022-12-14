package edu.kalum.core.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cargo")
public class Cargo implements Serializable {
    @Id
    @Column(name = "cargo_id")
    public String cargoId;
    @Column(name = "descipcion")
    public String descripcion;
    @Column(name = "prefijo")
    public String prefijo;
    @Column(name = "monto")
    public String monto;
    @Column(name = "genera_mora")
    public String generaMora;
    @Column(name = "porcentaje_mora")
    public String porcentajeMora;
}
