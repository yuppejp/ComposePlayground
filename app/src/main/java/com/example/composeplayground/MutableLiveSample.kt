package com.example.composeplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composeplayground.ui.theme.ComposePlaygroundTheme

class MutableLiveSampleViewModel : ViewModel() {
    //val items = MutableLiveData<ArrayList<String>>(ArrayList())
    val items = MutableLiveData<MutableList<String>>(mutableStateListOf())
    var counter = 0

    init {
        add()
    }

    fun add() {
        counter++
        items.value?.add(counter.toString())

        //items.postValue(items.value)
        //items.value = items.value
    }
}

@Composable
fun MutableLiveSampleView(viewModel: MutableLiveSampleViewModel) {
    val items = viewModel.items.observeAsState()

    Column {
        Button(onClick = {
            viewModel.add()
        }) {
            Text("add")
        }

        items.value?.forEach { item ->
            Text(item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MutableLiveSamplePreview() {
    ComposePlaygroundTheme {
        MutableLiveSampleView(MutableLiveSampleViewModel())
    }
}
