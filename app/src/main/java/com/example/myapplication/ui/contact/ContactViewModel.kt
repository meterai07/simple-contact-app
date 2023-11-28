package com.example.myapplication.ui.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.contact.Contact
import com.example.myapplication.data.contact.ContactDao
import com.example.myapplication.data.contact.ContactEvent
import com.example.myapplication.data.contact.ContactState
import com.example.myapplication.util.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class ContactViewModel (
    private val dao: ContactDao
):ViewModel() {
    private val _sortType = MutableStateFlow(SortType.NAME)
    private val _contacts = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.NAME -> dao.getContactsOrderedByName()
                SortType.PHONE_NUMBER -> dao.getContactsOrderedByPhoneNumber()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(ContactState())
    val state = combine(_state, _sortType, _contacts) { state, sortType, contacts ->
        state.copy(
            sortType = sortType,
            contacts = contacts
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    fun onEvent(event: ContactEvent) {
        when(event) {
            is ContactEvent.DeleteContacts -> {
                viewModelScope.launch {
                    dao.deleteContact(event.contact)
                }
            }
            ContactEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingContact = false,
                    name = "",
                    phoneNumber = ""
                ) }
            }
            ContactEvent.SaveContact -> {
                val name = state.value.name
                val phoneNumber = state.value.phoneNumber

                if (name.isBlank() || phoneNumber.isBlank()){
                    return
                }

                val contact = Contact(
                    name = name,
                    phoneNumber = phoneNumber
                )

                viewModelScope.launch {
                    dao.insertContact(contact)
                }

                _state.update { it.copy(
                    isAddingContact = false,
                    name = "",
                    phoneNumber = ""
                ) }
            }
            is ContactEvent.SetName -> {
                _state.update { it.copy(
                    name = event.name
                ) }
            }
            is ContactEvent.SetPhoneNumber -> {
                _state.update { it.copy(
                    phoneNumber = event.phoneNumber
                ) }
            }
            ContactEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingContact = true
                ) }
            }
            is ContactEvent.SortContacts -> {
                _sortType.value = event.sortType
            }
        }
    }
}