package com.example.mvvmprac.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mvvmprac.model.Contact
import com.example.mvvmprac.model.ContactDatabase

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val contactDatabase = ContactDatabase.getInstance(application)

    fun getAll(): LiveData<List<Contact>> {
        return contactDatabase?.contactDao()?.getAll()!!
    }
    fun insert(contact : Contact) {
        contactDatabase?.contactDao()?.insert(contact)
    }
    fun delete(contact : Contact) {
        contactDatabase?.contactDao()?.delete(contact)
    }
}