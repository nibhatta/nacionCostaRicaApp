package com.nacion.android.nacioncostarica.home.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.home.holder.HomeViewHolder;
import com.nacion.android.nacioncostarica.models.ContentItemList;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class MenuListAdapter extends ArrayAdapter<String> implements HomeListView {
    private static final int VIEWS_TYPE_COUNT = 8;
    private HomeListPresenter presenter;
    private LayoutInflater inflater;
    private Context context;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;

    public MenuListAdapter(Context context, List<String> argContents, FragmentManager argFragmentManager) {
        super(context, R.layout.menu_list_item, argContents);
        this.context = context;

        inflater = LayoutInflater.from(context);
        fragmentManager = argFragmentManager;
    }

    public HomeListPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(HomeListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String itemList = getItem(position);
        int codeType = getItemViewType(position);
        ViewHolder holder;

        //View view = super.getView(position, convertView, parent);

        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.menu_list_item, null);
            convertView.setOnTouchListener(touchListener);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        //setHolderViewValuesByCodeType(holder, itemList, codeType);

        return convertView;
    }

    private View.OnTouchListener touchListener = new View.OnTouchListener(){

        float downX;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch(motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    downX = motionEvent.getX();
                    break;
                case MotionEvent.ACTION_MOVE:

                    float x = motionEvent.getX() + view.getTranslationX();
                    float deltaX = x - downX;
                    float deltaXAbs = Math.abs(deltaX);

                    view.setTranslationX(deltaX);
                    view.setAlpha(1 - deltaXAbs / view.getWidth());
                    break;
                default:
                    return false;
            }
            return true;
        }

    };

    class ViewHolder{

    }

    private void setHolderViewValuesByCodeType(HomeViewHolder argHolder, ContentItemList argItem, int argCodeType){
        switch(argCodeType){
            case NacionConstants.MODULE_CODE_ONE:
                argHolder.setViewHolderValuesForHighlightView(argItem);
                break;
            case NacionConstants.MODULE_CODE_TWO:
                argHolder.setViewHolderValuesForArticleView(argItem);
                break;
            case NacionConstants.MODULE_CODE_THREE:
                argHolder.setViewHolderValuesForVideoGalleryView();
                break;
            case NacionConstants.MODULE_CODE_FOURTH:

                break;
            case NacionConstants.MODULE_CODE_FIVE:

                break;
            case NacionConstants.MODULE_CODE_EIGHT:

                break;
        }
    }

    /*
    @Override
    public int getViewTypeCount() {
        return VIEWS_TYPE_COUNT;
    }

    @Override

    public int getItemViewType(int position) {
        ContentItemList content = getItem(position);
        return content.getModule().getTypeCode();
    }
    */
}
