package br.com.ifood.medeiros.forum.mappers

interface Mapper<T, U> {

    fun map(t: T): U

}
