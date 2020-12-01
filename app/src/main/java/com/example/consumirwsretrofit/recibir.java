package com.example.consumirwsretrofit;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class recibir extends AppCompatActivity {
    private EditText txtMensaje;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recibir);
        txtMensaje = findViewById(R.id.lblMensaje);
        Bundle bundle = this.getIntent().getExtras();
        txtMensaje.setText(bundle.getString("languages"));
        txtMensaje.setKeyListener(null);
    }
}
