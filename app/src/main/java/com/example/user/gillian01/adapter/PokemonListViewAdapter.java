package com.example.user.gillian01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user.gillian01.R; // important
import com.example.user.gillian01.model.OwnedPokemonInfo;

import java.util.List;

/**
 * Created by Gillian on 16/8/25.
 */
public class PokemonListViewAdapter extends ArrayAdapter<OwnedPokemonInfo>{

    LayoutInflater mInflater;
    int mRowLayoutId;

    public PokemonListViewAdapter(Context context, int resource, List<OwnedPokemonInfo> objects) {
        super(context, resource, objects);
        mRowLayoutId = resource;
        mInflater = LayoutInflater.from(context);

    }

    public  class ViewHolder {

        View mRowView;
        ImageView mAppearanceImg;
        TextView mNameText;
        TextView mLevelText;
        TextView mCurrentHP;
        TextView mMaxHP;
        ProgressBar mHPBar;
        OwnedPokemonInfo mData;

        public ViewHolder(View rowView)
        {
            mRowView = rowView;
            mAppearanceImg = (ImageView)rowView.findViewById(R.id.appearanceImg);
            mNameText = (TextView)rowView.findViewById(R.id.nameText);
            mLevelText = (TextView)rowView.findViewById(R.id.levelText);
            mCurrentHP = (TextView)rowView.findViewById(R.id.currentHP);
            mMaxHP = (TextView)rowView.findViewById(R.id.maxHP);
            mHPBar = (ProgressBar)rowView.findViewById(R.id.HPBar);
        }

        public void setView(OwnedPokemonInfo data)
        {
            mData = data;

            mNameText .setText(data.name);
            mLevelText.setText(String.valueOf(data.level));
            mCurrentHP.setText(String.valueOf(data.currentHP));
            mMaxHP.setText(String.valueOf(data.maxHP));
            int progress = (int)((float)data.currentHP/ data.maxHP)*100 ;
            mHPBar.setProgress(progress);

            //todo load image through lib from intenet


        }
    }


}
