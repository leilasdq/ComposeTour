package com.example.composecodelab6

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecodelab6.ui.theme.ComposeCodelab6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCodelab6Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TitleBar()
                        },
                        floatingActionButton = {
                            FabButtonSample()
                        },
                        bottomBar = {
                            BottomNavSample()
                        }
                    ) { innerPadding ->
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            CardBoxItems("Android", "3 min ago")
                            ButtonOne()
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun TitleBar() {
    val activity = (LocalContext.current as? Activity)
    Column {
        TopAppBar(
            title = { Text(text = "Page title") },
            navigationIcon = {
                IconButton(onClick = { activity?.finish() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Menu Btn")
                }
            },
            elevation = 4.dp,
            actions = {
                ToolbarActions()
            }
        )
    }

}

@Composable
fun ToolbarActions() {
    val context = LocalContext.current
    Row(
        modifier = Modifier.padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(painterResource(R.drawable.ic_create), "create new chat",
            Modifier.clickable {
                Toast.makeText(context, "New clicked", Toast.LENGTH_SHORT).show()
            })
        Spacer(modifier = Modifier.padding(4.dp))
        Icon(painterResource(R.drawable.ic_search), "content search",
            Modifier.clickable {
                Toast.makeText(context, "Search clicked", Toast.LENGTH_SHORT).show()
            })
        Spacer(modifier = Modifier.padding(4.dp))
        Icon(painterResource(R.drawable.ic_support), "Support Part",
            Modifier.clickable {
                Toast.makeText(context, "Support clicked", Toast.LENGTH_SHORT).show()
            })
        Spacer(modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun CardBoxItems(name: String, lastSeen: String) {
    Row(modifier = Modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(4.dp))
        .clickable { }
        .padding(top = 16.dp, bottom = 16.dp)
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
fun ButtonOne() {
    Column(
        modifier = Modifier
            .padding(start = 8.dp)
        //.align(Alignment.CenterVertically)
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
        ) {
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Image(
                    painterResource(R.drawable.ic_photo), "content description",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(Modifier.size(4.dp))
                Text("Button")
            }
        }
    }
}

@Composable
fun FabButtonSample() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.clip(CircleShape)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_photo),
            contentDescription = "content description"
        )
    }
}

@Composable
fun BottomNavSample() {
    val context = LocalContext.current
    val mappedList = listOf(
        "Home" to Icons.Filled.Home,
        "Fav" to Icons.Filled.Favorite,
        "Setting" to Icons.Filled.Settings,
        "Info" to Icons.Filled.Info,
    )
    val items = listOf("Songs", "Artists", "Playlists")
    var selectedItem by remember { mutableStateOf(0) }
    BottomNavigation {
        mappedList.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(item.second, contentDescription = null) },
                label = { Text(item.first) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeCodelab6Theme {
        Scaffold(
            topBar = {
                TitleBar()
            },
            floatingActionButton = {
                FabButtonSample()
            },
            bottomBar = {
                BottomNavSample()
            }
        ) { innerPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                CardBoxItems("Android", "3 min ago")
                ButtonOne()
            }

        }
    }

}