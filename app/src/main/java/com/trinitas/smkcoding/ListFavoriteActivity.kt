package com.trinitas.smkcoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.facebook.stetho.inspector.protocol.module.Database
import com.trinitas.smkcoding.database.DatabaseContract
import com.trinitas.smkcoding.database.database
import com.trinitas.smkcoding.model.ResultsItem
import kotlinx.coroutines.selects.select
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ListFavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_favorite)

        selectFavoriteListFromSqlite()

    }
    private fun selectFavoriteListFromSqlite(){
        database.use {
            val selectData = select(ResultsItem.TABLE_FAVORITE)
            val list: MutableList<DatabaseContract> =
                selectData.parseList(classParser<DatabaseContract>()) as MutableList
            val arrayListMovie =
                list as ArrayList<DatabaseContract>
            Log.d( "LISTFAVORITE", arrayListMovie.toString())
        }
        }
    }


