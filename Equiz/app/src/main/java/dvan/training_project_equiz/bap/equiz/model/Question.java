package dvan.training_project_equiz.bap.equiz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by D'van on 1/16/2017.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Question extends RealmObject {
    @SerializedName("idQ")
    @Expose
    private int idQ;
    @SerializedName("numberQ")
    @Expose
    private int numberQ;
    @SerializedName("contentQ")
    @Expose
    private String contentQ;
    @SerializedName("optionA")
    @Expose
    private String optionA;
    @SerializedName("optionB")
    @Expose
    private String optionB;
    @SerializedName("optionC")
    @Expose
    private String optionC;
    @SerializedName("optionD")
    @Expose
    private String optionD;
    @SerializedName("correctAnswer")
    @Expose
    private String correctAnswer;
}