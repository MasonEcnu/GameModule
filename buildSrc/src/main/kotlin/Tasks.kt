import org.gradle.api.Project
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.attributes
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.register
import java.io.File
import java.io.FileFilter

/**
 * Created by mwu on 2020/8/3
 */

fun Project.registerGenerateTargetJarTask() {
    tasks.register<Jar>("generateTargetJar") {
        description = "构建指定jar包"
        group = "GENERATE_TARGET_JAR"
        val fullPackageName: String? by project
        val patchClass: String? by project

        var packagePath = ""
        var jarFileName = ""
        fullPackageName?.also {
            packagePath = it.replace(".", "/")
        }
        patchClass?.also {
            jarFileName = "com_mason_$patchClass.jar"
        }

        doFirst {
            requireNotNull(fullPackageName) { "Missing property patchPackage" }
            requireNotNull(patchClass) { "Missing property patchClass" }

            require(packagePath.isNotEmpty()) { "Missing property packagePath" }
            require(jarFileName.isNotEmpty()) { "Missing property jarFileName" }

            sourceSets.main.get().output.classesDirs.find { baseDir ->
                !File("${baseDir.absolutePath}/$packagePath")
                    .listFiles(FileFilter { it.name == "$patchClass.class" })
                    .isNullOrEmpty()
            } ?: error("参数错误，找不到: $packagePath/$patchClass.class")
        }

        from(sourceSets.main.get().output)
        include("$packagePath/*")
        archiveFileName.set(jarFileName)

        manifest {
            attributes("Main-Class" to "$fullPackageName.$patchClass")
        }

        doLast {
            val patchJarDistDir: String? by project
            val libsDir: String by project
            if (patchJarDistDir != null) {
                copy {
                    into(patchJarDistDir as String)
                    from(libsDir) {
                        include(jarFileName)
                    }
                }
                println("$jarFileName is published to $patchJarDistDir")
            }
        }
    }
}