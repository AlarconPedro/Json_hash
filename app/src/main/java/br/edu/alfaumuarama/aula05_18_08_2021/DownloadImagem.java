package br.edu.alfaumuarama.aula05_18_08_2021;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImagem extends AsyncTask <String, Void, Bitmap> {

    ImageView imagem;

    public DownloadImagem(ImageView imagem){
        this.imagem = imagem;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = null;
        String link = strings[0];

        //criando a URL apartir do link inserido por parametros
        try {

            URL url = new URL(link);

            //salvar os dados prevenientes do link
            InputStream inputStream = url.openStream();

            //transforma os dados da web em imagem bitmap
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        //capturando o bitmap que foi baixado da web e mostrando na tela
        imagem.setImageBitmap(bitmap);
    }
}
