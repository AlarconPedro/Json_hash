package br.edu.alfaumuarama.aula05_18_08_2021;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Funcoes {
    public static void verificaPermissao(Activity activity, String nomePermissao) {

        //verifica se já não tem permissão
        if(ContextCompat.checkSelfPermission(activity, nomePermissao) != PackageManager.PERMISSION_GRANTED) {

            //verifica se o usuario negou a solicitação de permissão
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, nomePermissao)) {
                ActivityCompat.requestPermissions(activity, new String[] { nomePermissao }, 0);
            }
        }
    }
}
