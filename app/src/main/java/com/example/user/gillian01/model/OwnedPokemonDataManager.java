package com.example.user.gillian01.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by user on 2016/8/24.
 */
public class OwnedPokemonDataManager {

    Context mcontext  ;// system app defalut context ; access system service
    ArrayList<OwnedPokemonInfo> ownedPokemonInfos = null;

    public OwnedPokemonDataManager(Context context)
    {
        mcontext = context;


    }

    public void loadListViewData(){
        ownedPokemonInfos = new ArrayList<>();

        BufferedReader reader;
        String line = null;
        String[] datafields = null;

        try{
                reader = new BufferedReader(new InputStreamReader(mcontext.getAssets().open("pokemon_data.csv")));

            while((line = reader.readLine()) != null)
            {
                datafields = line.split(",");
                ownedPokemonInfos.add(construcrPokemonInfo(datafields));
            }
            reader.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private OwnedPokemonInfo construcrPokemonInfo(String[] dataFileds)
    {
        
        return null;
    }


}
