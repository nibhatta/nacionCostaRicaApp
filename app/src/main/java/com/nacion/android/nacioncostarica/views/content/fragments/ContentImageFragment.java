package com.nacion.android.nacioncostarica.views.content.fragments;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.views.content.ContentPresenter;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.tasks.ImageDownloaderTask;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class ContentImageFragment extends Fragment implements ContentFragment {

    private ContentImageFragment instance;
    private ImageView imageView;
    private int fragmentIndex;
    private String imageUrl;
    private String photoAuthor;
    private ImageDownloaderTask task;
    private View rootView;
    private Content content;
    private ContentPresenter presenter;

    public ContentImageFragment getInstance(int argIndex, String argUrl){
        if(instance == null){
            instance = new ContentImageFragment();
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
        rootView = inflater.inflate(R.layout.fragment_image_content, container, false);
        imageView = (ImageView)rootView.findViewById(R.id.imageView);
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

    @Override
    public String getPhotoAuthor() {
        return photoAuthor;
    }

    @Override
    public void setPhotoAuthor(String photoAuthor) {
        this.photoAuthor = photoAuthor;
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

    public ContentPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(ContentPresenter presenter) {
        this.presenter = presenter;
    }
}
