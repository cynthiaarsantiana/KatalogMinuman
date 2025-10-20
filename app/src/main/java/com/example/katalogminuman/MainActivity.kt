package com.example.katalogminuman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.katalogminuman.ui.theme.KatalogMinumanTheme

// Data class untuk objek minuman
data class Minuman(val nama: String, val gambar: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KatalogMinumanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KatalogMinumanApp()
                }
            }
        }
    }
}

@Composable
fun KatalogMinumanApp() {
    var isGrid by remember { mutableStateOf(false) }

    // Daftar data minuman
    val daftarMinuman = listOf(
        Minuman("Kopi Hitam", R.drawable.kopi),
        Minuman("Teh Hijau", R.drawable.teh),
        Minuman("Jus Jeruk", R.drawable.jus),
        Minuman("Susu Coklat", R.drawable.susu),
        Minuman("Air Mineral", R.drawable.air)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Tombol ganti tampilan
        Button(
            onClick = { isGrid = !isGrid },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = if (isGrid) "Tampilkan List" else "Tampilkan Grid")
        }

        // Tampilkan daftar atau grid
        if (isGrid) {
            MinumanGrid(daftarMinuman)
        } else {
            MinumanList(daftarMinuman)
        }
    }
}

@Composable
fun MinumanList(minumanList: List<Minuman>) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(minumanList) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFB2DFDB))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = item.gambar),
                        contentDescription = item.nama,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(end = 16.dp)
                    )
                    Text(
                        text = item.nama,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun MinumanGrid(minumanList: List<Minuman>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(minumanList.size) { index ->
            val item = minumanList[index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFCC80))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = item.gambar),
                        contentDescription = item.nama,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = item.nama,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
