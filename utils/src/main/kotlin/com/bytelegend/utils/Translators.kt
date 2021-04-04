package com.bytelegend.utils

import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.LocalizedTextFormat
import com.github.houbb.opencc4j.util.ZhConverterUtil
import com.google.cloud.translate.Translate
import com.google.cloud.translate.TranslateOptions

interface Translator {
    fun translate(format: LocalizedTextFormat, text: String, src: Locale, dest: Locale): String
}

object UnauthorizedTranslator : Translator {
    override fun translate(format: LocalizedTextFormat, text: String, src: Locale, dest: Locale): String =
        if (src == Locale.ZH_HANS && dest == Locale.ZH_HANT) {
            ZhConverterUtil.toTraditional(text)
        } else {
            text
        }
}

val DEFAULT_TRANSLATOR = if (System.getenv("GOOGLE_APPLICATION_CREDENTIALS") == null) {
    UnauthorizedTranslator
} else {
    GoogleCloudApiTranslator()
}

class GoogleCloudApiTranslator : Translator {
    private val translate: Translate by lazy { TranslateOptions.getDefaultInstance().service }

    override fun translate(format: LocalizedTextFormat, text: String, src: Locale, dest: Locale): String {
        println("Translating '$text' from $src to $dest")
        return translate.translate(
            text,
            Translate.TranslateOption.format(format.toString().toLowerCase()),
            Translate.TranslateOption.sourceLanguage(src.googleTranslateApiCode),
            Translate.TranslateOption.targetLanguage(dest.googleTranslateApiCode)
        ).translatedText
    }
}
