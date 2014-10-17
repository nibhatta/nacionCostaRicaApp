package com.nacion.android.nacioncostarica.home.listeners;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.main.MainPresenter;

/**
 * Created by Gustavo Matarrita on 15/10/2014.
 */
public class MenuOnTouchListener implements View.OnTouchListener{

    private final static int SWIPE_DURATION = 250;
    private final static int MIN_DISTANCE = 20;
    private final static int RIGHT_EDGE = 0;
    private final static int LEFT_EDGE = -20;
    private final static int COMPLETE_ALPHA = 1;
    private final static int START_X = 0;

    private float downX;
    private float upX;
    private float endX;
    private TextView menuTextView;
    private ImageView deleteImageView;
    private ImageView notificationImageView;
    private DrawerLayout parentDrawerLayout;
    private ViewGroup parentReferences;
    private MainPresenter presenter;

    public MenuOnTouchListener(DrawerLayout argParentDrawerLayout, ViewGroup argParentReferences){
        this.parentDrawerLayout = argParentDrawerLayout;
        this.parentReferences = argParentReferences;
    }

    @Override
    public boolean onTouch(final View view, MotionEvent motionEvent) {

        menuTextView = (TextView) view.findViewById(R.id.menuIdTextView);
        deleteImageView = (ImageView)view.findViewById(R.id.deleteImageView);
        notificationImageView = (ImageView)view.findViewById(R.id.notificationImageView);
        float deltaX = 0;
        float deltaXAbs = 0;

        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                disableParentsView();
                downX = motionEvent.getX();
                presenter.showLeftSubMenuFromMenuOnTouchListener();
                break;

            case MotionEvent.ACTION_CANCEL:
                enableParentsView();
                view.setAlpha(COMPLETE_ALPHA);
                view.setTranslationX(START_X);
                break;

            case MotionEvent.ACTION_MOVE:
                disableParentsView();
                deltaX = motionEvent.getX() - downX;
                deltaXAbs = Math.abs(deltaX);
                if(deltaXAbs > Math.abs(MIN_DISTANCE)) {
                    view.setAlpha(COMPLETE_ALPHA - deltaXAbs / view.getWidth());
                }
                break;

            case MotionEvent.ACTION_UP:
                disableParentsView();
                upX = motionEvent.getX();
                deltaX = downX - upX;
                deltaXAbs = Math.abs(deltaX);

                if(deltaXAbs > Math.abs(MIN_DISTANCE)) {
                    if (isFromLeftToRight()) {
                        setViewWhenIsFromLeftToRight();
                    } else {
                        setViewWhenIsFromRightToLeft();
                    }
                }else{
                    return false;
                }
                setAnimationOnEnd(view);
                break;
        default:
            return false;
        }
        return true;
    }

    private void disableParentsView(){
        parentDrawerLayout.requestDisallowInterceptTouchEvent(true);
        parentReferences.setEnabled(false);
        parentDrawerLayout.setEnabled(false);
    }

    private void enableParentsView(){
        parentDrawerLayout.requestDisallowInterceptTouchEvent(false);
        parentReferences.setEnabled(true);
        parentDrawerLayout.setEnabled(true);
    }

    private boolean isFromLeftToRight(){
        return((downX - upX) < 0);
    }

    private boolean isFromRightToLeft(){
        return((downX - upX) > 0);
    }

    private void setViewWhenIsFromLeftToRight(){
        deleteImageView.setVisibility(View.INVISIBLE);
        notificationImageView.setVisibility(View.VISIBLE);
        endX = RIGHT_EDGE;
    }

    private void setViewWhenIsFromRightToLeft(){
        deleteImageView.setVisibility(View.VISIBLE);
        notificationImageView.setVisibility(View.INVISIBLE);
        endX = LEFT_EDGE;
    }

    private void setAnimationOnEnd(final View argView){
        ViewPropertyAnimator animator = argView.animate();
        animator.setDuration(SWIPE_DURATION).alpha(COMPLETE_ALPHA);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            animator.setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    argView.setAlpha(1);
                    menuTextView.setTranslationX(endX);
                    enableParentsView();
                }
            });
        }else{
            animator.withEndAction(new Runnable() {
                @Override
                public void run() {
                    argView.setAlpha(1);
                    menuTextView.setTranslationX(endX);
                    enableParentsView();
                }
            });
        }
    }

    public MainPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }
}
