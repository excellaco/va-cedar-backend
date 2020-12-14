# Spring Boot

## Status

> Accepted

## Context

As a starter project for consultants, we require a framework for quickly building production-ready micro-services.

## Decision

Spring Boot is built for this exact purpose and has a high adoption rate in the industry (and historically, our clients). Therefore, we are choosing to use the most recent version of Spring Boot (2.2 as of this writing) as our base for this project.

## Consequences

Spring Boot provides a strong, modular framework with numerous tools and libraries in the Spring ecosystem. We feel the configuration-centered approach of Boot will help us implement consistent architectural opinions, while allowing for templating of options left to each individual consultant team.

However, we recognize that there is a learning curve involved; Boot does a significant amount of "behind-the-scenes magic" that is non-obvious to many developers. For example, significant features can be enabled or disabled with easy-to-miss flags, and there are often default values that are buried in the Boot documentation. We are confident that we can mitigate this risk through pre-emptive documentation and explanatory comments in this codebase.
