package com.example.quizz.entidades

class Pergunta (
    var category: String,
    var type: String,
    var difficulty: String,
    var question: String,
    var correct_answer: String,
    var incorrect_answers: ArrayList<String>
)