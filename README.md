# Fabric Example Mod

Draylar's fork of the fabric-example-mod. Includes a modified package structure as well as several registry classes and a client initializer.

## Setup

1. Edit build.gradle and mod.json to suit your needs.
    * The "mixins" object can be removed from mod.json if you do not need to use mixins.
    * Please replace all occurences of "modid" with your own mod ID - sometimes, a different string may also suffice.
2. Run the following command:

```
./gradlew idea
```
