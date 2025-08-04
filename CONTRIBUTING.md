# 🛠️ Contributing to Unitility

Thank you for your interest in contributing to **Unitility**!  
This guide outlines how you can help improve the library — whether by fixing bugs, adding features, improving docs, or proposing new ideas.

---

## Getting Started

1. **Fork the repository**
2. Create a branch from `main`, e.g. `feature/new-quantity` or `fix/unit-parsing`
3. Make your changes, use story id/number in a commit message id provided,
4. Write or update unit tests
5. Run the test suite (`mvn clean verify`)
6. Submit a pull request with a clear description of your changes

---

## Contribution Guidelines

- Keep changes **small and focused**
- Use clear, descriptive commit messages
- Follow existing **naming conventions** and **code structure**
- All public classes and methods should include **Javadoc**
- Cover new logic with **unit tests**

---

## Common Contributions

- New physical quantities and their units (at least one for SI/metric and one for an Imperial system),
- Bug fixes (e.g. conversion logic, unit parsing edge cases)
- Integration modules (Quarkus, Spring Boot, Jackson, etc.)
- Additional unit tests for coverage
- Documentation improvements

---

## Reporting Bugs

For a good bug report, include:
- Steps to reproduce
- Expected vs actual behavior
- Sample code (if applicable)
- Unitility version, Java version, OS

> Open issues under [GitHub Issues](https://github.com/pjazdzyk/unitility/issues)

---

## Questions or Proposals?

Use [GitHub Discussions](https://github.com/pjazdzyk/unitility/discussions) for:
- General questions
- Feature suggestions
- Design discussions

---

Thanks again for contributing to Unitility!
