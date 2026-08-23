# CS 320 – Software Testing, Automation, and Quality Assurance

This repository contains portfolio artifacts from CS 320. It showcases my work in
software testing, automation, and quality assurance, including unit tests written
to uncover errors, analysis of different testing approaches based on requirements,
and the application of appropriate testing strategies.

## Artifacts

**Project One – Contact Service**
- `Contact.java`
- `ContactService.java`
- `ContactTest.java`
- `ContactServiceTest.java`

**Project Two – Summary and Reflections Report**
- `7-2 Project Two.docx`

## Reflection

### How can I ensure that my code, program, or software is functional and secure?

I ensure my code is functional and secure by validating every input before it is
allowed into the system and by backing that validation with automated unit tests.
In the contact service, each field is checked against its business rule—for example,
the contact ID cannot be null or exceed ten characters, and the phone number must be
exactly ten digits—and the constructor and setters throw an `IllegalArgumentException`
when a rule is violated rather than letting the program continue in an invalid state.
On the testing side, I used negative testing to deliberately push bad data (null values,
oversized strings, malformed phone numbers) at the code to confirm it rejects them, and
I used code coverage as a metric to verify that every conditional branch was actually
exercised. Reaching full line and branch coverage on the core classes gave me confidence
that no untested path could silently allow invalid or unsafe data through.

### How do I interpret user needs and incorporate them into a program?

I interpret user needs by treating each customer requirement as a concrete, testable
rule and mapping it directly to code. The customer specified constraints such as unique,
non-updatable IDs, maximum field lengths, exact-length phone numbers, and appointment
dates that cannot fall in the past. For each of these, I implemented a matching validation
in the object or service class and then wrote at least one unit test to prove the program
behaves as the customer intended. This one-to-one mapping between requirement and test kept
the implementation aligned with what the user actually asked for, and it made it easy to
demonstrate that every requirement was met rather than assumed.

### How do I approach designing software?

I approach software design by separating responsibilities and making invalid states hard
to reach. I split each feature into a data object (such as `Contact`) and a service class
(such as `ContactService`) so that the object is responsible for enforcing its own field
rules while the service manages storage and lookup through an in-memory `HashMap` keyed by
ID. I made the ID field `final` so it cannot be changed after creation, and I placed
validation inside the setters so the rules cannot be bypassed when a field is updated. I
also kept the code maintainable by following the DRY principle—using the `@BeforeEach`
annotation to set up a fresh service before each test and writing small helper methods for
repeated logic like generating future dates—so the design stays clean, testable, and easy
to extend.
