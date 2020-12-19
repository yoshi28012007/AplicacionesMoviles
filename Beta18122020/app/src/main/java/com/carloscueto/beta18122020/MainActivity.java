package com.carloscueto.beta18122020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.carloscueto.beta18122020.bean.Alumno;
import com.carloscueto.beta18122020.service.AlumnoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> lstAlumno = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAlumnos();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lstAlumno);
        listView = findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);
    }

    private void getAlumnos(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AlumnoService alumnoService = retrofit.create(AlumnoService.class);
        Call<List<Alumno>>  call = alumnoService.getAlumno();

        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {

                for(Alumno alumno : response.body()){
                    lstAlumno.add(alumno.getTitle());
                }

                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {

            }
        });
    }

}