package com.robelseyoum3.perseusprayer.data.model

//0 would be azan time
//1 would be sunrise time
data class AzanTime(
    var name: String,
    var time: String,
    var type: Int
)

/*
@Entity(tableName = "azan_times")
data class AzanTime(
    @TypeConverters(ListTypeConverter::class)
    @ColumnInfo(name = "name")var name: String,
    @ColumnInfo(name = "time")var time: String,
    @ColumnInfo(name = "type")var type: String
)
 */
