/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
            // this can't handle HTML properly
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
            Translate.TranslateOption.format(format.toString().lowercase()),
            Translate.TranslateOption.sourceLanguage(src.googleTranslateApiCode),
            Translate.TranslateOption.targetLanguage(dest.googleTranslateApiCode)
        ).translatedText
    }
}
