package br.edu.ifpr.stiehl.todolist.entidades
import java.util.*

data class Article(
    var source: Source,
    var author: String?,
    var title: String,
    var description: String?,
    var url: String,
    var urlToImage: String?,
    var publishedAt: String,
    var content: String?
)