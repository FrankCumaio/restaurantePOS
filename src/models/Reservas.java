package models;
// Generated Sep 23, 2015 11:22:21 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Reservas generated by hbm2java
 */
@Entity
@Table(name="reservas"
    ,catalog="sgrestaurante"
    , uniqueConstraints = {@UniqueConstraint(columnNames="celular"), @UniqueConstraint(columnNames="numeroIdentificacao"), @UniqueConstraint(columnNames="telefone")} 
)
public class Reservas  implements java.io.Serializable {


     private Integer idReservas;
     private Cliente cliente;
     private String nomeCliente;
     private String telefone;
     private String celular;
     private String tipoIdentificacao;
     private String numeroIdentificacao;
     private Date data;
     private String horarioInicial;
     private String horarioFinal;
     private int nrAcompanhantes;
     private int nrMesas;
     private String observacoes;
     private double preco;

    public Reservas() {
    }

	
    public Reservas(String nomeCliente, String telefone, String celular, String tipoIdentificacao, String numeroIdentificacao, Date data, String horarioInicial, String horarioFinal, int nrAcompanhantes, int nrMesas, double preco) {
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.celular = celular;
        this.tipoIdentificacao = tipoIdentificacao;
        this.numeroIdentificacao = numeroIdentificacao;
        this.data = data;
        this.horarioInicial = horarioInicial;
        this.horarioFinal = horarioFinal;
        this.nrAcompanhantes = nrAcompanhantes;
        this.nrMesas = nrMesas;
        this.preco = preco;
    }
    public Reservas(Cliente cliente, String nomeCliente, String telefone, String celular, String tipoIdentificacao, String numeroIdentificacao, Date data, String horarioInicial, String horarioFinal, int nrAcompanhantes, int nrMesas, String observacoes, double preco) {
       this.cliente = cliente;
       this.nomeCliente = nomeCliente;
       this.telefone = telefone;
       this.celular = celular;
       this.tipoIdentificacao = tipoIdentificacao;
       this.numeroIdentificacao = numeroIdentificacao;
       this.data = data;
       this.horarioInicial = horarioInicial;
       this.horarioFinal = horarioFinal;
       this.nrAcompanhantes = nrAcompanhantes;
       this.nrMesas = nrMesas;
       this.observacoes = observacoes;
       this.preco = preco;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idReservas", unique=true, nullable=false)
    public Integer getIdReservas() {
        return this.idReservas;
    }
    
    public void setIdReservas(Integer idReservas) {
        this.idReservas = idReservas;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Cliente_idCliente")
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    @Column(name="nomeCliente", nullable=false)
    public String getNomeCliente() {
        return this.nomeCliente;
    }
    
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    
    @Column(name="telefone", unique=true, nullable=false, length=85)
    public String getTelefone() {
        return this.telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    @Column(name="celular", unique=true, nullable=false, length=45)
    public String getCelular() {
        return this.celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }

    
    @Column(name="tipoIdentificacao", nullable=false, length=45)
    public String getTipoIdentificacao() {
        return this.tipoIdentificacao;
    }
    
    public void setTipoIdentificacao(String tipoIdentificacao) {
        this.tipoIdentificacao = tipoIdentificacao;
    }

    
    @Column(name="numeroIdentificacao", unique=true, nullable=false, length=45)
    public String getNumeroIdentificacao() {
        return this.numeroIdentificacao;
    }
    
    public void setNumeroIdentificacao(String numeroIdentificacao) {
        this.numeroIdentificacao = numeroIdentificacao;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="data", nullable=false, length=10)
    public Date getData() {
        return this.data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }

    
    @Column(name="horarioInicial", nullable=false, length=45)
    public String getHorarioInicial() {
        return this.horarioInicial;
    }
    
    public void setHorarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    
    @Column(name="horarioFinal", nullable=false, length=45)
    public String getHorarioFinal() {
        return this.horarioFinal;
    }
    
    public void setHorarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    
    @Column(name="nrAcompanhantes", nullable=false)
    public int getNrAcompanhantes() {
        return this.nrAcompanhantes;
    }
    
    public void setNrAcompanhantes(int nrAcompanhantes) {
        this.nrAcompanhantes = nrAcompanhantes;
    }

    
    @Column(name="nrMesas", nullable=false)
    public int getNrMesas() {
        return this.nrMesas;
    }
    
    public void setNrMesas(int nrMesas) {
        this.nrMesas = nrMesas;
    }

    
    @Column(name="observacoes")
    public String getObservacoes() {
        return this.observacoes;
    }
    
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    
    @Column(name="preco", nullable=false, precision=22, scale=0)
    public double getPreco() {
        return this.preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }




}


