package br.usjt.ftce.desmob.clientev1.view;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by Família on 03/06/2017.
 */

public interface DetalheView {
    void setImagem(Bitmap imagem);

    void mensagem(String mensagem);

    Context getContext();
}
