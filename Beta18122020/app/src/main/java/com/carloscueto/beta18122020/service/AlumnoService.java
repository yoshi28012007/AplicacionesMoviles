package com.carloscueto.beta18122020.service;

import com.carloscueto.beta18122020.bean.Alumno;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlumnoService {

    String API_ROUTE = "/posts";

    @GET(API_ROUTE)
    Call<List<Alumno>>  getAlumno();

}
