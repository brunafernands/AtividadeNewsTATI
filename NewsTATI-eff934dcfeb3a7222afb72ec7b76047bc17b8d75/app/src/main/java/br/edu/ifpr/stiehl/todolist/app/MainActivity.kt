package br.edu.ifpr.stiehl.todolist.app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifpr.stiehl.todolist.R
import br.edu.ifpr.stiehl.todolist.entidades.Article
import br.edu.ifpr.stiehl.todolist.entidades.NewsResult
import br.edu.ifpr.stiehl.todolist.servicos.ResultsService
import br.edu.ifpr.stiehl.todolist.ui.ResultAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.SearchView
import kotlinx.android.synthetic.main.item_article.*
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var service: ResultsService
    lateinit var adapter: ResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(br.edu.ifpr.stiehl.todolist.R.layout.activity_main)

        configuraRetrofit()
        carregaDados("")

        Busca.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                carregaDados(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                carregaDados(query)
                return true
            }

        })
    }

    fun configuraRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ResultsService::class.java)
    }

    fun carregaDados(q:String) {
        service.news(this.getResources().getConfiguration().locale.getCountry(), "5bf5c78e95d84b0387036b52fcaa02f2", q).enqueue(object : Callback<NewsResult> {
            override fun onFailure(call: Call<NewsResult>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<NewsResult>,
                response: Response<NewsResult>
            ) {
                val results = response.body()
                if (results != null)
                    configuraRecyclerView(results?.articles)
            }
        })
    }

    fun configuraRecyclerView(results: List<Article>) {
        adapter = ResultAdapter(results)
        listTarefas.adapter = adapter

        listTarefas.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
    }
}

