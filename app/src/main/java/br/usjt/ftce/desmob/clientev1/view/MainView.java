package br.usjt.ftce.desmob.clientev1.view;

import android.content.Context;

import java.util.ArrayList;

import br.usjt.ftce.desmob.clientev1.model.Cliente;

/**
 * Created by Hygor on 03/06/2017.
 */

public interface MainView {

    void mensagem(String mensagem);

    void listarClientes(ArrayList<Cliente> lista);

    Context getContext();
}
