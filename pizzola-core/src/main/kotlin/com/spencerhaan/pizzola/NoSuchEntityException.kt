package com.spencerhaan.pizzola

class NoSuchEntityException(id: Any, type: Class<*>) : Throwable("entity of type ${type.simpleName}, could not be accessed using id $id") {

    constructor(type: Class<*>, vararg ids: Any) : this(ids.toList(), type)
}
