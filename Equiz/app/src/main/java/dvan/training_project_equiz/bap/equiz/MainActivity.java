package dvan.training_project_equiz.bap.equiz;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import dvan.training_project_equiz.bap.equiz.model.Question;
import dvan.training_project_equiz.bap.equiz.response.QuestionResponse;
import dvan.training_project_equiz.bap.equiz.rest.ApiClient;
import dvan.training_project_equiz.bap.equiz.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 *
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewById
    TextView mTvResult;

    @Click({R.id.mBtnGetJson})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.mBtnGetJson:
                getJson();
                break;
            default:
                break;
        }
    }

    private void getJson() {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Question>> call = service.getAll();
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if (response.isSuccessful()){
                    System.out.println("thanh cong : " + response.body().size());
                }else {
                    System.out.println("that bai");
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {

            }
        });

    }
}

