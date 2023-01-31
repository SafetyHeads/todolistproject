package com.company.todolistproject

import android.content.Context
import java.io.FileNotFoundException
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object FileHelper {
    const val FILENAME = "listinfoa.dat"

    @JvmStatic
    fun writeData(item: List<String>, context: Context) {
        try {
            val fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)
            val oas = ObjectOutputStream(fos)
            oas.writeObject(item)
            oas.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun readData(context: Context): MutableList<String> {
        var itemlist: MutableList<String> = mutableListOf<String>()
        try {
            val fis = context.openFileInput(FILENAME)
            val ois = ObjectInputStream(fis)
            itemlist = ois.readObject() as ArrayList<String>
        } catch (e: FileNotFoundException) {
            itemlist = ArrayList()
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return itemlist
    }
}