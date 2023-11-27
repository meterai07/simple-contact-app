package com.example.myapplication.data.contact

import com.example.myapplication.util.SortType

sealed interface ContactEvent {
    object SaveContact: ContactEvent
    object ShowDialog: ContactEvent
    object HideDialog: ContactEvent
    data class SetName(val name: String): ContactEvent
    data class SetPhoneNumber(val phoneNumber: String): ContactEvent
    data class SortContacts(val sortType: SortType): ContactEvent
    data class DeleteContacts(val contact: Contact): ContactEvent
}