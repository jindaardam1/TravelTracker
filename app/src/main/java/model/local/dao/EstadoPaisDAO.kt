package model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import model.local.entity.EstadoPais

/**
 * Data Access Object (DAO) para la entidad EstadoPais en la base de datos local.
 *
 * Permite realizar operaciones CRUD (Create, Read, Update, Delete) en la tabla de estado_paises.
 */
@Dao
interface EstadoPaisDAO {

    /**
     * Inserta un nuevo EstadoPais en la base de datos.
     *
     * @param estadoPais EstadoPais a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(estadoPais: EstadoPais)

    /**
     * Obtiene todos los registros de EstadoPais almacenados en la base de datos.
     *
     * @return Lista de EstadoPais.
     */
    @Query("SELECT * FROM estado_paises")
    fun getAll(): List<EstadoPais>

    /**
     * Obtiene la lista de países que han sido visitados (ha_estado = 1).
     *
     * @return Lista de EstadoPais que han sido visitados.
     */
    @Query("SELECT * FROM estado_paises WHERE ha_estado = 1")
    fun getPaisesVisitados(): List<EstadoPais>

    /**
     * Actualiza el estado de "visitando" de un país específico.
     *
     * @param nombrePais Nombre del país a actualizar.
     * @param visitando Nuevo estado de "visitando".
     */
    @Query("UPDATE estado_paises SET visitando = :visitando WHERE nombre_pais = :nombrePais")
    suspend fun updateVisitando(nombrePais: String, visitando: Boolean)

    /**
     * Actualiza el estado de "ha_estado" de un país específico.
     *
     * @param nombrePais Nombre del país a actualizar.
     * @param haEstado Nuevo estado de "ha_estado".
     */
    @Query("UPDATE estado_paises SET ha_estado = :haEstado WHERE nombre_pais = :nombrePais")
    suspend fun updateHaEstado(nombrePais: String, haEstado: Boolean)
}