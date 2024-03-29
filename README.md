tiltakspenger-testmeldinger
================
App som kan sende ut testmeldinger på Rapiden. Deployes bare til dev
på https://tiltakspenger-testmeldinger.intern.dev.nav.no/

Sjekk [TestmeldingerApi](src/main/kotlin/no/nav/tiltakspenger/testmeldinger/routes/TestmeldingerApi.kt) for å se hvilke
endepunkt som er tilgjengelige

En del av
satsningen ["Flere i arbeid – P4"](https://memu.no/artikler/stor-satsing-skal-fornye-navs-utdaterte-it-losninger-og-digitale-verktoy/)

# Komme i gang

## Forutsetninger

- [JDK](https://jdk.java.net/)
- [Kotlin](https://kotlinlang.org/)
- [Gradle](https://gradle.org/) brukes som byggeverktøy og er inkludert i oppsettet

For hvilke versjoner som brukes, [se byggefilen](build.gradle.kts)

## Bygging og denslags

For å bygge artifaktene:

```sh
./gradlew build
```

---

# Henvendelser

Spørsmål knyttet til koden eller prosjektet kan stilles som issues her på GitHub.

## For NAV-ansatte

Interne henvendelser kan sendes via Slack i kanalen #tpts-tech.
