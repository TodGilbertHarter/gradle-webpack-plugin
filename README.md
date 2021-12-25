Webpack plugin
==============

This is a fork I made of the 'gradle-webpack-plugin' because I just needed something simple that I could use with the gradle-node-plugin v3.1.1 (the currently active fork). The upstream version only works with the older pre-fork v2.x of gradle-node-plugin. I've updated this one so it should work with newer tools, including compatibility with Gradle 7.x, and newer versions of Groovy/Spock. It SHOULD also work pretty well on Java 17 :). 

Publishing
----------

CI publishes to Nexus internally on each build as normal.

To publish to Gradle plugin portal, from a machine with internet access, do something like:

```bash
BUILD_NUMBER=11 ./gradlew --no-daemon publishPlugins
```

You must have already set up a plugin portal API key as described on the site: https://plugins.gradle.org/docs/submit
