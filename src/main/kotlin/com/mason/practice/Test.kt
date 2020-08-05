package com.mason.practice

import java.io.File

/**
 * Created by mwu on 2020/7/1
 */
fun main() {
//    showQQ()
    showWX()
}

fun showWX() {
    println("微信")
    val sidStr = "1110004\n" +
            "1110005\n" +
            "110112\n" +
            "110115\n" +
            "10138\n" +
            "1110046\n" +
            "110002\n" +
            "110060\n" +
            "1110014\n" +
            "110121\n" +
            "10153\n" +
            "10136\n" +
            "10160\n" +
            "110120\n" +
            "10157\n" +
            "10142\n" +
            "1110063\n" +
            "110116\n" +
            "10161\n" +
            "1110007\n" +
            "110118\n" +
            "110139\n" +
            "10132\n" +
            "10162\n" +
            "10126\n" +
            "10141\n" +
            "110099\n" +
            "1110059\n" +
            "1110029\n" +
            "10135\n" +
            "110087\n" +
            "1110010\n" +
            "10150\n" +
            "10147\n" +
            "10146\n" +
            "10152\n" +
            "1110009\n" +
            "1110025\n" +
            "10158\n" +
            "110127\n" +
            "110001\n" +
            "10133\n" +
            "1110038\n" +
            "10143\n" +
            "1110022\n" +
            "1110045\n" +
            "10119\n" +
            "1110039\n" +
            "10151\n" +
            "10140\n" +
            "1110011\n" +
            "110111\n" +
            "110130\n" +
            "10145\n" +
            "10149\n" +
            "1110049\n" +
            "10155\n" +
            "10154\n" +
            "1110064\n" +
            "10131\n" +
            "1110056\n" +
            "10148\n" +
            "110008\n" +
            "1110026\n" +
            "110055\n" +
            "10137\n" +
            "110106\n" +
            "1110068\n" +
            "1110016\n" +
            "10156\n" +
            "10159\n" +
            "1110032\n" +
            "10164\n" +
            "10163\n" +
            "1110003"

    showDiffSid(sidStr)
}

fun showQQ() {
    println("手Q")

    val sidStr = "2220010\n" +
            "2220064\n" +
            "2220015\n" +
            "220079\n" +
            "2220026\n" +
            "2220011\n" +
            "220073\n" +
            "2220008\n" +
            "2220001\n" +
            "20095\n" +
            "2220004\n" +
            "2220066\n" +
            "20090\n" +
            "20086\n" +
            "20097\n" +
            "2220007\n" +
            "220080\n" +
            "20091\n" +
            "20096\n" +
            "2220002\n" +
            "20093\n" +
            "20088\n" +
            "2220032\n" +
            "2220025\n" +
            "20085\n" +
            "2220020\n" +
            "2220012\n" +
            "20092\n" +
            "20089\n" +
            "29995\n" +
            "2220017\n" +
            "20094\n" +
            "2220003\n" +
            "20087"
    showDiffSid(sidStr)
}

fun showDiffSid(sidStr: String) {
    println("Hello World!")
    val sidSet = hashSetOf<String>()
    sidStr.split("\n").forEach {
        sidSet.add(it.trim())
    }

    val logFilePath = "D:\\bk_log_search_100361_manager_log.txt"
    val logFile = File(logFilePath)

    val fileStringBuilder = StringBuilder()
    logFile.forEachLine {
        fileStringBuilder.append(it.replace("\\", "")).append("\n")
    }
    val fileContent = fileStringBuilder.toString()
    sidSet.removeIf {
        val reg = "\"Partition\" : ${it.toInt()}"
        fileContent.contains(reg)
    }
    println(sidSet)
}
