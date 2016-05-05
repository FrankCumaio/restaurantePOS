/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import models.Formaspagamento;
import models.Mesas;
import models.Reservas;
import models.Usuario;

/**
 *
 * @author Mr.Belton
 */
public class TestingHibernate {
    
    public static void main(String[] args) {
        
       // DBConnector.salvar(new (1,5,"Area de fumantes"));
       // DBConnector.salvar(new Formaspagamento("Belton",0.05));
        
        
        
       // DBConnector.salvar(new Usuario("Inercio","12345"));
        DBConnector.apagar(Reservas.class,1);
        //GenericDao.getInstancia().guardar(new Usuario("Inercio","1234"));
        
    }
    
}
