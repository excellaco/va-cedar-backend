# Jetty

## Status

> Accepted

## Context

As a starter project for consultants, we require a framework that supports MVC and WebFlux so that we can use the MVC annotations for Swagger. 

## Decision
Jetty was selected because it supports both MVC and WebFlux, whereas, Netty does not. Since it supports both, the MVC annotations can be used so that Swagger can automatically generate the documententation. Once Swagger supports WebFlux, this decision should be revisited.

## Consequences
Both frameworks are lightweight and performant. The primary issue is WebFlux was purpose built with Netty first. The integrations are tighter across the Spring Framework stack. For example, Spring Cloud Contract does not support Jetty, but general availability support is coming soon based on it being in a milestone release.

Once Swagger OpenAPI supports WebFlux, MVC and Jetty should be removed.
