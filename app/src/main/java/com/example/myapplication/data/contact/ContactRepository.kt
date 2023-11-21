package com.example.myapplication.data.contact

import com.example.myapplication.util.SortType

sealed interface ContactRepository {
    object SaveContact: ContactRepository
    object ShowDialog: ContactRepository
    object HideDialog: ContactRepository
    data class SetName(val name: String): ContactRepository
    data class SetPhoneNumber(val phoneNumber: String): ContactRepository
    data class SortContacts(val sortType: SortType): ContactRepository
    data class DeleteContacts(val contact: Contact): ContactRepository
}