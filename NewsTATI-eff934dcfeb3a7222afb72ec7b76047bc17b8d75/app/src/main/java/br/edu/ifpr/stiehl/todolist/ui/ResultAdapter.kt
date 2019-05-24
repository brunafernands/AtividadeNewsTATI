package br.edu.ifpr.stiehl.todolist.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifpr.stiehl.todolist.R
import br.edu.ifpr.stiehl.todolist.entidades.Article
import br.edu.ifpr.stiehl.todolist.entidades.NewsResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_article.*
import kotlinx.android.synthetic.main.item_article.view.*
import java.text.SimpleDateFormat

class ResultAdapter(private var articles: List<Article>) :
    RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {


    override fun getItemViewType(position: Int) = R.layout.item_article

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ResultViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) =
        holder.preencherView(articles[position])

    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun preencherView(article:Article) {
            itemView.txtTitulo.setText(article.content)
            itemView.txtDescricao.setText(article.title)
            itemView.txtAuthor.setText(article.author)

            if(article.author.isNullOrBlank()){
                itemView.txtAuthor.isInvisible
            }
            else{
                itemView.txtAuthor.visibility
            }

            val inFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val outFormatter = SimpleDateFormat("dd MMM yyyy, HH'h'mm")


            val date = outFormatter.format(inFormatter.parse(article.publishedAt))
            itemView.txtInforme.text = article.source.name + ", " + date


            itemView.VerMais.setOnClickListener {
                val uri = Uri.parse(article.url)
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(uri)
                itemView.context.startActivity(intent)
            }
            Picasso.get().load(article.urlToImage).placeholder(R.drawable.load).error(R.drawable.erro).into(itemView.imageArticle)

        }

    }

}