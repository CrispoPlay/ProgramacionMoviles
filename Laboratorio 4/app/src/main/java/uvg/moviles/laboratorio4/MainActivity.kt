/**
 * Nombre: Cristian Estuardo Orellana Dieguez
 * Carnet: 25664
 * Laboratorio 4: configuración de UI
 */
package uvg.moviles.laboratorio4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uvg.moviles.laboratorio4.ui.theme.Laboratorio4Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Laboratorio4Theme {
                PantallaLaboratorio4()
            }
        }
    }
}

@Composable
fun PantallaLaboratorio4() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        PortadaUVG(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@Composable
fun PortadaUVG(modifier: Modifier = Modifier) {
    val verdeUVG = Color(0xFF116B37)

    Box(
        modifier = modifier
            .background(Color.White)
            .padding(
                horizontal = 14.dp,
                vertical = 10.dp
            )
            .border(
                width = 3.dp,
                color = verdeUVG
            )
            .padding(
                horizontal = 12.dp,
                vertical = 24.dp
            )
    ) {
        Image(
            painter = painterResource(R.drawable.logo_uvg),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.Center)
                .size(500.dp)
                .alpha(0.10f)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .offset(y = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Universidad del Valle\nde Guatemala",
                fontSize = 21.sp,
                lineHeight = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Programación de plataformas\nmóviles, Sección 30",
                fontSize = 17.sp,
                lineHeight = 21.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(18.dp))

            FilaInformacion(
                titulo = "INTEGRANTES",
                contenido = "Cristian Orellana\nRicardo Escobar\nSergio López"
            )

            Spacer(modifier = Modifier.height(14.dp))

            FilaInformacion(
                titulo = "CATEDRÁTICO",
                contenido = "Juan Carlos Durini"
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Cristian Estuardo Orellana Dieguez\n25664",
                fontSize = 11.sp,
                lineHeight = 14.sp,
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun FilaInformacion(
    titulo: String,
    contenido: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = titulo,
            modifier = Modifier.width(88.dp),
            fontSize = 10.sp,
            lineHeight = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )


        Text(
            text = contenido,
            modifier = Modifier.weight(1f),
            fontSize = 11.sp,
            lineHeight = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.DarkGray
        )
    }
}

@Preview(
    name = "Portada UVG",
    showBackground = true,
    widthDp = 286,
    heightDp = 455
)
@Composable
fun PortadaUVGPreview() {
    Laboratorio4Theme {
        PortadaUVG(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(
    name = "Portada en teléfono",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PortadaTelefonoPreview() {
    Laboratorio4Theme {
        PantallaLaboratorio4()
    }
}