# MCME-Base

Shared base library for [MCME](https://github.com/MCME) Minecraft plugins. It provides a
common, platform-agnostic API so plugin code can be written once and run on both the
**Paper** backend servers and the **Velocity** proxy.

## What it provides

- **`com.mcmiddleearth.base.core`** — platform-agnostic abstractions: `McmeProxyPlayer`,
  `McmeServerInfo`, `Message` / `McmeColors`, a Brigadier-based command framework, task
  scheduling, permissions, configuration, and logging interfaces.
- **`com.mcmiddleearth.base.bukkit`** — the Paper/Bukkit implementation.
- **`com.mcmiddleearth.base.velocity`** — the Velocity implementation.

A consuming plugin depends on `core` for its logic and uses the `bukkit` or `velocity`
implementation for the platform it runs on.

## Platform

|            |                                             |
|------------|---------------------------------------------|
| Minecraft  | 26.2 (compiled against Paper `26.1.2.build.72-stable`) |
| Proxy      | Velocity `3.4.0`                            |
| Java       | 25                                          |
| Build      | Maven                                        |

> Bungee/Waterfall support was **removed in 2.0.0** — MCME runs Paper + Velocity only.

## Building

Requires JDK 25.

```sh
JAVA_HOME=/path/to/jdk-25 mvn install
```

Dependencies resolve from PaperMC and Sonatype (declared in `pom.xml`); no extra
repositories are needed.

## Using it as a dependency

```xml
<dependency>
    <groupId>com.mcmiddleearth</groupId>
    <artifactId>mcme-base</artifactId>
    <version>2.0.0</version>
    <scope>provided</scope>
</dependency>
```

The mcme-base plugin jar must be present on the server/proxy at runtime.

> **Do not pin Adventure** in your own pom — it is supplied by `paper-api` / `velocity-api`
> at the platform version, and pinning it can downgrade below what the platform needs.

## Consumers

- [MCME-Connect](https://github.com/MCME/MCME-Connect) — the network connectivity plugin (primary consumer).

## Changelog

### 2.0.0
- Migrated to Minecraft 26.2 (Paper 26.1.2 / Velocity 3.4 / Java 25).
- **Removed** the Bungee layer and the `waterfall-api` dependency (Velocity-only network).
- Adventure is no longer shaded — it is provided by the platform.
