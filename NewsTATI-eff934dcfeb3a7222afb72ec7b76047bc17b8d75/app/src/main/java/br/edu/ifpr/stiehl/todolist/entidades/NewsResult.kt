package br.edu.ifpr.stiehl.todolist.entidades

data class NewsResult (
    var status: String,
    var totalResults: Long,
    var articles: List<Article>
)