package com.acoders.readnetic.ui.view.home

import androidx.lifecycle.ViewModel
import com.acoders.readnetic.usecase.GetBestsellersUseCase
import com.acoders.readnetic.usecase.SwitchBookFavoriteUseCase

class HomeViewModel(
    getBestSellersUseCase: GetBestsellersUseCase,
    private val switchBookFavoriteUseCase: SwitchBookFavoriteUseCase
): ViewModel(){
}