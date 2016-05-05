
package util;

import java.io.Serializable;
import java.util.List;
import javax.swing.JOptionPane;
import models.Pedidos;
import org.hibernate.HibernateException;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

/**
 * @author Muchanguito
 */
public class DBConnector implements Serializable {

    static Session sessao;

    public static void iniciarSessao() {
        sessao = util.HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
    }

    
    public static Object salvarAuxiliar(Object o) {
        int i = 0;
        try {
            try{
            try{
            sessao = util.HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            i = (int) sessao.save(o);
            sessao.getTransaction().commit();
            
           
            } catch(ConstraintViolationException a){
            JOptionPane.showMessageDialog(null,"Ja existe um objecto com esses dados! Tente novamente","Erro",JOptionPane.ERROR_MESSAGE);
            sessao.getTransaction().rollback();
            return 0;
            
        }
            }catch(PropertyValueException v){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatorios!","Atencao!",JOptionPane.WARNING_MESSAGE);
                sessao.getTransaction().rollback();
                return 0;
                
            } 
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null,"Erro ao gravar objecto"+"\n"+e);
            sessao.getTransaction().rollback();
            e.printStackTrace();
            return 0;
       
        }
        return o;
    }
    
    
    public static Object salvar(Object o) {
        int i = 0;
        try {
            try{
            try{
            sessao = util.HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            i = (int) sessao.save(o);
            sessao.getTransaction().commit();
            JOptionPane.showMessageDialog(null,"Gravado com sucesso!");
           
            } catch(ConstraintViolationException a){
            JOptionPane.showMessageDialog(null,"Ja existe um objecto com esses dados! Tente novamente","Erro",JOptionPane.ERROR_MESSAGE);
            sessao.getTransaction().rollback();
            return 0;
            
        }
            }catch(PropertyValueException v){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatorios!","Atencao!",JOptionPane.WARNING_MESSAGE);
                sessao.getTransaction().rollback();
                return 0;
                
            } 
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null,"Erro ao gravar objecto"+"\n"+e);
            sessao.getTransaction().rollback();
            e.printStackTrace();
            return 0;
       
        }
        return o;
    }
 
     public static Object salvarPedido(Pedidos p) {
           sessao= HibernateUtil.getSessionFactory().getCurrentSession();
        sessao.beginTransaction();
        sessao.save(p);
        sessao.getTransaction().commit();
        JOptionPane.showMessageDialog(null, "Bem gravado");
        return p;
    }
    
    public static Object buscar(Class tipo, int id) throws HibernateException {

        Object o = null;
        
        try {
            sessao = util.HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            o = sessao.get(tipo, id);
           sessao.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Nao e possivel buscar este objecto "+e, "ERRO", 1);
            sessao.getTransaction().rollback();
            return 0;
        }
        return o;
    }


    public static void apagarAuxiliar(Class tipo, int id) {
            
        try {
            sessao = util.HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            sessao.delete(sessao.get(tipo, id));
            sessao.getTransaction().commit();
           
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Nao e possivel remover este objecto"+e, "ERRO", 1);
            sessao.getTransaction().rollback();
            return;
        }
            
    }
    
    
    public static void apagar(Class tipo, int id) {
            
        try {
            sessao = util.HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            sessao.delete(sessao.get(tipo, id));
            sessao.getTransaction().commit();
            JOptionPane.showMessageDialog(null,"Removido com sucesso!");
           
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Nao e possivel remover este objecto"+e, "ERRO", 1);
            sessao.getTransaction().rollback();
            return;
        }
            
    }

    
    public static void actualizarAuxiliar(Object o) throws HibernateException {
        try {
            sessao = util.HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            sessao.update(o);
            sessao.getTransaction().commit();
            
        } catch (HibernateException w) {
            JOptionPane.showMessageDialog(null, "Nao e possivel actualizar este objecto"+w, "ERRO", 1);
            sessao.getTransaction().rollback();
            return;
        }

    }
    
    public static void actualizar(Object o) throws HibernateException {
        try {
            sessao = util.HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            sessao.update(o);
            sessao.getTransaction().commit();
            JOptionPane.showMessageDialog(null,"Actualizado com sucesso!");
        } catch (HibernateException w) {
            JOptionPane.showMessageDialog(null, "Nao e possivel actualizar este objecto"+w, "ERRO", 1);
            sessao.getTransaction().rollback();
            return;
        }

    }
  

    public static List buscarTodos(Class tipo) throws HibernateException {
        List lista = null;
      //  sessao.getTransaction().rollback();
        try {
            sessao = util.HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            lista = sessao.createCriteria(tipo).list();
            sessao.getTransaction().rollback();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Hibernate: Erro ao carregar lista", "ERRO", JOptionPane.ERROR_MESSAGE);
            sessao.getTransaction().rollback();
        }

        return lista;
    }

    public static void fecharSessao() {
        sessao = util.HibernateUtil.getSessionFactory().getCurrentSession();
        sessao.beginTransaction();
        sessao.close();
    }
}
