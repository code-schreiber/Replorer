package com.toolslab.replorer.converter

interface Converter<S, M> {
    fun convert(source: S): M
}
