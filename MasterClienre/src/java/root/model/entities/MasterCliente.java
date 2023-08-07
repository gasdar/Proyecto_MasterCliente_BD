/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eliulson
 */
@Entity
@Table(name = "master_clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterCliente.findAll", query = "SELECT m FROM MasterCliente m")
    , @NamedQuery(name = "MasterCliente.findByCliIdentificacion", query = "SELECT m FROM MasterCliente m WHERE m.cliIdentificacion = :cliIdentificacion")
    , @NamedQuery(name = "MasterCliente.findByCliNombres", query = "SELECT m FROM MasterCliente m WHERE m.cliNombres = :cliNombres")
    , @NamedQuery(name = "MasterCliente.findByCliApellido1", query = "SELECT m FROM MasterCliente m WHERE m.cliApellido1 = :cliApellido1")
    , @NamedQuery(name = "MasterCliente.findByCliApellido2", query = "SELECT m FROM MasterCliente m WHERE m.cliApellido2 = :cliApellido2")
    , @NamedQuery(name = "MasterCliente.findByCliFechaNacimiento", query = "SELECT m FROM MasterCliente m WHERE m.cliFechaNacimiento = :cliFechaNacimiento")
    , @NamedQuery(name = "MasterCliente.findByCliEmail", query = "SELECT m FROM MasterCliente m WHERE m.cliEmail = :cliEmail")
    , @NamedQuery(name = "MasterCliente.findByCliDir", query = "SELECT m FROM MasterCliente m WHERE m.cliDir = :cliDir")
    , @NamedQuery(name = "MasterCliente.findByCliFono", query = "SELECT m FROM MasterCliente m WHERE m.cliFono = :cliFono")})
public class MasterCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "cli_identificacion")
    private String cliIdentificacion;
    @Size(max = 2147483647)
    @Column(name = "cli_nombres")
    private String cliNombres;
    @Size(max = 2147483647)
    @Column(name = "cli_apellido1")
    private String cliApellido1;
    @Size(max = 2147483647)
    @Column(name = "cli_apellido2")
    private String cliApellido2;
    @Column(name = "cli_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date cliFechaNacimiento;
    @Size(max = 2147483647)
    @Column(name = "cli_email")
    private String cliEmail;
    @Size(max = 2147483647)
    @Column(name = "cli_dir")
    private String cliDir;
    @Size(max = 2147483647)
    @Column(name = "cli_fono")
    private String cliFono;

    public MasterCliente() {
    }

    public MasterCliente(String cliIdentificacion) {
        this.cliIdentificacion = cliIdentificacion;
    }

    public String getCliIdentificacion() {
        return cliIdentificacion;
    }

    public void setCliIdentificacion(String cliIdentificacion) {
        this.cliIdentificacion = cliIdentificacion;
    }

    public String getCliNombres() {
        return cliNombres;
    }

    public void setCliNombres(String cliNombres) {
        this.cliNombres = cliNombres;
    }

    public String getCliApellido1() {
        return cliApellido1;
    }

    public void setCliApellido1(String cliApellido1) {
        this.cliApellido1 = cliApellido1;
    }

    public String getCliApellido2() {
        return cliApellido2;
    }

    public void setCliApellido2(String cliApellido2) {
        this.cliApellido2 = cliApellido2;
    }

    public Date getCliFechaNacimiento() {
        return cliFechaNacimiento;
    }

    public void setCliFechaNacimiento(Date cliFechaNacimiento) {
        this.cliFechaNacimiento = cliFechaNacimiento;
    }

    public String getCliEmail() {
        return cliEmail;
    }

    public void setCliEmail(String cliEmail) {
        this.cliEmail = cliEmail;
    }

    public String getCliDir() {
        return cliDir;
    }

    public void setCliDir(String cliDir) {
        this.cliDir = cliDir;
    }

    public String getCliFono() {
        return cliFono;
    }

    public void setCliFono(String cliFono) {
        this.cliFono = cliFono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliIdentificacion != null ? cliIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterCliente)) {
            return false;
        }
        MasterCliente other = (MasterCliente) object;
        if ((this.cliIdentificacion == null && other.cliIdentificacion != null) || (this.cliIdentificacion != null && !this.cliIdentificacion.equals(other.cliIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "root.model.entities.MasterCliente[ cliIdentificacion=" + cliIdentificacion + " ]";
    }
    
}
