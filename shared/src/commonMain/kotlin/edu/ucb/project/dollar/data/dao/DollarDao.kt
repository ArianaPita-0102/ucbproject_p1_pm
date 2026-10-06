package edu.ucb.project.dollar.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import edu.ucb.project.dollar.data.entity.DollarEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DollarDao {
    @Insert
    suspend fun insert(dollar: DollarEntity)

    @Query("SELECT * FROM dollars ORDER BY timestamp DESC")
    fun observeAll(): Flow<List<DollarEntity>>
}
