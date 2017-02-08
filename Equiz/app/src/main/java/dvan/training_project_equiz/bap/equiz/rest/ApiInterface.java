package dvan.training_project_equiz.bap.equiz.rest;

import java.util.List;

import dvan.training_project_equiz.bap.equiz.model.Question;
import dvan.training_project_equiz.bap.equiz.response.QuestionResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by D'van on 1/16/2017.
 */

public interface ApiInterface {
    @GET("/api/Questions")
    Call<List<Question>> getAll();

//    @GET("/api/Questions/{id}")
//    Call<Question> getQuestion(@Path("id") int id);
}
