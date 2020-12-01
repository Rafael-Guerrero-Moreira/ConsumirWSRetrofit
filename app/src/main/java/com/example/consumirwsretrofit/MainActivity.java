package com.example.consumirwsretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consumirwsretrofit.Retrofit.InterfazL;
import com.example.consumirwsretrofit.Retrofit.LenguajesP;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView txtlanguages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnenviar(View view)
    {
        txtlanguages= (TextView)findViewById(R.id.lbllistalanguages);

        txtlanguages.setText("Solicitando Lenguajes de programación...");

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://judge0.p.rapidapi.com/").addConverterFactory(GsonConverterFactory.create()).build();
        InterfazL service = retrofit.create(InterfazL.class);
        Call<List<LenguajesP>> result = service.getLanguages();
        result.enqueue(new Callback<List<LenguajesP>>() {
            @Override
            public void onResponse(Call<List<LenguajesP>> call, Response<List<LenguajesP>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Error. \n Detalle: "+response.code()+" => "+response.message() , Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    List<LenguajesP> covidDataGroup = response.body();
                    if (covidDataGroup.size() == 0) {
                        Toast.makeText(MainActivity.this, "No encontré encontrado resultados.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else {
                        List<LenguajesP> languagesList = response.body();
                        String lstlanguages = new String ();
                        for(LenguajesP post: languagesList){
                            lstlanguages= lstlanguages +"\n"+ post.getName();
                        }
                        Intent intent = new Intent(MainActivity.this, recibir.class);
                        Bundle b = new Bundle();
                        b.putString("languages", lstlanguages);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<LenguajesP>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error. Intente nuevamente más tarde. \n Detalle: "+t.getMessage() , Toast.LENGTH_LONG).show();
            }
        });





    }
}