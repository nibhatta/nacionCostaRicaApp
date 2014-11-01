package com.nacion.android.nacioncostarica.views.home.listeners;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.views.main.MainPresenter;

/**
 * Created by Gustavo Matarrita on 15/10/2014.
 */
public class MenuOnTouchListener extends MenuListener implements View.OnTouchListener{
    private final static int LONG_PRESS_TIME = 500;
    private final static int SWIPE_DURATION = 250;
    private final static int MIN_DISTANCE = 20;
    private final static int RIGHT_EDGE = 0;
    private final static int LEFT_EDGE = -20;
    private final static int COMPLETE_ALPHA = 1;
    private final static int START_X = 0;

    private final Handler handler = new Handler();
    private int position;
    private float downX;
    private float upX;
    private float endX;
    private TextView menuTextView;
    private ImageView deleteImageView;
    private ToggleButton notificationToggleButton;
    private DrawerLayout parentDrawerLayout;
    private ViewGroup parentReferences;

    private static Runnable longPressed;

    public MenuOnTouchListener(DrawerLayout parentDrawerLayout, ViewGroup parentReferences, int position){
        this.position = position;
        this.parentDrawerLayout = parentDrawerLayout;
        this.parentReferences = parentReferences;
    }

    @Override
    public boolean onTouch(final View view, MotionEvent motionEvent) {

        menuTextView = (TextView) view.findViewById(R.id.menuIdTextView);
        deleteImageView = (ImageView)view.findViewById(R.id.deleteImageView);
        notificationToggleButton = (ToggleButton)view.findViewById(R.id.notificationToggleButton);
        float deltaX = 0, deltaXAbs = 0;
        long timeDown = 0, timeUp = 0;
        boolean stealFromChildren = true;

        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(MenuOnTouchListener.class.getName(), "=====> ACTION DOWN");
                disableParentsView();
                downX = motionEvent.getX();

                longPressed = new Runnable() {
                    @Override
                    public void run() {
                        Log.e(MenuOnTouchListener.class.getName(), "=====> Position: " + position);
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                        view.startDrag(null, shadowBuilder, ((ListView)parentReferences).getItemAtPosition(position), 0);
                        setStartPosition(position);
                    }
                };
                handler.postDelayed(longPressed, LONG_PRESS_TIME);

                break;

            case MotionEvent.ACTION_CANCEL:
                Log.e(MenuOnTouchListener.class.getName(), "=====> ACTION CANCEL");
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
                Log.e(MenuOnTouchListener.class.getName(), "=====> ACTION UP");
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
                    stealFromChildren = false;
                }
                setAnimationOnEnd(view);
                break;
            default:
                stealFromChildren = false;
                break;
        }
        return stealFromChildren;
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
        notificationToggleButton.setVisibility(View.VISIBLE);
        endX = RIGHT_EDGE;
    }

    private void setViewWhenIsFromRightToLeft(){
        deleteImageView.setVisibility(View.VISIBLE);
        notificationToggleButton.setVisibility(View.INVISIBLE);
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
}