package com.example.alan_.pmdm2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_TEXT = 0;
    private EditText nom;
    private RadioButton molt, poc;
    private Button enviar;
    private TextView mostra;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom = (EditText) this.findViewById(R.id.et_nom);
        molt = (RadioButton) this.findViewById(R.id.rb_molt);
        poc = (RadioButton) this.findViewById(R.id.rb_poquet);
        enviar = (Button) this.findViewById(R.id.bt_enviar);
        mostra = (TextView) this.findViewById(R.id.tv_mostra);

        if (savedInstanceState != null){
            String msgMostra = savedInstanceState.getString("missatge");
            mostra.setText(msgMostra);
        }

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SubActivity.class);

                i.putExtra("edat", mostra.getText());
                i.putExtra("resNom", nom.getText().toString());
                if (molt.isChecked()) {
                    i.putExtra("resSexe", molt.getText());
                }else{
                    i.putExtra("resSexe", poc.getText());
                }
                setResult(Activity.RESULT_OK, i);
                MainActivity.this.startActivityForResult(i, REQUEST_TEXT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_TEXT){
            if (resultCode == Activity.RESULT_OK){
                String resultat = data.getExtras().get("edat").toString();
                int anys = Integer.parseInt(resultat);
                if (anys < 18) {
                    mostra.setText("Com que tens "+anys+" anys, ets un pipiolo");
                }else if (anys > 45) {
                    mostra.setText("Com que tens "+anys+" anys, prepara el bastó.");
                }else {
                    mostra.setText("Com que tens "+anys+" anys, estàs en la flor de la vida.");
                }
                nom.setEnabled(false);
                molt.setEnabled(false);
                poc.setEnabled(false);
                enviar.setEnabled(false);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mostra = (TextView) findViewById(R.id.tv_mostra);
        outState.putString("missatge", mostra.getText().toString());
        super.onSaveInstanceState(outState);
    }
}