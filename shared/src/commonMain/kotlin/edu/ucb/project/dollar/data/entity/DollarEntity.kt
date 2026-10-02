package edu.ucb.project.dollar.data.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "dollars")
data class DollarEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "dollar_official") val dollarOfficial: Double,
    @ColumnInfo(name = "dollar_parallel") val dollarParallel: Double,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
)
