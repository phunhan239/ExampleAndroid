package dvan.training_project_equiz.bap.equiz.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import dvan.training_project_equiz.bap.equiz.model.Question;

/**
 * Created by D'van on 1/16/2017.
 */

public class QuestionResponse {
    @SerializedName("results")
    private List<Question> results;

    public List<Question> getResults() {
        return results;
    }

    public void setResults(List<Question> results) {
        this.results = results;
    }
}
