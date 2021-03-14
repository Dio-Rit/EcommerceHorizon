/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apoio;

import java.util.ArrayList;

/**
 *
 * @author pretto
 */
// Utiliza Generics como tipo de dado
public interface IDAO_T<T> {

    public String salvar(T o);

    public String atualizar(T o);

    public String excluir(int id);

    public T consultarId(int id);
}
