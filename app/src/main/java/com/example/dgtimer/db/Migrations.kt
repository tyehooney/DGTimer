package com.example.dgtimer.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

internal val MIGRATION_2_to_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.run {
            execSQL(
                """
                    CREATE TABLE capsules_new (
                        id INTEGER NOT NULL, 
                        name TEXT NOT NULL DEFAULT '', 
                        typeId INTEGER NOT NULL DEFAULT 0, 
                        stage TEXT NOT NULL DEFAULT '', 
                        color TEXT, 
                        image TEXT, 
                        major INTEGER NOT NULL,
                        PRIMARY KEY(id)
                    )
                """.trimIndent()
            )
            execSQL(
                """
                    INSERT INTO capsules_new (id, name, stage, color, image, major) 
                    SELECT id, name, stage, color, image, major FROM capsules
                """.trimIndent()
            )
            execSQL("DROP TABLE capsules")
            execSQL("ALTER TABLE capsules_new RENAME TO capsules")
        }
    }
}