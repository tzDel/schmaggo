import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	alias(libs.plugins.springBoot)
	alias(libs.plugins.springDependencyManagement)
	alias(libs.plugins.kotlinJvm)
	alias(libs.plugins.kotlinSpring)
	alias(libs.plugins.kotlinJpa)
}

group = "com.tzDel"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	runtimeOnly("org.postgresql:postgresql:42.3.3")
	implementation(libs.bundles.springBoot)
	implementation(libs.bundles.kotlin)
	testImplementation(libs.bundles.test)
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
