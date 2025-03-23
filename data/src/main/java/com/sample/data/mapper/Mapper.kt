package com.sample.data.mapper

import com.sample.data.model.ResponseData
import com.sample.data.model.Source
import com.sample.domain.model.User
import okhttp3.Response


fun ResponseData.toUserList(): List<User> {
    return sources.map { it.toUsers() }
}

fun Source.toUsers(): User {
    return User(
        category,
        country,
        description, id,
        language,
        name,
        url
    )
}