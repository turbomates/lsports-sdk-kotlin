package com.turbomates.kotlin.lsports.sdk.model

import com.turbomates.kotlin.lsports.sdk.serializer.EnumWithValueSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(with = LanguageSerializer::class)
enum class Language(val id: Int, val value: String) {
    ENGLISH(1, "en"),
    RUSSIAN(3, "ru"),
    GEORGIAN(4, "ge"),
    JAPANESE(5, "jp"),
    GERMAN(6, "de"),
    SPANISH(9, "es"),
    FRENCH(10, "fr"),
    TURKISH(11, "tr"),
    ARMENIAN(12, "ar"),
    SIMPLIFIED_CHINESE(13, "cz"),
    TRADITIONAL_CHINESE(16, "cn"),
    KOREAN(17, "ko"),
    VIETNAMESE(18, "vi"),
    THAI(19, "th"),
    INDONESIAN(21, "in"),
    MALAY(22, "ms"),
    PORTUGUESE(29, "pt"),
    ITALIAN(30, "it"),
    CROATIAN(31, "hr"),
    SLOVAK(32, "sk"),
    SLOVENIAN(33, "sl"),
    NORWEGIAN(34, "no"),
    SWEDISH(35, "se"),
    DANISH(36, "da"),
    DUTCH(37, "nl"),
    POLISH(38, "pl"),
    CZECH(39, "cs"),
    LATVIAN(40, "lv"),
    FINNISH(41, "fi"),
    BOSNIAN(42, "bs"),
    HUNGARIAN(43, "hu"),
    BULGARIAN(44, "bg"),
    SERBIAN(45, "sr"),
    GREEK(46, "el"),
    ROMANIAN(47, "ro"),
    ESTONIAN(48, "et"),
    MACEDONIAN(49, "mk"),
    LITHUANIAN(50, "lt"),
    ARABIC(51, "aa"),
    AZERBAIJAN(52, "az"),
    HEBREW(53, "heb"),
    ALBANIAN(54, "sqi"),
    UKRAINIAN(55, "ukr"),
    SERBIAN_LATIN(56, "srl"),
    MONTENEGRIN(57, "me"),
    BRAZILIAN_PORTUGUESE(73, "pt_br")
}

private class LanguageSerializer : EnumWithValueSerializer<Language, Int>(
    "Language",
    Int.serializer(),
    { id },
    { v ->  Language.values().first { it.id == v } }
)
