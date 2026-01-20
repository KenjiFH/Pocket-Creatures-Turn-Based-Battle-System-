# Pocket-Creatures-A-Turn-Based-Battle-System-
a Turn-Based Battle game made entirely in java, totally not inspired by a certain other IP. Created as a Systems &amp; UX Architecture Case Study & to practice writing documentation on an old project

sprites and assets were all from https://github.com/clear-code-projects/5games/tree/main/Monster%20battle/images  (if only i could have just used pygame.....)

---

**Type:** Architecture & UX-Focused SWE Project


**Stack:** Java, Swing


**Focus:** UI state management, turn sequencing, user experience, design tradeoffs

---

## Overview

This project is a ~~Pokémon~~-like turn-based battle game built to explore **state-driven UI design**, **event sequencing**, and **safe input handling** in an event-driven environment.

The goal was to design a rewarding combat loop that is **predictable, readable, and extensible**, while avoiding architectural complexity.

---

## Key Engineering Concepts

### UI State Management

* Battle screens (Main / Fight / Switch / Bag) are modeled explicitly using `CardLayout`
* Each screen represents a discrete battle state with deterministic transitions

### Turn & Input Control

* User input is gated with boolean is_my_turn variables to prevent actions during animations, event states or enemy turns
* Prevents invalid state mutations common in UI-driven systems

### UX Sequencing

* Combat feedback (messages, animations, damage) is controlled using Swing Timers
* Ensures clear cause-and-effect for players while remaining thread-safe

### Dynamic UI Binding

* Move lists, inventory counts, and available actions update directly from game state
* Inventory and switch menus scale based on data

---

## Architecture (High-Level)

* **BattleWindow** — UI orchestration and UX flow control
* **BattleSystem** — Core combat state and rules
* **SpritePanel / MessageBoxPanel** — Visual and textual feedback
* **Domain Models** — Pokémon, moves, items

Follows a lightweight MVC-style separation with the UI acting as a controller layer.

---

## Design Tradeoffs

**Chosen intentionally:**

* Centralized UI controller for faster iteration
* Timer-based sequencing for simplicity and thread safety

**Deferred for future iterations:**

* Event-driven combat pipeline
* Explicit battle phase state machine
* Deeper UI/domain decoupling

---

## What This Demonstrates

* State-based UI design in Java
* Event-driven programming and turn sequencing
* UX-first system feedback
* Conscious scope control and architectural decision-making

---

## Possible Improvements

* Extract a dedicated `BattleController` for decoupling of `BattleSystem`
* Replace turn lock with explicit battle phase states

---


