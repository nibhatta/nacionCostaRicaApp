package com.nacion.android.nacioncostarica.home.galleryAdapter;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.nacion.android.nacioncostarica.NacionFragment;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.home.HomePresenter;
import com.nacion.android.nacioncostarica.home.HomePresenterImpl;
import com.nacion.android.nacioncostarica.home.HomeView;
import com.nacion.android.nacioncostarica.home.listAdapter.HomeListAdapter;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class VideoFragment extends Fragment implements HomeView, NacionFragment {

    private HomePresenter presenter;
    private VideoFragment singleton;
    private VideoView videoView;
    private int fragmentIndex;
    private HomeListAdapter homeListAdapter;
    private ListView homeListView;


    public VideoFragment getInstance(int argIndex){
        if(singleton == null){
            singleton = new VideoFragment();
            singleton.setFragmentIndex(argIndex);
        }
        return singleton;
    }

    public VideoFragment(){
        presenter = new HomePresenterImpl(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);

        /*
        videoView = (VideoView)rootView.findViewById(R.id.videoGalleryVideoView);

        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(videoView);

        //String videoToPlay = "rtsp://v6.cache1.c.youtube.com/CjYLENy73wIaLQnF4qJzpSt4nhMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYMDFmvL1wMysTQw=/0/0/0/video.3gp";
        String videoToPlay = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov";
        Uri videoUri = Uri.parse(videoToPlay);
        videoView.requestFocus();
        videoView.setVideoURI(videoUri);
        videoView.seekTo(videoView.getDuration() - 10);
        videoView.setMediaController(mediaController);
        */
        return rootView;
    }

    @Override
    public int getFragmentIndex() {
        return fragmentIndex;
    }

    @Override
    public void setFragmentIndex(int argIndex) {
        fragmentIndex = argIndex;
    }
}
