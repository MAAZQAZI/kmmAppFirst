enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        mavenLocal()
        google()
        gradlePluginPortal()
        mavenCentral()


    }
}

val props = java.util.Properties().apply {
    file("local.properties").inputStream().use {
        load(it)
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        google()
        mavenCentral()

        maven(url = "https://repo.repsy.io/mvn/vyro/internal") {
            credentials {
                username = props.getProperty("repsyUsername")
                password = props.getProperty("repsyPassword")
            }
        }

    }
}

rootProject.name = "kmmAppFirst"
include(":HelloWorld")
include(":shared")