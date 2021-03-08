package com.bytelegend.app.shared

//data class LocalizedText(
//    val key: String,
//    val locale: Locale,
//    val text: String
//) : ToKotlinCode {
//    override fun toKotlinCode() = """
//        LocalizedText("$key", Locale.of("$locale"), "$text")
//    """.trimIndent()
//}
//
//object I18nTextContainer {
//    val texts = mapOf<String, Map<Locale, LocalizedText>>()
//
//    fun getTextTemplate(key: String, locale: Locale): LocalizedText {
//        val textsOfAllLocales = texts.getValue(key)
//        return textsOfAllLocales.getOrElse(locale) { textsOfAllLocales.getValue(DEFAULT_LOCALE) }
//    }
//}
//
