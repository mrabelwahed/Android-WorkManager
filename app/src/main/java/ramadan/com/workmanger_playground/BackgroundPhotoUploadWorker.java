package ramadan.com.workmanger_playground;

import android.support.annotation.NonNull;
import android.util.Log;

import androidx.work.Worker;

/**
 * Created by mahmoud on 31/05/18.
 */

public class BackgroundPhotoUploadWorker extends Worker {

    @NonNull
    @Override
    public WorkerResult doWork() {
        Log.d("uploading","image...........................");
        return WorkerResult.SUCCESS;
    }
}
