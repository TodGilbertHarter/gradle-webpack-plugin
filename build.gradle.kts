plugins {
    `java-gradle-plugin`
    groovy
    `maven-publish`
    id("com.gradle.plugin-publish") version "0.11.0"
    id("com.timgroup.jarmangit") version "1.1.86"
}

val repoUrl: String? by project
val repoUsername: String? by project
val repoPassword: String? by project

val buildNumber: String? by extra { "0" } // System.getenv("ORIGINAL_BUILD_NUMBER") ?: System.getenv("BUILD_NUMBER") }
val githubUrl by extra("https://github.com/TodGilbertHarter/gradle-webpack-plugin")

group = "com.timgroup"
if (buildNumber != null) version = "1.1.$buildNumber"
description = "Build Javascript sources with Webpack and test with Mocha / Jest"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.github.node-gradle:gradle-node-plugin:3.1.1")

    testImplementation("junit:junit:4.13")
    testImplementation("org.spockframework:spock-core:2.0-groovy-3.0") {
        exclude(module = "groovy-all")
    }
}

tasks {
    "test"(Test::class) {
        maxParallelForks = 4
    }
}

gradlePlugin {
    plugins {
        create("webpack") {
            id = "com.giantelectronicbrain.webpack"
            implementationClass = "com.timgroup.gradle.webpack.WebpackPlugin"
            description = project.description
            displayName = "Webpack / Mocha plugin"
        }
    }
}

pluginBundle {
    website = githubUrl
    vcsUrl = githubUrl
    tags = setOf("webpack", "mocha", "jest", "nodejs")
}

publishing {
    repositories {
    	maven("../localrepo") {
    		name = "localRepo"
    	}
/*        if (project.hasProperty("repoUrl")) {
            maven("$repoUrl/repositories/yd-release-candidates") {
                name = "timgroup"
                credentials {
                    username = repoUsername.toString()
                    password = repoPassword.toString()
                }
            }
        } */
    }
}
