package com.example.myapplication.ui.contact

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.contact.ContactEvent
import com.example.myapplication.data.contact.ContactState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactDialog (
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(ContactEvent.HideDialog)
        },
        text = {
            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = state.name,
                    onValueChange = {
                        onEvent(ContactEvent.SetName(it))
                    },
                    placeholder = {
                        Text(text = "Type Here")
                    },
                    label = {
                        Text(text = "Name")
                    },
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            androidx.compose.material3.Icon(imageVector = Icons.Filled.Person, contentDescription = "Person Icon")
                        }
                    },
                    singleLine = true
                )
                OutlinedTextField(
                    value = state.phoneNumber,
                    onValueChange = {
                        if (it.length <= 14) onEvent(ContactEvent.SetPhoneNumber(it))
                    },
                    placeholder = {
                        Text(text = "Type Here")
                    },
                    label = {
                        Text(text = "Phone Number")
                    },
                    maxLines = 1,
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            androidx.compose.material3.Icon(imageVector = Icons.Filled.Phone, contentDescription = "Person Icon")
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(ContactEvent.SaveContact)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}