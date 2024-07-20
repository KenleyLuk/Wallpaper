package com.kenley.wallpaper.ui.theme.view.category.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kenley.wallpaper.apiData.ImageData
import com.kenley.wallpaper.ui.theme.view.category.repo.PictureListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PictureListViewModel @Inject constructor(private val repository: PictureListRepository) : ViewModel() {
    private val _image = MutableLiveData<List<ImageData>>()
    val image: LiveData<List<ImageData>> = _image

    fun getImage(searchStr: String, orientation: String, order: String, type: String, colors: String) {
        viewModelScope.launch {
            val result = repository.getImage(searchStr, orientation, order, type, colors)
            _image.value = result
        }
    }
}