package com.example.myapplication.data.contact

import com.example.myapplication.util.SortType

data class ContactState(
    val contacts: List<Contact> = emptyList(),
    val name: String = "",
    val phoneNumber: String = "",
    val isAddingContact: Boolean = false,
    val sortType: SortType = SortType.NAME
)
