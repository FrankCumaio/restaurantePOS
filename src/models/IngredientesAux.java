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

/**
 * IngredientesAux generated by hbm2java
 */
@Entity
@Table(name="ingredientes_aux"
    ,catalog="sgrestaurante"
)
public class IngredientesAux  implements java.io.Serializable {


     private Integer codigo;
     private int id;
     private String produto;
     private double quantidade;
     private double preco;
     private Set<Pratos> pratoses = new HashSet<Pratos>(0);

    public IngredientesAux() {
    }

	
    public IngredientesAux(int id, String produto, double quantidade, double preco) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    public IngredientesAux(int id, String produto, double quantidade, double preco, Set<Pratos> pratoses) {
       this.id = id;
       this.produto = produto;
       this.quantidade = quantidade;
       this.preco = preco;
       this.pratoses = pratoses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="codigo", unique=true, nullable=false)
    public Integer getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    
    @Column(name="ID", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="produto", nullable=false, length=45)
    public String getProduto() {
        return this.produto;
    }
    
    public void setProduto(String produto) {
        this.produto = produto;
    }

    
    @Column(name="quantidade", nullable=false, precision=22, scale=0)
    public double getQuantidade() {
        return this.quantidade;
    }
    
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    
    @Column(name="preco", nullable=false, precision=22, scale=0)
    public double getPreco() {
        return this.preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="ingredientesAux")
    public Set<Pratos> getPratoses() {
        return this.pratoses;
    }
    
    public void setPratoses(Set<Pratos> pratoses) {
        this.pratoses = pratoses;
    }




}


