package br.usjt.ftce.desmob.clientev1.presenter;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.io.IOException;

import br.usjt.ftce.desmob.clientev1.DetalheClienteActivity;
import br.usjt.ftce.desmob.clientev1.MainActivity;
import br.usjt.ftce.desmob.clientev1.model.Cliente;
import br.usjt.ftce.desmob.clientev1.model.ClienteRequester;
import br.usjt.ftce.desmob.clientev1.view.DetalheView;

/**
 * Created by Hygor on 03/06/2017.
 */

public class DetalheActivityPresenter implements DetalheClientePresenter {
    ClienteRequester clienteRequester;
    DetalheView detalheView;

    public DetalheActivityPresenter(DetalheView detalheView){
        this.clienteRequester =  new ClienteRequester();
        this.detalheView = detalheView;
    }

    @Override
    public void carregaImagem(Cliente cliente) {
        if(clienteRequester.isConnected(detalheView.getContext())) {
            new DownloadImage().execute(MainActivity.SERVIDOR +
                    MainActivity.APLICACAO + "/img/" + cliente.getImagem() + ".jpg");
        } else {
            detalheView.mensagem("Rede indisponivel, nao foi possivel carregar a imagem");
        }
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                return clienteRequester.getImage(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        public void onPostExecute(Bitmap result){
            detalheView.setImagem(result);
        }
    }

}
