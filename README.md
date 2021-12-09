# AniAPI Java SDK
## Introduction
A Java SDK for [AniAPI](https://github.com/AniAPI-Team/AniAPI) REST APIs

## Prerequisites
* Java 11
* Maven 3

## Installation
### Config in pom.xml
```
<dependency>
    <groupId>io.github.censodev</groupId>
    <artifactId>aniapi-java-sdk</artifactId>
    <version>2.0.0</version>
</dependency>
```
### Install with Maven CLI
```
mvn install
```

## Usages
```java
var client = AniApiClient.builder().build();

var filter = AnimeFilter
        .builder()
        .title("Cowboy Bebop")
        .anilistId(1L)
        .malId(1L)
        .formats(new AnimeFormatEnum[]{AnimeFormatEnum.TV, AnimeFormatEnum.TV_SHORT})
        .status(AnimeStatusEnum.FINISHED)
        .year(1998)
        .season(AnimeSeasonEnum.SPRING)
        .genres(new String[]{"Action", "Guns", "Military"})
        .sort("titles.en", SortDirectionEnum.DESCENDING)
        .build();
var res = client.getAnimeList(filter).get();
```
