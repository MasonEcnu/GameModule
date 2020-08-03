import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.the

internal val Project.sourceSets get() = the<SourceSetContainer>()

/**
 * Provides the existing [main][org.gradle.api.tasks.SourceSet] element.
 */
val SourceSetContainer.patch: NamedDomainObjectProvider<SourceSet> get() = named<SourceSet>("patch")

/**
 * Provides the existing [main][org.gradle.api.tasks.SourceSet] element.
 */
val SourceSetContainer.main: NamedDomainObjectProvider<SourceSet> get() = named<SourceSet>("main")

/**
 * Provides the existing [jar][org.gradle.api.tasks.bundling.Jar] task.
 */
val TaskContainer.jar: TaskProvider<Jar> get() = named<Jar>("jar")

/**
 * Provides the existing [runtime][org.gradle.api.artifacts.Configuration] element.
 */
val NamedDomainObjectContainer<Configuration>.runtime: NamedDomainObjectProvider<Configuration>
  get() = named<Configuration>("runtime")
