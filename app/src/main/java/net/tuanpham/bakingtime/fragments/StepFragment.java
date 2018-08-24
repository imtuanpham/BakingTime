package net.tuanpham.bakingtime.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;

import net.tuanpham.bakingtime.R;

import net.tuanpham.bakingtime.data.entities.Step;
import net.tuanpham.bakingtime.data.viewmodels.StepViewModel;

public class StepFragment extends Fragment {

    private final String LOG_TAG = StepFragment.class.getSimpleName();
    private final static String PLAYER_CURRENT_POSITION = "PLAYER_CURRENT_POSITION";
    private final static String PLAYER_PLAY_WHEN_READY = "PLAYER_PLAY_WHEN_READY";

    private StepViewModel mStepViewModel;
    private Step mStep;

    private TextView mStepDescription;

    private Uri mVideoURI;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private long mPlayerCurrentPosition;
    private boolean mPlayerPlayWhenReady;

    // Mandatory empty constructor
    public StepFragment() {
    }

    // method that allows activity to transfer data to fragment
    public void putArguments(Bundle args) {
        int stepId = args.getInt(StepViewModel.STEP_ID);
        mStepViewModel.selectStep(stepId);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView");

        if(savedInstanceState != null) {
            mPlayerCurrentPosition = savedInstanceState.getLong(PLAYER_CURRENT_POSITION);
            mPlayerPlayWhenReady = savedInstanceState.getBoolean(PLAYER_PLAY_WHEN_READY);
        } else {
            mPlayerCurrentPosition = 0;
            mPlayerPlayWhenReady = true;
        }

        final View rootView = inflater.inflate(R.layout.fragment_step, container, false);
        mStepDescription = (TextView) rootView.findViewById(R.id.tv_step_description);

        // Initialize the player view.
        mPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.playerView);

        mStepViewModel = ViewModelProviders.of(this.getActivity()).get(StepViewModel.class);

        mStepViewModel.getSelectedStepId().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer stepId) {
                Log.d(LOG_TAG, "stepId: " + String.valueOf(stepId));
                getStep(stepId);
            }
        });

        // Return the root view
        return rootView;
    }

    private void getStep(int stepId) {
        mStepViewModel.getStep(stepId).observe(this, new Observer<Step>() {
            @Override
            public void onChanged(@Nullable final Step step) {
                Log.d(LOG_TAG, "getStep onChanged");
                mStep = step;
                populateData();
            }
        });
    }

    private void populateData() {
        mStepDescription.setText(mStep.getDescription());
        String videoURL = mStep.getVideoURL().trim();
        if (!videoURL.isEmpty()) {
            mVideoURI = Uri.parse(videoURL);
        } else {
            mVideoURI = null;
            mPlayerView.setVisibility(View.INVISIBLE);
        }
        initializePlayer();
    }


    // REFERENCE: https://github.com/udacity/AdvancedAndroid_ClassicalMusicQuiz/blob/TMED.01-Solution-AddExoPlayer/app/src/main/java/com/example/android/classicalmusicquiz/QuizActivity.java

    /**
     * Initialize ExoPlayer.
     */
    private void initializePlayer() {
        if (mExoPlayer == null && mVideoURI != null) {

            // Create an instance of the ExoPlayer.
            // Measures bandwidth during playback. Can be null if not required.
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            DefaultTrackSelector trackSelector =
                    new DefaultTrackSelector(videoTrackSelectionFactory);

            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(this.getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(this.getContext(), this.mStep.getShortDescription());
            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this.getContext(),
                                                        userAgent);

            // This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mVideoURI);

            // Prepare the player with the source.
            mExoPlayer.prepare(videoSource);

            Log.d(LOG_TAG, "saved current position: " + String.valueOf(mPlayerCurrentPosition));

            mExoPlayer.seekTo(mPlayerCurrentPosition);
            mExoPlayer.setPlayWhenReady(mPlayerPlayWhenReady);
        }
    }


    /**
     * Release ExoPlayer.
     */
    private void releasePlayer() {
        // if the player doesn't exist, there is no need to release it
        if(mExoPlayer == null)
            return;

        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    /**
     * Save ExoPlayer State.
     */
    private void savePlayerState() {
        // if the player doesn't exist, there is no need to save state
        if(mExoPlayer == null)
            return;

        Log.d(LOG_TAG, "saving current position: " + String.valueOf(mPlayerCurrentPosition));

        mPlayerCurrentPosition = mExoPlayer.getCurrentPosition();
        mPlayerPlayWhenReady = mExoPlayer.getPlayWhenReady();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onPause() {
        Log.d(LOG_TAG, "onPause");
        super.onPause();

        // save player state
        savePlayerState();

        if (Util.SDK_INT <= 23) {
            // release player
            releasePlayer();
        }
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(LOG_TAG, "onSaveInstanceState");
        outState.putLong(PLAYER_CURRENT_POSITION, mPlayerCurrentPosition);
        outState.putBoolean(PLAYER_PLAY_WHEN_READY, mPlayerPlayWhenReady);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        Log.d(LOG_TAG, "onStop");
        super.onStop();
        if (Util.SDK_INT > 23) {
            // release player
            releasePlayer();
        }
    }

    @Override
    public void onStart() {
        Log.d(LOG_TAG, "onStart");
        super.onStart();
        if (Util.SDK_INT > 23) {
            // initialize player
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        Log.d(LOG_TAG, "onResume");
        super.onResume();
        if ((Util.SDK_INT <= 23)) {
            // initialize player
            initializePlayer();
        }
    }
}
