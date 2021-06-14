package com.bytelegend.client.app.engine.util

import com.bytelegend.app.shared.i18n.Locale

@Suppress("UnsafeCastFromDynamic", "UNUSED_PARAMETER", "UNUSED_VARIABLE")
fun format(epochMs: Long, locale: Locale): String {
    val localeString = locale.javascriptLocale
    return js(
        """
        new Intl.DateTimeFormat(
          localeString,
          {
            dateStyle: 'long',
            timeStyle: 'long'
          }
        ).format(new Date(epochMs))"""
    )
}
