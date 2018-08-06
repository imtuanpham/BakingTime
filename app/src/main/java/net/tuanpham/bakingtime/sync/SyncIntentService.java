/*
    @author: Tuan Pham
    @since: 2018-08-05 16:39:45

    Reference: Sunshine Project
 */
package net.tuanpham.bakingtime.sync;

import android.app.IntentService;
import android.content.Intent;

public class SyncIntentService extends IntentService {

    public SyncIntentService() {
        super("SyncIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SyncTask.syncBakingData(this);
    }
}