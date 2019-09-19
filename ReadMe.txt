========================================================================================================================
Loan Service
========================================================================================================================

The project is implemented using java spring boot.  The following is the package structure:
|loan-service
 - build scripts
++++ |src
++++ ++++ |main
++++ ++++ ++++ |java package(com.oaf.loanservice)
++++ ++++ ++++  - controller: consists of APIs and web views
++++ ++++ ++++  - dao: data access helpers
++++ ++++ ++++  - domain: domain objects
++++ ++++ ++++  - service: services
++++ ++++ |resources
++++ ++++  - db migrations
++++ ++++  - Configuration files
++++ |test
++++  - Test classes and resources