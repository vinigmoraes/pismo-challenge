package integration.utils

fun readJson(fileName: String) = ClassLoader.getSystemResource("json/$fileName.json").readText()
