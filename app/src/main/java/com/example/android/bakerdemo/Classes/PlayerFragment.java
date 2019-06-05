package com.example.android.bakerdemo.Classes;

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

import com.example.android.bakerdemo.R;
import com.example.android.bakerdemo.Recipe.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class PlayerFragment extends Fragment {

    PlayerView playerView;
    SimpleExoPlayer player;

    TextView summaryText;
    TextView descriptionText;
    long pos = 0;

    public PlayerFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_fragment, container, false);

        pos = Container.getPositionInplayer();
        Step step = Container.getStep();

        summaryText = view.findViewById(R.id.summary_txt);
        descriptionText = view.findViewById(R.id.description_txt);
        playerView = view.findViewById(R.id.pv);
        playerView.setUseArtwork(true);

        summaryText.setText(step.getSummary().toString());
        descriptionText.setText(step.getDescription().toString());
        String url = step.getVid();
        if (url.isEmpty()) {
            playerView.setVisibility(View.GONE);


        } else {

            createPlayer(url, pos);
        }

        return view;
    }

    void createPlayer(String url, Long position) {
        Log.d("Test", pos + "");
        TrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
        DefaultDataSourceFactory dataSource = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "Player"));
        MediaSource extractorMediaSource = new ExtractorMediaSource.Factory(dataSource).createMediaSource(Uri.parse(url));
        player.prepare(extractorMediaSource);
        playerView.setPlayer(player);
        player.seekTo(position);
        player.setPlayWhenReady(false);


    }


    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {

            player.setPlayWhenReady(false);

        }
    }

    @Override
    public void onDestroy() {

        if (player != null) {
            player.release();
            player.stop();
        }
        super.onDestroy();

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (player != null) {
            Container.setPositionInplayer(player.getCurrentPosition());
        }
    }
}
