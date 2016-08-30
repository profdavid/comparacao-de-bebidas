package com.example.dpdevelly.comparacaodeprodutos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PrincipalActivity extends AppCompatActivity {

    private EditText edtVal1;
    private EditText edtVal2;
    private EditText edtMl1;
    private EditText edtMl2;
    private Button btComparar;
    private TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Mapeando campos da tela
        edtVal1     = (EditText) findViewById(R.id.edtVal1);
        edtVal2     = (EditText) findViewById(R.id.edtVal2);
        edtMl1      = (EditText) findViewById(R.id.edtMl1);
        edtMl2      = (EditText) findViewById(R.id.edtMl2);
        btComparar  = (Button) findViewById(R.id.btComparar);
        txtInfo     = (TextView) findViewById(R.id.txtInfo);

        //Limpando texto informativo
        txtInfo.setText("");

        //Ao clicar no botão Comparar
        btComparar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float res1, res2;
                String strOpMelhor, strOpPior, strResultado;
                float valMelhor, mlMelhor, valPior, mlPior, resMelhor, resPior;

                //Se os dados estiverem preenchidos
                if(verificarVazios()) {
                    //Recolhendo os valores dos campos
                    float val1  = Float.valueOf(edtVal1.getText().toString().replace(",", "."));
                    float val2  = Float.valueOf(edtVal2.getText().toString().replace(",", "."));
                    float ml1   = Float.valueOf(edtMl1.getText().toString().replace(",", "."));
                    float ml2   = Float.valueOf(edtMl2.getText().toString().replace(",", "."));

                    //Realizando o cálculo
                    res1 = val1 / ml1;
                    res2 = val2 / ml2;

                    //Impressões de teste
                    //Log.d("mylog", "res1 = "+res1+", res2 = "+res2);

                    //Realisando a análise do produto mais vantajoso
                    if(res1 < res2){
                        valMelhor = val1;
                        mlMelhor = ml1;

                        valPior = val2;
                        mlPior = ml2;

                        resMelhor = res1;
                        resPior = res2;
                    }
                    else{
                        valMelhor = val2;
                        mlMelhor = ml2;

                        valPior = val1;
                        mlPior = ml1;

                        resMelhor = res2;
                        resPior = res1;
                    }

                    //Montando a string de resultado
                    DecimalFormat df = new DecimalFormat("0.00");
                    strResultado = "Melhor Opção: "+mlMelhor+" ml - R$ "+df.format(valMelhor)+"\n";
                    strResultado += "Pior Opção: "+mlPior+" ml - R$ "+df.format(valPior)+"\n\n";
                    strResultado += "Informações:\n";
                    strResultado += "- A Melhor Opção é "+df.format(100-((resMelhor/resPior)*100))+"% mais barato (proporcionalmente);\n";
                    strResultado += "- A Pior Opção deveria custar R$ "+df.format(resMelhor*mlPior)+" para ser igual a Melhor Opção;\n";

                    //Chamando tela de resultados
                    Intent intentResultado = new Intent(PrincipalActivity.this, ResultadoActivity.class);
                    intentResultado.putExtra("res", strResultado);
                    startActivity(intentResultado);
                }
                else{
                    //Apresentando mensagem informativa na tela
                    txtInfo.setText("Atenção! Preencha todos os campos.");
                }
            }
        });
    }

    private boolean verificarVazios(){
        if(edtVal1.getText().toString().equals("") ||
           edtVal2.getText().toString().equals("") ||
           edtMl1.getText().toString().equals("")  ||
           edtMl2.getText().toString().equals(""))
            return false;
        else
            return true;
    }
}
