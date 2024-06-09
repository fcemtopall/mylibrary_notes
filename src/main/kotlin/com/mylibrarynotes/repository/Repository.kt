package com.mylibrarynotes.repository

import com.mylibrarynotes.data.model.User
import com.mylibrarynotes.data.table.UserTable
import com.mylibrarynotes.repository.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert

class Repository {

    suspend fun addUser(user: User) {
        dbQuery {
            UserTable.insert { userTable ->
                userTable[UserTable.email] = user.email
                userTable[UserTable.hashedPassword] = user.hashedPassword
                userTable[UserTable.name] = user.username
            }
        }
    }

    suspend fun findUserByEmail(email: String) = dbQuery {
        UserTable.select(UserTable.email.eq(email))
            .map { rowToUser(it) }
            .singleOrNull()
    }

    private fun rowToUser(row: ResultRow): User {
        return User(
            email = row[UserTable.email],
            hashedPassword = row[UserTable.hashedPassword],
            username = row[UserTable.name]
        )
    }
}