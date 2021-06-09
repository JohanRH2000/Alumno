package com.example.alumno;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.alumno.api.ServiceAlumnoApi;
import com.example.alumno.entity.Alumno;
import com.example.alumno.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> datps = new ArrayList<>();
    ListView listViewAlumno;
    ArrayAdapter<String> adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewAlumno = findViewById(R.id.id_ListaAlumno);
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datps);
        listViewAlumno.setAdapter(adaptador);
        cargaData();


    }
    public void cargaData(){
        ServiceAlumnoApi api = ConnectionRest.getConnection().create(ServiceAlumnoApi.class);
        Call<List<Alumno>> call =   api.listaAlumno();
        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {
                if(response.isSuccessful()){
                    List<Alumno> respuesta = response.body();
                    for (Alumno x : respuesta){
                        datps.add(x.getIdAlumno() +" -> " + x.getNombres());
                    }
                    adaptador.notifyDataSetChanged();
                }else{
                    muestraMensaje("La  respuesta no es satisfactoria");
                }
            }

            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {
                muestraMensaje("--> Error :" + t.getMessage());
            }
        });
    }

    public void muestraMensaje(String msg){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(msg);
        alert.show();
    }
}