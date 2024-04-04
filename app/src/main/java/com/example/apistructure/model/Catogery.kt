// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.apistructure.model

data class Catogery (
    val data: Data,
    val message: String,
    val status: Boolean
)

data class Data (
    val count: Long,
    val results: List<Resultn>
)

data class Resultn (
    val createdAt: String,
    val brandName: String,
    val color: String,
    val brandID: Long,
    val v: Long,
    val id: String,
    val brandLogo: String,
    val categoryID: List<CategoryID>,
    val updatedAt: String
)

data class CategoryID (
    val createdAt: String,
    val categoryLogo: String,
    val v: Long,
    val categoryName: String,
    val id: String,
    val updatedAt: String
)
