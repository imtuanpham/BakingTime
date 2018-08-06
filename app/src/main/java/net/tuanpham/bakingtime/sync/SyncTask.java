/*
    @author: Tuan Pham
    @since: 2018-08-05 16:40:04

    Reference: Sunshine Project
 */

package net.tuanpham.bakingtime.sync;

import android.content.Context;
import android.util.Log;

import net.tuanpham.bakingtime.utils.NetworkUtils;

import java.net.URL;

public class SyncTask {

    private final static String LOG_TAG = SyncTask.class.getSimpleName();

    synchronized public static void syncBakingData(Context context) {

        Log.d(LOG_TAG, "Syncing Baking Data...");
        URL requestUrl = NetworkUtils.buildListUrl();
        try {
            String jsonResponse = NetworkUtils
                    .getResponseFromHttpUrl(requestUrl);

            Log.d(LOG_TAG, jsonResponse);

            /* If the code reaches this point, we have successfully performed our sync */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}