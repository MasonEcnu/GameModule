package com.mason.practice.file

import java.io.File
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinTask
import java.util.concurrent.RecursiveTask


/**
 * Created by mwu on 2020/6/28
 * 懒的复制文件了
 * 写个工具试试
 */
// 音乐类型后缀
val MUSIC_TYPE = hashSetOf(
    "cue",
    "wav",
    "mp3",
    "flac"
)

// 忽略的歌曲名
val IGNORE_NAME = hashSetOf(
    "remix",
    "demo"
)

fun main() {
    val basePath = "F:\\Musics\\源文件\\飞儿乐团"
    val baseDic = File(basePath)
    val targetPath = "F:\\Musics\\Test"
    // 清除目标路径下的文件
    clearTargetPath(targetPath)
    println("baseDic:${baseDic.absolutePath}")
    val fileNameSet = hashSetOf<String>()
    val filePathSet = hashSetOf<File>()
    // 读取全文件路径
    readDic(baseDic, targetPath, fileNameSet, filePathSet)
    val fileList = filePathSet.toList()
    println("源文件总数：${fileList.size}")
    val startTime = System.currentTimeMillis()
    println("开始复制文件to:$targetPath")
    // 开始复制文件
    copyFileWithForkJoin(fileList, targetPath)
    val endTime = System.currentTimeMillis()
    println("复制文件结束，耗时：${(endTime - startTime) / 1000}秒")
}

fun clearTargetPath(targetPath: String) {
    val file = File(targetPath)
    if (!file.exists()) { //判断是否待删除目录是否存在
        System.err.println("The dir are not exists!")
        return
    }

    val content = file.list() ?: return //取得当前目录下所有文件和文件夹
    for (name in content) {
        val temp = File(targetPath, name)
        if (temp.isDirectory) { //判断是否是目录
            clearTargetPath(temp.absolutePath) //递归调用，删除目录里的内容
            temp.delete() //删除空目录
        } else {
            if (!temp.delete()) { //直接删除文件
                System.err.println("Failed to delete $name")
            }
        }
    }
}

fun copyFileWithParallelStream(files: List<File>, targetPath: String, prefix: String = "") {
    files.parallelStream().forEach { file ->
        file.runCatching {
            takeIf { it.exists() }?.inputStream()?.use { inputStream ->
                val fileName = handleFileName(file, prefix)
                if (IGNORE_NAME.any { fileName.contains(it, true) }) return@forEach
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

// 改成这样，速度也没快多少啊
fun copyFileWithForkJoin(files: List<File>, targetPath: String, prefix: String = "") {
    val threshold = 5
    val forkJoinPool = ForkJoinPool((files.size / threshold) + 1)
    val task = FileCopyTask(files, targetPath, prefix, threshold)
    val result: ForkJoinTask<Int> = forkJoinPool.submit(task)
    try {
        val res = result.get()
        println("复制文件总数：$res")
    } catch (e: InterruptedException) {
        e.printStackTrace()
    } catch (e: ExecutionException) {
        e.printStackTrace()
    }
}

class FileCopyTask(
    private val files: List<File>,
    private val targetPath: String,
    private val prefix: String = "",
    private val threshold: Int = 5
) : RecursiveTask<Int>() {

    override fun compute(): Int {
        var sum = 0
        if (files.size <= threshold) {
            copyFileWithParallelStream(files, targetPath, prefix)
            sum += files.size
        } else {
            val subTasks = ArrayList<FileCopyTask>()
            (0..files.size step threshold).forEach { start ->
                val tempFiles = files.subList(start, (start + threshold).coerceAtMost(files.size))
                val subTask = FileCopyTask(tempFiles, targetPath, prefix)
                subTasks.add(subTask)
                subTask.fork()
            }
            subTasks.forEach { task ->
                sum += task.join()
            }
        }
        return sum
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
        if (MUSIC_TYPE.any { filePath.endsWith(it) }) {
            // 名字+后缀，用来唯一确定一首歌
            val fileName = file.name
            if (!fileNameSet.contains(fileName)) {
                fileNameSet.add(fileName)
                filePathSet.add(file)
            }
        }
    }
}

fun String.isNumeric(): Boolean = toCharArray().all { Character.isDigit(it) }

fun Char.isNumeric(): Boolean = Character.isDigit(this)