package com.example.vasilkhusainovtest.common

object ApiConfig {
    const val GITHUB_BASE_URL = "https://api.github.com/"

    const val SEARCH_USERS_ENDPOINT = "search/users"
    const val SEARCH_REPOSITORY_ENDPOINT = "search/repositories"

    //Задержка, чтобы не спамить сервер запросами на каждый символ
    const val DEBOUNCE_DELAY = 300
}