package uvg.moviles.laboratorio5

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Directions
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Update
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uvg.moviles.laboratorio5.ui.theme.Laboratorio5Theme

private const val FULL_NAME = "Cristian Estuardo Orellana Dieguez"
private const val RESTAURANT_NAME = "Donde Mario's Campestre"
private const val RESTAURANT_ADDRESS = "Km 27, Santa Elena Barillas"
private const val RESTAURANT_HOURS = "7:00 a. m. - 7:30 p. m."
private const val RESTAURANT_LATITUDE = 14.4618125
private const val RESTAURANT_LONGITUDE = -90.4941094

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Laboratorio5Theme {
                RestaurantScheduleScreen()
            }
        }
    }
}

@Composable
private fun RestaurantScheduleScreen() {
    val context = LocalContext.current

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            UpdateBanner(onDownloadClick = { openWhatsAppInPlayStore(context) })

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Jueves",
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "9 de julio",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                OutlinedButton(onClick = {
                    Toast.makeText(context, "Jornada terminada", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Terminar jornada")
                }
            }

            RestaurantCard(
                onStartClick = {
                    Toast.makeText(context, FULL_NAME, Toast.LENGTH_SHORT).show()
                },
                onDirectionsClick = { openRestaurantInMaps(context) },
                onDetailsClick = {
                    Toast.makeText(
                        context,
                        "Parrilladas y mariscos\nQQ",
                        Toast.LENGTH_LONG,
                    ).show()
                },
            )
        }
    }
}

@Composable
private fun UpdateBanner(onDownloadClick: () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
            ) {
                Icon(
                    imageVector = Icons.Rounded.Update,
                    contentDescription = null,
                    modifier = Modifier.padding(12.dp),
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Actualización disponible",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge,
            )

            TextButton(onClick = onDownloadClick) {
                Icon(
                    imageVector = Icons.Rounded.Download,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Descargar")
            }
        }
    }
}

@Composable
private fun RestaurantCard(
    onStartClick: () -> Unit,
    onDirectionsClick: () -> Unit,
    onDetailsClick: () -> Unit,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        ),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = RESTAURANT_NAME,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )

                IconButton(onClick = onDirectionsClick) {
                    Icon(
                        imageVector = Icons.Rounded.Directions,
                        contentDescription = "Abrir indicaciones en Google Maps",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            Text(
                text = RESTAURANT_ADDRESS,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = RESTAURANT_HOURS,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = onStartClick,
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Iniciar")
                }

                TextButton(
                    onClick = onDetailsClick,
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Detalles")
                }
            }
        }
    }
}

private fun openWhatsAppInPlayStore(context: Context) {
    val packageName = "com.whatsapp"
    val playStoreIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("market://details?id=$packageName"),
    )

    try {
        context.startActivity(playStoreIntent)
    } catch (_: ActivityNotFoundException) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName"),
            ),
        )
    }
}

private fun openRestaurantInMaps(context: Context) {
    val label = Uri.encode(RESTAURANT_NAME)
    val location = "$RESTAURANT_LATITUDE,$RESTAURANT_LONGITUDE"
    val googleMapsIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("geo:$location?q=$location($label)"),
    ).setPackage("com.google.android.apps.maps")

    try {
        context.startActivity(googleMapsIntent)
    } catch (_: ActivityNotFoundException) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/search/?api=1&query=$location"),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RestaurantSchedulePreview() {
    Laboratorio5Theme {
        RestaurantScheduleScreen()
    }
}