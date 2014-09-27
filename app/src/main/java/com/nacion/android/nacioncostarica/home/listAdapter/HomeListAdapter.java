package com.nacion.android.nacioncostarica.home.listAdapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.nacion.android.nacioncostarica.NacionFragment;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.home.galleryAdapter.GalleryImageAdapter;
import com.nacion.android.nacioncostarica.home.galleryAdapter.GalleryImagePagerAdapter;
import com.nacion.android.nacioncostarica.home.galleryAdapter.ImageFragment;
import com.nacion.android.nacioncostarica.models.ContentItemList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class HomeListAdapter extends ArrayAdapter<ContentItemList> implements HomeListView {
    private static final int VIEWS_TYPE_COUNT = 6;
    private HomeListPresenter presenter;
    private LayoutInflater inflater;
    private Context mContext;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;

    private Integer[] mImageIds = {
            R.drawable.ic_launcher,
            R.drawable.plus_icon,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher
    };

    public HomeListAdapter(Context context, List<ContentItemList> argContents, FragmentManager argFragmentManager) {
        super(context, R.layout.item_module, argContents);
        mContext = context;
        presenter = new HomeListPresenterImpl(this);
        inflater = LayoutInflater.from(context);
        fragmentManager = argFragmentManager;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContentItemList itemList = getItem(position);
        int codeType = getItemViewType(position);
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            switch(codeType){
                case NacionConstants.MODULE_CODE_ONE:
                    convertView = inflater.inflate(R.layout.item_module, null);
                    break;
                case NacionConstants.MODULE_CODE_TWO:
                    convertView = inflater.inflate(R.layout.item_article, null);
                    break;
                case NacionConstants.MODULE_CODE_THREE:
                    convertView = inflater.inflate(R.layout.item_video_gallery, null);
                    break;
                case NacionConstants.MODULE_CODE_FOURTH:
                    convertView = inflater.inflate(R.layout.item_weather, null);
                    break;
                case NacionConstants.MODULE_CODE_FIVE:

                    convertView = inflater.inflate(R.layout.item_image_gallery_new, null);

                    holder.viewPager = (ViewPager)convertView.findViewById(R.id.imageGalleryViewPager);




                    /*
                    selectedImage=(ImageView)convertView.findViewById(R.id.currentImageView);
                    Gallery gallery = (Gallery)convertView.findViewById(R.id.imageGallery);
                    gallery.setSpacing(1);
                    gallery.setAdapter(new GalleryImageAdapter(mContext));

                    gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            Toast.makeText(mContext, "Your selected position = " + position, Toast.LENGTH_SHORT).show();
                            // show the selected Image
                            selectedImage.setImageResource(mImageIds[position]);
                        }
                    });*/

                    break;
                case NacionConstants.MODULE_CODE_SIX:
                    convertView = inflater.inflate(R.layout.item_more_news, null);
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        if(codeType == NacionConstants.MODULE_CODE_FIVE){
            GalleryImagePagerAdapter pagerAdapter = new GalleryImagePagerAdapter(fragmentManager, getFragmentsArray());
            holder.viewPager.setAdapter(pagerAdapter);
            holder.viewPager.setOffscreenPageLimit(4);
        }



        return convertView;
    }

    private List<NacionFragment> getFragmentsArray(){
        List<NacionFragment> fragments = new ArrayList<NacionFragment>();
        fragments.add(new ImageFragment().getInstance(0));
        fragments.add(new ImageFragment().getInstance(1));
        fragments.add(new ImageFragment().getInstance(2));
        fragments.add(new ImageFragment().getInstance(3));
        return fragments;
    }


    private class ViewHolder{
        ViewPager viewPager;


    }

    @Override
    public int getViewTypeCount() {
        return VIEWS_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        ContentItemList content = getItem(position);
        return content.getModule().getTypeCode();
    }
}
