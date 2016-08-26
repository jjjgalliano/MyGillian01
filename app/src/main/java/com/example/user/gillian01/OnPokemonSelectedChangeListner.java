package com.example.user.gillian01;

import com.example.user.gillian01.model.OwnedPokemonInfo;

/**
 * Created by Gillian on 16/8/25.
 */
public interface OnPokemonSelectedChangeListner {

  //  void onSelectedChanged(OwnedPokemonInfo ownedPokemonInfo);// interface default  public

    void onSelectedChange(OwnedPokemonInfo ownedPokemonInfo);
}
