package models;
// Generated Sep 23, 2015 11:22:21 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Formaspagamento generated by hbm2java
 */
@Entity
@Table(name="formaspagamento"
    ,catalog="sgrestaurante"
    , uniqueConstraints = @UniqueConstraint(columnNames="Tipo") 
)
public class Formaspagamento  implements java.io.Serializable {


     private Integer idFormasPagamento;
     private String tipo;
     private double taxaDeJuros;
     private Set<Vendas> vendases = new HashSet<Vendas>(0);

    public Formaspagamento() {
    }

	
    public Formaspagamento(String tipo, double taxaDeJuros) {
        this.tipo = tipo;
        this.taxaDeJuros = taxaDeJuros;
    }
    public Formaspagamento(String tipo, double taxaDeJuros, Set<Vendas> vendases) {
       this.tipo = tipo;
       this.taxaDeJuros = taxaDeJuros;
       this.vendases = vendases;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idFormasPagamento", unique=true, nullable=false)
    public Integer getIdFormasPagamento() {
        return this.idFormasPagamento;
    }
    
    public void setIdFormasPagamento(Integer idFormasPagamento) {
        this.idFormasPagamento = idFormasPagamento;
    }

    
    @Column(name="Tipo", unique=true, nullable=false, length=45)
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    @Column(name="TaxaDeJuros", nullable=false, precision=22, scale=0)
    public double getTaxaDeJuros() {
        return this.taxaDeJuros;
    }
    
    public void setTaxaDeJuros(double taxaDeJuros) {
        this.taxaDeJuros = taxaDeJuros;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="formaspagamento")
    public Set<Vendas> getVendases() {
        return this.vendases;
    }
    
    public void setVendases(Set<Vendas> vendases) {
        this.vendases = vendases;
    }




}

