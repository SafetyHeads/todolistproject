package com.company.todolistproject.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.company.todolistproject.R
import com.company.todolistproject.domain.Task

class MainActivity : FragmentActivity() {
    var item: EditText? = null
    var add: Button? = null
    var listView: ListView? = null
    lateinit var arrayAdapter: ArrayAdapter<Task>

    lateinit var viewModel: MainViewModel

    // M-V-C -> MVVM + clean arch


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        item = findViewById(R.id.editText)
        add = findViewById(R.id.button)
        listView = findViewById(R.id.list)

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, viewModel.tasks)

        listView?.setAdapter(arrayAdapter)

        add?.setOnClickListener(View.OnClickListener {
            val itemName = item?.getText().toString()

            viewModel.saveTask(itemName)
            item?.setText("")
        })

        viewModel.tasksLiveData.observe(this, Observer {newList->
            arrayAdapter.clear()
            arrayAdapter.addAll(newList)
            arrayAdapter.notifyDataSetChanged()
        })


        listView?.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val alert = AlertDialog.Builder(this@MainActivity)
            alert.setTitle("Delete")
            alert.setMessage("Do you want to delete this item from list?")
            alert.setCancelable(false)
            alert.setNegativeButton("No") { dialog, which -> dialog.cancel() }
            alert.setPositiveButton("Yes") { dialog, which ->

                // TODO create delete task

//                itemlist!!.removeAt(position)
//                arrayAdapter!!.notifyDataSetChanged()
//                writeData(itemlist, applicationContext)
            }
            val alertDialog = alert.create()
            alertDialog.show()
        }
    }
}