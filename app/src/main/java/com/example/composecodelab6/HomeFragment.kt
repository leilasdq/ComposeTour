package com.example.composecodelab6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.composecodelab6.databinding.FragmetHomeBinding
import com.example.composecodelab6.ui.theme.ComposeCodelab6Theme
import java.util.*

class HomeFragment: Fragment() {

    private var _binding: FragmetHomeBinding? = null
    private val binding = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmetHomeBinding.inflate(inflater, container, true)

        val list = mutableListOf<ChatItemDataClass>()
        for (i in 1..100) {
            val time = if (i%8==0) "last seen recently" else "${(1..60).random()} minutes ago"
            val data = ChatItemDataClass(name = "num #$i", lastSeen = time )
            list.add(data)
        }

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ComposeCodelab6Theme {
                    Scaffold { innerPadding ->
                        ChatLists(list = list, modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

@Composable
fun ChatLists(list: List<ChatItemDataClass>, modifier: Modifier) {
    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState,
        modifier = modifier
    ) {
        list.forEach { data ->
            item {
                ChatListItems(name = data.name, lastSeen = data.lastSeen)
            }
        }
    }
}

@Composable
fun ChatListItems(name: String, lastSeen: String) {
    Row(modifier = Modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(4.dp))
        .clickable { }
        .fillMaxWidth()
    ) {
        Surface(
            Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {

        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = name, style = MaterialTheme.typography.h6)
            Text(text = lastSeen, style = MaterialTheme.typography.body1)
        }
    }
}



@Composable
@Preview
fun Preview() {
    val list = mutableListOf<ChatItemDataClass>()
    for (i in 1..100) {
        val time = if (i%8==0) "last seen recently" else "${(1..60).random()} minutes ago"
        val data = ChatItemDataClass(name = "num #$i", lastSeen = time )
        list.add(data)
    }
    ComposeCodelab6Theme {
        Scaffold { innerPadding ->
            ChatLists(list = list, modifier = Modifier.padding(innerPadding))
        }
    }
}

data class ChatItemDataClass(
    val name: String,
    val lastSeen: String = "recently"
)