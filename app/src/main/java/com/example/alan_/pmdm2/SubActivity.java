package com.example.alan_.pmdm2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {

    private TextView tvSexe;
    private EditText etEdat;
    private Button btContinuar;
    private String sexe, resNom, mns;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        tvSexe = (TextView) this.findViewById(R.id.tv_resSexe);
        etEdat = (EditText) this.findViewById(R.id.et_edat);
        btContinuar = (Button) this.findViewById(R.id.bt_continuar);
        Bundle b = getIntent().getExtras();

        resNom = b.getString("resNom");
        sexe = b.getString("resSexe");

        System.out.println(resNom+sexe);
        if (sexe.compareTo("Poquet") == 0){
            mns = (resNom+", 20 euros tenen la culpa.");
        } else {
            mns = (resNom+", m´ha que eres valent!!");
        }

        tvSexe.setText(mns.toString());

        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SubActivity.class);
                i.putExtra("edat", etEdat.getText());
                setResult(Activity.RESULT_OK, i);
                SubActivity.this.finish();
            }
        });
    }
}