package com.company.todolistproject

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.company.todolistproject.FileHelper.readData
import com.company.todolistproject.FileHelper.writeData

class MainActivity : FragmentActivity() {
    var item: EditText? = null
    var add: Button? = null
    var listView: ListView? = null
    var itemlist: MutableList<String> = mutableListOf()
    var arrayAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        item = findViewById(R.id.editText)
        add = findViewById(R.id.button)
        listView = findViewById(R.id.list)
        itemlist = readData(this)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemlist)

        listView?.setAdapter(arrayAdapter)

        add?.setOnClickListener(View.OnClickListener {
            val itemName = item?.getText().toString()
            itemlist?.add(itemName)
            item?.setText("")
            writeData(itemlist, applicationContext)
            arrayAdapter!!.notifyDataSetChanged()
        })
        listView?.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val alert = AlertDialog.Builder(this@MainActivity)
            alert.setTitle("Delete")
            alert.setMessage("Do you want to delete this item from list?")
            alert.setCancelable(false)
            alert.setNegativeButton("No") { dialog, which -> dialog.cancel() }
            alert.setPositiveButton("Yes") { dialog, which ->
                itemlist!!.removeAt(position)
                arrayAdapter!!.notifyDataSetChanged()
                writeData(itemlist, applicationContext)
            }
            val alertDialog = alert.create()
            alertDialog.show()
        }
    }
}