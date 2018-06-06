package ramadan.com.workmanger_playground;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkStatus;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    WorkManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      final  Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        manager = WorkManager.getInstance();


        Observable.interval(10, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(BackgroundPhotoUploadWorker.class)
                                .setConstraints(constraints).build();
                        manager.enqueue(request);
                        manager.getStatusById(request.getId()).observe(MainActivity.this, new Observer<WorkStatus>() {
                            @Override
                            public void onChanged(@Nullable WorkStatus workStatus) {
                                if (workStatus != null && workStatus.getState().isFinished())
                                    Log.d("sucess", "image is uploaded successfully");
                            }
                        });
                    }
                });



    }
}
