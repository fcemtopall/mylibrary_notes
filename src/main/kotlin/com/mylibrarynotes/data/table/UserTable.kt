package com.mylibrarynotes.data.table

import org.jetbrains.exposed.sql.Table

object UserTable : Table() {
    val email = varchar("email", 255)
    val name = varchar("name", 255)
    val password = varchar("password", 255)
    val hashedPassword = varchar("hashedPassword", 255)

    override val primaryKey: PrimaryKey = PrimaryKey(email)
}