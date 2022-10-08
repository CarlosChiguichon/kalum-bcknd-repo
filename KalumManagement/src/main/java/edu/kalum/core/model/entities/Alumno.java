package edu.kalum.core.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {
    @Id
    @Column(name = "carne")
    public String carne;
    @NotEmpty(message = "El campo apellidos no puede estar vacio")
    @NotNull(message = "El valor del campo apellido no es valido")
    @Column(name = "apellidos")
    public String apellidos;
    @NotEmpty(message = "El campo nombres no puede estar vacio")
    @NotNull(message = "El valor del campo nombres no es valido")
    @Column(name = "nombres")
    public String nombres;
    @NotEmpty(message = "El campo direccion no puede estar vacio")
    @NotNull(message = "El valor del campo direccion no es valido")
    @Column(name = "direccion")
    public String direccion;
    @NotEmpty(message = "El campo telefono no puede estar vacio")
    @NotNull(message = "El valor del campo telefono no es valido")
    @Column(name = "telefono")
    public String telefono;
    @NotEmpty(message = "El campo email no puede estar vacio")
    @NotNull(message = "El valor del campo email no es valido")
    @Column(name = "email")
    public String email;
}
