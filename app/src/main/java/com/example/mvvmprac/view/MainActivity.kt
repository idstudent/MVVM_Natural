package com.example.mvvmprac.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmprac.R
import com.example.mvvmprac.databinding.ActivityMainBinding
import com.example.mvvmprac.model.Contact
import com.example.mvvmprac.viewmodel.ContactViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var contactViewModel : ContactViewModel
    private lateinit var binding : ActivityMainBinding

    companion object {
        fun moveAddView(context : Context)  : Intent{
            var intent = Intent(context, AddActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDatabinding()
        setViewList(binding.mainRecycleview)
    }

    private fun initDatabinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        contactViewModel = ContactViewModel(this);
        binding.mainViewModel = contactViewModel
    }

    private fun setViewList(recyclerView: RecyclerView) {
        val adapter = ContactAdapter({
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra(AddActivity.EXTRA_CONTACT_NAME, it.name)
            intent.putExtra(AddActivity.EXTRA_CONTACT_NUMBER, it.number)
            intent.putExtra(AddActivity.EXTRA_CONTACT_ID, it.id)
            startActivity(intent)
        }, {
            deleteDialog(it)
        })

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        contactViewModel.getAll().observe(this, Observer<List<Contact>> {
            adapter.setContacts(it)
        })
    }
    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                lifecycleScope.launch(Dispatchers.IO) {
                    contactViewModel.delete(contact)
                }
            }
        builder.show()
    }
}
