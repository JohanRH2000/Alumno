package com.example.alumno.api;
import com.example.alumno.entity.Alumno;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
public interface ServiceAlumnoApi {
    @GET("alumno")
    public abstract Call<List<Alumno>> listaAlumno();

}
