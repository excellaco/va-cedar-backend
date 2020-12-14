LeanKit Board: https://excellaconsulting.leankit.com/board/1068388832

Team: Tahnee Hasan, Alex Hoffman, Parke Hultman, Han Yoon

Consulted Accounts: Div4, RFAD

Background and Opportunity:
  We have an opportunity (and a need) to build products that make our consultants more productive. Making our consultants more   productive accomplishes these primary objectives:
   ​ Increasing delivery efficiency and impact
   ​ Mitigating delivery risk
   ​ Strengthening our competitiveness
   ​ Improving employee engagement
   ​ Increasing our ability to scale
   
   
Our Principles for this TCP-Java iteration:
  ​ We must be customer-focused. We must build tools that our customers need and want. In this case, our customers are our         account teams.

  ​ Adoption, and more specifically speed to adoption, is the most critical measure in the short-term. Objective-based metrics     will be added over time.

  ​ This effort, both in short and long-term, must be viewed as an investment, not a cost.

  ​ We need to think big, start small, and learn fast.
  
------
Meeting Notes:

Initially breaking into 3 Phases over 2 weeks
  - Analysis/User Feedback & Prioritization
  - Development
  - Adoption/Roll-out

Analysis/User Feedback
  - How to make the tool better
  - QOL improvements
  - How to spur adoption
  - What features are missing that were added
  - Did tcp features hinder or help?
Prioritization of features
  - 3 days (ideally 1-2 days)
Development
  - Han and Parke
Documentation
  - ADRs
  - Readmes
  - PR template
Adoption/Roll-out
  - 2 days
  - How do we socialize the tools
  - Do we have training sessions on the tools
Metrics
  - Not in 2 week timeframe
  - Adoption is only metric that matters
  - Current, potential, and BD opps
  
Meeting with RFAD (Ken Russel), 05/04/2020:

Features RFAD uses:
  - Authentication and Authorization, OAUTH2, JWT flow
  - Webflux and Reactor
  - Java 11
  - JPA
  - docker-compose (postgres, ws, ability to run tests)
  - testing
    - testng
    - example unit and integration
  - swagger configured with spring security
  - spring actuator
  - bean validation (simple use cases, no custom)
  - lombok
  - goJF

Features RFAD does not use:
  - DevOps code
  - no tcp-glue code

Improvements:
  - service to service communication (ken - 3)
    - configuration is not built out
    - making it easier to integrate with other services/apps
  - more integrations such as kafka, mongo, elasticsearch (ken - 1)
  - Remove DevOps / tcp-glue  
  - More test variety (Ken - 2):
    - faker for test (i.e. wiremock, mockito, etc)
    - CDC test example
    - Component tests 
  - static code analysis tool (find bugs)
  
Nice to have:
  - integrate with cloud providers (aws, azure, etc)
  - cli to spin up microservice based on user input

Ease of use (1-10 easy to hard):
  - 4,5 to bring down the repo and get started

  
  
