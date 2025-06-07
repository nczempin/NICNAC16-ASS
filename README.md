# NICNAC16-ASS: Assembler for the NICNAC16 CPU

## What Problem Does This Solve?
NICNAC16-ASS is an assembler specifically designed for the [NICNAC16 CPU architecture](https://github.com/nczempin/NICNAC16). It enables developers to write assembly code for the NICNAC16 processor and convert it into machine code that can be executed by the CPU or its simulator. This tool bridges the gap between human-readable assembly language and NICNAC16 machine code.

## Who Is This For?
- Developers working with the NICNAC16 CPU architecture
- Computer architecture enthusiasts interested in assembly programming
- Students learning about assembly language and CPU design
- Anyone exploring the NICNAC16 ecosystem of tools

## Current Implementation Status
- ✅ Basic assembly syntax parsing
- ✅ Symbol table management for labels
- ✅ Core instruction set support
- ✅ Basic error reporting
- 🚧 Advanced assembler directives
- 🚧 Macro support
- 📋 Optimization features
- 📋 Integration with NICNAC16 simulator

## Setup Instructions

### Prerequisites
- A C++20 compatible compiler (e.g., `g++`)
- `make` build tool
- Basic understanding of assembly language concepts

### Installation
1. Clone the repository:
   ```
   git clone https://github.com/nczempin/NICNAC16-ASS.git
   cd NICNAC16-ASS
   ```

2. Verify the environment and build the assembler:
   ```
   ./setup.sh
   make
   ```

### Running the Assembler
1. Create an assembly file (e.g., `program.asm`) with NICNAC16 assembly code

2. Run the assembler:
   ```
   ./nicnac16-asm program.asm
   ```

3. The assembler will generate machine code output that can be loaded into the NICNAC16 CPU or simulator

## Project Scope

### What This IS
- An assembler for the NICNAC16 assembly language
- A tool for generating executable code for the NICNAC16 CPU
- Part of the NICNAC16 development ecosystem
- An educational project demonstrating assembler construction principles

### What This IS NOT
- Not a full-featured IDE for assembly development
- Not a simulator for the NICNAC16 CPU (see [NICNAC16-SIM](https://github.com/nczempin/NICNAC16-SIM) for that)
- Not intended for production-level software development
- Not compatible with other CPU architectures

## Repository Structure
- `src/` - C++20 source code
  - `main.cpp` - Entry point and assembler implementation
- `tests/` - Simple regression tests

## Assembly Language Features
- Standard instruction set for NICNAC16 architecture
- Label definitions for program flow control
- Basic assembler directives
- Support for numeric literals and symbols

## Related Projects
- [NICNAC16](https://github.com/nczempin/NICNAC16) - The CPU architecture this assembler targets
- [NICNAC16-CC](https://github.com/nczempin/NICNAC16-CC) - C compiler for NICNAC16
- [NICNAC16-SIM](https://github.com/nczempin/NICNAC16-SIM) - Simulator for NICNAC16
- [NICNAC16-DISASS](https://github.com/nczempin/NICNAC16-DISASS) - Disassembler for NICNAC16

## Development Status
This is an experimental educational project in active development. Contributions and feedback are welcome.
