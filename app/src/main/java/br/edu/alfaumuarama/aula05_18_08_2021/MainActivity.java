package br.edu.alfaumuarama.aula05_18_08_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MainActivity extends ListActivity {

    ArrayList<Emissora> listaEmissora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Funcoes.verificaPermissao(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        try {
            String link = "http://controle.mdvsistemas.com.br/Novelas/Emissoras/GetEmissora";
            listaEmissora = new BuscaDadosWeb().execute(link).get();

            /*
            String texto = "Codigo: " + listaEmissora.get(0).codigo +
                    "Nome: " + listaEmissora.get(0).nome +
                    "Logo: " + listaEmissora.get(0).logo;

            Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
             */

            //criando a fonte de dados para listagem
            ListAdapter adapter = new SimpleAdapter(
                    this, //classe que controla a lista (MainActivity)
                    getLista(), //lista com os dados em formato ArrayList<HashMap>
                    R.layout.listview_emissora,//Modelo da linha da listagem
                    new String[] {"nome"},//campos que vem da lista  de dados
                    new int[] { R.id.lblNome }//campos visuais do modelo
            );

            //adicionando a fonte de dados na lista principal da tela
            setListAdapter(adapter);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<HashMap<String, String>> getLista() {

        ArrayList<HashMap<String, String>> listaRetorno = new ArrayList<>();

        //converter um ArrayList de Objetos em um ArrayList de HashMap
        for (int i = 0; i < listaEmissora.size(); i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("codigo", String.valueOf(listaEmissora.get(i).codigo));
            item.put("nome", String.valueOf(listaEmissora.get(i).nome));

            listaRetorno.add(item);
        }

        return listaRetorno;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String texto = "codigo: " + listaEmissora.get(position).codigo + " " +
                "nome: " + listaEmissora.get(position).nome;

        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();

        //caminho para abrir segunda tela
        Intent telaDetalhe = new Intent(MainActivity.this, DetalhesActivity.class);

        //criando a classe responsavel pelos parametros
        Bundle parans = new Bundle();
        parans.putString("nome", listaEmissora.get(position).nome);
        parans.putString("logo", listaEmissora.get(position).logo);
        parans.putInt("codigo", listaEmissora.get(position).codigo);

        //adicionando os parametros criados no caminho da tela
        telaDetalhe.putExtras(parans);

        //startando a tela
        startActivity(telaDetalhe);
    }
}