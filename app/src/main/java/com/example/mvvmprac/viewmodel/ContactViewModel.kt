package com.example.mvvmprac.viewmodel

import android.content.Context
import android.view.View
import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import com.example.mvvmprac.model.Contact
import com.example.mvvmprac.model.ContactDatabase
import com.example.mvvmprac.view.AddActivity
import com.example.mvvmprac.view.MainActivity

class ContactViewModel(private var context: Context) : BaseObservable() {
    private val contactDatabase = ContactDatabase.getInstance(context)

    fun getAll(): LiveData<List<Contact>> {
        return contactDatabase?.contactDao()?.getAll()!!
    }
    fun insert(contact : Contact) {
        contactDatabase?.contactDao()?.insert(contact)
    }
    fun delete(contact : Contact) {
        contactDatabase?.contactDao()?.delete(contact)
    }

    fun addClick(view : View) {
        context.startActivity(MainActivity.moveAddView(view.context))
    }
}
