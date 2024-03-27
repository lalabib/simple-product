package com.lalabib.latihan.simpleproduct.utils

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lalabib.latihan.simpleproduct.R
import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import com.lalabib.latihan.simpleproduct.data.local.room.ProductDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class StartingProduct (private val context: Context): RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            fillWithStartingProduct(context)
        }
    }

    //Filling database with the data from JSON
    private fun fillWithStartingProduct(context: Context) {
        //obtaining instance of data access object
        val database = Room.databaseBuilder(context, ProductDatabase::class.java, "Product.db")
            .build()
        val dao = database.productDao()
        val jsonArray = loadJsonArrayProduct(context)
        // using try catch to load the necessary data
        try {
            if (jsonArray != null) {
                for (i in 0 until jsonArray.length()) {
                    //variable to obtain the JSON object
                    val item = jsonArray.getJSONObject(i)
                    //Using the JSON object to assign data, loaded to entity and insert to db
                    val product = ProductEntity(
                            item.getString("id"),
                            item.getString("name"),
                            item.getString("description"),
                            item.getInt("price"),
                            item.getString("image"),
                            item.getString("author"),
                            item.getString("publicationYear"),
                            item.getString("publisher"),
                        )
                    dao.insertAllProduct(product)
                }
            }
        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
        database.close()
    }

    //read json array and load it to tb_product
    private fun loadJsonArrayProduct(context: Context): JSONArray? {
        val builder = StringBuilder()
        val data = context.resources.openRawResource(R.raw.tb_product)
        //using Buffered reader to read the input stream byte
        val reader = BufferedReader(InputStreamReader(data))
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                builder.append(line)
            }
            val json = JSONObject(builder.toString())
            return json.getJSONArray("tb_product")
        } catch (exception: IOException) {
            exception.printStackTrace()
        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
        return null
    }
}