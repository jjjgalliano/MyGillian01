package com.example.user.gillian01.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user.gillian01.OnPokemonSelectedChangeListner;
import com.example.user.gillian01.R; // important
import com.example.user.gillian01.model.OwnedPokemonInfo;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gillian on 16/8/25.
 */
public class PokemonListViewAdapter extends ArrayAdapter<OwnedPokemonInfo>  implements OnPokemonSelectedChangeListner {



    ArrayList<OwnedPokemonInfo> selectedPokemons = new ArrayList<>();

    LayoutInflater mInflater;
    int mRowLayoutId;

    public PokemonListViewAdapter(Context context, int resource, List<OwnedPokemonInfo> objects) {
        super(context, resource, objects);
        mRowLayoutId = resource;
        mInflater = LayoutInflater.from(context);
        ViewHolder.mAdapter = this;
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {

        OwnedPokemonInfo data  = getItem( position); // super GET OBJECT
        ViewHolder viewHolder = null;

        if(rowView == null)
        {
            rowView = mInflater.inflate(mRowLayoutId,null);
            viewHolder = new ViewHolder(rowView); // construct  a view HolderObject
            rowView.setTag(viewHolder); //  cache viewHolder

        }else  //  重複使用 rowview
        {
            viewHolder = (ViewHolder)rowView.getTag(); // return previous cached rowView

        }

        viewHolder.setView(data);
        return rowView;
    }

    @Override
    public void onSelectedChanged(OwnedPokemonInfo ownedPokemonInfo) {
        if(ownedPokemonInfo.isSelected)
        {
            selectedPokemons.add(ownedPokemonInfo);
        }else
        {
            selectedPokemons.remove(ownedPokemonInfo);  // if null ?? remove ??
        }
    }

    public static class ViewHolder implements View.OnClickListener{
      //  public interface OnPokemonSelectedChangeListner{
      //      void onSelectedChanged(OwnedPokemonInfo ownedPokemonInfo);// interface default  public
      //  }  // not static class can't put interface in it


        View mRowView;
        ImageView mAppearanceImg;
        TextView mNameText;
        TextView mLevelText;
        TextView mCurrentHP;
        TextView mMaxHP;
        ProgressBar mHPBar;
        OwnedPokemonInfo mData;

        public static PokemonListViewAdapter mAdapter;


        public ViewHolder(View rowView)
        {
            mRowView = rowView;
            mAppearanceImg = (ImageView)rowView.findViewById(R.id.appearanceImg);
            mAppearanceImg.setOnClickListener(this);

            mNameText = (TextView)rowView.findViewById(R.id.nameText);
            mLevelText = (TextView)rowView.findViewById(R.id.levelText);
            mCurrentHP = (TextView)rowView.findViewById(R.id.currentHP);
            mMaxHP = (TextView)rowView.findViewById(R.id.maxHP);
            mHPBar = (ProgressBar)rowView.findViewById(R.id.HPBar);
        }

        //bind mRowView with data
        public void setView(OwnedPokemonInfo data)
        {
            mData = data;

            mRowView.setActivated(data.isSelected);

            mNameText .setText(data.name);
            mLevelText.setText(String.valueOf(data.level));
            mCurrentHP.setText(String.valueOf(data.currentHP));
            mMaxHP.setText(String.valueOf(data.maxHP));
            int progress = (int)(((float)data.currentHP/ data.maxHP)*100) ;
            //Log.d("test",mCurrentHP +":"+String.valueOf(progress));
            mHPBar.setProgress(progress);

            //todo load image through lib from intenet
            int pokemonId = data.pokemonId;
            String imgUrl = String.format("http://www.csie.ntu.edu.tw/~r03944003/listImg/%d.png",pokemonId); //not yet
            ImageLoader.getInstance().displayImage(imgUrl,mAppearanceImg);

        }

        public void setSelected()
        {
            mData.isSelected = !mData.isSelected;
            mRowView.setActivated(mData.isSelected);

        }

        @Override
        public void onClick(View v) {
            int viewId = v.getId();
            if(viewId == R.id.appearanceImg)
            {
                setSelected();
            }
        }
    }


}
