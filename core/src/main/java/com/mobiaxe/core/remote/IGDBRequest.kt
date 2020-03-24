package com.mobiaxe.core.remote

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class IGDBRequest(
        val fields: String?,
        val sort: String?,
        val limit: Int?,
        val offset: Int?,
        val where: List<Long>?,
        val search: String?
) {

    companion object {
        private const val FIELDS = "f"
        private const val SORT = "s"
        private const val SEARCH = "search"
        private const val LIMIT = "l"
        private const val OFFSET = "o"
        private const val WHERE = "w"
        private const val COMMA = ";"
        private const val ASCENDING = "asc"
        private const val DESCENDING = "desc"
        private const val MEDIA_TYPE = "text/plain"
    }

    data class Builder(
            var fields: String? = null,
            var search: String? = null,
            var sort: String? = null,
            var limit: Int? = null,
            var offset: Int? = null,
            var where: List<Long>? = null,
            var isAscending: Boolean = false,
            var by: String? = null
    ) {

        fun search(query: String) = apply { this.search = query }

        fun fields(fields: String) = apply { this.fields = fields }

        fun sort(sort: String, isAscending: Boolean = false) = apply {
            this.sort = sort
            this.isAscending = isAscending
        }

        fun where(by: String) = apply { this.by = by }

        fun limit(limit: Int) = apply { this.limit = limit }

        fun offset(offset: Int) = apply { this.offset = offset }

        fun build(): RequestBody {

            val request = StringBuilder()

            if (search != null) {
                request.append("$SEARCH \"$search\"$COMMA")
            }

            if (fields != null) {
                request.append("$FIELDS $fields$COMMA")
            }

            if (sort != null) {
                if (isAscending) {
                    request.append("$SORT $sort $ASCENDING$COMMA")
                } else {
                    request.append("$SORT $sort $DESCENDING$COMMA")
                }
            }

            if (by != null) {
                request.append("$WHERE $by$COMMA")
            }

            if (limit != null) {
                request.append("$LIMIT $limit$COMMA")
            }

            if (offset != null) {
                request.append("$OFFSET $offset$COMMA")
            }

            return request.toString().toRequestBody(MEDIA_TYPE.toMediaTypeOrNull())
        }
    }
}