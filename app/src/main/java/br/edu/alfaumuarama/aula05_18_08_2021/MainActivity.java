package br.edu.alfaumuarama.aula05_18_08_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
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

        try {
            String link = "http://controle.mdvsistemas.com.br/Novelas/Emissoras/GetEmissora";
            listaEmissora = new BuscaDadosWeb().execute(link).get();

            String texto = "Codigo: " + listaEmissora.get(0).codigo +
                    "Nome: " + listaEmissora.get(0).nome +
                    "Logo: " + listaEmissora.get(0).logo;

            Toast.makeText(this, texto, Toast.LENGTH_LONG).show();

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

        for (int i = 0; i < listaEmissora.size(); i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("codigo", String.valueOf(listaEmissora.get(i).codigo));
            item.put("nome", String.valueOf(listaEmissora.get(i).nome));

            listaRetorno.add(item);
        }

        return listaRetorno;
    }
}