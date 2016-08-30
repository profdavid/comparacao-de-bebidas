package com.example.dpdevelly.comparacaodeprodutos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    private TextView txtResultado;
    private Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //Mapeando campos da tela
        txtResultado    = (TextView) findViewById(R.id.txtResultado);
        btVoltar        = (Button) findViewById(R.id.btVoltar);

        //Recebendo e apresentando o resultado na tela
        Bundle extras = getIntent().getExtras();
        String res = extras.getString("res");
        txtResultado.setText(res);

        //Retornando para a tela anterior
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPrincipal = new Intent(ResultadoActivity.this, PrincipalActivity.class);
                startActivity(intentPrincipal);
            }
        });
    }
}
