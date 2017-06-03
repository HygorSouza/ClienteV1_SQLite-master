package br.usjt.ftce.desmob.clientev1.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.ftce.desmob.clientev1.ListarClientesActivity;
import br.usjt.ftce.desmob.clientev1.model.Cliente;
import br.usjt.ftce.desmob.clientev1.model.ClienteDb;
import br.usjt.ftce.desmob.clientev1.model.ClienteRequester;
import br.usjt.ftce.desmob.clientev1.view.MainView;

import static br.usjt.ftce.desmob.clientev1.MainActivity.APLICACAO;
import static br.usjt.ftce.desmob.clientev1.MainActivity.RECURSO;
import static br.usjt.ftce.desmob.clientev1.MainActivity.SERVIDOR;

/**
 * Created by Hygor on 03/06/2017.
 */

public class MainActivityPresenter implements MainPresenter {

    private MainView mainView;
    private ClienteRequester clienteRequester;
    ArrayList<Cliente> lista;
    ClienteDb clienteDb;

    public MainActivityPresenter(MainView mainView, ClienteDb clienteDb){
        this.mainView = mainView;
        this.clienteRequester = new ClienteRequester();
        this.clienteDb = clienteDb;
    }

    @Override
    public void buscarClientes( final String chave) {
        if(clienteRequester.isConnected(mainView.getContext())){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lista = clienteRequester.get(SERVIDOR+APLICACAO+RECURSO, chave);
                        clienteDb.insereClientes(lista);
                        mainView.listarClientes(lista);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } else {
            mainView.mensagem("Rede indisponível. Carregando dados do cache ");
            new CarregaClienteBD().execute("");
        }
    }

    private class CarregaClienteBD extends AsyncTask<String, Void, ArrayList<Cliente>> {

        @Override
        protected ArrayList<Cliente> doInBackground(String... strings) {
            return clienteDb.selecionaClientes();
        }

        public void onPostExecute(ArrayList<Cliente> lista){
            mainView.listarClientes(lista);
        }
    }

}
