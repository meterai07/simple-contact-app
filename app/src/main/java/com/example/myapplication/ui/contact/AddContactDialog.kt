package com.example.myapplication.ui.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.contact.ContactEvent
import com.example.myapplication.data.contact.ContactState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactDialog(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val darkOverlayColor = Color(0x99000000)
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(ContactEvent.HideDialog)
        },
        containerColor = Color.White,
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add Contact",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
        },
        text = {
            Column(
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
                            Icon(imageVector = Icons.Filled.Person, contentDescription = "Person Icon")
                        }
                    },
                    singleLine = true,
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
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Phone, contentDescription = "Person Icon")
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        dismissButton = {
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                OutlinedButton(
                    onClick = {
                        onEvent(ContactEvent.HideDialog)
                    },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF3687D9)),
                ) {
                    Text(text = "Cancel")
                }
            }
        },
        confirmButton = {
            Box(
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(
                    onClick = {
                        onEvent(ContactEvent.SaveContact)
                    },
                    colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color(0xFF3687D9)),
                ) {
                    Text(text = "Save")
                }
            }
        }
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkOverlayColor)
    )
}
