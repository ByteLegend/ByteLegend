package com.bytelegend.app.servershared.dal

interface Mvcc {
    /**
     * VersionedRecordExtension recognizes `null` as initial value.
     */
    val version: Int?
}
