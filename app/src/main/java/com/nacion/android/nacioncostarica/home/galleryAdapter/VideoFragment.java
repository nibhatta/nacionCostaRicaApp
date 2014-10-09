package com.nacion.android.nacioncostarica.home.galleryAdapter;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.nacion.android.nacioncostarica.NacionFragment;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.home.HomePresenter;
import com.nacion.android.nacioncostarica.home.HomePresenterImpl;
import com.nacion.android.nacioncostarica.home.HomeView;
import com.nacion.android.nacioncostarica.home.listAdapter.HomeListAdapter;
import com.nacion.android.nacioncostarica.tasks.ImageDownloaderTask;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class VideoFragment extends Fragment implements HomeView, NacionFragment {

    private HomePresenter presenter;
    private VideoFragment singleton;
    private ImageView imageView;
    private int fragmentIndex;
    private String imageUrl;
    private String title;

    public VideoFragment getInstance(int argIndex, String argUrl){
        if(singleton == null){
            singleton = new VideoFragment();
            singleton.setFragmentIndex(argIndex);
            singleton.setImageUrl(argUrl);
        }
        return singleton;
    }

    public VideoFragment(){
        presenter = new HomePresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        imageView = (ImageView)rootView.findViewById(R.id.videoGalleryImageView);
        downloadImage();
        return rootView;
    }

    private void downloadImage(){
        ImageDownloaderTask task = new ImageDownloaderTask(imageView);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getImageUrl());
        }else{
            task.execute(getImageUrl());
        }
    }

    @Override
    public int getFragmentIndex() {
        return fragmentIndex;
    }

    @Override
    public void setFragmentIndex(int argIndex) {
        fragmentIndex = argIndex;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
