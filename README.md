# Build Scan® quickstart

This is an example project that you can use to experience the [Build Scan® service of Develocity][gradle.com].

It is a small Java project that has the [Develocity Gradle Plugin][manual] already applied.

## Create a Build Scan®

Follow these simple steps to create and publish a Build Scan® on [scans.gradle.com][scans.gradle.com]:

1. Clone this project
1. Run `./gradlew build --scan`
1. Agree to the [Terms of Service][terms-of-service] on the command line

The build should end with something similar to:

    Publishing build scan...
    https://gradle.com/s/ria2s2x5oaazq

Follow the green link shown at the end of the build to view your Build Scan® on [scans.gradle.com][scans.gradle.com].

Note: If you run a build without the `--scan` flag, no Build Scan® will be created and
no information will be sent.

## Experiment with Build Scans

Create different kinds of Build Scans by locally modifying this quickstart project. Here are some ideas:

- Edit `src/main/java/example/Example.java` to introduce compile errors
- Edit `src/test/java/example/ExampleTest.java` to introduce test failures
- Add more dependencies, more plugins, and more projects

Alternatively, enable one of your own builds to produce Build Scans by following the [step-by-step instructions][scans.gradle.com].

## Learn more

Read the [Develocity Gradle Plugin User Manual][manual] to learn more about the Build Scan® service of Develocity and the Develocity Gradle Plugin.

## Need help?

Talk to us on the [Gradle forum][gradle-forum].

If you are completely new to the Gradle Build Tool, start [here][gradle-download].

## License

The Build Scan™ quickstart project is open-source software released under the [Apache 2.0 License][apache-license].

[apache-license]: https://www.apache.org/licenses/LICENSE-2.0.html
[gradle-download]: https://gradle.org/install/
[manual]: https://docs.gradle.com/develocity/gradle-plugin/current/
[gradle.com]: https://www.gradle.com
[terms-of-service]: https://gradle.com/terms-of-service
[scans.gradle.com]: https://scans.gradle.com/
[gradle-forum]: https://discuss.gradle.org/c/help-discuss/scans