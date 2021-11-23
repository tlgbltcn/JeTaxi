package com.tlgbltcn.jetaxi.domain.mapper

interface Mapper<I, O> {
    fun map(input: I?): O
}

interface ListMapper<I, O> : Mapper<List<I>?, List<O>>
