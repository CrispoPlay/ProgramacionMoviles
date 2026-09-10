package uvg.moviles.Laboratorio6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val PrimaryBlue = Color(0xFF415F91)
private val IncrementGreen = Color(0xFF198A3A)
private val DecrementRed = Color(0xFFC62828)

data class CounterMovement(
    val value: Int,
    val isIncrement: Boolean
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CounterTheme {
                CounterApp()
            }
        }
    }
}

@Composable
fun CounterApp() {
    var counter by remember { mutableIntStateOf(0) }
    var increments by remember { mutableIntStateOf(0) }
    var decrements by remember { mutableIntStateOf(0) }
    var maximum by remember { mutableIntStateOf(0) }
    var minimum by remember { mutableIntStateOf(0) }

    val history = remember {
        mutableStateListOf<CounterMovement>()
    }

    fun changeCounter(delta: Int) {
        counter += delta

        if (delta > 0) {
            increments++
        } else {
            decrements++
        }

        maximum = maxOf(maximum, counter)
        minimum = minOf(minimum, counter)

        history.add(
            CounterMovement(
                value = counter,
                isIncrement = delta > 0
            )
        )
    }

    fun reset() {
        counter = 0
        increments = 0
        decrements = 0
        maximum = 0
        minimum = 0
        history.clear()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Button(
                onClick = { reset() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Text(
                    text = "Reiniciar",
                    fontSize = 17.sp
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 20.dp,
                    vertical = 24.dp
                )
        ) {
            Text(
                text = "Cristian Estuardo Orellana Dieguez",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CounterButton(symbol = "−") {
                    changeCounter(-1)
                }

                Text(
                    text = counter.toString(),
                    modifier = Modifier.padding(horizontal = 28.dp),
                    fontSize = 58.sp,
                    fontWeight = FontWeight.Medium
                )

                CounterButton(symbol = "+") {
                    changeCounter(1)
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Estadísticas",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            StatisticRow("Total incrementos", increments)
            StatisticRow("Total decrementos", decrements)
            StatisticRow("Valor máximo", maximum)
            StatisticRow("Valor mínimo", minimum)
            StatisticRow("Total cambios", increments + decrements)

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "Historial",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (history.isEmpty()) {
                Text(
                    text = "Aún no hay movimientos",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(historyGridHeight(history.size)),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    userScrollEnabled = false
                ) {
                    items(history) { movement ->
                        HistoryItem(movement)
                    }
                }
            }
        }
    }
}

@Composable
private fun CounterButton(
    symbol: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(52.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryBlue
        )
    ) {
        Text(
            text = symbol,
            fontSize = 28.sp
        )
    }
}

@Composable
private fun StatisticRow(
    label: String,
    value: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp
        )

        Text(
            text = value.toString(),
            fontSize = 17.sp
        )
    }
}

@Composable
private fun HistoryItem(
    movement: CounterMovement
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = if (movement.isIncrement) {
                    IncrementGreen
                } else {
                    DecrementRed
                },
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = movement.value.toString(),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

private fun historyGridHeight(itemCount: Int) =
    (((itemCount + 4) / 5) * 56).dp

@Composable
private fun CounterTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = PrimaryBlue
        ),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun CounterPreview() {
    CounterTheme {
        CounterApp()
    }
}