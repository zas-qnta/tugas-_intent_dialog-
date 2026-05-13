package com.example.registrasiseminarcompos

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    FormRegistrasiScreen(
                        onSubmit = {
                                nama,
                                nim,
                                prodi,
                                email,
                                nomorTelephone ->

                            val intent = Intent(
                                this,
                                HasilActivity::class.java
                            ).apply {

                                putExtra("NAMA", nama)
                                putExtra("NIM", nim)
                                putExtra("PRODI", prodi)
                                putExtra("EMAIL", email)
                                putExtra(
                                    "NOMOR_TELEPHONE",
                                    nomorTelephone
                                )
                            }

                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FormRegistrasiScreen(
    onSubmit: (
        String,
        String,
        String,
        String,
        String
    ) -> Unit
) {

    var nama by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var prodi by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var nomorTelephone by remember { mutableStateOf("") }

    var showWarningDialog by remember {
        mutableStateOf(false)
    }

    var showConfirmDialog by remember {
        mutableStateOf(false)
    }

    var showResetDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Form Registrasi Seminar Mahasiswa",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nama,
            onValueChange = {
                nama = it
            },

            label = {
                Text("Nama Mahasiswa")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = nim,
            onValueChange = {
                nim = it
            },

            label = {
                Text("NIM")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = prodi,
            onValueChange = {
                prodi = it
            },

            label = {
                Text("Program Studi")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },

            label = {
                Text("Email")
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = nomorTelephone,
            onValueChange = {
                nomorTelephone = it
            },

            label = {
                Text("Nomor Telephone")
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                if (
                    nama.isBlank() ||
                    nim.isBlank() ||
                    prodi.isBlank() ||
                    email.isBlank() ||
                    nomorTelephone.isBlank()
                ) {

                    showWarningDialog = true

                } else {

                    showConfirmDialog = true
                }
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Daftar Seminar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                showResetDialog = true
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Reset Form")
        }
    }


    if (showWarningDialog) {

        AlertDialog(
            onDismissRequest = {
                showWarningDialog = false
            },

            title = {
                Text("Peringatan")
            },

            text = {
                Text("Semua data harus diisi terlebih dahulu.")
            },

            confirmButton = {

                TextButton(
                    onClick = {
                        showWarningDialog = false
                    }
                ) {

                    Text("OK")
                }
            }
        )
    }


    if (showConfirmDialog) {

        AlertDialog(
            onDismissRequest = {
                showConfirmDialog = false
            },

            title = {
                Text("Konfirmasi")
            },

            text = {
                Text("Apakah data registrasi seminar akan dikirim?")
            },

            confirmButton = {

                TextButton(
                    onClick = {

                        showConfirmDialog = false

                        onSubmit(
                            nama,
                            nim,
                            prodi,
                            email,
                            nomorTelephone
                        )
                    }
                ) {

                    Text("Ya")
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showConfirmDialog = false
                    }
                ) {

                    Text("Batal")
                }
            }
        )
    }


    if (showResetDialog) {

        AlertDialog(
            onDismissRequest = {
                showResetDialog = false
            },

            title = {
                Text("Reset Form")
            },

            text = {
                Text("Apakah semua data ingin dihapus?")
            },

            confirmButton = {

                TextButton(
                    onClick = {

                        nama = ""
                        nim = ""
                        prodi = ""
                        email = ""
                        nomorTelephone = ""

                        showResetDialog = false
                    }
                ) {

                    Text("Ya")
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showResetDialog = false
                    }
                ) {

                    Text("Batal")
                }
            }
        )
    }
}