/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantePOS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import models.Categoriapratos;
import models.Categoriaproduto;
import models.Fornecedores;
import models.Ingredientes;
import models.IngredientesAux;
import models.Pratos;
import models.Produtos;
import net.proteanit.sql.DbUtils;
import org.hibernate.Session;
import util.DBConnector;

/**
 *
 * @author Mr.Belton
 */
public class JIFRegistroDePratos extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFCardapio
     */
     ArrayList<Categoriapratos> cPratos = null;
      ArrayList<Categoriaproduto> cProdutos = null; 
      ArrayList<Produtos> listaProdutos = null;
      ArrayList<String> produtos = new ArrayList<>();
     Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    Vector<String> columnNames = new Vector<String>();
       String[] ingrediente = new String[3];
     Pratos pratos = new Pratos();
     Ingredientes ingredientes;
     boolean update = false;

      static Session sessao; 
      private PreparedStatement pstm = null;
    private Connection con = null;
    private ResultSet rs = null;
    private Statement st;
     double preco =0;
     int lastID=0;
    
    public JIFRegistroDePratos() {
         super("Cardapio");
         try {
             initComponents();
             conectar();
             setClosable(true);
             setMaximizable(false);
             listarIngredientes();
             listarPratos();
             txtPreco.setText(0+"");
             jComboCategoriaListar.addItem("Todos");
             tableIngredientes.removeAll();
             
             String sql = "SELECT * FROM ingredientes";
             rs=st.executeQuery(sql);
             while(rs.next()){
                 lastID = rs.getInt("ID");
             }
             
             cProdutos = (ArrayList<Categoriaproduto>) DBConnector.buscarTodos(Categoriaproduto.class);
             cPratos = (ArrayList<Categoriapratos>) DBConnector.buscarTodos(Categoriapratos.class);
             listaProdutos = (ArrayList<Produtos>) DBConnector.buscarTodos(Produtos.class);
             
             listaProdutos = (ArrayList<Produtos>) DBConnector.buscarTodos(Produtos.class);
             for(int i=0; i<listaProdutos.size(); i++){
                 jComboProdutos.addItem(listaProdutos.get(i).getNomeProduto());
             }
             
             cProdutos = (ArrayList<Categoriaproduto>) DBConnector.buscarTodos(Categoriaproduto.class);
             for(int i=0; i<cProdutos.size(); i++){
                 jComboCategoriaProduto.addItem(cProdutos.get(i).getNome());
             }
             
             cPratos = (ArrayList<Categoriapratos>) DBConnector.buscarTodos(Categoriapratos.class);
             for(int i=0; i<cPratos.size(); i++){
                 jComboCategoriaPrato.addItem(cPratos.get(i).getNome());
                 jComboCategoriaListar.addItem(cPratos.get(i).getNome());
             }
             
             
             
         } catch (SQLException ex) {
             Logger.getLogger(JIFRegistroDePratos.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         
    }

    
    public void conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgrestaurante","root","");
          st=con.createStatement();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
     }
    
    public void listarPratos(){
        String sql= "Select idPratos, nome, categoria, preco from pratos";
        try {
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            tablePratos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(JIFFormasDePagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarIngredientes(){
        String sql= "Select codigo, Produto, quantidade, Preco from ingredientes_aux";
        try {
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            tableIngredientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(JIFFormasDePagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public void listarIngredientesPorID(){
        
        Pratos prato = (Pratos) DBConnector.buscar(Pratos.class, (int) tablePratos.getValueAt(tablePratos.getSelectedRow(), 0));
         int id = prato.getIngredientesId();
         
        String sql= "Select codigo, Produto, quantidade, Preco from ingredientes where ID = ?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1,id);
            rs = pstm.executeQuery();
            tableIngredientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(JIFFormasDePagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void pesquisaNaTabela(){
           String sql= "Select idPratos, nome, categoria, preco from pratos where nome like ?";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, txtPesquisa.getText()+"%");
            rs = pstm.executeQuery();
            tablePratos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(JIFFornecedores.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    public void limparCampos(){
        txtNome.setText("");
        txtDescricao.setText("");
        txtPreco.setText("");
        txtQuant.setText("");
        
         try {
             PreparedStatement st =(PreparedStatement) con.prepareStatement("DELETE FROM `ingredientes_aux`");
             st.executeUpdate();
             listarIngredientes();
         } catch (SQLException ex) {
             Logger.getLogger(JIFRegistroDePratos.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboCategoriaPrato = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboCategoriaProduto = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txtQuant = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jComboProdutos = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableIngredientes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePratos = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboCategoriaListar = new javax.swing.JComboBox();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro de pratos"));

        jLabel2.setText("Nome do prato");

        jLabel5.setText("Categoria do prato");

        jComboCategoriaPrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboCategoriaPratoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos usados"));

        jLabel6.setText("Categoria");

        jComboCategoriaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboCategoriaProdutoActionPerformed(evt);
            }
        });

        jLabel4.setText("Quantidade");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "kg", "T", "l" }));

        jLabel1.setText("Produto");

        jButton1.setText("Adicionar produto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Remover produto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Limpar produtos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel7.setText("Preco inicial do prato");

        txtPreco.setForeground(new java.awt.Color(0, 153, 51));

        jLabel8.setText("MT");

        jButton8.setText("Actualizar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboCategoriaProduto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtQuant, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtPreco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboCategoriaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(txtQuant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton8))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de produtos"));

        tableIngredientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tableIngredientes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(301, 301, 301))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Descricao do prato")));

        txtDescricao.setColumns(20);
        txtDescricao.setLineWrap(true);
        txtDescricao.setRows(5);
        txtDescricao.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtDescricao);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboCategoriaPrato, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jComboCategoriaPrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de pratos"));

        tablePratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablePratos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePratosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablePratos);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setText("Gravar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Limpar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Remover");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Actualizar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel16.setText("Pesquisa por nome");

        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        jLabel3.setText("Listar por categoria do prato");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton7)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboCategoriaListar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jComboCategoriaListar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton5)
                        .addComponent(jButton6)
                        .addComponent(jButton7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         try {
             int idCategoria=0;
             String sql = "Select * from categoriapratos where nome like ?";
             pstm = con.prepareStatement(sql);
             pstm.setString(1,jComboCategoriaPrato.getSelectedItem().toString());
             rs = pstm.executeQuery();
             while(rs.next()){
                 idCategoria = rs.getInt("idCategoriaPratos");
             }
             Categoriapratos categoriaP = (Categoriapratos) DBConnector.buscar(Categoriapratos.class, idCategoria);
             
             pratos = new Pratos(categoriaP,ingredientes, null, txtNome.getText(), jComboCategoriaPrato.getSelectedItem().toString(), Double.parseDouble(txtPreco.getText()), txtDescricao.getText(),ingredientes.getId());
         
             DBConnector.salvar(pratos);
             listarPratos();
             limparCampos();
             
         } catch (SQLException ex) {
             Logger.getLogger(JIFRegistroDePratos.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        limparCampos();
        update = false;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboCategoriaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboCategoriaProdutoActionPerformed
        listaProdutos = (ArrayList<Produtos>) DBConnector.buscarTodos(Produtos.class);
        jComboProdutos.removeAllItems();
         for(int i=0; i<listaProdutos.size(); i++){
            try {
                
                ////////////////////////
                int idCategoria =0;
                String sql = "Select * from produtos where nomeProduto like ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1,listaProdutos.get(i).getNomeProduto());
                rs = pstm.executeQuery();
                while(rs.next()){
                    idCategoria = rs.getInt("CategoriaProduto_idCategoriaProduto");
                }
                Categoriaproduto categoria = (Categoriaproduto) DBConnector.buscar(Categoriaproduto.class, idCategoria);
                
                if(categoria.getNome().equals(jComboCategoriaProduto.getSelectedItem()))
                    jComboProdutos.addItem(listaProdutos.get(i).getNomeProduto());
                
            } catch (SQLException ex) {
                Logger.getLogger(JIFRegistroDePratos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
         
         
    }//GEN-LAST:event_jComboCategoriaProdutoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
         try {
             String nomeProduto = jComboProdutos.getSelectedItem().toString();
             double quantidade = Double.parseDouble(txtQuant.getText());
             
             double preco =0;
             String sql = "Select * from produtos where nomeProduto like ?";
             pstm = con.prepareStatement(sql);
             pstm.setString(1,nomeProduto);
             rs = pstm.executeQuery();
             while(rs.next()){
                 preco = rs.getDouble("Preco");
             }
             
             if(update==true){
              
              Pratos prato = (Pratos) DBConnector.buscar(Pratos.class, (int) tablePratos.getValueAt(tablePratos.getSelectedRow(), 0));   
                 
             DBConnector.salvarAuxiliar(new IngredientesAux(prato.getIngredientesId(), nomeProduto, quantidade, preco));
             ingredientes = (Ingredientes) DBConnector.salvarAuxiliar(new Ingredientes(prato.getIngredientesId(), nomeProduto, quantidade, preco));
             listarIngredientesPorID();
             }
             else{
                 
             DBConnector.salvarAuxiliar(new IngredientesAux(lastID+1, nomeProduto, quantidade, preco));
             ingredientes = (Ingredientes) DBConnector.salvarAuxiliar(new Ingredientes(lastID+1, nomeProduto, quantidade, preco));
             listarIngredientes();
             
             }
             
             txtPreco.setText((Double.parseDouble(txtPreco.getText())+preco)+"");
             
         } catch (SQLException ex) {
             Logger.getLogger(JIFRegistroDePratos.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(tableIngredientes.getSelectedRow() == -1){
           JOptionPane.showMessageDialog(null,"Seleccione o ingrediente a ser removido!","Atencao",JOptionPane.WARNING_MESSAGE);
           return;
       }else{
            if(update==true){
            DBConnector.apagarAuxiliar(Ingredientes.class, (int) tableIngredientes.getValueAt(tableIngredientes.getSelectedRow(), 0));
            listarIngredientesPorID();
            }else{
            DBConnector.apagarAuxiliar(IngredientesAux.class, (int) tableIngredientes.getValueAt(tableIngredientes.getSelectedRow(), 0));
            DBConnector.apagarAuxiliar(Ingredientes.class, (int) tableIngredientes.getValueAt(tableIngredientes.getSelectedRow(), 0));
            listarIngredientes();
        }
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         try {
             PreparedStatement st = (PreparedStatement) con.prepareStatement("DELETE FROM `ingredientes_aux`");
             st.executeUpdate();
             listarIngredientes();
             st = (PreparedStatement) con.prepareStatement("DELETE FROM `ingredientes` where ID = ?");
             st.setInt(1, (lastID+1));
              st.executeUpdate();
             
         } catch (SQLException ex) {
             Logger.getLogger(JIFRegistroDePratos.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       // String ingrediente = (String) model.getElementAt(jList1.getSelectedIndex());
      //  model.set(jList1.getSelectedIndex(), (jComboProdutos.getSelectedItem()+"  "+txtQuant.getText()+" "+jComboBox1.getSelectedItem()));
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jComboCategoriaPratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboCategoriaPratoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboCategoriaPratoActionPerformed

    private void tablePratosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePratosMouseClicked
    
        update = true;    
        Pratos prato = (Pratos) DBConnector.buscar(Pratos.class, (int) tablePratos.getValueAt(tablePratos.getSelectedRow(), 0));
           
            txtNome.setText(prato.getNome());
            txtDescricao.setText(prato.getDescricao());
            txtPreco.setText(prato.getPreco()+"");
            listarIngredientesPorID();
            
             
            
        
    }//GEN-LAST:event_tablePratosMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
      if(tablePratos.getSelectedRow() == -1){
           JOptionPane.showMessageDialog(null,"Seleccione o prato a ser removido!","Atencao",JOptionPane.WARNING_MESSAGE);
           return;
       }else{
            DBConnector.apagar(Pratos.class, (int) tablePratos.getValueAt(tablePratos.getSelectedRow(), 0));
            limparCampos();
            update = false;
      }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
             Pratos prato = (Pratos) DBConnector.buscar(Pratos.class, (int) tablePratos.getValueAt(tablePratos.getSelectedRow(), 0));
             
             int idCategoria=0;
             String sql = "Select * from categoriapratos where nome like ?";
             pstm = con.prepareStatement(sql);
             pstm.setString(1,jComboCategoriaPrato.getSelectedItem().toString());
             rs = pstm.executeQuery();
             while(rs.next()){
                 idCategoria = rs.getInt("idCategoriaPratos");
             }
             Categoriapratos categoriaP = (Categoriapratos) DBConnector.buscar(Categoriapratos.class, idCategoria);
             
             prato.setCategoria(jComboCategoriaPrato.getSelectedItem().toString());
             prato.setCategoriapratos(categoriaP);
             prato.setDescricao(txtDescricao.getText());
             prato.setNome(txtNome.getText());
             prato.setPreco(Double.parseDouble(txtPreco.getText()));
             
             DBConnector.actualizarAuxiliar(ingredientes);
             prato.setIngredientes(ingredientes);
             
             DBConnector.actualizar(prato);
             limparCampos();
             update = false;
         } catch (SQLException ex) {
             Logger.getLogger(JIFRegistroDePratos.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        pesquisaNaTabela();
    }//GEN-LAST:event_txtPesquisaKeyReleased

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboCategoriaListar;
    private javax.swing.JComboBox jComboCategoriaPrato;
    private javax.swing.JComboBox jComboCategoriaProduto;
    private javax.swing.JComboBox jComboProdutos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tableIngredientes;
    private javax.swing.JTable tablePratos;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtQuant;
    // End of variables declaration//GEN-END:variables
}
