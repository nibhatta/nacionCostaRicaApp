package com.nacion.android.nacioncostarica.home.galleryAdapter;

import android.content.Context;
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
import com.nacion.android.nacioncostarica.home.listAdapter.HomeListPresenter;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.tasks.ImageDownloaderTask;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class VideoFragment extends Fragment implements HomeView, NacionFragment {

    private VideoFragment instance;
    private ImageView imageView;
    private int fragmentIndex;
    private String imageUrl;
    private String title;
    private String section;
    private ImageDownloaderTask task;
    private View rootView;
    private Content content;
    private HomeListPresenter presenter;

    public VideoFragment getInstance(int argIndex, String argUrl){
        if(instance == null){
            instance = new VideoFragment();
            instance.setFragmentIndex(argIndex);
            instance.setImageUrl(argUrl);
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_video, container, false);
        imageView = (ImageView)rootView.findViewById(R.id.videoGalleryImageView);
        final String section = getContent().getSection();
        final int articleId = getContent().getId();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.startVideoActivity();
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        downloadImage();
    }

    @Override
    public void onResume() {
        super.onResume();
        downloadImage();
    }

    @Override
    public void onPause() {
        super.onPause();
        cancelAsyncTask();
    }

    @Override
    public void reloadImage(){
        downloadImage();
    }

    private void downloadImage(){
        cancelAsyncTask();
        task = new ImageDownloaderTask(imageView);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getImageUrl());
        }else{
            task.execute(getImageUrl());
        }
    }

    private void cancelAsyncTask(){
        if(task != null && task.getStatus() != ImageDownloaderTask.Status.FINISHED){
            task.cancel(true);
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

    @Override
    public String getSection() {
        return section;
    }

    @Override
    public void setSection(String argSection) {
        this.section = argSection;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public HomeListPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(HomeListPresenter presenter) {
        this.presenter = presenter;
    }
}
