package br.edu.alfaumuarama.aula05_18_08_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalhesActivity extends AppCompatActivity {

    TextView txtNome = findViewById(R.id.txtNome);
    ImageView imgLogo = findViewById(R.id.imgLogo);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        //capturando o caminho de tela que foi utilizado para abrir esta tela
        Intent caminhoTela = getIntent();

        //verifica se existe um caminho valido
        if(caminhoTela != null) {

            //capturando os parametros enviados junto ao caminho da tela
            Bundle parans = caminhoTela.getExtras();

            //verifica se os parametros não são nulos
            if(parans != null) {
                String nome = parans.getString("nome");
                String logo = parans.getString("logo");
                int codigo = parans.getInt("codigo");

                txtNome.setText(nome);
                new DownloadImagem(imgLogo).execute(logo);
            }
        }
    }
}