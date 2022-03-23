rootProject.name = "Schmaggo"
enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("kotlin", "1.6.10")
            version("springBoot","2.5.5")
            version("springDependencyManagement", "1.0.11.RELEASE")
            version("jackson", "2.13.1")
            version("mockito", "4.0.0")

            plugin("springBoot", "org.springframework.boot").versionRef("springBoot")
            plugin("kotlinSpring", "org.jetbrains.kotlin.plugin.spring").versionRef("kotlin")
            plugin("kotlinJpa", "org.jetbrains.kotlin.plugin.jpa").versionRef("kotlin")
            plugin("kotlinJvm", "org.jetbrains.kotlin.jvm").versionRef("kotlin")
            plugin("springDependencyManagement","io.spring.dependency-management").versionRef("springDependencyManagement")

            library("jackson-kotlin", "com.fasterxml.jackson.module", "jackson-module-kotlin").versionRef("jackson")
            library("kotlin-reflect", "org.jetbrains.kotlin", "kotlin-reflect").versionRef("kotlin")
            library("kotlin-stdlib", "org.jetbrains.kotlin", "kotlin-stdlib").versionRef("kotlin")
            bundle("kotlin", listOf("kotlin-reflect", "kotlin-stdlib", "jackson-kotlin"))

            library("springboot-starter", "org.springframework.boot", "spring-boot-starter").versionRef("springBoot")
            library("springboot-jpa","org.springframework.boot", "spring-boot-starter-data-jpa").versionRef("springBoot")
            library("springboot-web","org.springframework.boot", "spring-boot-starter-web").versionRef("springBoot")
            bundle("springBoot", listOf("springboot-starter", "springboot-jpa", "springboot-web"))

            library("mockito-kotlin", "org.mockito.kotlin", "mockito-kotlin").versionRef("mockito")
            library("springboot-starterTest","org.springframework.boot", "spring-boot-starter-test").versionRef("springBoot")
            bundle("test", listOf("mockito-kotlin", "springboot-starterTest"))
        }
    }
}

