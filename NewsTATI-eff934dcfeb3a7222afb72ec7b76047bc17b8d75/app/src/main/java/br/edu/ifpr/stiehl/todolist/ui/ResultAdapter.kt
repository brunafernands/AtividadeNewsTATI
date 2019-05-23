package br.edu.ifpr.stiehl.todolist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifpr.stiehl.todolist.R
import br.edu.ifpr.stiehl.todolist.entidades.Article
import br.edu.ifpr.stiehl.todolist.entidades.NewsResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_article.view.*

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
            Picasso.get().load(article.urlToImage).into(itemView.imageArticle)
        }
    }

}