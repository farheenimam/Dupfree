# Dupfree

![CLI Screenshot](path/to/cli-screenshot.png)

Dupfree is a powerful, CLI-based plagiarism detection tool designed to detect textual similarity and assess AI-generated text likelihood. With the ability to process text inputs, TXT files, and PDF documents, Dupfree generates detailed analysis reports, including output on the console and in TXT format.

This tool is developed through the collaboration of Farheen Imam and Arisha Mumtaz.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [How It Works](#how-it-works)
- [Report Details](#report-details)
- [Testing](#testing)
- [Contributing](#contributing)
- [Output Examples](#output-examples)
- [Contact](#contact)
- [License](#license)

## Introduction

Dupfree helps you detect plagiarism across multiple files or manually input text and evaluates whether the text might be AI-generated. It utilizes a custom hash set data structure for efficient processing and includes a comprehensive text analysis module.

## Features

- **Plagiarism Detection**:
  - Compare input text against TXT and PDF documents.
  - Generate a similarity score and severity level (Low, Moderate, High).
- **AI Text Analysis**:
  - Evaluate sentence complexity, readability, and AI likelihood.
- **Comprehensive Reporting**:
  - Outputs results in the console.
  - Saves detailed TXT reports.
- **CLI-Based Operation**:
  - User-friendly, command-line-based interaction.
  - Displays step-by-step processing in a terminal environment.
- **PDF Support**:
  - Handles and processes PDF files through a custom `PDDocument` class.

## Architecture

Dupfree employs a modular architecture based on layered design principles:

1. **Core Functionalities**:
   - Text cleaning and preprocessing to standardize inputs.
   - CustomHashSet for efficient string operations, ensuring fast union and intersection operations for shingles.
   - Text analysis for determining AI likelihood using key linguistic metrics.
2. **Modular Design**:
   - **Dupfree Class**: Provides a user-friendly CLI, manages the workflow, and coordinates between modules.
   - **TextAnalysis Class**: Implements AI likelihood detection by analyzing sentence structure, readability, and vocabulary richness.
   - **CustomHashSet Class**: Implements a custom hash set for optimized operations in detecting similarity using shingles.
   - **PDDocument Class**: Simulates PDF handling with essential methods for loading and closing PDF files.

## Prerequisites

- **Software**:
  - Java Development Kit (JDK) 8 or later
  - Apache Maven for dependency management and testing
  - A terminal or command-line interface
- **Additional Resources**:
  - `stopwords.txt` file for text cleaning (ensures commonly used words are excluded from analysis).
  - PDFBox library functionality simulated for PDF handling.

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/yourusername/dupfree.git
   cd dupfree
   ```

2. **Set Up the Environment**:

   - Ensure `stopwords.txt` is present in the working directory.
   - Compile the project:
     ```bash
     javac Dupfree.java CustomHashSet.java textanalysis.java pdf/PDDocument.java
     ```

3. **Run the Application**:

   ```bash
   java Dupfree
   ```

## Usage

1. **Launch the Tool**:
   Run the program using `java Dupfree`. You will see a menu:

   ```
   1. Input text manually
   2. Input TXT document to check for plagiarism
   3. Input PDF document to check for plagiarism
   4. Exit
   ```

2. **Select an Option**:

   - Input text manually to compare it against multiple files.
   - Upload a TXT file for plagiarism analysis against other files.
   - Upload a PDF file to detect plagiarism and assess AI likelihood.

3. **View Results**:

   - Results are shown in the console with detailed insights.
   - A TXT report is saved in the current directory for future reference.

## How It Works

1. **Preprocessing**:

   - Converts text to lowercase and removes special characters for uniformity.
   - Filters out stopwords using a customizable stopwords list.

2. **Plagiarism Detection**:

   - Splits input text into shingles (n-grams of text).
   - Uses Jaccard similarity to compare the input against provided files.

3. **AI Text Analysis**:

   - Measures the Type-Token Ratio (TTR) to assess vocabulary diversity.
   - Analyzes average sentence length to determine complexity.
   - Calculates Flesch Reading Ease for readability scoring.
   - Combines metrics into an AI likelihood score, offering insights on whether the text may be AI-generated.

4. **Reporting**:

   - Outputs similarity scores and severity levels for plagiarism.
   - Presents detailed text analysis metrics (sentence complexity, readability, and AI likelihood).
   - Generates a comprehensive TXT report summarizing all findings.

## Report Details

The report includes:

- **Plagiarism Results**:
  - Similarity score and severity level (Low, Moderate, High).
  - Number of files compared and those flagged as plagiarized.
  - Average similarity percentage across plagiarized files.

- **Text Analysis**:
  - Sentence Length: Average length with a readability interpretation (e.g., "Short sentences - Easy to read").
  - Reading Ease: Flesch Reading Ease score with comments on complexity.
  - AI Likelihood: Probability of AI-generated content with detailed explanations.

## Testing

Run unit tests to ensure the tool's correctness and reliability:

```bash
mvn test
```

Tests cover:
- Text normalization and preprocessing.
- Accurate Jaccard similarity computation.
- Correct identification of plagiarism severity levels.
- AI text analysis and scoring accuracy.

Ensure all functionalities, including text analysis and plagiarism detection, pass the tests.

## Contributing

1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature-branch
   ```
3. Commit changes and push:
   ```bash
   git commit -m 'Add feature'
   git push origin feature-branch
   ```
4. Submit a pull request for review.

## Output Examples

Include screenshots of:
1. The CLI displaying menu options and processing results.
2. Example TXT reports generated by the tool, showing plagiarism results and text analysis.

## Contact

For inquiries or support, please contact Farheen Imam at [farheenimam.offically@gmail.com].

## License

This project is licensed under the MIT License. See the LICENSE file for details.

