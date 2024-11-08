Using Flow
e.g.
fun searchPodcasts(query: String): Flow<List<Podcast>> = flow {
    emit(emptyList()) // UI clear the result
    delay(300) // 防止 在一定時間内，函数只执行一次。 otherwise 搜索结果会一直闪烁更新 / 閃爍更新 
    val results = api.searchPodcasts(query)
    emit(results)
}

fun searchPodcasts(query: String): Flow<Status<Podcast>> = flow {
    emit(Resource.Loading)
    delay(300)
    try {
        val results = api.searchPodcasts(query)
        emit(Resource.Success(results))
    } catch (e: Exception) {
        emit(Resource.Error(ThrowableWithMessage(throwable = e)))
    }
}

