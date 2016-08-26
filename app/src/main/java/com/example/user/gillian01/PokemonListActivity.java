package com.example.user.gillian01;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.gillian01.adapter.PokemonListViewAdapter;
import com.example.user.gillian01.model.OwnedPokemonDataManager;
import com.example.user.gillian01.model.OwnedPokemonInfo;

import java.util.ArrayList;

public class PokemonListActivity extends CustomizedActivity implements  OnPokemonSelectedChangeListner {

//public class PokemonListActivity extends AppCompatActivity implements  OnPokemonSelectedChangeListner {


    public  PokemonListViewAdapter arrayAdapter;
    public ArrayList<OwnedPokemonInfo> ownedPokemonInfos;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
       // avtivityName = this.getClass().getSimpleName();
       // super.onCreate(savedInstanceState, persistentState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        avtivityName = this.getClass().getSimpleName();

        OwnedPokemonDataManager dataManager = new OwnedPokemonDataManager(this);
        dataManager.loadListViewData();

        ownedPokemonInfos = dataManager.getOwnedPokemonInfos();

        OwnedPokemonInfo[] initPokemonInfos = dataManager.getIntiPokemonInfos();
        Intent srcIntent = getIntent();
        int selectedIndex = srcIntent.getIntExtra(MainActivity.selectedPokemonIndexKey, 0);
        ownedPokemonInfos.add(0, initPokemonInfos[selectedIndex]);

        ListView listView = (ListView)findViewById(R.id.listView);
        arrayAdapter = new PokemonListViewAdapter(this,
                R.layout.row_view_of_pokemon_list,
                ownedPokemonInfos);
        arrayAdapter.pokemonSelectedChangeListener = this;


        listView.setAdapter(arrayAdapter);
        // a lot

    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int itemId = item.getItemId();
        if(itemId == R.id.action_delete) {
            for(OwnedPokemonInfo ownedPokemonInfo : arrayAdapter.selectedPokemons) {
                ownedPokemonInfos.remove(ownedPokemonInfo);
            }
            arrayAdapter.selectedPokemons.clear();
            arrayAdapter.notifyDataSetChanged();
            invalidateOptionsMenu();
//              can use this
//            arrayAdapter.remove();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        if(arrayAdapter.selectedPokemons.isEmpty()) {
            return false; //not showing anything on action bar
        }
        else {
            getMenuInflater().inflate(R.menu.selected_pokemon_action_bar, menu);
            return true; //show the menu items on action bar
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        OwnedPokemonDataManager dataManager = new OwnedPokemonDataManager(this); // activity can be as context
        dataManager.loadListViewData();

       ownedPokemonInfos = dataManager.getOwnedPokemonInfos();

        /*
        ArrayList<String> pokemonNames = new ArrayList<>();
        for(OwnedPokemonInfo ownedPokemonInfo : ownedPokemonInfos)
        {
            pokemonNames.add(ownedPokemonInfo.name);
        }


        ListView listView =(ListView)findViewById(R.id.listView); //??
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                pokemonNames);

        listView.setAdapter(arrayAdapter);

        */


        ListView listView =(ListView)findViewById(R.id.listView);
         arrayAdapter = new PokemonListViewAdapter(this,
                R.layout.row_view_of_pokemon_list,
                ownedPokemonInfos);

        listView.setAdapter(arrayAdapter);

    }


//    public void onSelectedChange(OwnedPokemonInfo ownedPokemonInfo)
//    {
//        invalidateOptionsMenu();
//    }

    @Override
    public void onSelectedChange(OwnedPokemonInfo ownedPokemonInfo) {
        invalidateOptionsMenu();
    }
}
