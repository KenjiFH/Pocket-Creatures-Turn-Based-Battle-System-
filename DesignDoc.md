
# Pocket-Creatures-Turn-Based-Battle-System – Design Document

**Project Type:** Systems & UX Architecture Case Study
**Language / Stack:** Java, Swing
**Status:** Prototype / Iteration 1

---

## 1. Overview

This project implements a turn-based battle system with a graphical user interface. The primary focus is not graphical fidelity, but **state management, user interaction flow, and extensibility of combat logic and game mechanics**.

The system explores how to coordinate UI events, turn-based logic, animations, and player feedback in a single-threaded, event-driven environment (Swing), while keeping the system understandable and extensible.

---

## 2. Goals & Constraints

### 2.1 Goals

* Implement a deterministic, turn-based combat loop
* Make combat actions readable and understandable to users
* Support switching characters, item usage, and multiple action menus
* Avoid race conditions or invalid user input during animations
* Prioritize clarity and correctness over premature optimization

### 2.2 Non-Goals

* Multiplayer or networking
* Advanced AI behavior
* ECS
* Persistent save/load systems

> **Rationale:** Explicit non-goals helped control scope and prevented architectural overengineering during early iterations.

---

## 3. High-Level Architecture

### 3.1 Component Responsibilities

| Component                                 | Responsibility                                   |
| ----------------------------------------- | ------------------------------------------------ |
| `BattleWindow`                            | UI orchestration, input handling, UX sequencing  |
| `BattleSystem`                            | Core combat state, turn counter, teams           |
| `SpritePanel`                             | Visual representation of characters & animations |
| `MessageBoxPanel`                         | Serialized textual feedback to the player        |
| Domain Models (`Pokemon`, `Move`, `Item`) | Game state & rules                               |

> The system loosely follows an **MVC-style separation**, where `BattleWindow` acts as a controller-like layer coordinating between UI and domain logic.

---

### 3.2 Data Flow (Battle Turn)

1. Player selects action (UI event)
2. Input validated against turn state
3. UI feedback displayed (message / animation)
4. Domain state updated
5. Enemy turn triggered via timer
6. Control returned to player

---

## 4. UI State Management

### 4.1 CardLayout for Screen Transitions

**Decision:** Use `CardLayout` to represent battle screens:

* Main action menu
* Attack selection
* Switch selection
* Inventory (scrollable)

**Why:**

* Battle UI is inherently state-based
* Each screen is mutually exclusive
* CardLayout simplifies transitions without component teardown

**Tradeoffs:**

* Centralized UI logic in one class
* Requires careful naming and management of screens

**Alternative Considered:**

* Dynamic component replacement
* Modal dialogs per action

---

## 5. Turn Control & Input Safety

### 5.1 Turn Lock Mechanism

**Decision:** Introduce an explicit `inTurn` boolean guard

**Problem:**
Swing allows user input during animations or timers, leading to invalid state changes.

**Solution:**

* All user actions check `inTurn` before executing
* Input is disabled during enemy actions and animations

**Tradeoffs:**

* Simple but coarse-grained
* Logic duplication across handlers

**Future Improvement:**
Replace with an explicit battle phase enum:

```java
enum BattlePhase {
  PLAYER_INPUT,
  ANIMATION,
  ENEMY_TURN,
  RESOLUTION
}
```

---

## 6. UX Sequencing & Feedback

### 6.1 Timed Feedback Using Swing Timers

**Decision:** Use Swing `Timer` objects to sequence animations, messages, and damage resolution

**Why:**

* Ensures all UI updates occur on the Event Dispatch Thread
* Avoids threading complexity
* Preserves readability of combat events

**Tradeoffs:**

* Time-based logic harder to test
* More coordination code

**UX Benefit:**

* Clear cause-and-effect for players
* Reduced cognitive load during combat

---

## 7. Action Handling Design

### 7.1 Attack Resolution

* Move selected via UI
* Damage calculated using stats + move power
* Health bar updated
* Enemy response scheduled

**Design Choice:**
Combat resolution is serialized, not instantaneous, to preserve player comprehension.

---

### 7.2 Switching Characters

**Constraints Enforced:**

* Cannot switch during enemy turn
* Cannot switch to fainted character
* Cannot switch to currently active character

**UX Decision:**
Invalid actions silently return player to main menu to reduce friction.

---

### 7.3 Item Usage

**Design Choice:**
Inventory modeled as a map of item → quantity, rendered dynamically.

**Benefits:**

* UI reflects game state directly
* Supports arbitrary inventory size via scrolling

**Tradeoff:**

* Tight coupling between UI and inventory structure

---

## 8. Error Handling & Edge Cases

Handled explicitly:

* Prevent actions while out of turn
* Prevent switching to fainted characters
* End battle when all characters are fainted
* Visual indicators for unavailable actions (red buttons)

---

## 9. Known Limitations

### 9.1 Large Controller Class

`BattleWindow` currently handles:

* UI layout
* Input handling
* Combat sequencing
* UX timing

**Reason:**

* Early-stage iteration favored visibility and speed over abstraction
* Avoided premature separation before requirements stabilized

---

## 10. Future Improvements

### 10.1 Architectural

* Extract `BattleController` from UI layer
* Introduce event-driven combat resolution
* Decouple UI updates via observer pattern
* Replace timers with a centralized action queue

### 10.2 UX

* Explicit disabled buttons instead of silent failures
* Visual turn indicators
* Speed-up / skip animations option

### 10.3 Testing

* Unit tests for damage calculation
* Simulation tests for turn sequencing
* Mock UI event testing

---

## 11. Key Takeaways

* The project emphasizes **intentional tradeoffs**, not feature breadth
* Design choices were driven by clarity, safety, and scope control
* The system is structured to evolve as complexity increases

> This project demonstrates practical experience with UI state management, event-driven programming, and UX-oriented system design in a constrained environment

