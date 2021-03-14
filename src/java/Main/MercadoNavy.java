/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Apoio.ConexaoBD;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class MercadoNavy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (ConexaoBD.getInstance().getConnection() != null) {
            // log.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao abrir conexão!");
        }
        
        
        //Teste github

    }
}
