package com.eargaez.mvvm_clean.presentation.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eargaez.mvvm_clean.domain.Resource
import com.eargaez.mvvm_clean.domain.usecases.GetBeersUseCase
import com.eargaez.mvvm_clean.domain.models.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeersViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCase
): ViewModel() {

    private val _result = MutableStateFlow<Resource<List<Beer>>?>(null)
    val result: StateFlow<Resource<List<Beer>>?> get() = _result
    private val _beers = MutableLiveData<MutableList<Beer>>(mutableListOf())
    val beers : LiveData<MutableList<Beer>> get() = _beers

    private var page: Int = 0

    init {
        getBeers()
    }

    fun getBeers() = viewModelScope.launch {
        page++
        getBeersUseCase.invoke(page)
            .collect {
                _result.value = it
            }
    }

    fun addItems(response: Resource<List<Beer>>) {
        result.value.let { result ->
            result ?: return@let
            if (response.data.isEmpty()) return@let
            val new = response.data
            beers.value?.addAll(new)
            _beers.postValue(beers.value)
        }
    }
}