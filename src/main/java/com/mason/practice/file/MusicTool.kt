package com.mason.practice.file

import java.io.File

/**
 * Created by mwu on 2020/6/28
 * 懒的复制文件了
 * 写个工具试试
 */

fun main() {
    val basePath = "F:\\迅雷下载\\蔡依林"
    val baseDic = File(basePath)
    val targetPath = "F:\\music\\Jolin"
    println("baseDic:${baseDic.absolutePath}")
    val fileNameSet = hashSetOf<String>()
    val filePathSet = hashSetOf<File>()
    readDic(baseDic, targetPath, fileNameSet, filePathSet)
    println("开始复制文件to:$targetPath")
    copyFile(filePathSet, targetPath, "蔡依林 - ")
    println("复制文件结束")
}

fun copyFile(files: HashSet<File>, targetPath: String, prefix: String = "") {
    files.forEach { file ->
        file.runCatching {
            takeIf { it.exists() }?.inputStream()?.use { inputStream ->
                val fileName = handleFileName(file, prefix)
                if (fileName.contains("REMIX", true)) return@forEach
                val targetUrl = "${targetPath}${File.separator}$fileName"
                File(targetUrl).runCatching {
                    takeIf { !it.exists() }?.outputStream()?.use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
            }
        }.onFailure {
            println(it)
        }
    }
}

fun handleFileName(file: File, prefix: String): String {
    return if (prefix.isNotEmpty()) {
        if (file.name[0].isNumeric()) {
            val tempFileName = file.name.substring(2).trim()
            prefix + if (tempFileName.startsWith(".")) {
                tempFileName.substring(1).trim()
            } else {
                tempFileName
            }
        } else {
            file.name
        }
    } else {
        file.name
    }
}

fun readDic(
    file: File,
    targetPath: String,
    fileNameSet: HashSet<String>,
    filePathSet: HashSet<File>
) {
    if (file.isDirectory) {
        file.list()?.forEach { dicList ->
            val tempPath = "${file.path}${File.separator}$dicList"
            readDic(File(tempPath), targetPath, fileNameSet, filePathSet)
        }
    } else {
        val filePath = file.path
        if (filePath.endsWith("flac") || filePath.endsWith("mp3")) {
            val fileName = file.nameWithoutExtension
            if (!fileNameSet.contains(fileName)) {
                fileNameSet.add(fileName)
                filePathSet.add(file)
            }
        }
    }
}

fun String.isNumeric(): Boolean = toCharArray().all { Character.isDigit(it) }

fun Char.isNumeric(): Boolean = Character.isDigit(this)