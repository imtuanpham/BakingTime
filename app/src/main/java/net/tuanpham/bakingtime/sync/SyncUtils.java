/*
    @author: Tuan Pham
    @since: 2018-08-05 16:39:34

    Reference: Sunshine Project
 */
package net.tuanpham.bakingtime.sync;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

public class SyncUtils {

    private static boolean sInitialized;

    synchronized public static void initialize(@NonNull final Context context) {
        if (sInitialized) return;

        SyncAsyncTask task = new SyncAsyncTask();
        task.execute(context);

        sInitialized = true;
    }


    private static class SyncAsyncTask extends AsyncTask<Context, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Context... contexts) {
            Context context = contexts[0];
            startImmediateSync(context);
            return null;
        }

        @Override
        protected void onPostExecute(Cursor s) {
            super.onPostExecute(s);
            // close the cursor to avoid the memory leak
            if(s != null) s.close();
        }
    }

    /**
     * Helper method to perform a sync immediately using an IntentService for asynchronous
     * execution.
     *
     * @param context The Context used to start the IntentService for the sync.
     */
    public static void startImmediateSync(@NonNull final Context context) {
        Intent intentToSyncImmediately = new Intent(context, SyncIntentService.class);
        context.startService(intentToSyncImmediately);
    }
}