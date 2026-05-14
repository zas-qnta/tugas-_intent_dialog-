package com.zaskiaqnita_2430511024.registrasiseminar

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

class HasilActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nama = intent.getStringExtra("NAMA") ?: ""
        val nim = intent.getStringExtra("NIM") ?: ""
        val prodi = intent.getStringExtra("PRODI") ?: ""
        val email = intent.getStringExtra("EMAIL") ?: ""

        val nomorTelephone =
            intent.getStringExtra(
                "NOMOR_TELEPHONE"
            ) ?: ""

        setContent {

            MaterialTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    HasilRegistrasiScreen(
                        nama = nama,
                        nim = nim,
                        prodi = prodi,
                        email = email,
                        nomorTelephone = nomorTelephone,

                        onOpenWebsite = {

                            val browserIntent = Intent(
                                Intent.ACTION_VIEW,

                                "https://unpad.ac.id".toUri()
                            )

                            startActivity(browserIntent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HasilRegistrasiScreen(
    nama: String,
    nim: String,
    prodi: String,
    email: String,
    nomorTelephone: String,
    onOpenWebsite: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Hasil Registrasi Seminar",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Nama : $nama")
        Text(text = "NIM : $nim")
        Text(text = "Prodi : $prodi")
        Text(text = "Email : $email")
        Text(text = "Nomor Telephone : $nomorTelephone")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onOpenWebsite,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Buka Website Kampus")
        }
    }
}