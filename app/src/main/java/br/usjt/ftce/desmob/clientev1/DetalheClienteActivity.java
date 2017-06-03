package br.usjt.ftce.desmob.clientev1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import br.usjt.ftce.desmob.clientev1.model.Cliente;
import br.usjt.ftce.desmob.clientev1.model.ClienteRequester;
import br.usjt.ftce.desmob.clientev1.presenter.DetalheActivityPresenter;
import br.usjt.ftce.desmob.clientev1.presenter.DetalheClientePresenter;
import br.usjt.ftce.desmob.clientev1.view.DetalheView;

public class DetalheClienteActivity extends Activity implements DetalheView {
    TextView textViewNome, textViewFone, textViewEmail;
    ImageView imagemCliente;
    Cliente cliente;
    DetalheClientePresenter detalhePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_cliente);
        textViewNome = (TextView) findViewById(R.id.txt_cliente_nome);
        textViewFone = (TextView) findViewById(R.id.txt_cliente_fone);
        textViewEmail = (TextView) findViewById(R.id.txt_cliente_email);
        imagemCliente = (ImageView) findViewById(R.id.cliente_image_view);
        Intent intent = getIntent();
        cliente = (Cliente)intent.getSerializableExtra(ListarClientesActivity.CLIENTE);
        textViewNome.setText(cliente.getNome());
        textViewEmail.setText(cliente.getEmail());
        textViewFone.setText(cliente.getFone());

        detalhePresenter = new DetalheActivityPresenter(this);
        detalhePresenter.carregaImagem(cliente);
    }

    @Override
    public void setImagem(Bitmap imagem) {
        imagemCliente.setImageBitmap(imagem);
    }

    @Override
    public void mensagem(String mensagem) {
        Toast toast = Toast.makeText(this,mensagem, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public Context getContext() {
        return this;
    }

}

