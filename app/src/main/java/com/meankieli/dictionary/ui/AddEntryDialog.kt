package com.meankieli.dictionary.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun AddEntryDialog(
    onDismiss: () -> Unit,
    onAddEntry: (meankieli: String, swedish: String, pos: String, user: String) -> Unit
) {
    var meankieli by remember { mutableStateOf("") }
    var swedish by remember { mutableStateOf("") }
    var pos by remember { mutableStateOf("") }
    var user by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    "Add New Entry",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = meankieli,
                    onValueChange = { meankieli = it },
                    label = { Text("Meänkieli Word") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = swedish,
                    onValueChange = { swedish = it },
                    label = { Text("Swedish Translation") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = pos,
                    onValueChange = { pos = it },
                    label = { Text("Part of Speech") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = user,
                    onValueChange = { user = it },
                    label = { Text("Added By") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            if (meankieli.isNotBlank() && swedish.isNotBlank() && pos.isNotBlank() && user.isNotBlank()) {
                                onAddEntry(meankieli, swedish, pos, user)
                                onDismiss()
                            }
                        },
                        enabled = meankieli.isNotBlank() && swedish.isNotBlank() && pos.isNotBlank() && user.isNotBlank()
                    ) {
                        Text("Add")
                    }
                }
            }
        }
    }
} 