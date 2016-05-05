/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantePOS;

/**
 *
 * @author Frank Cumaio
 */
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import util.DBConnector;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;
import javax.imageio.spi.ServiceRegistry;
import models.Categoriapratos;
import models.Categoriaproduto;
import models.Produtos;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import util.HibernateUtil;

public class Demo implements ActionListener{
    private JPanel Cafetaria = new javax.swing.JPanel();
    private JPanel Bebidas = new javax.swing.JPanel();    
    private JTable jtVendas = new javax.swing.JTable();
    ArrayList<String> produtos = new ArrayList<>();
    ArrayList<Double> bebidasPreco = new ArrayList<>();
    ArrayList<Produtos> alguns = null;
    private String teste ="";
     private JButton bt; 
      static Session sessao;
      private static SessionFactory factory; 
      private String nomeCategoria ="";
      int idCategoria;
      JXTabbedPane tabbedPane = new JXTabbedPane(JTabbedPane.LEFT);
      Session session = util.HibernateUtil.getSessionFactory().openSession();
      
    
     Vector<Vector<Object>> data = new Vector<Vector<Object>>();
     Vector<String> columnNames = new Vector<String>();
public Demo() throws Exception{
    // initComponents();
        DBConnector.iniciarSessao();
        produtos();
        //teste();
        categoria();
}

public String categoria(){
    Session session = null;
    Transaction tx = null;
    
    try {
        session = util.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        nomeCategoria = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
        List cp = (List) session.createQuery("from Categoriapratos where nome like '"+nomeCategoria+"'").list();
        Iterator it = cp.iterator();
//       
        while(it.hasNext()){
        Categoriapratos c = (Categoriapratos)it.next();
        idCategoria = c.getIdCategoriaPratos();
     
        }
        
        try {
        session = util.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //JOptionPane.showMessageDialog(null, idCategoria);
        List listaUsuarios = session.createQuery("from Produtos where 	CategoriaProduto_idCategoriaProduto = '"+idCategoria+"'").list();
        Iterator st = listaUsuarios.iterator();
        
        while(st.hasNext()){
        Produtos p = (Produtos)st.next();
        produtos.add(p.getNomeProduto());
        bebidasPreco.add(p.getPreco());
      }
       
    }
    catch(Exception e) {
      if(tx != null)
        tx.rollback();
      System.out.println(e.getMessage());
    }
        
    }
    catch(Exception e) {
      if(tx != null)
        tx.rollback();
      System.out.println(e.getMessage());
    }
        return null;
}


public void produtos() {     
    Session session = null;
    Transaction tx = null;
    try {
        session = util.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //JOptionPane.showMessageDialog(null, idCategoria);
        List listaUsuarios = session.createQuery("from Produtos where 	CategoriaProduto_idCategoriaProduto = '"+idCategoria+"'").list();
        Iterator it = listaUsuarios.iterator();
        
        while(it.hasNext()){
        Produtos p = (Produtos)it.next();
        produtos.add(p.getNomeProduto());
        bebidasPreco.add(p.getPreco());
      }
    }
    catch(Exception e) {
      if(tx != null)
        tx.rollback();
      System.out.println(e.getMessage());
    }
  }
                             

     public void teste() throws Exception{
 
        Cafetaria.setLayout(new MigLayout());
        
        Bebidas.setLayout(new MigLayout());
        
        // lista();
//        if (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()).equals("Cafetaria")){
           for(int i=0;i<produtos.size();i++){
               columnNames.add("#");
            columnNames.add("Quant.");
            columnNames.add("Design.");
            columnNames.add("Preco");
            
          bt=new JButton(produtos.get(i));
          bt.setName("bt"+produtos.get(i));
          bt.setPreferredSize(new Dimension(120, 50));
          Cafetaria.add(bt, "cell 0 "+i);
          tabbedPane.setComponentAt(0, Cafetaria);
          bt.addActionListener(this); 
        }
        
//           else{
//          for(int j=0;j<produtos.size();j++){
//                columnNames.add("#");
//            columnNames.add("Quant.");
//            columnNames.add("Design.");
//            columnNames.add("Preco");
//            
//          bt=new JButton(produtos.get(j));
//          bt.setName("bt"+produtos.get(j));
//          bt.setPreferredSize(new Dimension(120, 50));
//          Bebidas.add(bt, "cell 0 "+j);
//          bt.addActionListener(this);
//          }
        }
       


    private void createAndShowGUI() {

    tabbedPane.setPreferredSize(new Dimension(200,500));
    AbstractTabRenderer renderer = (AbstractTabRenderer)tabbedPane.getTabRenderer();
    renderer.setPrototypeText("This text is a prototype");
    renderer.setHorizontalTextAlignment(SwingConstants.LEADING);
    Transaction tx = null;
    
    try {
    session.beginTransaction();
    List categorias = session.createQuery("from Categoriapratos").list();
    Iterator it = categorias.iterator();

    while(it.hasNext()){
    Categoriapratos cp = (Categoriapratos)it.next();
    tabbedPane.addTab(cp.getNome(), createEmptyPanel());
    }
    }
    catch(Exception e) {
    if(tx != null)
    tx.rollback();
    System.out.println(e.getMessage());
    }
//        tabbedPane.addTab("Cafetaria", Cafetaria);
//        tabbedPane.addTab("Bebidas", new ImageIcon(Login.class.getResource("/Icons/bebidas.png")), Bebidas, "Bebidas");
//        tabbedPane.addTab("Salgados & Folhados", new ImageIcon(Login.class.getResource("/Icons/aperitivos.png")), createEmptyPanel(), "Aperitivos");
//        tabbedPane.addTab("Soupas", new ImageIcon(Login.class.getResource("/Icons/soupas.png")), createEmptyPanel(), "Aperitivos");
//        tabbedPane.addTab("Sandwiches & Tostas", new ImageIcon(Login.class.getResource("/Icons/sandwich.png")), createEmptyPanel(), "Aperitivos");
//        tabbedPane.addTab("Pequeno Almoco", new ImageIcon(Login.class.getResource("/Icons/matabicho.png")), createEmptyPanel(), "Aperitivos");
//        tabbedPane.addTab("Principais", new ImageIcon(Login.class.getResource("/Icons/principal.png")), createEmptyPanel(), "Aperitivos");
//        tabbedPane.addTab("Hamburgers & Pregos", new ImageIcon(Login.class.getResource("/Icons/hamburger.png")), createEmptyPanel(), "Aperitivos");
//        tabbedPane.addTab("Pizzas", new ImageIcon(Login.class.getResource("/Icons/pizza.png")), createEmptyPanel(), "Aperitivos");
//        tabbedPane.addTab("Extras", new ImageIcon(Login.class.getResource("/Icons/extras.png")), createEmptyPanel(), "Aperitivos");
//        tabbedPane.addTab("Pastelaria & Sobremesas", new ImageIcon(Login.class.getResource("/Icons/sweet.png")), createEmptyPanel(), "Aperitivos");
//        tabbedPane.addTab("Gelados & Batidos", new ImageIcon(Login.class.getResource("/Icons/gelados.png")), createEmptyPanel(), "Aperitivos");
        JFrame frame = new JFrame("Venda//s");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(tabbedPane);
        frame.getContentPane().setLayout(new MigLayout());
        
        
        JPanel panelTab = new JPanel();
        panelTab.setMinimumSize(new Dimension(800, 550));
        panelTab.setMaximumSize(new Dimension(800, 550));
       //panelTab.setPreferredSize(new Dimension(500, 500));
        panelTab.setLayout(new MigLayout());
        //panelTab.setPreferredSize(new Dimension(500, 500));
        
        frame.getContentPane().add(panelTab, "cell 0 0,alignx left");
        panelTab.add(tabbedPane);
       
       
        
        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(280, 550));
        panel.setPreferredSize(new Dimension(100, 10));
        //panel.setPreferredSize(new Dimension(50, 50));
        panel.setLayout(new MigLayout());
        JLabel jLabel1 = new JLabel("Frank salvador ");
        JLabel jLabel2 = new JLabel("Mesa");
        JLabel jLabel3 = new JLabel("02");
        JButton jbOpcoes = new JButton("Opcoes");
        JButton jbEnviar = new JButton("Enviar C.");
        jbEnviar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jbEnviar.setBackground(Color.RED);
        JButton jbCancelar = new JButton("Cancelar");
        jbCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JButton jbPagar = new JButton("PAGAR");
        jbPagar.setFont(new Font("Tahoma", Font.BOLD, 14));
        jbPagar.setBackground(Color.GREEN);
        jbPagar.setActionCommand("");
        JScrollPane scrollTabela = new javax.swing.JScrollPane();
        scrollTabela.setMinimumSize(new Dimension(50, 22));
        jtVendas.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"New column", "New column", "New column", "New column"
        	}
        ));
        scrollTabela.setViewportView(jtVendas);
        panel.add(jLabel1, "cell 0 0,alignx left,aligny bottom");
        panel.add(jLabel2, "cell 2 0,alignx right,aligny top");
        panel.add(jLabel3, "cell 4 0,alignx left,aligny bottom");
        panel.add(scrollTabela, "cell 0 1 5 1,alignx left,growy");
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(1, 1));
        panel.add(scrollPane, "cell 0 2 5 1");
        
        JPanel panel_1 = new JPanel();
        scrollPane.setViewportView(panel_1);
        panel_1.setLayout(new MigLayout("", "[49px][]", "[10px]"));
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        panel_1.add(btnNewButton, "cell 0 0,alignx left,aligny top");
        
        JButton btnNewButton_1 = new JButton("New button");
        panel_1.add(btnNewButton_1, "cell 1 0");
        panel.add(jbOpcoes, "cell 0 4,grow");
        panel.add(jbEnviar, "cell 0 3,grow");
        panel.add(jbCancelar, "cell 2 4 3 1,alignx right");
        panel.add(jbPagar, "cell 4 3,grow");
        //panel.setLayout(new MigLayout("", "[2px][46px][300px]", "[32px][]"));
         frame.getContentPane().add(panel, "cell 1 0,alignx left,growy");
        frame.setPreferredSize(new Dimension(1100, 600));
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
       
        tabbedPane.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                produtos.clear();
                Bebidas.removeAll();
                Cafetaria.removeAll();
                categoria();
                try {
                    teste();
                } catch (Exception ex) {
                    Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        JOptionPane.showMessageDialog(null,tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
        

    }
    

    private JPanel createEmptyPanel() {
        JPanel dummyPanel = new JPanel() {

            @Override
            public Dimension getPreferredSize() {
                return isPreferredSizeSet() ?
                            super.getPreferredSize() : new Dimension(400, 300);
            }

        };
        return dummyPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Demo().createAndShowGUI();
                } catch (Exception ex) {
                    Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
                JButton alvo=(JButton)e.getSource(); 
                int pos = 0;
                Vector<Object> vectorBebidas = new Vector<Object>();
                for (int i=0;i<produtos.size();i++){
                   if(produtos.get(i).equals(alvo.getText())){
            vectorBebidas.add(1);
	    vectorBebidas.add(1);
	    vectorBebidas.add(alvo.getText());
            vectorBebidas.add(bebidasPreco.get(i));
	    data.add(vectorBebidas);
	     jtVendas.setModel(new DefaultTableModel(data,columnNames));
                   }
                 
                }		
    }
    
    

    class JXTabbedPane extends JTabbedPane {

        private ITabRenderer tabRenderer = new DefaultTabRenderer();

        public JXTabbedPane() {
            super();
        }

        public JXTabbedPane(int tabPlacement) {
            super(tabPlacement);
        }

        public JXTabbedPane(int tabPlacement, int tabLayoutPolicy) {
            super(tabPlacement, tabLayoutPolicy);
        }

        public ITabRenderer getTabRenderer() {
            return tabRenderer;
        }

        public void setTabRenderer(ITabRenderer tabRenderer) {
            this.tabRenderer = tabRenderer;
        }

        @Override
        public void addTab(String title, Component component) {
            this.addTab(title, null, component, null);
        }

        @Override
        public void addTab(String title, Icon icon, Component component) {
            this.addTab(title, icon, component, null);
        }

        @Override
        public void addTab(String title, Icon icon, Component component, String tip) {
            super.addTab(title, icon, component, tip);
            int tabIndex = getTabCount() - 1;
            Component tab = tabRenderer.getTabRendererComponent(this, title, icon, tabIndex);
            super.setTabComponentAt(tabIndex, tab);
        }
    }

    interface ITabRenderer {

        public Component getTabRendererComponent(JTabbedPane tabbedPane, String text, Icon icon, int tabIndex);

    }

    abstract class AbstractTabRenderer implements ITabRenderer {

        private String prototypeText = "";
        private Icon prototypeIcon = UIManager.getIcon("OptionPane.informationIcon");
        private int horizontalTextAlignment = SwingConstants.CENTER;
        private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

        public AbstractTabRenderer() {
            super();
        }

        public void setPrototypeText(String text) {
            String oldText = this.prototypeText;
            this.prototypeText = text;
            firePropertyChange("prototypeText", oldText, text);
        }

        public String getPrototypeText() {
            return prototypeText;
        }

        public Icon getPrototypeIcon() {
            return prototypeIcon;
        }

        public void setPrototypeIcon(Icon icon) {
            Icon oldIcon = this.prototypeIcon;
            this.prototypeIcon = icon;
            firePropertyChange("prototypeIcon", oldIcon, icon);
        }

        public int getHorizontalTextAlignment() {
            return horizontalTextAlignment;
        }

        public void setHorizontalTextAlignment(int horizontalTextAlignment) {
            this.horizontalTextAlignment = horizontalTextAlignment;
        }

        public PropertyChangeListener[] getPropertyChangeListeners() {
            return propertyChangeSupport.getPropertyChangeListeners();
        }

        public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
            return propertyChangeSupport.getPropertyChangeListeners(propertyName);
        }

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            propertyChangeSupport.addPropertyChangeListener(listener);
        }

        public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
            propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
        }

        protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
            PropertyChangeListener[] listeners = getPropertyChangeListeners();
            for (int i = listeners.length - 1; i >= 0; i--) {
                listeners[i].propertyChange(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
            }
        }
    }

    class DefaultTabRenderer extends AbstractTabRenderer implements PropertyChangeListener {

        private Component prototypeComponent;

        public DefaultTabRenderer() {
            super();
            prototypeComponent = generateRendererComponent(getPrototypeText(), getPrototypeIcon(), getHorizontalTextAlignment());
            addPropertyChangeListener(this);
        }

        private Component generateRendererComponent(String text, Icon icon, int horizontalTabTextAlignmen) {
            JPanel rendererComponent = new JPanel(new GridBagLayout());
            rendererComponent.setOpaque(false);

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(2, 4, 2, 4);
            c.fill = GridBagConstraints.HORIZONTAL;
            rendererComponent.add(new JLabel(icon), c);

            c.gridx = 1;
            c.weightx = 1;
            rendererComponent.add(new JLabel(text, horizontalTabTextAlignmen), c);

            return rendererComponent;
        }

        @Override
        public Component getTabRendererComponent(JTabbedPane tabbedPane, String text, Icon icon, int tabIndex) {
            Component rendererComponent = generateRendererComponent(text, icon, getHorizontalTextAlignment());
            int prototypeWidth = prototypeComponent.getPreferredSize().width;
            int prototypeHeight = prototypeComponent.getPreferredSize().height;
            rendererComponent.setPreferredSize(new Dimension(prototypeWidth, prototypeHeight));
            return rendererComponent;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            String propertyName = evt.getPropertyName();
            if ("prototypeText".equals(propertyName) || "prototypeIcon".equals(propertyName)) {
                this.prototypeComponent = generateRendererComponent(getPrototypeText(), getPrototypeIcon(), getHorizontalTextAlignment());
            }
        }
    }
}
    
    
    

